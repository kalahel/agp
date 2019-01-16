package com.ucp.business.data.Model;

import lombok.*;

import java.util.ArrayList;
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

    public void computePrice() {
        double prix = hotel.getPrice();
        for (Transport t : transports) prix = prix + t.getPrice();
        this.excursionPrice = prix;
    }

    public void setTouristicSites(TouristicSite... sites) {
        for (TouristicSite site : sites) {
            this.touristicSites.add(site);
        }
    }

    public void setTransports(Transport... transports) {
        for (Transport transport : transports) {
            this.transports.add(transport);
        }
    }

    public static class ExcursionBuilder {
        public ExcursionBuilder setTouristicSites(TouristicSite... sites) {
            this.touristicSites = new ArrayList<TouristicSite>();
            for (TouristicSite site : sites) {
                this.touristicSites.add(site);
            }
            return this;
        }

        public ExcursionBuilder setTransports(Transport... transports) {
            this.transports = new ArrayList<Transport>();
            for (Transport transport : transports) {
                this.transports.add(transport);
            }
            return this;
        }

        //FIXME WRONG RESULT
        public ExcursionBuilder computePrice() {
            double prix = this.hotel.getPrice();
            System.out.println("    Prix de l'hotel : " + prix);
            for (Transport t : this.transports) {
                prix = prix + t.getPrice();
                System.out.println("        prix du transport" + t.getPrice());
            }
            this.excursionPrice = prix;
            System.out.print("      prix total = " + prix);
            return this;
        }
    }
}
