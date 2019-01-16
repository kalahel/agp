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
}
