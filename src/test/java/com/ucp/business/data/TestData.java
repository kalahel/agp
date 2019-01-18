package com.ucp.business.data;

import com.ucp.business.data.Model.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestData {

    @Test
    public void coordinatesTest() throws Exception {
        try {
            Coordinates coordinates = new Coordinates(123.2, 56.6);

            coordinates.setY_axis(123.2);
            coordinates.setX_axis(56.6);

            assertEquals(56.6, coordinates.getX_axis(), 0);
            assertEquals(123.2, coordinates.getY_axis(), 0);
        } catch (Exception e) {
            System.err.println("[Error : Coordinates]: " + e);
        }
    }

    @Test
    public void transportTest() throws Exception {
        try {
            Transport transport = new Boat();
            transport.setPrice(200);

            assertEquals(200, transport.getPrice(), 0);

        } catch (Exception e) {
            System.err.println("[Error : Transport]" + e);
        }
    }

    @Test
    public void beachTest() throws Exception {
        try {
            Beach beach = new Beach("beach name");
            beach.setName("beach rename");

            assertEquals("beach rename", beach.getName());

        } catch (Exception e) {
            System.err.println("[Error Beach]: " + e);
        }
    }

    @Test
    public void hotelTest() throws Exception {
        try {
            Coordinates coordinates = new Coordinates(112.9, 96.6);
            Beach beach = new Beach("Beach 1");

            Hotel hotel = new Hotel(100, coordinates, beach, 10);

            Hotel hotel1 = hotel;

            Beach beach1 = new Beach("new Beach");

            hotel1.setBeach(beach1);
            hotel.setBeach(beach1);

            hotel1.setPrice(123);
            hotel.setPrice(123);

            assertEquals(hotel, hotel1);

        } catch (Exception e) {
            System.err.println("[Error : Hotel]: " + e);
        }
    }

    @Test
    public void touristicPlaceTest() throws Exception {
        try {
            TouristicSite touristicSite = new ActivityPlace();
            touristicSite.setCoordinates(new Coordinates(126.2, 96.3));
            touristicSite.setDescription("Test description");

            TouristicSite touristicSite1 = new ActivityPlace();
            touristicSite1.setCoordinates(touristicSite.getCoordinates());
            touristicSite1.setDescription(touristicSite.getDescription());

            assertEquals(touristicSite, touristicSite1);
        } catch (Exception e) {
            System.err.println("[Error : tourist Place] :" + e);
        }
    }

    @Test
    public void distanceTest() throws Exception {
        try {
            Coordinates coord = new Coordinates(123.569, 42.26);
            Coordinates coord1 = new Coordinates(153.45, 85.5);

            double distance = coord.getDistance(coord1);
            assertEquals(distance, Math.sqrt(Math.pow(coord.getX_axis() - coord1.getX_axis(), 2) + Math.pow(coord.getY_axis() + coord1.getY_axis(), 2)), 0);

        } catch (Exception e) {
            System.err.println("[Error : Distance Coordinates]: " + e);
        }
    }

    @Test
    public void getDistanceFromGeoCoordinatesWithCoordinateTest() throws Exception {
        try {
            //Creating coordinates with real gps axis. The two points have been picked up in Massy, Ile de France.
            Coordinates startingCoord = new Coordinates(48.724755, 2.268383);
            Coordinates arrivalCoord = new Coordinates(48.726404, 2.271548);

            double degrees2radian = (Math.PI / 180);

            double dlong = (startingCoord.getY_axis() - arrivalCoord.getY_axis()) * degrees2radian;
            double dlat = (startingCoord.getX_axis() - arrivalCoord.getX_axis()) * degrees2radian;

            double result = Math.pow(Math.sin(dlat / 2.0), 2)
                    + Math.cos(arrivalCoord.getX_axis() * degrees2radian)
                    * Math.cos(startingCoord.getX_axis() * degrees2radian)
                    * Math.pow(Math.sin(dlong / 2.0), 2);

            result = 2 * Math.atan2(Math.sqrt(result), Math.sqrt(1 - result));
            result = 6367 * result;

            double resultTmp = startingCoord.getDistanceFromGeoCoordinates(arrivalCoord);

            assertEquals(result, resultTmp, 0);

        } catch (Exception e) {
            System.err.println("[Error : getDistanceFromGeoCoordinatesWithCoord]: ");
        }
    }

    @Test
    public void getDistanceFromGeoCoordinatesWithLatLongTest() throws Exception {
        try {
            //Creating coordinates with real gps axis. The two points have been picked up in Massy, Ile de France.
            Coordinates startingCoord = new Coordinates(48.724755, 2.268383);
            Coordinates arrivalCoord = new Coordinates(48.726404, 2.271548);

            double degrees2radian = (Math.PI / 180);

            double dlong = (startingCoord.getY_axis() - arrivalCoord.getY_axis()) * degrees2radian;
            double dlat = (startingCoord.getX_axis() - arrivalCoord.getX_axis()) * degrees2radian;

            double result = Math.pow(Math.sin(dlat / 2.0), 2)
                    + Math.cos(arrivalCoord.getX_axis() * degrees2radian)
                    * Math.cos(startingCoord.getX_axis() * degrees2radian)
                    * Math.pow(Math.sin(dlong / 2.0), 2);

            result = 2 * Math.atan2(Math.sqrt(result), Math.sqrt(1 - result));
            result = 6367 * result;

            double resultTmp = startingCoord.getDistanceFromGeoCoordinates(arrivalCoord.getX_axis(), arrivalCoord.getY_axis());

            assertEquals(result, resultTmp, 0);

        } catch (Exception e) {
            System.err.println("[Error : getDistanceFromGeoCoordinatesWithLongLat]: ");
        }
    }
}
