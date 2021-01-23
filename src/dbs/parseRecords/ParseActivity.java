package dbs.parseRecords;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dbs.entryObjects.ActivityEntry;
import dbs.ReadRecords;

public class ParseActivity {
	// insert activity data
	public static ArrayList<ActivityEntry> getActivityData() throws IOException, ParseException, java.text.ParseException {
		ArrayList<ActivityEntry> activityEntries = new ArrayList<>();	
		ArrayList<String> records = ReadRecords.readFile("smartWatchData/Activity.txt");
		for (String each : records) {
			ActivityEntry entry = parseActivity(each);
			if (!(entry == null)) activityEntries.add(entry);				
		}
		return activityEntries;
	}
	
	private static ActivityEntry parseActivity(String record) throws java.text.ParseException {
		// parse each line as json
		JSONParser parser = new JSONParser();
		JSONObject json;
		ActivityEntry entry = null;
		try {
			json = (JSONObject) parser.parse(record);
			// get values from json 
			String sensorName = (String) json.get("sensor_name");
			
			String timestampString = (String) json.get("time_stamp");
			Timestamp timestamp = ReadRecords.parseDateTime(timestampString);
			
			long stepCount = (long) ((JSONObject)json.get("sensor_data")).get("step_counts");	
			
			long stepDelta = (long) ((JSONObject)json.get("sensor_data")).get("step_delta");	
		
			entry = new ActivityEntry(sensorName, timestamp, stepCount, stepDelta);
		} catch (ParseException e) {
			System.out.println(e + ", skip entry.");
			return null;
		}
		
			
		return entry;
	}
		
}
