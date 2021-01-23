package dbs.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;

import dbs.ConnectDB;
import dbs.entryObjects.RunningRecord;

public class QueryDB {
	
	// takes the date and returns notification count
	public static int getNotificationCount(String date) throws SQLException {
		int notificationCount = 0;
		try (Connection conn = ConnectDB.connectMySQL()){
			
			// use query to get data 
			// counts number of entries in a given day
			String query = "select count(*) from heartrate where DATE(time) = DATE('" 
					+ date + "')";
			Statement stmt = conn.createStatement(); 
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				 notificationCount = rs.getInt(1);
			}
		}
		return notificationCount;
	}
	
	// get total steps in a day
	public static int getTotalSteps(String date) throws SQLException {
		int totalSteps = 0;
		try (Connection conn = ConnectDB.connectMySQL()){
			
			// gets final step count from a given day
			String query = " select MAX(step_count) from activity where DATE(time) = DATE('" 
					+ date + "')";
			Statement stmt = conn.createStatement(); 
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				 totalSteps = rs.getInt(1);
			}
		}
		return totalSteps;
	}
	
	// get running steps
	public static ArrayList<RunningRecord> getRunningRecords(String date) throws SQLException {
		ArrayList<RunningRecord> runningRecords = new ArrayList<>();
		try (Connection conn = ConnectDB.connectMySQL()){
			Statement stmt = conn.createStatement(); 
			
			// get result set
			// join activfit and activity tables, then get entries that are both running and match the timestamps
			// from the activity table to get step counts
			String query = 
					"select activfit.start_time, activfit.end_time, MAX(activity.step_count) as step_count\r\n" + 
					"from activity \r\n" + 
					"inner join activfit\r\n" + 
					"on DATE(activity.time) = DATE(activfit.start_time)\r\n" + 
					"where activfit.activity = 'running' and DATE(start_time) = DATE('" + date + "')\r\n" + 
					"group by start_time, end_time"; 
			ResultSet rs = stmt.executeQuery(query);
			// parse each running record 
			while (rs.next()) {
				Timestamp startTime = rs.getTimestamp(1);
				Timestamp endTime = rs.getTimestamp(2);
				long steps = rs.getLong(3);
				RunningRecord record = new RunningRecord(startTime, endTime, steps);
				runningRecords.add(record);
			}
		}
		return runningRecords;		
	}
	
	public static void main(String[] args) throws SQLException, ParseException {
		System.out.println(getTotalSteps("2016-06-07"));
	}
}

























