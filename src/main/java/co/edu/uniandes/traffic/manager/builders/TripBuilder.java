package co.edu.uniandes.traffic.manager.builders;

import co.edu.uniandes.traffic.manager.domain.Trip;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class TripBuilder {

    //2010-01-26 07:41:00
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Trip buildTrip(String [] values){
        Trip trip = new Trip();
        try {
            trip.setVendor(values[0]);
            trip.setPickUpTime( !"".equals(values[1]) ? castToDateTime(values[1]) : null);
            trip.setDropOffTime( !"".equals(values[2]) ? castToDateTime(values[2]) : null );
            trip.setPassengerCount(casToInt(values[3]));
            trip.setTripDistance(casToDouble(values[4]));
            trip.setPickUpLonPickUp( casToDouble(values[5]) );
            trip.setPickUpLatPickUp( casToDouble(values[6]) );
            trip.setRateCode(casToInt(values[7]));
            trip.setStoreFlag(casToInt(values[8]));
            trip.setPickUpLonDropOff(casToDouble(values[9]));
            trip.setPickUpLatDropOff(casToDouble(values[10]));
            trip.setPaymentType(values[11]);
            trip.setFareAmount( casToDouble(values[12]));
            trip.setSureCharge(casToDouble(values[13]));
            trip.setMtaTax( casToDouble(values[14]) );
            trip.setTipAmount( casToDouble(values[15]) );
            trip.setTollsAmount( casToDouble(values[16]) );
            trip.setTotalAmount( casToDouble(values[17]) );
        }catch (ClassCastException e ){
            System.out.println("Cannot cast this value: " + e.getMessage());
        } catch (Exception e ){
            System.out.println("Error: " + e.getMessage());
        }
        return trip;
    }

    private static LocalDateTime castToDateTime(String date){
        return LocalDateTime.parse(date, formatter);
    }

    private static Double casToDouble(String data){
        return !"".equals(data) ? Double.parseDouble(data) : null;
    }

    private static Integer casToInt(String data){
        return !"".equals(data) ? Integer.parseInt(data) : null;
    }

}
