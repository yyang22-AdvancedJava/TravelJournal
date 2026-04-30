package com.weatherapi;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the specific weather condition details.
 * This class captures the textual description, numeric code, and icon URL
 * associated with the current weather state as provided by the WeatherAPI.
 *
 * @author yyang22
 */
public class Condition{

	@JsonProperty("code")
	private int code;

	@JsonProperty("icon")
	private String icon;

	@JsonProperty("text")
	private String text;

	/**
	 * Sets the unique condition code.
	 * @param code the numeric weather condition code
	 */
	public void setCode(int code){
		this.code = code;
	}

	/**
	 * Gets the unique condition code.
	 * @return the numeric weather condition code
	 */
	public int getCode(){
		return code;
	}

	/**
	 * Sets the URL or path for the weather condition icon.
	 * @param icon the weather icon string
	 */
	public void setIcon(String icon){
		this.icon = icon;
	}

	/**
	 * Gets the URL or path for the weather condition icon.
	 * @return the weather icon string
	 */
	public String getIcon(){
		return icon;
	}

	/**
	 * Sets the textual description of the weather (e.g., "Sunny", "Partly cloudy").
	 * @param text the condition description text
	 */
	public void setText(String text){
		this.text = text;
	}

	/**
	 * Gets the textual description of the weather.
	 * @return the condition description text
	 */
	public String getText(){
		return text;
	}

	/**
	 * Returns a string representation of the weather condition.
	 * @return a formatted string containing code, icon, and text attributes
	 */
	@Override
 	public String toString(){
		return 
			"Condition{" + 
			"code = '" + code + '\'' + 
			",icon = '" + icon + '\'' + 
			",text = '" + text + '\'' + 
			"}";
		}
}