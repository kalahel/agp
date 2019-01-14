package com.ucp.business.data;

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
        touristicSite_1.setDescription("toursitic desc");


        System.out.println(transport_1.getPrice());
        System.out.println(beach_1.getName());
        System.out.println(hotel_1.toString());
        System.out.println(touristicSite_1.toString());

    }
}