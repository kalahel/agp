package com.ucp.business.data.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class Day {
    private int dailyTransportLimitation = 4;
    //Can be null if no excursion has been planned on this day.
    private Excursion excursion;
    public double getComfort(){
        if(this.getExcursion() == null) return 0;
        double comfort = 0.0;
        int factorOfComfort = 1;
        System.out.println("TRANSPORT SIZE " +this.getExcursion().getTransports().size());
        System.out.println("DAILY TRAINSPORT LIM " +this.dailyTransportLimitation);

        if(this.getExcursion().getTransports().size() > this.dailyTransportLimitation) factorOfComfort = -1;
        System.out.println("FACTOR OF COMFORT " +factorOfComfort);
        for(Transport t : this.getExcursion().getTransports()){
            comfort += t.getComfort() * factorOfComfort;
        }
        comfort += this.getExcursion().getComfort() + this.getExcursion().getHotel().getComfort();
        return comfort;
    }
}
