package com.ucp.PropositionEngine;

import com.ucp.Request.Request;
import com.ucp.business.data.Model.*;
import com.ucp.dao.HotelDao;
import com.ucp.dao.TouristicSiteDao;
import com.ucp.dao.TransportDao;

import java.util.ArrayList;

public class PropositionEngine {
    private static ArrayList<HotelDao> hotels;
    private static ArrayList<TouristicSiteDao> sites;
    private static ArrayList<TransportDao> transports;
    private static PropositionCriterias proposition;

    public static void getProposition(PropositionCriterias prop) {
        proposition = prop;
    }

    public static void computeProposition() {
        ArrayList<TouristicSiteDao> activities = selectActivities();
    }

    public static void retrievePropositionFromDatabase() {
        hotels = (ArrayList<HotelDao>) Request.getHotelsFromUserCriterias(proposition.getComfort());
        sites = (ArrayList<TouristicSiteDao>) Request.getTouristicSitesFromUserCriterias(proposition.getCriterias());
        transports = (ArrayList<TransportDao>) Request.getTransports();
    }

    private static ArrayList<TouristicSiteDao> selectActivities() {
        ArrayList<TouristicSiteDao> subset = new ArrayList<>();
        try {
            subset = (ArrayList<TouristicSiteDao>) sites.subList(0, proposition
                    .getAverageActivitiesPerDay() * (proposition
                    .getStayDuration() - proposition
                    .getChillDays()));
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("IL NY AS PAS ASSEZ");
        }
        return subset;
    }

    private Stay findValidStayFromList(ArrayList<Stay> stays){
        double priceGap = 0.0;
        double comfortGap = 0.0;
        for(Stay stay : stays){
            priceGap = proposition.getMaxBudget() - stay.getPrice();
            comfortGap = proposition.getComfort() - stay.getComfort();
            if(priceGap > 0 && comfortGap < 0) return stay;
        }
        return null;
    }

    private Stay computeMostOptimalStay(ArrayList<TouristicSiteDao> subset){
        ArrayList<Stay> stays = new ArrayList<>();
        for(HotelDao hotel : hotels){

            ArrayList<Day> days = new ArrayList<>();
            for (int i = 0; i < subset.size(); i++) {
                if (i % proposition.getAverageActivitiesPerDay() == 0) {
                    ArrayList<TouristicSiteDao> dailyActivities = (ArrayList<TouristicSiteDao>) subset.subList(0, proposition.getAverageActivitiesPerDay() - 1);
                    while (subset.size() > (subset.size() - dailyActivities.size())) {
                        subset.remove(0);
                    }
                    ArrayList<Transport> transports = new ArrayList<>();
                    //TODO ADD RANDOM BETWEEN TRANSPORTATIONS
                    while (transports.size() <= dailyActivities.size()) {
                        transports.add(new Bus(1, 1));
                    }
                    ArrayList<TouristicSite> sites = new ArrayList<>();
                    //TODO USE CONVERTER BETWEEN DAO AND BUSINESS
                    for (TouristicSiteDao site : dailyActivities) {
                        TouristicSite tSite = site.generateTouristicSite();
                        sites.add(tSite);
                    }
                    Day day = new Day(proposition.getAverageActivitiesPerDay(), Excursion.builder()
                            .setTouristicSites(sites)
                            .setTransports(transports)
                            .hotel(hotel.generateHotel())
                            .computeComfort()
                            .computePrice()
                            .build());
                    days.add(day);
                }
            }
            stays.add(Stay.builder().hotel(hotel.generateHotel()).setDays(days).computeComfort().computePrice().build());
        }
        return findValidStayFromList(stays);
    }
}
