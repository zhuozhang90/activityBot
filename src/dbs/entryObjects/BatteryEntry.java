package dbs.entryObjects;

import java.sql.Timestamp;

public class BatteryEntry {
	private String sensorName;
	private Timestamp timestamp;
	private long percentage;
	private boolean charging;
	
	public BatteryEntry() {};
	public BatteryEntry(String sensorName, Timestamp timestamp, long percentage, boolean charging) {
		this.sensorName = sensorName;
		this.timestamp = timestamp;
		this.percentage = percentage;
		this.charging = charging;
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
	public long getPercentage() {
		return percentage;
	}
	public void setPercentage(long percentage) {
		this.percentage = percentage;
	}
	public boolean isCharging() {
		return charging;
	}
	public void setCharging(boolean charging) {
		this.charging = charging;
	}
	@Override
	public String toString() {
		return sensorName + ", " + timestamp + ", " + percentage + ", " + charging;
	}
}
