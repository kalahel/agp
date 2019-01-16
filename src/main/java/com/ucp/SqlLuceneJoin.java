package com.ucp;

import com.ucp.dao.TouristicSiteDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Allows to join the SQL and Lucene results with an SQL query
 */
public class SqlLuceneJoin {
    final static Logger logger = LogManager.getLogger(SqlLuceneJoin.class);

    /**
     * Joins the sql result with the lucene result.
     * Sorts the result by score in decreasing order.
     *
     * @param whereCondition SQL condition applied to the SQL DB
     * @param searchWords    Lucene keywords
     * @return List of Touristic Sites (and their documents) according to the query
     * @throws IOException    From Lucene operator
     * @throws ParseException From text parsing
     */
    public static List<TouristicSiteJoined> sqlJoinLucene(String whereCondition, String searchWords) throws IOException, ParseException {
        List<TouristicSiteJoined> resultList = new ArrayList<>();

        SqlIterator sqlIterator = new SqlIterator();


        sqlIterator.init(whereCondition);


        while (sqlIterator.hasNext()) {
            TouristicSiteDao currentSite = sqlIterator.next();

            LuceneIterator luceneIterator = new LuceneIterator();
            luceneIterator.init(searchWords);


            while (luceneIterator.hasNext()) {
                DocumentResult documentResult = luceneIterator.next();

                // FIXME CHECK TO FIND BETTER WAY FOR THE REGEX
                if (Integer.toString(currentSite.getId())
                        .equals(documentResult.getDocument().
                                getField("nom").toString().replaceAll("[^0-9]", ""))) {
                    resultList.add(new TouristicSiteJoined(currentSite, documentResult));
                    break;

                }
            }
        }
        Collections.sort(resultList);

        return resultList;
    }

    /**
     * Parses a mixed query and calls sqlJoinLucene() with the two separated parts of it.
     *
     * @param query Mixed SQL/Text query. Only passed from the where condition (implied "SELECT * FROM placeholderdatastorable")
     * @return List of Touristic Sites (and their documents) according to the query
     * @throws IOException    From Lucene operator
     * @throws ParseException From text parsing
     */
    public static List<TouristicSiteJoined> sqlJoinLuceneQuery(String query) throws IOException, ParseException {
        String withQuery = "";
        String sqlQuery = "";
        String[] queryDecomposed;
        // FIXME CAUSE PROBLEMS WITH DESCRIPTION NOT IN LOWERCASE
        queryDecomposed = query.toLowerCase().split("with ");
        sqlQuery = queryDecomposed[0];
        withQuery = queryDecomposed[1];

        return SqlLuceneJoin.sqlJoinLucene(sqlQuery, withQuery);
    }
}
