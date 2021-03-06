package com.ucp.business.data.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Transport implements Comfort {
    protected double price;
    protected double comfort;
}
