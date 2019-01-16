package com.ucp.dao;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@Data
public class HotelDao {
    @Id
    @GeneratedValue
    int id;
    protected double price;
    protected double longitude;
    protected double latitude;
    protected String beach;
    protected double comfort;
}
