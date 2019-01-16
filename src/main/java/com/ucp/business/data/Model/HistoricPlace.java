package com.ucp.business.data.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class HistoricPlace extends TouristicSite {
    public HistoricPlace(String desc, Coordinates coord, double comfort) {
        super(desc, coord, comfort);
    }
}
