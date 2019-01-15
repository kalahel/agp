package com.ucp.business.data.Model;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stay {
    private double price;
    private List<Day> daysList;
    public void addDay(Day day){
        daysList.add(day);
    }
}
