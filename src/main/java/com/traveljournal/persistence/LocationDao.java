package com.traveljournal.persistence;

import com.traveljournal.entity.Location;

public class LocationDao extends GenericDao<Location> {
    public LocationDao() {
        super(Location.class);
    }
}