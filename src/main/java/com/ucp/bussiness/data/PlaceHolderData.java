package com.ucp.bussiness.data;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.Table;


@Named
public class PlaceHolderData {
    private String holderName;
    private int holderValue;

    public PlaceHolderData() {
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
}
