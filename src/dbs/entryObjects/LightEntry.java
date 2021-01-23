package dbs.entryObjects;

import java.sql.Timestamp;

public class LightEntry {
	private String sensorName;
	private Timestamp timestamp;
	private double lux;
	
	public LightEntry() {};
	public LightEntry(String sensorName, Timestamp timestamp, double lux) {
		this.sensorName = sensorName;
		this.timestamp = timestamp;
		this.lux = lux;
	}
	public String getSensorName() {
		return sensorName;
	}
	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public double getLux() {
		return lux;
	}
	public void setLux(double lux) {
		this.lux = lux;
	}
	
	@Override 
	public String toString() {
		return sensorName + ", " + timestamp + ", " + lux;
	}
}
