package com.weatherapi;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the current weather conditions returned by the Weather API.
 *
 * @author yyang22
 */
public class Current{

	/** Feels like temperature in Celsius. */
	@JsonProperty("feelslike_c")
	private Object feelslikeC;

	/** Feels like temperature in Fahrenheit. */
	@JsonProperty("feelslike_f")
	private Object feelslikeF;

	/** Wind direction in degrees. */
	@JsonProperty("wind_degree")
	private int windDegree;

	/** Wind chill temperature in Fahrenheit. */
	@JsonProperty("windchill_f")
	private Object windchillF;

	/** Wind chill temperature in Celsius. */
	@JsonProperty("windchill_c")
	private Object windchillC;

	/** Local time when the weather data was last updated (Unix Epoch). */
	@JsonProperty("last_updated_epoch")
	private int lastUpdatedEpoch;

	/** Temperature in Celsius. */
	@JsonProperty("temp_c")
	private Object tempC;

	/** Temperature in Fahrenheit. */
	@JsonProperty("temp_f")
	private Object tempF;

	/** Cloud cover as a percentage (%). */
	@JsonProperty("cloud")
	private int cloud;

	/** Wind speed in kilometers per hour. */
	@JsonProperty("wind_kph")
	private Object windKph;

	/** Wind speed in miles per hour. */
	@JsonProperty("wind_mph")
	private Object windMph;

	/** Humidity level as a percentage (%). */
	@JsonProperty("humidity")
	private int humidity;

	/** Dew point in Fahrenheit. */
	@JsonProperty("dewpoint_f")
	private Object dewpointF;

	/** Global Tilt Irradiance (GTI) index. */
	@JsonProperty("gti")
	private int gti;

	/** Direct Normal Irradiance (DNI) index. */
	@JsonProperty("dni")
	private int dni;

	/** Ultraviolet (UV) Index. */
	@JsonProperty("uv")
	private Object uv;

	/** Local time when the weather data was last updated (String format). */
	@JsonProperty("last_updated")
	private String lastUpdated;

	/** Heat index in Fahrenheit. */
	@JsonProperty("heatindex_f")
	private Object heatindexF;

	/** Dew point in Celsius. */
	@JsonProperty("dewpoint_c")
	private Object dewpointC;

	/** Diffuse Horizontal Irradiance index. */
	@JsonProperty("diff_rad")
	private int diffRad;

	/** Indicates if it is currently day (1) or night (0). */
	@JsonProperty("is_day")
	private int isDay;

	/** Precipitation amount in inches. */
	@JsonProperty("precip_in")
	private Object precipIn;

	/** Heat index in Celsius. */
	@JsonProperty("heatindex_c")
	private Object heatindexC;

	/** Wind direction as a compass point (e.g., "NW"). */
	@JsonProperty("wind_dir")
	private String windDir;

	/** Wind gust speed in miles per hour. */
	@JsonProperty("gust_mph")
	private Object gustMph;

	/** Atmospheric pressure in inches. */
	@JsonProperty("pressure_in")
	private Object pressureIn;

	/** Wind gust speed in kilometers per hour. */
	@JsonProperty("gust_kph")
	private Object gustKph;

	/** Precipitation amount in millimeters. */
	@JsonProperty("precip_mm")
	private Object precipMm;

	/** Shortwave Radiation index. */
	@JsonProperty("short_rad")
	private int shortRad;

	/** Weather condition object containing descriptive text and icons. */
	@JsonProperty("condition")
	private Condition condition;

	/** Visibility in kilometers. */
	@JsonProperty("vis_km")
	private Object visKm;

	/** Atmospheric pressure in millibars (hectopascals). */
	@JsonProperty("pressure_mb")
	private Object pressureMb;

	/** Visibility in miles. */
	@JsonProperty("vis_miles")
	private Object visMiles;

	/**
	 * Sets the feels-like temperature in Celsius.
	 * @param feelslikeC The feels-like temperature in Celsius.
	 */
	public void setFeelslikeC(Object feelslikeC){
		this.feelslikeC = feelslikeC;
	}

	/**
	 * Gets the feels-like temperature in Celsius.
	 * @return The feels-like temperature in Celsius.
	 */
	public Object getFeelslikeC(){
		return feelslikeC;
	}

	/**
	 * Sets the feels-like temperature in Fahrenheit.
	 * @param feelslikeF The feels-like temperature in Fahrenheit.
	 */
	public void setFeelslikeF(Object feelslikeF){
		this.feelslikeF = feelslikeF;
	}

	/**
	 * Gets the feels-like temperature in Fahrenheit.
	 * @return The feels-like temperature in Fahrenheit.
	 */
	public Object getFeelslikeF(){
		return feelslikeF;
	}

	/**
	 * Sets the wind direction in degrees.
	 * @param windDegree The wind direction in degrees.
	 */
	public void setWindDegree(int windDegree){
		this.windDegree = windDegree;
	}

	/**
	 * Gets the wind direction in degrees.
	 * @return The wind direction in degrees.
	 */
	public int getWindDegree(){
		return windDegree;
	}

	/**
	 * Sets the wind chill temperature in Fahrenheit.
	 * @param windchillF The wind chill in Fahrenheit.
	 */
	public void setWindchillF(Object windchillF){
		this.windchillF = windchillF;
	}

	/**
	 * Gets the wind chill temperature in Fahrenheit.
	 * @return The wind chill in Fahrenheit.
	 */
	public Object getWindchillF(){
		return windchillF;
	}

	/**
	 * Sets the wind chill temperature in Celsius.
	 * @param windchillC The wind chill in Celsius.
	 */
	public void setWindchillC(Object windchillC){
		this.windchillC = windchillC;
	}

	/**
	 * Gets the wind chill temperature in Celsius.
	 * @return The wind chill in Celsius.
	 */
	public Object getWindchillC(){
		return windchillC;
	}

	/**
	 * Sets the time the weather data was last updated.
	 * @param lastUpdatedEpoch The last updated time in Unix Epoch format.
	 */
	public void setLastUpdatedEpoch(int lastUpdatedEpoch){
		this.lastUpdatedEpoch = lastUpdatedEpoch;
	}

	/**
	 * Gets the time the weather data was last updated.
	 * @return The last updated time in Unix Epoch format.
	 */
	public int getLastUpdatedEpoch(){
		return lastUpdatedEpoch;
	}

	/**
	 * Sets the current temperature in Celsius.
	 * @param tempC The temperature in Celsius.
	 */
	public void setTempC(Object tempC){
		this.tempC = tempC;
	}

	/**
	 * Gets the current temperature in Celsius.
	 * @return The temperature in Celsius.
	 */
	public Object getTempC(){
		return tempC;
	}

	/**
	 * Sets the current temperature in Fahrenheit.
	 * @param tempF The temperature in Fahrenheit.
	 */
	public void setTempF(Object tempF){
		this.tempF = tempF;
	}

	/**
	 * Gets the current temperature in Fahrenheit.
	 * @return The temperature in Fahrenheit.
	 */
	public Object getTempF(){
		return tempF;
	}

	/**
	 * Sets the cloud cover percentage.
	 * @param cloud The cloud cover percentage (%).
	 */
	public void setCloud(int cloud){
		this.cloud = cloud;
	}

	/**
	 * Gets the cloud cover percentage.
	 * @return The cloud cover percentage (%).
	 */
	public int getCloud(){
		return cloud;
	}

	/**
	 * Sets the wind speed in kilometers per hour.
	 * @param windKph The wind speed in kph.
	 */
	public void setWindKph(Object windKph){
		this.windKph = windKph;
	}

	/**
	 * Gets the wind speed in kilometers per hour.
	 * @return The wind speed in kph.
	 */
	public Object getWindKph(){
		return windKph;
	}

	/**
	 * Sets the wind speed in miles per hour.
	 * @param windMph The wind speed in mph.
	 */
	public void setWindMph(Object windMph){
		this.windMph = windMph;
	}

	/**
	 * Gets the wind speed in miles per hour.
	 * @return The wind speed in mph.
	 */
	public Object getWindMph(){
		return windMph;
	}

	/**
	 * Sets the humidity level percentage.
	 * @param humidity The humidity percentage (%).
	 */
	public void setHumidity(int humidity){
		this.humidity = humidity;
	}

	/**
	 * Gets the humidity level percentage.
	 * @return The humidity percentage (%).
	 */
	public int getHumidity(){
		return humidity;
	}

	/**
	 * Sets the dew point in Fahrenheit.
	 * @param dewpointF The dew point in Fahrenheit.
	 */
	public void setDewpointF(Object dewpointF){
		this.dewpointF = dewpointF;
	}

	/**
	 * Gets the dew point in Fahrenheit.
	 * @return The dew point in Fahrenheit.
	 */
	public Object getDewpointF(){
		return dewpointF;
	}

	/**
	 * Sets the Global Tilt Irradiance (GTI).
	 * @param gti The GTI value.
	 */
	public void setGti(int gti){
		this.gti = gti;
	}

	/**
	 * Gets the Global Tilt Irradiance (GTI).
	 * @return The GTI value.
	 */
	public int getGti(){
		return gti;
	}

	/**
	 * Sets the Direct Normal Irradiance (DNI).
	 * @param dni The DNI value.
	 */
	public void setDni(int dni){
		this.dni = dni;
	}

	/**
	 * Gets the Direct Normal Irradiance (DNI).
	 * @return The DNI value.
	 */
	public int getDni(){
		return dni;
	}

	/**
	 * Sets the UV index.
	 * @param uv The UV index.
	 */
	public void setUv(Object uv){
		this.uv = uv;
	}

	/**
	 * Gets the UV index.
	 * @return The UV index.
	 */
	public Object getUv(){
		return uv;
	}

	/**
	 * Sets the time the weather data was last updated.
	 * @param lastUpdated The last updated time as a formatted string.
	 */
	public void setLastUpdated(String lastUpdated){
		this.lastUpdated = lastUpdated;
	}

	/**
	 * Gets the time the weather data was last updated.
	 * @return The last updated time as a formatted string.
	 */
	public String getLastUpdated(){
		return lastUpdated;
	}

	/**
	 * Sets the heat index in Fahrenheit.
	 * @param heatindexF The heat index in Fahrenheit.
	 */
	public void setHeatindexF(Object heatindexF){
		this.heatindexF = heatindexF;
	}

	/**
	 * Gets the heat index in Fahrenheit.
	 * @return The heat index in Fahrenheit.
	 */
	public Object getHeatindexF(){
		return heatindexF;
	}

	/**
	 * Sets the dew point in Celsius.
	 * @param dewpointC The dew point in Celsius.
	 */
	public void setDewpointC(Object dewpointC){
		this.dewpointC = dewpointC;
	}

	/**
	 * Gets the dew point in Celsius.
	 * @return The dew point in Celsius.
	 */
	public Object getDewpointC(){
		return dewpointC;
	}

	/**
	 * Sets the Diffuse Horizontal Irradiance (diff_rad).
	 * @param diffRad The diffuse radiation value.
	 */
	public void setDiffRad(int diffRad){
		this.diffRad = diffRad;
	}

	/**
	 * Gets the Diffuse Horizontal Irradiance (diff_rad).
	 * @return The diffuse radiation value.
	 */
	public int getDiffRad(){
		return diffRad;
	}

	/**
	 * Sets the day/night indicator.
	 * @param isDay Indicator (1 for day, 0 for night).
	 */
	public void setIsDay(int isDay){
		this.isDay = isDay;
	}

	/**
	 * Gets the day/night indicator.
	 * @return Indicator (1 for day, 0 for night).
	 */
	public int getIsDay(){
		return isDay;
	}

	/**
	 * Sets the precipitation amount in inches.
	 * @param precipIn The precipitation in inches.
	 */
	public void setPrecipIn(Object precipIn){
		this.precipIn = precipIn;
	}

	/**
	 * Gets the precipitation amount in inches.
	 * @return The precipitation in inches.
	 */
	public Object getPrecipIn(){
		return precipIn;
	}

	/**
	 * Sets the heat index in Celsius.
	 * @param heatindexC The heat index in Celsius.
	 */
	public void setHeatindexC(Object heatindexC){
		this.heatindexC = heatindexC;
	}

	/**
	 * Gets the heat index in Celsius.
	 * @return The heat index in Celsius.
	 */
	public Object getHeatindexC(){
		return heatindexC;
	}

	/**
	 * Sets the wind direction as a compass point.
	 * @param windDir The wind direction (e.g., "SW").
	 */
	public void setWindDir(String windDir){
		this.windDir = windDir;
	}

	/**
	 * Gets the wind direction as a compass point.
	 * @return The wind direction (e.g., "SW").
	 */
	public String getWindDir(){
		return windDir;
	}

	/**
	 * Sets the maximum wind gust speed in miles per hour.
	 * @param gustMph The wind gust in mph.
	 */
	public void setGustMph(Object gustMph){
		this.gustMph = gustMph;
	}

	/**
	 * Gets the maximum wind gust speed in miles per hour.
	 * @return The wind gust in mph.
	 */
	public Object getGustMph(){
		return gustMph;
	}

	/**
	 * Sets the atmospheric pressure in inches.
	 * @param pressureIn The pressure in inches.
	 */
	public void setPressureIn(Object pressureIn){
		this.pressureIn = pressureIn;
	}

	/**
	 * Gets the atmospheric pressure in inches.
	 * @return The pressure in inches.
	 */
	public Object getPressureIn(){
		return pressureIn;
	}

	/**
	 * Sets the maximum wind gust speed in kilometers per hour.
	 * @param gustKph The wind gust in kph.
	 */
	public void setGustKph(Object gustKph){
		this.gustKph = gustKph;
	}

	/**
	 * Gets the maximum wind gust speed in kilometers per hour.
	 * @return The wind gust in kph.
	 */
	public Object getGustKph(){
		return gustKph;
	}

	/**
	 * Sets the precipitation amount in millimeters.
	 * @param precipMm The precipitation in mm.
	 */
	public void setPrecipMm(Object precipMm){
		this.precipMm = precipMm;
	}

	/**
	 * Gets the precipitation amount in millimeters.
	 * @return The precipitation in mm.
	 */
	public Object getPrecipMm(){
		return precipMm;
	}

	/**
	 * Sets the shortwave radiation index.
	 * @param shortRad The shortwave radiation value.
	 */
	public void setShortRad(int shortRad){
		this.shortRad = shortRad;
	}

	/**
	 * Gets the shortwave radiation index.
	 * @return The shortwave radiation value.
	 */
	public int getShortRad(){
		return shortRad;
	}

	/**
	 * Sets the current weather condition details (text, icon, and code).
	 * @param condition The {@link Condition} object.
	 */
	public void setCondition(Condition condition){
		this.condition = condition;
	}

	/**
	 * Gets the current weather condition details (text, icon, and code).
	 * @return The {@link Condition} object.
	 */
	public Condition getCondition(){
		return condition;
	}

	/**
	 * Sets the visibility in kilometers.
	 * @param visKm The visibility in km.
	 */
	public void setVisKm(Object visKm){
		this.visKm = visKm;
	}

	/**
	 * Gets the visibility in kilometers.
	 * @return The visibility in km.
	 */
	public Object getVisKm(){
		return visKm;
	}

	/**
	 * Sets the atmospheric pressure in millibars (hectopascals).
	 * @param pressureMb The pressure in mb.
	 */
	public void setPressureMb(Object pressureMb){
		this.pressureMb = pressureMb;
	}

	/**
	 * Gets the atmospheric pressure in millibars (hectopascals).
	 * @return The pressure in mb.
	 */
	public Object getPressureMb(){
		return pressureMb;
	}

	/**
	 * Sets the visibility in miles.
	 * @param visMiles The visibility in miles.
	 */
	public void setVisMiles(Object visMiles){
		this.visMiles = visMiles;
	}

	/**
	 * Gets the visibility in miles.
	 * @return The visibility in miles.
	 */
	public Object getVisMiles(){
		return visMiles;
	}

	/**
	 * Returns a string representation of the Current object.
	 * Includes all weather metrics and conditions for debugging purposes.
	 * @return A string containing the state of all fields.
	 */
	@Override
 	public String toString(){
		return 
			"Current{" + 
			"feelslike_c = '" + feelslikeC + '\'' + 
			",feelslike_f = '" + feelslikeF + '\'' + 
			",wind_degree = '" + windDegree + '\'' + 
			",windchill_f = '" + windchillF + '\'' + 
			",windchill_c = '" + windchillC + '\'' + 
			",last_updated_epoch = '" + lastUpdatedEpoch + '\'' + 
			",temp_c = '" + tempC + '\'' + 
			",temp_f = '" + tempF + '\'' + 
			",cloud = '" + cloud + '\'' + 
			",wind_kph = '" + windKph + '\'' + 
			",wind_mph = '" + windMph + '\'' + 
			",humidity = '" + humidity + '\'' + 
			",dewpoint_f = '" + dewpointF + '\'' + 
			",gti = '" + gti + '\'' + 
			",dni = '" + dni + '\'' + 
			",uv = '" + uv + '\'' + 
			",last_updated = '" + lastUpdated + '\'' + 
			",heatindex_f = '" + heatindexF + '\'' + 
			",dewpoint_c = '" + dewpointC + '\'' + 
			",diff_rad = '" + diffRad + '\'' + 
			",is_day = '" + isDay + '\'' + 
			",precip_in = '" + precipIn + '\'' + 
			",heatindex_c = '" + heatindexC + '\'' + 
			",wind_dir = '" + windDir + '\'' + 
			",gust_mph = '" + gustMph + '\'' + 
			",pressure_in = '" + pressureIn + '\'' + 
			",gust_kph = '" + gustKph + '\'' + 
			",precip_mm = '" + precipMm + '\'' + 
			",short_rad = '" + shortRad + '\'' + 
			",condition = '" + condition + '\'' + 
			",vis_km = '" + visKm + '\'' + 
			",pressure_mb = '" + pressureMb + '\'' + 
			",vis_miles = '" + visMiles + '\'' + 
			"}";
		}
}