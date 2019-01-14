package com.ucp.business.data;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Excursion {
    private double comfort;
    private double excursionPrice;
    private Hotel hotel;
    private List<Transport> transports;
    private List<TouristicSite> touristicSites;

}
