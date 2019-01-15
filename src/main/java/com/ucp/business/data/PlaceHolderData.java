package com.ucp.business.data;

import lombok.Data;

import javax.inject.Named;


@Named
@Data
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
