package com.ucp.business.data.Model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stay {
    private double price;
    private List<Day> daysList;
    private Hotel hotel;
    private double comfort;

    public void addDay(Day day) {
        daysList.add(day);
    }

    public static class StayBuilder {

        public StayBuilder computePrice() {
            for (Day day : this.daysList) {
                if (day.getExcursion() != null) {
                    this.price += day.getExcursion().getExcursionPrice();

                } else {
                    this.price += this.hotel.getPrice();
                }
            }
            return this;
        }

        public StayBuilder computeComfort(){
            for (Day day : this.daysList) {
                if(day.getExcursion() != null){
                    this.comfort += day.getComfort();
                }else{
                    this.comfort += this.hotel.getComfort();
                }
            }
            this.comfort=comfort/this.daysList.size();
            return this;
        }

        public StayBuilder setDays(Day... days) {
            this.daysList = new ArrayList<Day>();
            for (Day day : days) {
                this.daysList.add(day);
            }
            return this;
        }

        public StayBuilder setDays(ArrayList<Day> days) {
            this.daysList = days;
            return this;
        }
    }
}
