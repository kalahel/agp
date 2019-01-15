package com.ucp.business.data.Model;

public class DataTest {

    public static void main(String [ ] args){

        Coordinates coordinates_1 = new Coordinates(123.122, 134.402);
        Transport transport_1 = new Boat();
        transport_1.setPrice(200);
        Beach beach_1 = new Beach("Plage 1");
        beach_1.setName("Plage 1 Rename");

        Hotel hotel_1 = new Hotel(1000, coordinates_1, beach_1);
        TouristicSite touristicSite_1 = new ActivityPlace();
        touristicSite_1.setCoordinates(new Coordinates(123,154));
        touristicSite_1.setDescription("touristic desc");

        Coordinates coordinates_2 = new Coordinates();
        coordinates_2.setX_axis(21);
        coordinates_2.setY_axis(112);
        TouristicSite touristicSite_2 = new ActivityPlace("another touristic desc", coordinates_1);

        //Returns the euclidean distance between coordinates_1 and coordinates_2
        double distance_1_to_2 = coordinates_2.getDistance(coordinates_1);

        System.out.println(transport_1.getPrice());
        System.out.println(beach_1.getName());
        System.out.println(hotel_1.toString());
        System.out.println(touristicSite_1.toString());
        System.out.println(touristicSite_2.toString());

        System.out.println(coordinates_1.toString());
        System.out.println(coordinates_2.toString());
        System.out.println(distance_1_to_2);

        //Creating coordinates with real gps axis. The two points have been picked up in Massy, Ile de France.
        Coordinates startingPosition = new Coordinates(48.724755,  2.268383);
        Coordinates arrivalPosition = new Coordinates(48.726404,  2.271548);

        //Testing distance calculation between two gps points
        //result should be arount 0.296 km
        double distance_1 = startingPosition.getDistanceFromGeoCoordinates(arrivalPosition.getX_axis(), arrivalPosition.getY_axis());
        double distance_2 = startingPosition.getDistanceFromGeoCoordinates(arrivalPosition);
        System.out.println("Distance 1 = "+distance_1);
        System.out.println("Distance 1 = "+distance_2);
    }
}