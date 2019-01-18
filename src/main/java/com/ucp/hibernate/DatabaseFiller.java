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

        TouristicSiteDao activityPlace_3 = new HistoricPlaceDao();
        activityPlace_3.setDescription("volcano village");
        activityPlace_3.setX_axis(19.444224);
        activityPlace_3.setY_axis(-155.230330);
        activityPlace_3.setComfort(10);

        TouristicSiteDao activityPlace_4 = new ActivityPlaceDao();
        activityPlace_4.setDescription("kilauea volcano");
        activityPlace_4.setX_axis(19.406240);
        activityPlace_4.setY_axis(-155.283520);
        activityPlace_4.setComfort(5);

        TouristicSiteDao activityPlace_5 = new ActivityPlaceDao();
        activityPlace_5.setDescription("mauna kea observatory");
        activityPlace_5.setX_axis(19.823348);
        activityPlace_5.setY_axis(-155.472450);
        activityPlace_5.setComfort(5);

        TouristicSiteDao activityPlace_6 = new ActivityPlaceDao();
        activityPlace_6.setDescription("kohala mountain");
        activityPlace_6.setX_axis(20.0850956);
        activityPlace_6.setY_axis(-155.7189817);
        activityPlace_6.setComfort(5);

        TouristicSiteDao activityPlace_7 = new ActivityPlaceDao();
        activityPlace_6.setDescription("halaula");
        activityPlace_6.setX_axis(20.2298897);
        activityPlace_6.setY_axis(-155.7998821);
        activityPlace_6.setComfort(25);

        TouristicSiteDao activityPlace_8 = new HistoricPlaceDao();
        activityPlace_6.setDescription("lapakahi historical park");
        activityPlace_6.setX_axis(20.174977);
        activityPlace_6.setY_axis(-155.8990697);
        activityPlace_6.setComfort(25);

        TouristicSiteDao activityPlace_9 = new ActivityPlaceDao();
        activityPlace_6.setDescription("hanauma bay");
        activityPlace_6.setX_axis(19.2552451);
        activityPlace_6.setY_axis(-155.3057676);
        activityPlace_6.setComfort(30);

        TouristicSiteDao activityPlace_10 = new ActivityPlaceDao();
        activityPlace_6.setDescription("south point cliff dive");
        activityPlace_6.setX_axis(18.914793);
        activityPlace_6.setY_axis(-155.68309);
        activityPlace_6.setComfort(30);

        TouristicSiteDao activityPlace_11 = new ActivityPlaceDao();
        activityPlace_6.setDescription("kau forest reserve");
        activityPlace_6.setX_axis(19.1619495);
        activityPlace_6.setY_axis(-155.6431842);
        activityPlace_6.setComfort(35);

        TouristicSiteDao activityPlace_12 = new ActivityPlaceDao();
        activityPlace_6.setDescription("kipahoehoe");
        activityPlace_6.setX_axis(19.276093);
        activityPlace_6.setY_axis(-155.8500081);
        activityPlace_6.setComfort(30);

        TouristicSiteDao activityPlace_13 = new HistoricPlaceDao();
        activityPlace_6.setDescription("pu uhonua o honaunau");
        activityPlace_6.setX_axis(19.4213948);
        activityPlace_6.setY_axis(-155.9111015);
        activityPlace_6.setComfort(50);



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

        HotelDao hotel_4 = HotelDao.builder()
                .beach("kohala lodge beach")
                .comfort(80)
                .latitude(20.212477)
                .longitude(-155.845040)
                .price(529)
                .build();

        HotelDao hotel_5 = HotelDao.builder()
                .beach("waipio hostel beach")
                .comfort(10)
                .latitude(20.1034597)
                .longitude(-155.5968247)
                .price(68)
                .build();

        HotelDao hotel_6 = HotelDao.builder()
                .beach("kulaniapia falls")
                .comfort(30)
                .latitude(19.7235648)
                .longitude(-155.1633093)
                .price(249)
                .build();

        HotelDao hotel_7 = HotelDao.builder()
                .beach("wild ginger")
                .comfort(15)
                .latitude(19.717909)
                .longitude(-155.1164457)
                .price(80)
                .build();

        HotelDao hotel_8 = HotelDao.builder()
                .beach("hualaylay beach")
                .comfort(100)
                .latitude(19.8267984)
                .longitude(-155.9937483)
                .price(1000)
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
        session.persist(activityPlace_7);
        session.persist(activityPlace_8);
        session.persist(activityPlace_9);
        session.persist(activityPlace_10);
        session.persist(activityPlace_11);
        session.persist(activityPlace_12);
        session.persist(activityPlace_13);
        session.persist(hotel_1);
        session.persist(hotel_2);
        session.persist(hotel_3);
        session.persist(hotel_4);
        session.persist(hotel_5);
        session.persist(hotel_6);
        session.persist(hotel_7);
        session.persist(hotel_8);

        session.persist(bus);
        //Annoying warning but effective behavior
        session.persist(boat);

        transaction.commit();
        session.close();


        }

    }
