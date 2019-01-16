package com.ucp.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
