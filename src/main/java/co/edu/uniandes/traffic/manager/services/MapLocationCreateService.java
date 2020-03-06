package co.edu.uniandes.traffic.manager.services;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;

import java.util.List;

public interface MapLocationCreateService {

    List<Polygon> createPolygons(List<Geometry> polygons);
}
