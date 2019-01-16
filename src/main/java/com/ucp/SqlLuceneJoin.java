package com.ucp;

import com.ucp.business.data.Model.TouristicSite;
import com.ucp.dao.TouristicSiteDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SqlLuceneJoin {
    final static Logger logger = LogManager.getLogger(SqlLuceneJoin.class);


    // TODO ADD SQL DECOMPOSITION METHODS

    /**
     * Join the sql result with the lucene result
     *
     * @param whereCondition
     * @param searchWords
     * @return
     * @throws IOException
     * @throws ParseException
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

        return resultList;
    }

    /**
     * @param query Only passed from the where condition
     *              elude "SELECT * FROM placeholderdatastorable"
     * @return
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
