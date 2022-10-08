package GTFS;

/**
 * @author nairac
 * @version 1.0
 * @created 05-Oct-2022 8:14:32 PM
 */
public class Stop {

	private final int stopID;
	private String stopName;
	private double stopLat;
	private double stopLon;
	private String stopDesc;

	/**
	 * constructor for Stop
	 * @param stopID the string of the stopID
	 */
	protected Stop(int stopID){
		this.stopID = stopID;
		this.stopName = "";
		this.stopLat = 0;
		this.stopLon = 0;
		this.stopDesc = "";
	}

	/**
	 * verify that the stop_id used for finding the stop matches what the stop has.
	 * @param stopId the stop_id used for finding the stop
	 */
	protected boolean verifyStop(int stopId){
		return this.stopID == stopId;
	}

	/**
	 * set the name of the stop
	 * @param name of the stop
	 */
	protected void setStopName(String name){
		this.stopName = name;
	}

	/**
	 * set latitude of the stop
	 * @param latitude of the stop
	 */
	protected void setStopLat(double latitude){
		this.stopLat = latitude;
	}

	/**
	 * set longitude of the stop
	 * @param longitude of stop
	 */
	protected void setStopLon(double longitude){
		this.stopLon = longitude;
	}

	/**
	 * set a description of the stop
	 * @param description description of the stop
	 */
	protected void setStopDesc(String description) {
		this.stopDesc = description;
	}

	// start of getters
	protected int getStopId(){
		return stopID;
	}

	protected String getStopName(){
		return stopName;
	}

	protected double getStopLat(){
		return stopLat;
	}

	protected double getStopLon(){
		return stopLon;
	}

	protected String getStopDesc() {
		return stopDesc;
	}
}