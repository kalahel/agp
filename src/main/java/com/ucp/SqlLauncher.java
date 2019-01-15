package com.ucp;

import com.ucp.business.data.Model.HistoricPlace;
import com.ucp.business.data.Model.TouristicSite;
import com.ucp.dao.HistoricPlaceDao;
import com.ucp.dao.TouristicSiteDao;
import com.ucp.hibernate.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SqlLauncher {
    final static Logger logger = LogManager.getLogger(SqlLauncher.class);

    public static void main(String[] args) {

        DBConnection dbConnection = new DBConnection();
        Session session = dbConnection.getSessionFactory().getCurrentSession();

        TouristicSiteDao historicPlaceDao = new HistoricPlaceDao();
        historicPlaceDao.setDescription("Montagne");
        historicPlaceDao.setX_axis(1);
        historicPlaceDao.setY_axis(1);

        TouristicSiteDao historicPlaceDao2 = new HistoricPlaceDao();
        historicPlaceDao2.setDescription("plage");
        historicPlaceDao2.setX_axis(10);
        historicPlaceDao2.setY_axis(10);

        Transaction transaction = session.beginTransaction();
        session.persist(historicPlaceDao);
        session.persist(historicPlaceDao2);
        transaction.commit();
        session.close();

        // Iterator example
        SqlIterator sqlIterator = new SqlIterator();
        sqlIterator.init("where description='Montagne'");
        while (sqlIterator.hasNext()){
            TouristicSiteDao touristicSite = sqlIterator.next();
            System.out.println(touristicSite.getDescription());
        }

    }
}
