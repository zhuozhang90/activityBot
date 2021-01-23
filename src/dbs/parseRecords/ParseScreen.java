package dbs.parseRecords;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dbs.ReadRecords;
import dbs.entryObjects.ScreenEntry;

public class ParseScreen {
	// insert screen data
	public static ArrayList<ScreenEntry> getScreenData() throws IOException, java.text.ParseException, ParseException {
		ArrayList<ScreenEntry> screenEntries = new ArrayList<>();
		ArrayList<String> records = ReadRecords.readFile("smartWatchData/ScreenUsage.txt");
		for (String each : records) {
			ScreenEntry entry = parseScreen(each);
			if (!(entry == null)) screenEntries.add(entry);
		}
		return screenEntries;
	}
	
	private static ScreenEntry parseScreen(String record) throws java.text.ParseException {
		// parse each line as json
		JSONParser parser = new JSONParser();
		JSONObject json;
		ScreenEntry entry = null;
		try {
			json = (JSONObject) parser.parse(record);
			// get values from json 
			String startTimestampString = (String) json.get("start_timestamp");
			Timestamp startTimestamp = ReadRecords.parseDateTime(startTimestampString);
			
			String endTimestampString = (String) json.get("end_timestamp");
			Timestamp endTimestamp = ReadRecords.parseDateTime(endTimestampString);
			Double minElapsed = ((Number) json.get("min_elapsed")).doubleValue();
			
			entry = new ScreenEntry(startTimestamp, endTimestamp, minElapsed);
		} catch (ParseException e) {
			System.out.println(e + ", skip entry.");
			return null;
		}
		
				
				
		return entry;
	}
	
}
