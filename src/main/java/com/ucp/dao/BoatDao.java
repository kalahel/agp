package com.ucp.dao;

import com.ucp.business.data.Model.Boat;
import com.ucp.business.data.Model.Bus;
import com.ucp.business.data.Model.Transport;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
@Data
@NoArgsConstructor
public class BoatDao extends TransportDao {
    public BoatDao(Boat boat) {
        this.price = boat.getPrice();
        this.comfort = boat.getComfort();
    }

    @Override
    public Transport generateTransport() {
        return new Boat(this.price, this.comfort);
    }
}
