package com.ucp.dao;

import com.ucp.business.data.Model.ActivityPlace;
import com.ucp.business.data.Model.Coordinates;
import com.ucp.business.data.Model.TouristicSite;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
@Data
@NoArgsConstructor
public class ActivityPlaceDao extends TouristicSiteDao {

    public ActivityPlaceDao(ActivityPlace activityPlace) {
        this.description = activityPlace.getDescription();
        this.x_axis = activityPlace.getCoordinates().getX_axis();
        this.y_axis = activityPlace.getCoordinates().getY_axis();
        this.comfort = activityPlace.getComfort();
    }

    @Override
    public TouristicSite generateTouristicSite() {
        return new ActivityPlace(this.description, new Coordinates(this.x_axis, this.y_axis), this.comfort);
    }
}
