package com.ucp.dao;

import com.ucp.business.data.Model.Transport;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.INTEGER)
@Data
public abstract class TransportDao {
    @Id
    @GeneratedValue
    int id;
    protected double price;
    protected double comfort;

    abstract public Transport generateTransport();
}
