package com.ucp.dao;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.INTEGER)
@Data
public class TransportDao {
    @Id
    @GeneratedValue
    int id;
    protected double price;
    protected double comfort;
}
