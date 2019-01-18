package com.ucp.dao;

import com.ucp.business.data.Model.TouristicSite;
import lombok.Data;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.INTEGER)
@Data
public abstract class TouristicSiteDao {
    protected String description;
    protected double x_axis;
    protected double y_axis;
    protected double comfort;
    @Id
    @GeneratedValue
    int id;     // TODO CHECK ACCESS REQUIREMENT

    public abstract TouristicSite generateTouristicSite();
}
