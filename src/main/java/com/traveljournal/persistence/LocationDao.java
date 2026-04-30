package com.traveljournal.persistence;

import com.traveljournal.entity.Location;

/**
 * Data Access Object for Location entities.
 *
 * @author yyang22
 */
public class LocationDao extends GenericDao<Location> {
    /**
     * Instantiates a new Location DAO by passing the Location class
     * type to the superclass constructor.
     */
    public LocationDao() {
        super(Location.class);
    }
}