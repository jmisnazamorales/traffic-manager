package co.edu.uniandes.traffic.manager.services;

import org.locationtech.jts.geom.Polygon;

import java.util.List;

public interface MapLocationFindService {

    String findZoneInPolygon(Double lon, Double lat, List<Polygon> polygons);
}
