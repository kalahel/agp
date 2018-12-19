package com.ucp.bussiness.factories;

import com.ucp.bussiness.data.PlaceHolderData;

import javax.inject.Named;

@Named
public class PlaceHolderFactory {
    public PlaceHolderFactory() {
    }

    public PlaceHolderData createData(String name, int value) {
        PlaceHolderData placeHolderData = new PlaceHolderData();
        placeHolderData.setHolderName(name);
        placeHolderData.setHolderValue(value);
        return placeHolderData;
    }
}
