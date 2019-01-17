package com.ucp.dao;

import com.ucp.business.data.Model.Bus;
import com.ucp.business.data.Model.Transport;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
@Data
@NoArgsConstructor
public class BusDao extends TransportDao {

    public BusDao(Bus bus) {
        this.price = bus.getPrice();
        this.comfort = bus.getComfort();
    }

    @Override
    public Transport generateTransport() {
        return new Bus(this.price, this.comfort);
    }
}
