package dbs.entryObjects;

import java.sql.Timestamp;

public class ActivityEntry {
	private String sensorName;
	private Timestamp timestamp;
	private long stepCount;
	private long stepDelta;
	
	public ActivityEntry() {};
	public ActivityEntry(String sensorName, Timestamp timestamp, long stepCount, long stepDelta) {
		this.sensorName = sensorName;
		this.timestamp = timestamp;
		this.stepCount = stepCount;
		this.stepDelta = stepDelta;
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
	public long getStepCount() {
		return stepCount;
	}
	public void setStepCount(long stepCount) {
		this.stepCount = stepCount;
	}
	public long getStepDelta() {
		return stepDelta;
	}
	public void setStepDelta(long stepDelta) {
		this.stepDelta = stepDelta;
	}
	
	@Override
	public String toString() {
		return sensorName + ", " + timestamp + ", " + stepCount + ", " + stepDelta;
	}
	
}
