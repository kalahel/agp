package com.ucp.business.data.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    private double price;
    private Coordinates coordinates;
    private Beach beach;
    private double comfort;
}
