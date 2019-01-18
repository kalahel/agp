package com.ucp.PropositionEngine;

import com.ucp.Request.Request;
import com.ucp.business.data.Model.*;
import com.ucp.dao.HotelDao;
import com.ucp.dao.TouristicSiteDao;
import com.ucp.dao.TransportDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedList;

public class PropositionEngine {

    final static Logger logger = LogManager.getLogger(PropositionEngine.class);

    private static ArrayList<HotelDao> hotels;
    private static ArrayList<TouristicSiteDao> sites;
    private static ArrayList<TransportDao> transports;
    private static PropositionCriterias proposition;

    public static void setProposition(PropositionCriterias prop) {
        proposition = prop;
    }

    public static Stay computeProposition() {
        retrievePropositionFromDatabase();
        ArrayList<TouristicSiteDao> activities = selectActivities();
        return computeMostOptimalStay(activities);
    }

    private static void retrievePropositionFromDatabase() {
        logger.debug("ENTERING retrievePropositionFromDatabase :");
        logger.debug("collecting hotels from proposition");
        hotels = new ArrayList<>(Request.getHotelsFromUserCriterias(proposition.getComfort()));
        logger.debug("collecting sites from proposition");
        sites = new ArrayList<>(Request.getTouristicSitesFromUserCriterias(proposition.getCriterias()));
        logger.debug("collecting transports from proposition");
        transports = new ArrayList<>(Request.getTransports());
        logger.debug("sites : " + sites.toString());
        logger.debug("sites : " + sites.size());
    }

    private static ArrayList<TouristicSiteDao> selectActivities() {
        logger.debug("ENTERING selectActivities :");
        ArrayList<TouristicSiteDao> subset = new ArrayList<>();
        try {
            logger.debug("Trying to build subset");
            subset = new ArrayList<>(sites.subList(0, proposition
                    .getAverageActivitiesPerDay() * (proposition
                    .getStayDuration() - proposition
                    .getChillDays())));
            logger.debug("Subset built : " + subset.toString());
        } catch (IndexOutOfBoundsException e) {
            //e.printStackTrace();
            System.out.println("nombre de TouristicSites inssufisant");
        }
        logger.debug("Returning subset");
        return subset;
    }

    private static Stay findValidStayFromList(ArrayList<Stay> stays) {
        logger.debug("ENTERING findValidStayFromList");
        double priceGap = 0.0;
        double comfortGap = 0.0;
        logger.debug("Iterating over stays list of size = " + stays.size());
        for (Stay stay : stays) {
            priceGap = proposition.getMaxBudget() - stay.getPrice();
            comfortGap = proposition.getComfort() - stay.getComfort();
            if (priceGap > 0 && comfortGap < 0) {
                logger.debug("returning valid stay : " + stay.toString());
                return stay;
            }

        }
        logger.debug("No stay found, returning null");
        return null;
    }

    private static Stay computeMostOptimalStay(ArrayList<TouristicSiteDao> subset) {

        LinkedList<TouristicSiteDao> siteQueue = new LinkedList<>();


        logger.debug("ENTERING computeMostOptimalStay");
        ArrayList<Stay> stays = new ArrayList<>();
        logger.debug("Iterating over hotel list");
        for (HotelDao hotel : hotels) {

            for (TouristicSiteDao touristicSiteDao :
                    subset) {
                siteQueue.addLast(touristicSiteDao);
            }

            ArrayList<Day> days = new ArrayList<>();
            for (int i = 0; i < subset.size(); i++) {
                ArrayList<Transport> transports = new ArrayList<>();
                ArrayList<TouristicSite> sites = new ArrayList<>();
                if (i % proposition.getAverageActivitiesPerDay() == 0 && !siteQueue.isEmpty()) {
                    ArrayList<TouristicSiteDao> dailyActivities = new ArrayList<>();
                    for (int j = 0; j < proposition.getAverageActivitiesPerDay(); j++) {
                        dailyActivities.add(siteQueue.getFirst());
                        siteQueue.removeFirst();
                    }


                    //TODO ADD RANDOM BETWEEN TRANSPORTATIONS AND EXTRACTION FROM DATABASE
                    while (transports.size() <= dailyActivities.size()) {
                        transports.add(new Bus(0.001, 1));
                    }


                    //TODO USE CONVERTER BETWEEN DAO AND BUSINESS
                    for (TouristicSiteDao site : dailyActivities) {
                        TouristicSite tSite = site.generateTouristicSite();
                        sites.add(tSite);
                    }

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
            stays.add(Stay.builder().hotel(hotel.generateHotel()).setDays(days).computeComfort().computePrice().build());
        }
        return findValidStayFromList(stays);
    }
}
