package co.edu.uniandes.traffic.manager.services;

import org.locationtech.jts.geom.Polygon;

import java.util.List;

public interface ReaderPolygonService {

    List<Polygon> getPolygonsFromFile();
}
