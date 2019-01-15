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
    public void addDay(Day day){
        daysList.add(day);
    }
    public static class StayBuilder{
        //FIXME WRONG RESULT
        public StayBuilder computePrice(){
            System.out.println("COMPUTE PRICE STAY");
            double price = 0;
            for(Day day : this.daysList){
                if(day.getExcursion() != null){
                    this.price += day.getExcursion().getExcursionPrice();
                    System.out.println("Hotel : "+this.hotel.toString());
                    System.out.println("Excursion today, global cost is : "+day.getExcursion().getExcursionPrice());
                }else {
                    this.price += this.hotel.getPrice();
                    System.out.println("Hotel : "+this.hotel.toString());
                    System.out.println("No excursion this day. Price is hotel cost : "+this.hotel.getPrice());
                }
            }
            return this;
        }

        public StayBuilder setDays(Day... days){
            this.daysList = new ArrayList<Day>();
            for(Day day : days){
                this.daysList.add(day);
            }
            return this;
        }
    }
}
