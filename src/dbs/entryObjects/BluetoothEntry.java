package dbs.entryObjects;

import java.sql.Timestamp;

public class BluetoothEntry {
	private String sensorName;
	private Timestamp timestamp;
	private String state;
	
	public BluetoothEntry() {};
	public BluetoothEntry(String sensorName, Timestamp timestamp, String state) {
		this.sensorName = sensorName;
		this.timestamp = timestamp;
		this.state = state;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return sensorName + ", " + timestamp + ", " + state;
	}
}
