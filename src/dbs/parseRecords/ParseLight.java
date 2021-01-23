package dbs.parseRecords;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dbs.ReadRecords;
import dbs.entryObjects.LightEntry;

public class ParseLight {
	// insert light data
		public static ArrayList<LightEntry> getLightData() throws IOException, ParseException, java.text.ParseException {
			ArrayList<LightEntry> lightEntries = new ArrayList<>();
			ArrayList<String> records = ReadRecords.readFile("smartWatchData/LightSensor.txt");
			for (String each : records) {
				LightEntry entry = parseLight(each);
//				System.out.println(entry);
				if (!(entry == null)) lightEntries.add(entry);
			}

			return lightEntries;
		}
		
		private static LightEntry parseLight(String record) throws java.text.ParseException {
			// parse each line as json
			JSONParser parser = new JSONParser();
			JSONObject json;
			LightEntry entry = null;
			try {
				json = (JSONObject) parser.parse(record);
				// get values from json 
				String sensorName = (String) json.get("sensor_name");
				
				String timestampString = (String) json.get("timestamp");
				Timestamp timestamp = ReadRecords.parseDateTime(timestampString);
				Double lux = ((Number) ((JSONObject)json.get("sensor_data")).get("lux")).doubleValue();	
				entry = new LightEntry(sensorName, timestamp, lux);
				
			} catch (ParseException e) {
				System.out.println(e + ", skip entry.");
				return null;
			}
			
			return entry;
		}
		
}
