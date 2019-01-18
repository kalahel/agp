package com.ucp.business.data.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Bus extends Transport {
    public Bus(double price, double comfort) {
        super(price, comfort);
    }

    @Override
    public double returnScaledComfort() {
        if (this.getComfort() < 20) return 1;
        if (this.getComfort() < 40) return 2;
        if (this.getComfort() < 60) return 3;
        if (this.getComfort() < 80) return 4;
        else return 5;
    }
}
