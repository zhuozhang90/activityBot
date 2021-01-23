package dbs.mongodb;

import java.util.ArrayList;

import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dbs.ConnectDB;
import dbs.entryObjects.ActivFitEntry;
import dbs.entryObjects.ActivityEntry;
import dbs.entryObjects.BatteryEntry;
import dbs.entryObjects.BluetoothEntry;
import dbs.entryObjects.HeartRateEntry;
import dbs.entryObjects.LightEntry;
import dbs.entryObjects.ScreenEntry;

public class CreateCollections {
	
	// insert activfit data
	public static void insertActivfitData(ArrayList<ActivFitEntry> records) {
		MongoDatabase db = ConnectDB.connectMongoDB();
	    MongoCollection<Document> collection = db.getCollection("activfit"); 

		for (ActivFitEntry entry : records) {
//			System.out.println(entry);
			// create a new document for each record
			Document document = new Document("sensor_name", entry.getSensorName()) 
					.append("start_time", entry.getStartTime())
				    .append("end_time", entry.getEndTime()) 
				    .append("activity", entry.getActivity()) 
				    .append("duration", entry.getDuration()); 
			// insert document
			collection.insertOne(document); 	
		}
	}
	
	// insert activity data
	public static void insertActivityData(ArrayList<ActivityEntry> records) {
		MongoDatabase db = ConnectDB.connectMongoDB();
	    MongoCollection<Document> collection = db.getCollection("activity"); 

		for (ActivityEntry entry : records) {
//			System.out.println(entry);
			Document document = new Document("sensor_name", entry.getSensorName()) 
					.append("time", entry.getTimestamp())
				    .append("step_count", entry.getStepCount()) 
				    .append("step_delta", entry.getStepDelta());  
			collection.insertOne(document); 	
		}
	}
	
	// insert battery data
	public static void insertBatteryData(ArrayList<BatteryEntry> records) {
		MongoDatabase db = ConnectDB.connectMongoDB();
	    MongoCollection<Document> collection = db.getCollection("battery"); 

		for (BatteryEntry entry : records) {
//			System.out.println(entry);
			Document document = new Document("sensor_name", entry.getSensorName()) 
					.append("time", entry.getTimestamp())
				    .append("percentage", entry.getPercentage()) 
				    .append("charging", entry.isCharging());  
			collection.insertOne(document); 	
		}
	}
	
	// insert battery data
	public static void insertHeartRateData(ArrayList<HeartRateEntry> records) {
		MongoDatabase db = ConnectDB.connectMongoDB();
	    MongoCollection<Document> collection = db.getCollection("heartrate"); 

		for (HeartRateEntry entry : records) {
//			System.out.println(entry);
			Document document = new Document("sensor_name", entry.getSensorName()) 
					.append("time", entry.getTimestamp())
				    .append("bpm", entry.getBpm());  
			collection.insertOne(document); 	
		}
	}
	
	// insert bluetooth data
	public static void insertBluetoothData(ArrayList<BluetoothEntry> records) {
		MongoDatabase db = ConnectDB.connectMongoDB();
	    MongoCollection<Document> collection = db.getCollection("bluetooth"); 

		for (BluetoothEntry entry : records) {
//			System.out.println(entry);
			Document document = new Document("sensor_name", entry.getSensorName()) 
					.append("time", entry.getTimestamp())
				    .append("state", entry.getState());  
			collection.insertOne(document); 	
		}
	}
	
	// insert bluetooth data
	public static void insertLightData(ArrayList<LightEntry> records) {
		MongoDatabase db = ConnectDB.connectMongoDB();
	    MongoCollection<Document> collection = db.getCollection("light"); 

		for (LightEntry entry : records) {
//			System.out.println(entry);
			Document document = new Document("sensor_name", entry.getSensorName()) 
					.append("time", entry.getTimestamp())
				    .append("lux", entry.getLux());  
			collection.insertOne(document); 	
		}
	}
	
	// insert light data
	public static void insertScreenData(ArrayList<ScreenEntry> records) {
		MongoDatabase db = ConnectDB.connectMongoDB();
	    MongoCollection<Document> collection = db.getCollection("screen"); 

		for (ScreenEntry entry : records) {
//			System.out.println(entry);
			Document document = new Document("start_time", entry.getStartTime()) 
					.append("end_time", entry.getEndTime())
				    .append("mins_elapsed", entry.getMinElapsed());  
			collection.insertOne(document); 	
		}
	}

}
