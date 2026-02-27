package com.swapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather{

	@JsonProperty("current")
	private Current current;

	@JsonProperty("location")
	private Location location;

	public void setCurrent(Current current){
		this.current = current;
	}

	public Current getCurrent(){
		return current;
	}

	public void setLocation(Location location){
		this.location = location;
	}

	public Location getLocation(){
		return location;
	}

	@Override
 	public String toString(){
		return 
			"Weather{" + 
			"current = '" + current + '\'' + 
			",location = '" + location + '\'' + 
			"}";
		}
}