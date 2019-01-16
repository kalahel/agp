package com.ucp;

import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.List;

public class JointLauncher {
    public static void main(String[] args) {
        try {
            LuceneLauncher.indexDocuments();
            List<TouristicSiteJoined> resultJoin = SqlLuceneJoin.sqlJoinLucene("", "volcano mountain village canyon");
            //List<TouristicSiteJoined> resultJoin = SqlLuceneJoin.sqlJoinLuceneQuery("where description='volcano village' with volcano");
            //List<TouristicSiteJoined> resultJoin = SqlLuceneJoin.sqlJoinLuceneQuery("where comfort > 7 with volcano");

            for (TouristicSiteJoined touristicSiteJoined :
                    resultJoin) {
                System.out.println(touristicSiteJoined.toString());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
