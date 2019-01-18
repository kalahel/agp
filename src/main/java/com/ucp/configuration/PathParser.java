package com.ucp.configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PathParser {
    public static String parsePath() {
        System.out.println("USER HOME : " + System.getProperty("user.home"));
        BufferedReader reader;
        String out = "";
        try {
            reader = new BufferedReader(new FileReader(System.getProperty("user.home") + "/agp.txt"));
            out = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }
}
