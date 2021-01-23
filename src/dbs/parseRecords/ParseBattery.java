package dbs.parseRecords;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dbs.ReadRecords;
import dbs.entryObjects.BatteryEntry;

public class ParseBattery {
	// insert battery data
	public static ArrayList<BatteryEntry> getBatteryData() throws IOException, ParseException, java.text.ParseException {
		ArrayList<BatteryEntry> batteryEntries = new ArrayList<>();
		ArrayList<String> records = ReadRecords.readFile("smartWatchData/BatterySensor.txt");
		for (String each : records) {
			BatteryEntry entry = parseBattery(each);
			if (!(entry == null)) batteryEntries.add(entry);
		}
		return batteryEntries;
	}
	
	private static BatteryEntry parseBattery(String record) {
		// parse each line as json
		JSONParser parser = new JSONParser();
		JSONObject json;
		BatteryEntry entry = null;
		try {
			json = (JSONObject) parser.parse(record);
			// get values from json 
			String sensorName = (String) json.get("sensor_name");
			
			String timestampString = (String) json.get("timestamp");
			Timestamp timestamp = ReadRecords.parseDateTime(timestampString);
			long percentage = (long) ((JSONObject)json.get("sensor_data")).get("percent");		
			boolean charging = (boolean) ((JSONObject)json.get("sensor_data")).get("charging");
			
			entry = new BatteryEntry(sensorName, timestamp, percentage, charging);
		} catch (ParseException e) {
			System.out.println(e + ", skip entry.");
			return null;
		} catch (java.text.ParseException e) {
			System.out.println("Timestamp parsing error " + e);
			return null;
		}
		
				
		return entry;
		
	}
}
