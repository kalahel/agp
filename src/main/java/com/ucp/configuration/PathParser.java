package com.ucp.configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PathParser {
    public static String parsePath() {
        BufferedReader reader;
        String out = "D:\\Bureau\\agp\\src\\main\\resources"; // Default value
        try {
            reader = new BufferedReader(new FileReader(System.getProperty("user.home") + "/agp.txt"));
            out = reader.readLine();
            reader.close();
        } catch (IOException e) {
            System.err.println("You need to create the file agp.txt in your home repository : " + System.getProperty("user.home") + "\nRefer yourself to the REAMDE.md for details\n");
            System.err.println("Default resource path set : " + out);
        }
        return out;
    }
}
