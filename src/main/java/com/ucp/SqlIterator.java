package com.ucp;

import com.ucp.dao.TouristicSiteDao;
import com.ucp.hibernate.DBConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Retrieves the results from an SQL query, stores and iterates on them.
 */
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
     * Initializes the iterator and loads a result from an SQL result list.
     *
     * @param whereCondition WHERE condition. Either empty, or must start with "WHERE".
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

    /**
     * @return Next entry in the results.
     */
    public TouristicSiteDao next() {
        TouristicSiteDao resultSite = this.results.get(this.currentIndex);

        if (this.currentIndex >= this.numberOfResult) {
            return null;
        }
        this.currentIndex++;
        return resultSite;
    }

    /**
     * @return True if the result list has at least one more result, False if it doesn't.
     */
    public boolean hasNext() {
        return this.currentIndex < this.numberOfResult;
    }


}
