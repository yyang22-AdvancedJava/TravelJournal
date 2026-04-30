package com.weatherapi;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the geographical location information for the weather data.
 * Includes details such as coordinates, time zone, and local time.
 *
 * @author yyang22
 */
public class Location{

	@JsonProperty("localtime")
	private String localtime;

	@JsonProperty("country")
	private String country;

	@JsonProperty("localtime_epoch")
	private int localtimeEpoch;

	@JsonProperty("name")
	private String name;

	@JsonProperty("lon")
	private Object lon;

	@JsonProperty("region")
	private String region;

	@JsonProperty("lat")
	private Object lat;

	@JsonProperty("tz_id")
	private String tzId;

	/**
	 * Sets the local time of the location.
	 * @param localtime The local time as a formatted string.
	 */
	public void setLocaltime(String localtime){
		this.localtime = localtime;
	}

	/**
	 * Gets the local time of the location.
	 * @return The local time as a formatted string.
	 */
	public String getLocaltime(){
		return localtime;
	}

	/**
	 * Sets the country name.
	 * @param country The name of the country.
	 */
	public void setCountry(String country){
		this.country = country;
	}

	/**
	 * Gets the country name.
	 * @return The name of the country.
	 */
	public String getCountry(){
		return country;
	}

	/**
	 * Sets the local time in Unix Epoch format.
	 * @param localtimeEpoch The local time as an epoch integer.
	 */
	public void setLocaltimeEpoch(int localtimeEpoch){
		this.localtimeEpoch = localtimeEpoch;
	}

	/**
	 * Gets the local time in Unix Epoch format.
	 * @return The local time as an epoch integer.
	 */
	public int getLocaltimeEpoch(){
		return localtimeEpoch;
	}

	/**
	 * Sets the name of the location (e.g., city name).
	 * @param name The location name.
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Gets the name of the location (e.g., city name).
	 * @return The location name.
	 */
	public String getName(){
		return name;
	}

	/**
	 * Sets the longitude coordinate of the location.
	 * @param lon The longitude value.
	 */
	public void setLon(Object lon){
		this.lon = lon;
	}

	/**
	 * Gets the longitude coordinate of the location.
	 * @return The longitude value.
	 */
	public Object getLon(){
		return lon;
	}

	/**
	 * Sets the region or state of the location.
	 * @param region The region name.
	 */
	public void setRegion(String region){
		this.region = region;
	}

	/**
	 * Gets the region or state of the location.
	 * @return The region name.
	 */
	public String getRegion(){
		return region;
	}

	/**
	 * Sets the latitude coordinate of the location.
	 * @param lat The latitude value.
	 */
	public void setLat(Object lat){
		this.lat = lat;
	}

	/**
	 * Gets the latitude coordinate of the location.
	 * @return The latitude value.
	 */
	public Object getLat(){
		return lat;
	}

	/**
	 * Sets the Time Zone ID.
	 * @param tzId The time zone identifier (e.g., "America/Chicago").
	 */
	public void setTzId(String tzId){
		this.tzId = tzId;
	}

	/**
	 * Gets the Time Zone ID.
	 * @return The time zone identifier (e.g., "America/Chicago").
	 */
	public String getTzId(){
		return tzId;
	}

	/**
	 * Returns a string representation of the Location object.
	 * @return A string containing geographical details.
	 */
	@Override
 	public String toString(){
		return 
			"Location{" + 
			"localtime = '" + localtime + '\'' + 
			",country = '" + country + '\'' + 
			",localtime_epoch = '" + localtimeEpoch + '\'' + 
			",name = '" + name + '\'' + 
			",lon = '" + lon + '\'' + 
			",region = '" + region + '\'' + 
			",lat = '" + lat + '\'' + 
			",tz_id = '" + tzId + '\'' + 
			"}";
		}
}