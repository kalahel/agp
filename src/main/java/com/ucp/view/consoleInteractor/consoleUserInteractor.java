package com.ucp.view.consoleInteractor;

import java.util.Scanner;

public class consoleUserInteractor {
    private static Scanner scanner = new Scanner(System.in);
    private static int stayDuration;
    private static int comfort;

    private static void welcomeDisplay(){
        System.out.println("Bonjour, bienvenue dans notre générateur de séjours");
        System.out.println("Vous allez être invité à saisir différentes informations pour que nous proposions le séjour qui vous conviens le mieux.");
    }

    private static void askForStayDuration(){
        System.out.println("Combien de jours souhaitez vous rester ?");
        stayDuration = scanner.nextInt();
        System.out.println("Vous avez choisis un séjour de "+stayDuration+". Si cela vous conviens, répondez 1, sinon, répondez 0.");
        int validation = scanner.nextInt();
        if(validation != 1) askForStayDuration();
    }

    private static void askForGlobalComfort(){
        System.out.println("Quel niveau de comfort souhaitez vous avoir ?");
        System.out.println("Vous pouvez saisir un chiffre entre 1 et 5");
        int comfort = scanner.nextInt();
        if(comfort > 5 || comfort < 0) askForGlobalComfort();
    }

    public static void main(String[] args){
        welcomeDisplay();
        askForStayDuration();
        askForGlobalComfort();

    }

}
