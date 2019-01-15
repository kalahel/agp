package com.ucp;

import com.ucp.dao.TouristicSiteDao;
import com.ucp.hibernate.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

public class SqlIterator {
    final static Logger logger = LogManager.getLogger(SqlIterator.class);

    private int currentIndex;
    private int numberOfResult;
    private List<TouristicSiteDao> results;

    public SqlIterator() {
        this.currentIndex = 0;
        this.numberOfResult = 0;
        this.results = null;
    }

    public void init() {
        // TODO ADJUST WITHOUT LOADING ALL THE DATA
        DBConnection dbConnection = new DBConnection();
        Session session = dbConnection.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        this.results = session.createSQLQuery("SELECT * FROM `placeholderdatastorable` ").addEntity(TouristicSiteDao.class).list();
        transaction.commit();
        session.close();
    }

    public TouristicSiteDao next() {
        TouristicSiteDao resultSite = this.results.get(this.currentIndex);

        if (this.currentIndex >= this.numberOfResult) {
            return null;
        }
        this.currentIndex++;
        return resultSite;
    }

    public boolean hasNext() {
        return this.currentIndex < this.numberOfResult;
    }


}
