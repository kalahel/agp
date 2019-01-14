package com.ucp.business.data;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stay {
    private double price;
    private List<Day> daysList;
}
