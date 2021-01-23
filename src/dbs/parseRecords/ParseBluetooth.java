package dbs.parseRecords;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dbs.ReadRecords;
import dbs.entryObjects.BluetoothEntry;

public class ParseBluetooth {
	// insert bluetooth data
	public static ArrayList<BluetoothEntry> getBluetoothData() throws IOException, ParseException, java.text.ParseException {
		ArrayList<BluetoothEntry> bluetoothEntries = new ArrayList<>();
		ArrayList<String> records = ReadRecords.readFile("smartWatchData/Bluetooth.txt");
		for (String each : records) {
			BluetoothEntry entry = parseBluetooth(each);
			if (!(entry == null)) bluetoothEntries.add(entry);
		}
		return bluetoothEntries;
	}
	
	private static BluetoothEntry parseBluetooth(String record) throws java.text.ParseException {
		// parse each line as json
		JSONParser parser = new JSONParser();
		JSONObject json;
		BluetoothEntry entry = null;
		try {
			json = (JSONObject) parser.parse(record);
			// get values from json 
			String sensorName = (String) json.get("sensor_name");
			
			String timestampString = (String) json.get("timestamp");
			Timestamp timestamp = ReadRecords.parseDateTime(timestampString);
			String state = (String) ((JSONObject)json.get("sensor_data")).get("state");	
			
			entry = new BluetoothEntry(sensorName, timestamp, state);
		} catch (ParseException e) {
			System.out.println(e + ", skip entry.");
			return null;
		}
			
		return entry;
	}
}
