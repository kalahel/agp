package com.ucp.dao;

import com.ucp.business.data.Model.Coordinates;
import com.ucp.business.data.Model.HistoricPlace;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
@Data
@NoArgsConstructor
public class HistoricPlaceDao extends TouristicSiteDao{

    public HistoricPlaceDao(HistoricPlace historicPlace) {
        this.description = historicPlace.getDescription();
        this.x_axis = historicPlace.getCoordinates().getX_axis();
        this.y_axis = historicPlace.getCoordinates().getY_axis();
        this.comfort = historicPlace.getComfort();
    }

    public HistoricPlace generateHistoricPlace(){
        return new HistoricPlace(this.description,new Coordinates(this.x_axis, this.y_axis), this.comfort);
    }
}
