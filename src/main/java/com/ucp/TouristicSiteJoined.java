package com.ucp;

import com.ucp.dao.TouristicSiteDao;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TouristicSiteJoined {
    private TouristicSiteDao touristicSiteDao;
    private DocumentResult documentResult;
}
