package com.ucp.business.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class TouristicSite {
    protected String description;
    protected Coordinates coordinates;
}
