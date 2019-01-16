package com.ucp;

import com.ucp.dao.TouristicSiteDao;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TouristicSiteJoined implements Comparable<TouristicSiteJoined> {
    private TouristicSiteDao touristicSiteDao;
    private DocumentResult documentResult;

    @Override
    public int compareTo(TouristicSiteJoined comparedSite) {
        if (comparedSite.documentResult.getScore() > this.documentResult.getScore())
            return 1;
        return -1;
    }
}
