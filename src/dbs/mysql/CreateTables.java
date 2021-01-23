package dbs.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dbs.ConnectDB;
import dbs.entryObjects.*;


public class CreateTables {
	
	// helper method to create a table
	public static void createTable(String sql) throws SQLException {
		try (Connection conn = ConnectDB.connectMySQL()){
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
	}
	
	// insert data from json format files
	public static void insertActivfitData(ArrayList<ActivFitEntry> records) throws SQLException {
		// connect to db
		try (Connection conn = ConnectDB.connectMySQL()){
			for (ActivFitEntry entry : records) {
				
				// add each entry to table
				// the mysql insert statement
			      String query = " insert into activfit (sensor_name, start_time, end_time, activity, duration)"
			        + " values (?, ?, ?, ?, ?)";

			      // create the mysql insert preparedstatement
			      PreparedStatement preparedStmt = conn.prepareStatement(query);
			      preparedStmt.setString(1, entry.getSensorName());
			      preparedStmt.setTimestamp(2, entry.getStartTime());
			      preparedStmt.setTimestamp(3, entry.getEndTime());
			      preparedStmt.setString(4, entry.getActivity());
			      preparedStmt.setLong(5, entry.getDuration());

			      // execute the preparedstatement
			      preparedStmt.execute();
			}
		}
	}
	
	// insert activity data
	public static void insertActivityData(ArrayList<ActivityEntry> records) throws SQLException {
		try (Connection conn = ConnectDB.connectMySQL()){
			for (ActivityEntry entry : records) {
				
				// add each entry to table
				// the mysql insert statement
				String query = " insert into activity (sensor_name, time, step_count, step_delta)"
				+ " values (?, ?, ?, ?)";
				
				// create the mysql insert preparedstatement
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, entry.getSensorName());
				preparedStmt.setTimestamp(2, entry.getTimestamp());
				preparedStmt.setLong(3, entry.getStepCount());
				preparedStmt.setLong(4, entry.getStepDelta());
				
				// execute the preparedstatement
				preparedStmt.execute();
			}
		}
	}

	// insert battery data
	public static void insertBatteryData(ArrayList<BatteryEntry> records) throws SQLException {
		// connect to db
		try (Connection conn = ConnectDB.connectMySQL()){
			for (BatteryEntry entry : records) {
//				System.out.println(entry);
				
				// add each entry to table
				// the mysql insert statement
			      String query = " insert into battery (sensor_name, time, percentage, charging)"
			        + " values (?, ?, ?, ?)";

			      // create the mysql insert preparedstatement
			      PreparedStatement preparedStmt = conn.prepareStatement(query);
			      preparedStmt.setString(1, entry.getSensorName());
			      preparedStmt.setTimestamp(2, entry.getTimestamp());
			      preparedStmt.setLong(3, entry.getPercentage());
			      preparedStmt.setBoolean(4, entry.isCharging());

			      // execute the preparedstatement
			      preparedStmt.execute();
			}
		}
	}

	// insert heart rate data
	public static void insertHeartRateData(ArrayList<HeartRateEntry> records) throws SQLException {
		// connect to db
		try (Connection conn = ConnectDB.connectMySQL()){
			for (HeartRateEntry entry : records) {
				
				// add each entry to table
				// the mysql insert statement
			      String query = " insert into heartrate (sensor_name, time, bpm)"
			        + " values (?, ?, ?)";

			      // create the mysql insert preparedstatement
			      PreparedStatement preparedStmt = conn.prepareStatement(query);
			      preparedStmt.setString(1, entry.getSensorName());
			      preparedStmt.setTimestamp(2, entry.getTimestamp());
			      preparedStmt.setLong(3, entry.getBpm());

			      // execute the preparedstatement
			      preparedStmt.execute();
			}
		}
	}
	

	// insert bluetooth data
	public static void insertBluetoothData(ArrayList<BluetoothEntry> records) throws SQLException {
		// connect to db
		try (Connection conn = ConnectDB.connectMySQL()){
			for (BluetoothEntry entry : records) {
				
				// add each entry to table
				// the mysql insert statement
			      String query = " insert into bluetooth (sensor_name, time, state)"
			        + " values (?, ?, ?)";

			      // create the mysql insert preparedstatement
			      PreparedStatement preparedStmt = conn.prepareStatement(query);
			      preparedStmt.setString(1, entry.getSensorName());
			      preparedStmt.setTimestamp(2, entry.getTimestamp());
			      preparedStmt.setString(3, entry.getState());

			      // execute the preparedstatement
			      preparedStmt.execute();
			}
		}
	}

	// insert light data
	public static void insertLightData(ArrayList<LightEntry> records) throws SQLException {
		// connect to db
		try (Connection conn = ConnectDB.connectMySQL()){
			for (LightEntry entry : records) {
				
				// add each entry to table
				// the mysql insert statement
			      String query = " insert into light (sensor_name, time, lux)"
			        + " values (?, ?, ?)";

			      // create the mysql insert preparedstatement
			      PreparedStatement preparedStmt = conn.prepareStatement(query);
			      preparedStmt.setString(1, entry.getSensorName());
			      preparedStmt.setTimestamp(2, entry.getTimestamp());
			      preparedStmt.setDouble(3, entry.getLux());

			      // execute the preparedstatement
			      preparedStmt.execute();
			}
		}
	}

	// insert screen data
	public static void insertScreenData(ArrayList<ScreenEntry> records) throws SQLException {
		// connect to db
		try (Connection conn = ConnectDB.connectMySQL()){
			for (ScreenEntry entry : records) {
				
				// add each entry to table
				// the mysql insert statement
			      String query = " insert into screen (start_time, end_time, mins_elapsed)"
			        + " values (?, ?, ?)";

			      // create the mysql insert preparedstatement
			      PreparedStatement preparedStmt = conn.prepareStatement(query);
			      preparedStmt.setTimestamp(1, entry.getStartTime());
			      preparedStmt.setTimestamp(2, entry.getEndTime());
			      preparedStmt.setDouble(3, entry.getMinElapsed());

			      // execute the preparedstatement
			      preparedStmt.execute();
			}
		}
	}


}
