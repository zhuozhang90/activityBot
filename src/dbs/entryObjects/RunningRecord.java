package dbs.entryObjects;

import java.sql.Timestamp;

// helper class to return running data
public class RunningRecord {
	private Timestamp startTime;
	private Timestamp endTime;
	private long steps;
	
	public RunningRecord(){}
	public RunningRecord(Timestamp startTime, Timestamp endTime, long steps) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.steps = steps;
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
	public long getSteps() {
		return steps;
	}
	public void setSteps(long steps) {
		this.steps = steps;
	}
	
	@Override
	public String toString() {
		return "You ran " + steps + " steps from " 
				+ startTime.toString() + " to " 
				+ endTime.toString();
	}
}
