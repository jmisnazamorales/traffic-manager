package co.edu.uniandes.traffic.manager.handlers;


import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.gml2.GMLConstants;
import org.locationtech.jts.io.gml2.GMLHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class KMLHandler extends DefaultHandler {

    private List geoms = new ArrayList();;

    private GMLHandler currGeomHandler;
    private String lastEltName = null;
    private GeometryFactory fact = new FixingGeometryFactory();

    public KMLHandler()
    {
        super();
    }

    public List getGeometries()
    {
        return geoms;
    }

    /**
     *  SAX handler. Handle state and state transitions based on an element
     *  starting.
     *
     *@param  uri               Description of the Parameter
     *@param  name              Description of the Parameter
     *@param  qName             Description of the Parameter
     *@param  atts              Description of the Parameter
     *@exception SAXException  Description of the Exception
     */
    public void startElement(String uri, String name, String qName, Attributes atts) throws SAXException {
        if (name.equalsIgnoreCase(GMLConstants.GML_POLYGON)) {
            currGeomHandler = new GMLHandler(fact, null);
        }
        if (currGeomHandler != null)
            currGeomHandler.startElement(uri, name, qName, atts);
        if (currGeomHandler == null) {
            lastEltName = name;
            //System.out.println(name);
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException
    {
        if (currGeomHandler != null) {
            currGeomHandler.characters(ch, start, length);
        }
        else {
            String content = new String(ch, start, length).trim();
            if (content.length() > 0) {
                System.out.println(lastEltName + "= " + content);
            }
        }
    }

    public void ignorableWhitespace(char[] ch, int start, int length)
            throws SAXException {
        if (currGeomHandler != null)
            currGeomHandler.ignorableWhitespace(ch, start, length);
    }

    /**
     *  SAX handler - handle state information and transitions based on ending
     *  elements.
     *
     *@param  uri               Description of the Parameter
     *@param  name              Description of the Parameter
     *@param  qName             Description of the Parameter
     *@exception  SAXException  Description of the Exception
     */
    public void endElement(String uri, String name, String qName)
            throws SAXException {
        // System.out.println("/" + name);

        if (currGeomHandler != null) {
            currGeomHandler.endElement(uri, name, qName);

            if (currGeomHandler.isGeometryComplete()) {
                Geometry g = currGeomHandler.getGeometry();
                System.out.println(g);
                geoms.add(g);

                // reset to indicate no longer parsing geometry
                currGeomHandler = null;
            }
        }

    }
}
