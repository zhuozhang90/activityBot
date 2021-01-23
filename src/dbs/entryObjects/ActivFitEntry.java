package dbs.entryObjects;

import java.sql.Timestamp;

// object to hold activfit data
public class ActivFitEntry {
	private String sensorName;
	private Timestamp startTime;
	private Timestamp endTime;
	private String activity;
	private long duration;
	
	public ActivFitEntry() {};
	public ActivFitEntry(String sensorName, Timestamp startTime, Timestamp endTime, String activity, long duration) {
		this.sensorName = sensorName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.activity = activity;
		this.duration = duration;
	}
	public String getSensorName() {
		return sensorName;
	}
	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	@Override
	public String toString() {
		return sensorName + ", " + startTime + ", " + endTime + ", " + activity + ", " + duration;
	}

}
