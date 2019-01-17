package com.ucp.PropositionEngine;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PropositionCriterias {
    protected int stayDuration;
    private int comfort;
    private int chillDays;
    private int averageActivitiesPerDay;
    private int maxBudget;
    private String criterias;
}
