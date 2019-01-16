package com.ucp.business.data;

import com.ucp.business.data.Model.*;
import org.junit.Test;
import org.springframework.expression.spel.ast.NullLiteral;

import static java.sql.Types.NULL;
import static org.junit.Assert.assertEquals;

public class TestCompleteEngine {

    TouristicSite volcanoKilauea = new ActivityPlace("Famous volcano known as the most active on Earth. It is in fact one of Hawaii highest mountains.",
            new Coordinates(19.406899, -155.283336), -10);
    TouristicSite VolcanoMaunaLoa = new ActivityPlace("This volcano is a typical shield one, and considered as one of the most active on earth.",
            new Coordinates(19.4720312, -155.5922038), -20);
    TouristicSite KailuaKona = new ActivityPlace("Charming village on the west coast of the island, with many facilities for foreign tourists.",
            new Coordinates(19.640754, -155.992771), 20);
    TouristicSite Milolii = new ActivityPlace("tiny village on the west coast of the island, with  mostly island residents and a small beach.",
            new Coordinates(19.640754, -155.992771), 0);

    TouristicSite MookiniHeiau = new HistoricPlace("Sacred religious place from the ancestral tribes of the island, and birth place of King Kamehameha",
            new Coordinates(20.255580, -155.882662), 5);
    TouristicSite PuukoholaNationalHistoricPark = new HistoricPlace("Natural park filled with ancient structures and tribal art pieces. You will learn about King Kamehameha and his history",
            new Coordinates(20.0258056, -155.8221014), 5);

    //Creating hotels and the associated beach
    Beach beach_1 = new Beach("Punaluu Black Sand Beach");
    Hotel SeaMountainCondo = new Hotel(170, new Coordinates(19.1336205, -155.5136796), beach_1, 20);
    Beach beach_2 = new Beach("Magic sand beach park");
    Hotel FallingWatersVacationVilla = new Hotel(291, new Coordinates(19.5940869, -155.9720517), beach_2, 50);

    //Creating transportations
    Transport bus_1 = new Bus(10, 1);
    Transport bus_2 = new Bus(15, 1);
    Transport bus_3 = new Bus(20, 1);

    Transport boat_1 = new Boat(20, 5);
    Transport boat_2 = new Boat(25, 5);
    Transport boat_3 = new Boat(30, 5);

    //TODO DEVELOP
    Day day_1 = new Day(4, Excursion.builder()
            .setTransports(boat_1, boat_1)
            .setTouristicSites(KailuaKona)
            .hotel(SeaMountainCondo)
            .computePrice()
            .build());

    Day day_2 = new Day();

    Day day_3 = new Day(4, Excursion.builder()
            .setTransports(bus_1, bus_2, boat_1, bus_3)
            .setTouristicSites(VolcanoMaunaLoa, volcanoKilauea, MookiniHeiau)
            .hotel(SeaMountainCondo)
            .comfort(NULL)
            .computePrice()
            .build());

    Stay stay = Stay.builder()
            .setDays(day_1, day_2, day_3)
            .hotel(FallingWatersVacationVilla)
            .computePrice()
            .build();

    @Test
    public void dayBuilderTest() throws Exception {
        try {
            //Creating touristic place
            Excursion excursion = new Excursion();

            excursion.setComfort(NULL);
            excursion.setTransports(bus_1, bus_2, boat_1, bus_3);
            excursion.setTouristicSites(VolcanoMaunaLoa, volcanoKilauea, MookiniHeiau);
            excursion.setHotel(SeaMountainCondo);
            excursion.computePrice();

            Day day_test = new Day(4,excursion);

            assertEquals(day_2, day_test);

        } catch (Exception e) {
            System.err.println("[Error :dayTest]: " + e);
        }
    }

    @Test
    public void stayBuilderTest() {
        try {
            Stay stay1 = new Stay();
            stay1 = stay;

            assertEquals(stay1.getDaysList(), stay.getDaysList());
            assertEquals(stay1.getPrice(), stay.getPrice(), 0);
            assertEquals(stay1.getHotel(), stay.getHotel());

        } catch (Exception e) {
            System.err.println("[Error : StayBuilder]: " + e);
        }
    }

    @Test
    public void computePrice() throws Exception {
        try {
            double price = stay.getHotel().getPrice();
            for (int index = 0; index < stay.getDaysList().size(); index++) {
                for (Transport t : stay.getDaysList().get(index).getExcursion().getTransports()) {
                    price = price + t.getPrice();
                }
            }
            assertEquals(price, stay.getPrice(), 0);
        } catch (Exception e) {
            System.err.println("[Error : computePrice]: ");
        }
    }

    @Test
    public void testComfort() throws Exception {
        try{
            double comfort = 0.0;

            if (day_2.getExcursion() == null) comfort = 0.0;

            int factorOfComfort = 1;

            if (day_2.getExcursion().getTransports().size() > day_2.getDailyTransportLimitation()) factorOfComfort = -1;

            for (Transport t : day_2.getExcursion().getTransports()) {
                comfort += t.getComfort() * factorOfComfort;
            }
            comfort += day_2.getExcursion().getComfort() + day_2.getExcursion().getHotel().getComfort();
        }catch (Exception e){
            System.err.println("[Error : Comfort]: "+e);
        }
    }

}
