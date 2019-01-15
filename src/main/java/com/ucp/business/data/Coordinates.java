package com.ucp.business.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates {
    private double x_axis;
    private double y_axis;
    public double getDistance(Coordinates coordinates){
        return Math.sqrt(Math.pow(coordinates.x_axis - this.x_axis, 2) + Math.pow(coordinates.y_axis + this.y_axis, 2));
    }
    public double getDistanceFromGeoCoordinates( double latitude, double longitude) {
        double degrees2radian = (Math.PI / 180);
        double dlong = (longitude - this.y_axis) * degrees2radian;
        double dlat = (latitude - this.x_axis) * degrees2radian;
        double result = Math.pow(Math.sin(dlat / 2.0), 2)
                + Math.cos(this.x_axis * degrees2radian)
                * Math.cos(latitude * degrees2radian)
                * Math.pow(Math.sin(dlong / 2.0), 2);
        result = 2 * Math.atan2(Math.sqrt(result), Math.sqrt(1 - result));
        return 6367 * result;
    }

    public double getDistanceFromGeoCoordinates( Coordinates coordinates) {
        double degrees2radian = (Math.PI / 180);
        double dlong = (coordinates.getY_axis() - this.y_axis) * degrees2radian;
        double dlat = (coordinates.getX_axis() - this.x_axis) * degrees2radian;
        double result = Math.pow(Math.sin(dlat / 2.0), 2)
                + Math.cos(this.x_axis * degrees2radian)
                * Math.cos(coordinates.getX_axis() * degrees2radian)
                * Math.pow(Math.sin(dlong / 2.0), 2);
        result = 2 * Math.atan2(Math.sqrt(result), Math.sqrt(1 - result));
        return 6367 * result;
    }
}
