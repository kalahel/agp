package com.ucp.business.data.Model.Tests;

import com.ucp.business.data.Model.*;

import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;

public class CompleteEngineTest {
    public static void main(String [ ] args){

        //Creating touristic places
        TouristicSite volcanoKilauea = new ActivityPlace("Famous volcano known as the most active on Earth. It is in fact one of Hawaii highest mountains.",
                new Coordinates(19.406899, -155.283336));
        TouristicSite VolcanoMaunaLoa = new ActivityPlace("This volcano is a typical shield one, and considered as one of the most active on earth.",
                new Coordinates(19.4720312, -155.5922038));
        TouristicSite KailuaKona = new ActivityPlace("Charming village on the west coast of the island, with many facilities for foreign tourists.",
                new Coordinates(19.640754, -155.992771));
        TouristicSite Milolii = new ActivityPlace("tiny village on the west coast of the island, with  mostly island residents and a small beach.",
                new Coordinates(19.640754, -155.992771));

        TouristicSite MookiniHeiau = new HistoricPlace("Sacred religious place from the ancestral tribes of the island, and birth place of King Kamehameha",
                new Coordinates(20.255580, -155.882662));
        TouristicSite PuukoholaNationalHistoricPark = new HistoricPlace("Natural park filled with ancient structures and tribal art pieces. You will learn about King Kamehameha and his history",
                new Coordinates(20.0258056, -155.8221014));

        //Creating hotels and the associated beach
        Beach beach_1 = new Beach("Punaluu Black Sand Beach");
        Hotel SeaMountainCondo = new Hotel(170, new Coordinates(19.1336205, -155.5136796), beach_1);
        Beach beach_2 = new Beach("Magic sand beach park");
        Hotel FallingWatersVacationVilla = new Hotel(291, new Coordinates(19.5940869, -155.9720517), beach_2);

        //Creating transportations
        Transport bus_1 = new Bus(10);
        Transport bus_2 = new Bus(15);
        Transport bus_3 = new Bus(20);

        Transport boat_1 = new Boat(20);
        Transport boat_2 = new Boat(25);
        Transport boat_3 = new Boat(30);

        Stay stay = Stay.builder().build();
        List<Day> daysList= new ArrayList<>();

        //TODO DEVELOP
        //Day day_1 = new Day(new Excursion(NULL, NULL, SeaMountainCondo, transportsDay_1, touristicSitesDay_1).setTouristicSites(VolcanoMaunaLoa);
        //day_1.getExcursion().computePrice();
        //System.out.println(day_1.toString());
    }
}
