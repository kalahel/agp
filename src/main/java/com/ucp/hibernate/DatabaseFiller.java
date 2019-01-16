package com.ucp.hibernate;

import com.ucp.SqlIterator;
import com.ucp.SqlLauncher;
import com.ucp.business.data.Model.HistoricPlace;
import com.ucp.business.data.Model.TouristicSite;
import com.ucp.dao.*;
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

        TouristicSiteDao activityPlace_5 = new ActivityPlaceDao();
        activityPlace_2.setDescription("mauna kea observatory");
        activityPlace_2.setX_axis(19.823348);
        activityPlace_2.setY_axis(-155.472450);
        activityPlace_2.setComfort(5);

        TouristicSiteDao activityPlace_6 = new ActivityPlaceDao();
        activityPlace_2.setDescription("kohala mountain");
        activityPlace_2.setX_axis(20.0850956);
        activityPlace_2.setY_axis(-155.7189817);
        activityPlace_2.setComfort(5);

        HotelDao hotel_1 = HotelDao.builder()
                .beach("wild seashore")
                .comfort(20)
                .latitude(19.1329063)
                .longitude(-155.5124336)
                .price(144)
                .build();

        HotelDao hotel_2 = HotelDao.builder()
                .beach("anaeho omalu beach")
                .comfort(45)
                .latitude(19.9155972)
                .longitude(-155.8957128)
                .price(287)
                .build();

        HotelDao hotel_3 = HotelDao.builder()
                .beach("pauoa bay")
                .comfort(60)
                .latitude(19.9507468)
                .longitude(-155.8656309)
                .price(371)
                .build();

        TransportDao bus = new BusDao();
        bus.setComfort(1);
        bus.setPrice(3);

        TransportDao boat = new BoatDao();
        boat.setComfort(6);
        boat.setPrice(25);

        Transaction transaction = session.beginTransaction();
        session.persist(activityPlace_1);
        session.persist(activityPlace_2);
        session.persist(activityPlace_3);
        session.persist(activityPlace_4);
        session.persist(activityPlace_5);
        session.persist(activityPlace_6);

        session.persist(hotel_1);
        session.persist(hotel_2);
        session.persist(hotel_3);

        session.persist(bus);

        transaction.commit();
        session.close();


        }

    }
