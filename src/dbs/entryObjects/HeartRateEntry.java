package dbs.entryObjects;

import java.sql.Timestamp;

public class HeartRateEntry {
	private String sensorName;
	private Timestamp timestamp;
	private long bpm;
	
	public HeartRateEntry() {};
	public HeartRateEntry(String sensorName, Timestamp timestamp, long bpm) {
		this.sensorName = sensorName;
		this.timestamp = timestamp;
		this.bpm = bpm;
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
	public long getBpm() {
		return bpm;
	}
	public void setBpm(long bpm) {
		this.bpm = bpm;
	}
	
	@Override 
	public String toString() {
		return sensorName + ", " + timestamp + ", " + bpm;
	}
}
