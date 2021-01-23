package dbs.parseRecords;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dbs.entryObjects.ActivFitEntry;
import dbs.ReadRecords;

public class ParseActivFit {
	// insert data from json format files
	public static ArrayList<ActivFitEntry> getActivfitData() throws IOException, ParseException, java.text.ParseException {
		ArrayList<ActivFitEntry> activFitEntries = new ArrayList<>();
		ArrayList<String> records = ReadRecords.readFile("smartWatchData/ActivFit.txt");
		for (String each : records) {
			ActivFitEntry entry = parseActivFit(each);
			if (!(entry == null)) activFitEntries.add(entry);
		}
		return activFitEntries;
	}
	
	// parse entries from activfit
	private static ActivFitEntry parseActivFit(String record) throws java.text.ParseException {
		// parse each line as json
		JSONParser parser = new JSONParser();
		JSONObject json;
		ActivFitEntry entry = null;
		try {
			json = (JSONObject) parser.parse(record);
			// get values from json 
			String sensorName = (String) json.get("sensor_name");
			
			String startTime = (String) ((JSONObject)json.get("timestamp")).get("start_time");
			Timestamp startTimeStamp = ReadRecords.parseDateTime(startTime);
			
			String endTime = (String) ((JSONObject)json.get("timestamp")).get("end_time");	
			Timestamp endTimeStamp = ReadRecords.parseDateTime(endTime);
			
			String activity = (String) ((JSONObject)json.get("sensor_data")).get("activity");		
			
			long duration = (long) ((JSONObject)json.get("sensor_data")).get("duration");
			
			entry = new ActivFitEntry(sensorName, startTimeStamp, endTimeStamp, activity, duration);
		} catch (ParseException e) {
			System.out.println(e + ", skip entry.");
			return null;
		}
		
		
		// return a new activfitentry object to hold all data
		return entry;
	}
		
}
