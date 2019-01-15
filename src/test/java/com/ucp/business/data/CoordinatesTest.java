package com.ucp.business.data;

import com.ucp.business.data.Model.Coordinates;
import org.junit.*;

import static org.junit.Assert.*;

public class CoordinatesTest {

    @BeforeClass
    public static void beforeCoordinate(){
        System.out.println("Start test on Coordinates");
    }

    @Before
    public void coordinatesTest() throws Exception{
        try{
            Coordinates coordinates = new Coordinates();
        }catch (Exception e){
            System.err.println("Error : "+e);
        }
    }

    @Test
    public void getDistance() throws Exception {
        try{
            Coordinates coordinates = new Coordinates();

            double y_axis = 10.0;
            double x_axis = 10.0;

            coordinates.setY_axis(y_axis);
            coordinates.setX_axis(x_axis);

            double result  = Math.sqrt(Math.pow(x_axis - x_axis, 2) + Math.pow(y_axis + y_axis, 2));

            assertEquals(result, coordinates.getDistance(coordinates),0);
        }catch (Exception e){
            System.err.println("Error getDistance : "+e);
        }
    }

    @Test
    public void getY_axisTest() throws Exception{
        try {
            Coordinates coordinates = new Coordinates();

            double y_axis = 11.16;

            coordinates.setY_axis(y_axis);

            assertEquals(y_axis, coordinates.getY_axis(), 0);

            if(y_axis!=coordinates.getY_axis()){
                System.err.println("Error getY_axis value different");
            }

        }catch (Exception e){
            System.err.println("Error getY_axis : "+e);
        }
    }

    @Test
    public void getX_axisTest() throws Exception{
        try{

            Coordinates coordinates = new Coordinates();

            double x_axis = 12.98;

            coordinates.setX_axis(x_axis);

            assertEquals(x_axis,coordinates.getX_axis(),0);

            if(x_axis!=coordinates.getX_axis()){
                System.err.println("Error getX_axis value different");
            }
        }catch (Exception e){
            System.err.println("Error : "+e);
        }
    }

    @AfterClass
    public static void finTestCoordinate() {
        System.out.println("End test of Coordinates");
    }

}