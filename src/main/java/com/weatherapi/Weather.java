package com.weatherapi;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The root container class for the weather API response.
 * It holds both the geographical information and the current meteorological data.
 *
 * @author yyang22
 */
public class Weather{

	@JsonProperty("current")
	private Current current;

	@JsonProperty("location")
	private Location location;

	/**
	 * Sets the current weather conditions.
	 * @param current The {@link Current} object containing detailed weather metrics.
	 */
	public void setCurrent(Current current){
		this.current = current;
	}

	/**
	 * Gets the current weather conditions.
	 * @return The {@link Current} object containing detailed weather metrics.
	 */
	public Current getCurrent(){
		return current;
	}

	/**
	 * Sets the geographical location information.
	 * @param location The {@link Location} object containing coordinates and timezone data.
	 */
	public void setLocation(Location location){
		this.location = location;
	}

	/**
	 * Gets the geographical location information.
	 * @return The {@link Location} object containing coordinates and timezone data.
	 */
	public Location getLocation(){
		return location;
	}

	/**
	 * Returns a string representation of the entire Weather response.
	 * @return A string containing current weather and location details.
	 */
	@Override
 	public String toString(){
		return 
			"Weather{" + 
			"current = '" + current + '\'' + 
			",location = '" + location + '\'' + 
			"}";
		}
}