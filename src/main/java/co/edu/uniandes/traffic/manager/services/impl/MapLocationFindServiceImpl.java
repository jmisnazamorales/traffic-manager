package co.edu.uniandes.traffic.manager.services.impl;

import co.edu.uniandes.traffic.manager.services.MapLocationFindService;
import org.locationtech.jts.geom.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapLocationFindServiceImpl implements MapLocationFindService {


    @Override
    public String findZoneInPolygon(Double lon, Double lat, List<Polygon> polygons) {
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate coordinate = new Coordinate(lon, lat);
        Point point = geometryFactory.createPoint(coordinate);
        List<Geometry> selected = polygons.stream().filter (x -> x.contains(point) ).collect(Collectors.toList());
        return selected.toString();
    }
}
