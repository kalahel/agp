package com.ucp;


import com.ucp.bussiness.data.PlaceHolderData;
import com.ucp.bussiness.factories.PlaceHolderFactory;
import com.ucp.configuration.SpringConfiguration;
import com.ucp.hibernate.DBConnection;
import com.ucp.hibernate.PlaceHolderDataStorable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
        PlaceHolderDataStorable placeHolderDataStorable = new PlaceHolderDataStorable();
        placeHolderDataStorable.setAll(placeHolderData);

        // Transaction example
        Transaction transaction = session.beginTransaction();
        session.persist(placeHolderDataStorable);
        transaction.commit();
        session.close();
    }
}