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

        TouristicSiteDao historicPlaceDao = new ActivityPlaceDao();
        historicPlaceDao.setDescription("waikiki beach");
        historicPlaceDao.setX_axis(21.280651);
        historicPlaceDao.setY_axis(-157.837204);
        historicPlaceDao.setComfort(20);





        TouristicSiteDao historicPlaceDao2 = new HistoricPlaceDao();
        historicPlaceDao2.setDescription("plage");
        historicPlaceDao2.setX_axis(10);
        historicPlaceDao2.setY_axis(10);

        Transaction transaction = session.beginTransaction();
        session.persist(historicPlaceDao);
        session.persist(historicPlaceDao2);
        transaction.commit();
        session.close();


        }

    }
