package co.edu.uniandes.traffic.manager.services.impl;


import co.edu.uniandes.traffic.manager.services.MapLocationCreateService;
import org.locationtech.jts.geom.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapLocationCreateServiceImpl implements MapLocationCreateService {

    @Override
    public List<Polygon> createPolygons(List<Geometry> polygons) {
        GeometryFactory geometryFactory = new GeometryFactory();
        List<Polygon> polygonList= new ArrayList<>();
        polygons.forEach( polygon -> {
            try {
                Coordinate[] points = polygon.getCoordinates();
                LinearRing ring = geometryFactory.createLinearRing(points);
                polygonList.add(geometryFactory.createPolygon(ring, null));
            }catch (Exception e ){
            }
        });
        return polygonList;
    }

}
