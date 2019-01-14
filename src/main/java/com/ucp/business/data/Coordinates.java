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
}
