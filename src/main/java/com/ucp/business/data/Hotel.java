package com.ucp.business.data;

import lombok.Data;

@Data
public class Hotel {
    private double price;
    private Coordinates coordinates;
    private Beach beach;
}
