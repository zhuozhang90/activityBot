package dbs.mongodb;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import dbs.entryObjects.RunningRecord;
import dbs.mongodb.QueryDB;
import dbs.parseRecords.ParseActivFit;
import dbs.parseRecords.ParseActivity;
import dbs.parseRecords.ParseBattery;
import dbs.parseRecords.ParseBluetooth;
import dbs.parseRecords.ParseHeartRate;
import dbs.parseRecords.ParseLight;
import dbs.parseRecords.ParseScreen;
import dbs.mongodb.RunMongoDB;

@SuppressWarnings("unused")
public class RunMongoDB {
	
	// insert data from all collections
	public static void insertAllData() throws IOException, ParseException, java.text.ParseException {
		CreateCollections.insertActivfitData(ParseActivFit.getActivfitData());
		System.out.println("activfit done");
		CreateCollections.insertActivityData(ParseActivity.getActivityData());
		System.out.println("activity done");
		CreateCollections.insertBatteryData(ParseBattery.getBatteryData());
		System.out.println("battery done");
		CreateCollections.insertHeartRateData(ParseHeartRate.getHeartRateData());
		System.out.println("heartrate done");
		CreateCollections.insertBluetoothData(ParseBluetooth.getBluetoothData());
		System.out.println("bluetooth done");
		CreateCollections.insertLightData(ParseLight.getLightData());
		System.out.println("light done");
		CreateCollections.insertScreenData(ParseScreen.getScreenData());
		System.out.println("screen done");
		System.out.println("all collections created and populated.");

	}
	
	// wrappers for Query methods
	public static String getHeartrateNotificationCount(String date) throws SQLException, java.text.ParseException {
		return "You got " + QueryDB.getNotificationCount(date) + " heartrate notifications on " + date;	

	}
	
	public static String getTotalSteps(String date) throws SQLException, java.text.ParseException {
		return "You had " + QueryDB.getTotalSteps(date) + " steps on " + date;

	}
	
	public static String getRunningData(String date) throws SQLException, java.text.ParseException {
		ArrayList<RunningRecord> runningRecords = QueryDB.getRunningRecords(date);
		String res = "";
		if (runningRecords.size() < 1) return "You didn't run that day";
		for (RunningRecord record : runningRecords) {
			res += record.toString();
		}
		return res;
	}

	
	public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {
//		RunMongoDB.insertAllData();

	}
}
