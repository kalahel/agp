package com.ucp.hibernate;

import com.ucp.SqlIterator;
import com.ucp.SqlLauncher;
import com.ucp.dao.ActivityPlaceDao;
import com.ucp.dao.HistoricPlaceDao;
import com.ucp.dao.TouristicSiteDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DatabaseFiller {
    final static Logger logger = LogManager.getLogger(SqlLauncher.class);

    public static void main(String[] args) {

        DBConnection dbConnection = new DBConnection();
        Session session = dbConnection.getSessionFactory().getCurrentSession();

        TouristicSiteDao activityPlace_1 = new ActivityPlaceDao();
        activityPlace_1.setDescription("waikiki beach");
        activityPlace_1.setX_axis(21.280651);
        activityPlace_1.setY_axis(-157.837204);
        activityPlace_1.setComfort(20);

        TouristicSiteDao activityPlace_2 = new ActivityPlaceDao();
        activityPlace_2.setDescription("waimea canyon");
        activityPlace_2.setX_axis(22.071832);
        activityPlace_2.setY_axis(-159.661238);
        activityPlace_2.setComfort(15);

        TouristicSiteDao activityPlace_3 = new ActivityPlaceDao();
        activityPlace_2.setDescription("volcano village");
        activityPlace_2.setX_axis(19.444224);
        activityPlace_2.setY_axis(-155.230330);
        activityPlace_2.setComfort(10);

        TouristicSiteDao activityPlace_4 = new ActivityPlaceDao();
        activityPlace_2.setDescription("kilauea volcano");
        activityPlace_2.setX_axis(19.406240);
        activityPlace_2.setY_axis(-155.283520);
        activityPlace_2.setComfort(5);

        TouristicSiteDao activityPlace_5 = TouristicSiteDao.builder()
                .description("mauna kea observatory")
                .x_axis(19.823348)
                .y_axis(-155.472450)
                .comfort(5)
                .build();

        TouristicSiteDao activityPlace_6 = TouristicSiteDao.builder()
                .description("kohala mountain")
                .x_axis(20.0850956)
                .y_axis(-155.7189817)
                .comfort(5)
                .build();

        Transaction transaction = session.beginTransaction();
        session.persist(activityPlace_1);
        session.persist(activityPlace_2);
        session.persist(activityPlace_3);
        session.persist(activityPlace_4);
        session.persist(activityPlace_5);
        session.persist(activityPlace_6);

        transaction.commit();
        session.close();


        }

    }
