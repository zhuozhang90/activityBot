package dbs.parseRecords;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dbs.ReadRecords;
import dbs.entryObjects.HeartRateEntry;

public class ParseHeartRate {
	// insert heart rate data
	public static ArrayList<HeartRateEntry> getHeartRateData() throws IOException, java.text.ParseException, ParseException {
		// connect to db
		ArrayList<HeartRateEntry> heartRateEntries = new ArrayList<>();
			ArrayList<String> records = ReadRecords.readFile("smartWatchData/HeartRate.txt");
			for (String each : records) {
				HeartRateEntry entry = parseHeartRate(each);
				if (!(entry == null)) heartRateEntries.add(entry);
			}
			return heartRateEntries;
		}
		
		
		private static HeartRateEntry parseHeartRate(String record) throws java.text.ParseException {
			// parse each line as json
			JSONParser parser = new JSONParser();
			JSONObject json;
			HeartRateEntry entry = null;
			try {
				json = (JSONObject) parser.parse(record);
				
				// get values from json 
				String sensorName = (String) json.get("sensor_name");
				
				String timestampString = (String) json.get("timestamp");
				Timestamp timestamp = ReadRecords.parseDateTime(timestampString);
				long bpm = (long) ((JSONObject)json.get("sensor_data")).get("bpm");
				
				entry = new HeartRateEntry(sensorName, timestamp, bpm);
			} catch (ParseException e) {
				System.out.println(e + ", skip entry.");
				return null;
			}
		
			return entry;
					
		}
}
