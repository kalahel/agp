package com.ucp.view.consoleInteractor;

import com.ucp.PropositionEngine.PropositionCriterias;
import com.ucp.PropositionEngine.PropositionEngine;
import com.ucp.Request.Request;
import com.ucp.dao.HotelDao;
import com.ucp.dao.TouristicSiteDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class consoleUserInteractor {

    private static Scanner scanner = new Scanner(System.in);
    private static int stayDuration;
    private static int comfort;
    private static int chillDays;
    private static int averageActivitiesPerDay;
    private static int maxBudget;
    private static String criterias;

    private static void welcomeDisplay() {
        System.out.println("Bonjour, bienvenue dans notre générateur de séjours");
        System.out.println("Vous allez être invité à saisir différentes informations pour que nous proposions le séjour qui vous conviens le mieux.");
    }

    private static void askForStayDuration() {
        System.out.println("Combien de jours souhaitez vous rester ?");
        stayDuration = scanner.nextInt();
        System.out.println("Vous avez choisis un séjour de " + stayDuration + ". Si cela vous conviens, répondez 1, sinon, répondez 0.");
        int validation = scanner.nextInt();
        if (validation != 1) askForStayDuration();
    }

    private static void askForGlobalComfort() {
        System.out.println("Quel niveau de comfort souhaitez vous avoir ?");
        System.out.println("Vous pouvez saisir un chiffre entre 1 et 5");
        int response = scanner.nextInt();
        if (response > 5 || response < 0) askForGlobalComfort();
        else comfort = response;
    }

    private static void askForChillDays() {
        System.out.println("Combien de jours de repos parmis vos " + stayDuration + " jours de séjour souhaitez vous avoir ?");
        int response = scanner.nextInt();
        if (response < 0 || response > stayDuration) askForGlobalComfort();
        else chillDays = response;
    }

    private static void askForAverageActivitiesPerDay() {
        if (chillDays != 0 && stayDuration / chillDays != 1) {
            System.out.println("Combien d'activité souhaitez vous avoir en moyenne lorsque vous n'êtes pas en repos ?");
            averageActivitiesPerDay = scanner.nextInt();
        }
    }

    private static void askForMaxBudget() {
        System.out.println("QUel est votre budget maximum ? ");
        maxBudget = scanner.nextInt();
    }

    private static void askForCriterias() {
        System.out.println("Saisissez les mots clef permettant de carracteriser votre séjour ");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            criterias = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void executeInterractor() {
        welcomeDisplay();
        askForStayDuration();
        askForGlobalComfort();
        askForChillDays();
        askForAverageActivitiesPerDay();
        askForMaxBudget();
        askForCriterias();
    }


    private static void executePropositor() {
        PropositionEngine.setProposition(PropositionCriterias.builder()
                .averageActivitiesPerDay(averageActivitiesPerDay)
                .chillDays(chillDays)
                .comfort(comfort)
                .criterias(criterias)
                .maxBudget(maxBudget)
                .stayDuration(stayDuration)
                .build());
        PropositionEngine.computeProposition().toString();
    }

    public static void main(String[] args) {
        executeInterractor();
        executePropositor();
        comfort = 30;
        criterias = "mountain volcano lava stone";
        List<HotelDao> hotels = Request.getHotelsFromUserCriterias(comfort);
        List<TouristicSiteDao> sites = Request.getTouristicSitesFromUserCriterias(criterias);
        for(HotelDao hotel : hotels) System.out.println(hotel.toString());
        for(TouristicSiteDao site : sites) System.out.println(site.getId());
    }
}
