package com.ucp.dao;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
@Data
public class HistoricPlaceDao extends TouristicSiteDao{
}
