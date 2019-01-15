package com.ucp;

import com.ucp.dao.TouristicSiteDao;
import com.ucp.hibernate.DBConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SqlIterator {
    private int currentIndex;
    private int numberOfResult;
    private List<TouristicSiteDao> results;

    public SqlIterator() {
        this.currentIndex = 0;
        this.numberOfResult = 0;
        this.results = null;
    }

    /**
     * Initialize list of touristic site
     * @param whereCondition Where condition, must be started by "with"
     */
    public void init(String whereCondition) {
        // TODO ADJUST WITHOUT LOADING ALL THE DATA
        DBConnection dbConnection = new DBConnection();
        Session session = dbConnection.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        this.results = session.createSQLQuery("SELECT * FROM touristicsitedao " + whereCondition).addEntity(TouristicSiteDao.class).list();
        this.numberOfResult = results.size();
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
