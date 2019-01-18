package com.ucp.business.data;

import com.ucp.SqlIterator;
import com.ucp.dao.HistoricPlaceDao;
import com.ucp.dao.TouristicSiteDao;
import com.ucp.hibernate.DBConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class sqlLauncherTest {

    @Test
    public void connectionTest() throws Exception {
        try {
            DBConnection dbConnection = new DBConnection();
            Session session = dbConnection.getSessionFactory().getCurrentSession();

            TouristicSiteDao historicPlaceDao = new HistoricPlaceDao();
            historicPlaceDao.setDescription("Caverne du Gange");
            historicPlaceDao.setX_axis(1);
            historicPlaceDao.setY_axis(1);

            Transaction transaction = session.beginTransaction();
            session.persist(historicPlaceDao);
            transaction.commit();
            session.close();

            SqlIterator sqlIterator = new SqlIterator();
            TouristicSiteDao touristicSite = new HistoricPlaceDao();
            sqlIterator.init("where description='Caverne du Gange'");
            while (sqlIterator.hasNext()) {
                touristicSite = sqlIterator.next();
            }
            assertEquals(touristicSite, historicPlaceDao);

            Session session1 = dbConnection.getSessionFactory().getCurrentSession();
            Transaction transaction1 = session1.beginTransaction();
            session1.remove(historicPlaceDao);
            transaction1.commit();

        } catch (Exception e) {
            System.err.println("[Error connection] :" + e);
        }

    }
}
