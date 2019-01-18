package com.ucp.dao;

import com.ucp.business.data.Model.Beach;
import com.ucp.business.data.Model.Coordinates;
import com.ucp.business.data.Model.Hotel;
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
    protected double price;
    protected double longitude;
    protected double latitude;
    protected String beach;
    protected double comfort;
    @Id
    @GeneratedValue
    int id;

    public HotelDao(Hotel hotel) {
        this.price = hotel.getPrice();
        this.longitude = hotel.getCoordinates().getX_axis();
        this.latitude = hotel.getCoordinates().getY_axis();
        this.beach = hotel.getBeach().getName();
        this.comfort = hotel.getComfort();
    }

    public Hotel generateHotel() {
        return new Hotel(this.price, new Coordinates(this.longitude, this.latitude), new Beach(this.beach), this.comfort);
    }
}
