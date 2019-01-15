package com.ucp;


import com.ucp.business.data.examples.PlaceHolderData;
import com.ucp.business.factories.PlaceHolderFactory;
import com.ucp.configuration.SpringConfiguration;
import com.ucp.hibernate.DBConnection;
import com.ucp.hibernate.PlaceHolderDataStorable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Launcher {
    final static Logger logger = LogManager.getLogger(Launcher.class);

    public static void main(String[] args) {
        // Logger example, launcher must be instantiate
        Launcher launcher = new Launcher();
        logger.trace("Program initialization");

        // Spring basic setup example
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        PlaceHolderFactory placeHolderFactory = applicationContext.getBean("placeHolderFactory", PlaceHolderFactory.class);

        // Hibernate usage
        DBConnection dbConnection = new DBConnection();
        Session session = dbConnection.getSessionFactory().getCurrentSession();

        PlaceHolderData placeHolderData = placeHolderFactory.createData("Manos", 9001);
        PlaceHolderData placeHolderData1 = placeHolderFactory.createData("Liu", 9002);
        PlaceHolderDataStorable placeHolderDataStorable = new PlaceHolderDataStorable();
        PlaceHolderDataStorable placeHolderDataStorable1 = new PlaceHolderDataStorable();

        placeHolderDataStorable.setAll(placeHolderData);
        placeHolderDataStorable1.setAll(placeHolderData1);

        // Transaction example
        Transaction transaction = session.beginTransaction();
        session.persist(placeHolderDataStorable);
        session.persist(placeHolderDataStorable1);
        transaction.commit();

        /** **/
        session = dbConnection.getSessionFactory().getCurrentSession();
        transaction = session.beginTransaction();
        List<PlaceHolderDataStorable> result = session.createSQLQuery("SELECT * FROM `placeholderdatastorable` ").addEntity(PlaceHolderDataStorable.class).list();
        for (PlaceHolderDataStorable currentResult : result) {
            System.out.println(currentResult.toString());
        }
        transaction.commit();
        /** **/

        session.close();
    }
}
