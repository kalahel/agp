package com.ucp;

import com.ucp.dao.TouristicSiteDao;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Aggregation of the two classes TouristicSiteDao and DocumentResult. Useful for the DB join.
 */
@Data
@AllArgsConstructor
public class TouristicSiteJoined implements Comparable<TouristicSiteJoined> {
    private TouristicSiteDao touristicSiteDao;
    private DocumentResult documentResult;

    /**
     * Overrides compareTo method from the Comparable interface
     *
     * @param comparedSite The TouristicSiteJoined we want to compare our object with
     * @return 1 if the compared site has a greater score, -1 if our site has a greater score, 0 if the scores are equal
     */
    @Override
    public int compareTo(TouristicSiteJoined comparedSite) {
        if (comparedSite.documentResult.getScore() > this.documentResult.getScore())
            return 1;
        else if (comparedSite.documentResult.getScore() == this.documentResult.getScore())
            return 0;
        return -1;
    }
}
