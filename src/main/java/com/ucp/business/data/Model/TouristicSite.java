package com.ucp.business.data.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class TouristicSite implements Comfort {
    protected String description;
    protected Coordinates coordinates;
    protected double comfort;
}
