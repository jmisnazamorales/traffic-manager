package co.edu.uniandes.traffic.manager.services.impl;

import co.edu.uniandes.traffic.manager.handlers.KMLHandler;
import co.edu.uniandes.traffic.manager.services.MapLocationCreateService;
import co.edu.uniandes.traffic.manager.services.ReaderPolygonService;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ReaderPolygonServiceImpl implements ReaderPolygonService {
    @Value("${text.input.reader}")
    private String fileName;

    private final MapLocationCreateService service;

    public ReaderPolygonServiceImpl(MapLocationCreateService service){
        this.service = service;
    }

    @PostConstruct
    private void init(){
        getPolygonsFromFile();
    }

    @Override
    public List<Polygon> getPolygonsFromFile() {
        List<Geometry> geometries = null;
        XMLReader xr = new org.apache.xerces.parsers.SAXParser();
        KMLHandler kmlHandler = new KMLHandler();
        xr.setContentHandler(kmlHandler);
        xr.setErrorHandler(kmlHandler);
        try {
            Reader r = new BufferedReader(new FileReader(fileName));
            LineNumberReader myReader = new LineNumberReader(r);
            xr.parse(new InputSource(myReader));
            geometries = kmlHandler.getGeometries();
        }
        catch (IOException e ){

        }
        catch (SAXException e) {

        }
        return service.createPolygons(geometries);
    }
}
