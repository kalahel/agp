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

        public ExcursionBuilder setTouristicSites(ArrayList<TouristicSite> sites) {
            this.touristicSites = sites;
            return this;
        }

        public ExcursionBuilder setTransports(Transport... transports) {
            this.transports = new ArrayList<Transport>();
            for (Transport transport : transports) {
                this.transports.add(transport);
            }
            return this;
        }

        public ExcursionBuilder setTransports(ArrayList<Transport> transports) {
            this.transports = transports;
            return this;
        }

        public ExcursionBuilder computePrice() {
            double prix = this.hotel.getPrice();

            double priceFromHotelToFirstActivity = transports
                    .get(0).getPrice() * hotel
                    .getCoordinates()
                    .getDistanceFromGeoCoordinates(this.touristicSites.get(0).getCoordinates());
            double priceFromLastActivityToHotel = transports
                    .get(transports.size() - 1)
                    .getPrice() * hotel
                    .getCoordinates()
                    .getDistanceFromGeoCoordinates(this.touristicSites.get(touristicSites.size() - 1).getCoordinates());

            double priceOfTransportationBetweenActivities = 0.0;

            for (int i = 1; i < touristicSites.size() - 2; i++) {
                priceOfTransportationBetweenActivities += touristicSites
                        .get(i)
                        .getCoordinates()
                        .getDistanceFromGeoCoordinates(touristicSites.get(i + 1)
                                .getCoordinates()) * transports.get(i).getPrice();
            }
            prix += priceFromHotelToFirstActivity + priceFromLastActivityToHotel + priceOfTransportationBetweenActivities;
            this.excursionPrice = prix;
            System.out.print("      prix total = " + prix);
            return this;
        }

        public ExcursionBuilder computeComfort() {
            double comfort = 0.0;
            for (Transport transport : transports) {
                comfort += transport.getComfort();
            }
            for (TouristicSite site : touristicSites) {
                comfort += site.getComfort();
            }
            this.comfort = comfort / (touristicSites.size() + transports.size());
            return this;
        }
    }
}
