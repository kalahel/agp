package com.ucp.hibernate;

import com.ucp.business.data.PlaceHolderData;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PlaceHolderDataStorable {

    @Id
    @GeneratedValue
    int id;

    private String holderName;

    private int holderValue;

    public PlaceHolderDataStorable() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public int getHolderValue() {
        return holderValue;
    }

    public void setHolderValue(int holderValue) {
        this.holderValue = holderValue;
    }

    public void setAll(PlaceHolderData placeHolderData){
        this.setHolderName(placeHolderData.getHolderName());
        this.setHolderValue(placeHolderData.getHolderValue());
    }
}
