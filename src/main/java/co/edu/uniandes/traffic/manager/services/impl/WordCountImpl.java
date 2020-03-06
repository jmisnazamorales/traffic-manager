package co.edu.uniandes.traffic.manager.services.impl;


import co.edu.uniandes.traffic.manager.builders.TripBuilder;
import co.edu.uniandes.traffic.manager.domain.Count;
import co.edu.uniandes.traffic.manager.domain.Trip;
import co.edu.uniandes.traffic.manager.domain.Word;
import co.edu.uniandes.traffic.manager.services.MapLocationFindService;
import co.edu.uniandes.traffic.manager.services.ReaderPolygonService;
import co.edu.uniandes.traffic.manager.services.WordCount;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.RelationalGroupedDataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.apache.spark.sql.functions.col;
/**
 * Created by achat1 on 9/23/15.
 * Just an example to see if it works.
 */
@Service
public class WordCountImpl implements WordCount {

    private final SparkSession sparkSession;

    private final JavaSparkContext sparkContext;

    private final ReaderPolygonService polygonService;

    private final MapLocationFindService locationFindService;

    public WordCountImpl(SparkSession sparkSession, JavaSparkContext sparkContext
                       , ReaderPolygonService polygonService
                       ,MapLocationFindService locationFindService) {
        this.sparkSession = sparkSession;
        this.sparkContext = sparkContext;
        this.polygonService = polygonService;
        this.locationFindService = locationFindService;
    }

    @Override
    public List<Count> count() {
       /*
        String input = "hello world hello hello hello";
        String[] _words = input.split(" ");

        */
        String path = "data/yellow_tripdata.csv";
        JavaRDD<String> lines = sparkContext.textFile(path);
        //List<Trip> trips = new ArrayList<>();
        List<String[]> values = lines.map(x -> x.split("\\t")).collect();
        List<Trip> trips = values.stream().map (TripBuilder::buildTrip).collect(Collectors.toList());
        List<String> collection = trips.parallelStream()
                .filter( x -> x.getPickUpLonDropOff() != null && x.getPickUpLatDropOff() != null)
                .map(trip -> locationFindService.findZoneInPolygon(trip.getPickUpLonDropOff()
                                                            , trip.getPickUpLatDropOff()
                                                            , polygonService.getPolygonsFromFile())).collect(Collectors.toList());

        //Dataset<Row> dataFrameTrips = sparkSession.createDataFrame(trips, Trip.class);
        //dataFrameTrips.show();
        String input = "hello world hello hello hello";
        String[] _words = input.split(" ");
        List<Word> words = Arrays.stream(_words).map(Word::new).collect(Collectors.toList());
        Dataset<Row> dataFrame = sparkSession.createDataFrame(words, Word.class);
        dataFrame.show();
        //StructType structType = dataFrame.schema();
        RelationalGroupedDataset groupedDataset = dataFrame.groupBy(col("word"));
        groupedDataset.count().show();
        List<Row> rows = groupedDataset.count().collectAsList();//JavaConversions.asScalaBuffer(words)).count();
        return rows.stream().map(new Function<Row, Count>() {
            @Override
            public Count apply(Row row) {
                return new Count(row.getString(0), row.getLong(1));
            }
        }).collect(Collectors.toList());
    }
}