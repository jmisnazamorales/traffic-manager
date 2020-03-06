package co.edu.uniandes.traffic.manager.handlers;

import org.locationtech.jts.geom.*;

public class FixingGeometryFactory extends GeometryFactory {

    public LinearRing createLinearRing(CoordinateSequence cs)
    {
        if (cs.getCoordinate(0).equals(cs.getCoordinate(cs.size() - 1)))
            return super.createLinearRing(cs);
        // add a new coordinate to close the ring
        CoordinateSequenceFactory csFact = getCoordinateSequenceFactory();
        CoordinateSequence csNew = csFact.create(cs.size() + 1, cs.getDimension());
        CoordinateSequences.copy(cs, 0, csNew, 0, cs.size());
        CoordinateSequences.copyCoord(csNew, 0, csNew, csNew.size() - 1);
        return super.createLinearRing(csNew);
    }
}
