package com.ucp.dao;

import com.ucp.business.data.Model.Transport;
import lombok.Data;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.INTEGER)
@Data
public abstract class TransportDao {
    protected double price;
    protected double comfort;
    @Id
    @GeneratedValue
    int id;

    abstract public Transport generateTransport();
}
