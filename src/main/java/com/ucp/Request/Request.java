package com.ucp.Request;

import com.ucp.LuceneLauncher;
import com.ucp.SqlLuceneJoin;
import com.ucp.TouristicSiteJoined;
import com.ucp.dao.HotelDao;
import com.ucp.dao.TouristicSiteDao;
import com.ucp.hibernate.DBConnection;
import org.apache.lucene.queryparser.classic.ParseException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Request {

    public static List<TouristicSiteDao> getTouristicSitesFromUserCriterias(String criterias){
        ArrayList<TouristicSiteDao> result = new ArrayList<>();
        try {
            LuceneLauncher.indexDocuments();
            List<TouristicSiteJoined> resultJoin = SqlLuceneJoin.sqlJoinLucene("", "volcano mountain village canyon");
            for(TouristicSiteJoined site : resultJoin){
                result.add(site.getTouristicSiteDao());
            }
        }catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<HotelDao> getHotelsFromUserCriterias(int comfort){

        int requestSuperiorLimit = comfort + 10;
        int resquestInferiorLimit = comfort - 10;
        List<HotelDao> results = null;
        String whereCondition = "WHERE comfort BETWEEN "+resquestInferiorLimit +" AND " +requestSuperiorLimit;

        DBConnection dbConnection = new DBConnection();
        Session session = dbConnection.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        results = session.createSQLQuery("SELECT * FROM hoteldao " + whereCondition).addEntity(HotelDao.class).list();
        int numberOfResult = results.size();
        transaction.commit();
        session.close();
        return results;
    }
}
