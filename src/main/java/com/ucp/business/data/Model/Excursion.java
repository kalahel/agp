package com.ucp.business.data.Model;

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

    public void computePrice(){
        double prix = hotel.getPrice();
        for(Transport t : transports) prix = prix+t.getPrice();
        this.excursionPrice = prix;
    }

    public void setTouristicSites( TouristicSite... sites){
        for(TouristicSite site : sites) {
            this.touristicSites.add(site);
        }
    }

    public void setTransports( Transport... transports){
        for(Transport transport : transports) {
            this.transports.add(transport);
        }
    }
}
