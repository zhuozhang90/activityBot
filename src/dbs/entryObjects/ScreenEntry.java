package dbs.entryObjects;

import java.sql.Timestamp;

public class ScreenEntry {
	private Timestamp startTime;
	private Timestamp endTime;
	private double minElapsed;
	
	public ScreenEntry() {};
	public ScreenEntry(Timestamp startTime, Timestamp endTime, double timeElapsed) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.minElapsed = timeElapsed;
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
	public double getMinElapsed() {
		return minElapsed;
	}
	public void setMinElapsed(double timeElapsed) {
		this.minElapsed = timeElapsed;
	}
	
	@Override
	public String toString() {
		return startTime + ", " + endTime + ", " + minElapsed;
	}
}
