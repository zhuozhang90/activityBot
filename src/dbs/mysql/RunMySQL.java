package dbs.mysql;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import dbs.Constants;
import dbs.parseRecords.ParseActivFit;
import dbs.parseRecords.ParseActivity;
import dbs.parseRecords.ParseBattery;
import dbs.parseRecords.ParseBluetooth;
import dbs.parseRecords.ParseHeartRate;
import dbs.parseRecords.ParseLight;
import dbs.parseRecords.ParseScreen;
import dbs.entryObjects.RunningRecord;
import dbs.mysql.QueryDB;


public class RunMySQL {
	
	@SuppressWarnings("unused")
	// create all tables from given sql statements
	public static void createAllTables() throws SQLException {
		CreateTables.createTable(Constants.ACTIVFIT_SQL);
		CreateTables.createTable(Constants.ACTIVITY_SQL);
		CreateTables.createTable(Constants.BATTERY_SQL);
		CreateTables.createTable(Constants.HEARTRATE_SQL);
		CreateTables.createTable(Constants.BLUETOOTH_SQL);
		CreateTables.createTable(Constants.LIGHT_SQL);
		CreateTables.createTable(Constants.SCREEN_SQL);
	}
	
	// insert all data into table
	@SuppressWarnings("unused")
	public static void insertAllData() throws SQLException, IOException, org.json.simple.parser.ParseException, ParseException {
		CreateTables.insertActivfitData(ParseActivFit.getActivfitData());
		System.out.println("activfit done");
		CreateTables.insertActivityData(ParseActivity.getActivityData());
		System.out.println("activity done");
		CreateTables.insertBatteryData(ParseBattery.getBatteryData());
		System.out.println("battery done");
		CreateTables.insertHeartRateData(ParseHeartRate.getHeartRateData());
		System.out.println("heartrate done");
		CreateTables.insertBluetoothData(ParseBluetooth.getBluetoothData());
		System.out.println("bluetooth done");
		CreateTables.insertLightData(ParseLight.getLightData());
		System.out.println("light done");
		CreateTables.insertScreenData(ParseScreen.getScreenData());
		System.out.println("screen done");
		System.out.println("all collections created and populated.");
	}
	
	// wrappes for Query methods
	public static String getHeartrateNotificationCount(String date) throws SQLException, ParseException {
		return "You got " + QueryDB.getNotificationCount(date) + " heartrate notifications on " + date;	

	}
	
	public static String getTotalSteps(String date) throws SQLException, ParseException {
		return "You had " + QueryDB.getTotalSteps(date) + " steps on " + date;

	}
	
	public static String getRunningData(String date) throws SQLException, ParseException {
		ArrayList<RunningRecord> runningRecords = QueryDB.getRunningRecords(date);
		String res = "";
		if (runningRecords.size() < 1) return "You didn't run that day";
		for (RunningRecord record : runningRecords) {
			res += record.toString();
		}
		return res;
	}
	
	public static void main(String[] args) throws SQLException, ParseException {
		System.out.println(getTotalSteps("2016-06-07"));
	}
}
