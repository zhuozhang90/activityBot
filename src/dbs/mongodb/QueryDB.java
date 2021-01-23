package dbs.mongodb;

import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.commons.lang3.time.DateUtils;
import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;

import static com.mongodb.client.model.Filters.*;
import dbs.ConnectDB;
import dbs.ReadRecords;
import dbs.entryObjects.RunningRecord;

// query mongodb
public class QueryDB {

	// get number of notifications from heartrate
	public static int getNotificationCount(String date) throws ParseException {
		int notificationCount = 0;
		
		// get bounds for given day
		Date dateObject = ReadRecords.parseISODate(date);
		Date nextDate = DateUtils.addDays(dateObject, 1);

		MongoDatabase db = ConnectDB.connectMongoDB();
	    MongoCollection<Document> collection = db.getCollection("heartrate"); 
		
	    // get all entries between start of given day and start of next day
		FindIterable<Document> it = collection
				.find(and(gte("time", dateObject),
						lt("time", nextDate)));
		MongoCursor<Document> cursor = it.cursor();
		while (cursor.hasNext()) {
			 notificationCount++;
//		     System.out.println(cursor.next());
		}
		 
		return notificationCount;
	}
	
	// get total steps in a day
	public static Long getTotalSteps(String date) throws ParseException {
		// get bounds for date
		Date dateObject = ReadRecords.parseISODate(date);
		Date nextDate = DateUtils.addDays(dateObject, 1);
		
		MongoDatabase db = ConnectDB.connectMongoDB();
	    MongoCollection<Document> collection = db.getCollection("activity"); 
		
	    // find all entries between bounds and get final step count
		FindIterable<Document> it = collection
				.find(and(gte("time", dateObject), lt("time", nextDate)))
				.sort(Sorts.descending("step_count"));
		
		MongoCursor<Document> cursor = it.cursor();
//		while (cursor.hasNext()) {
//		     System.out.println(cursor.next());
//		}
		// return max count which is in the first entry in the cursor
		return (cursor.hasNext()? it.first().getLong("step_count") : 0);		
	}
	
	// helper function to get stepcount between two times
	private static long getSteps(Date startDate, Date endDate) throws ParseException {
		
		MongoDatabase db = ConnectDB.connectMongoDB();
	    MongoCollection<Document> collection = db.getCollection("activity"); 
		
	    // get step count from activity collection
		FindIterable<Document> it = collection
				.find(and(gte("time", startDate), lt("time", endDate)))
				.sort(Sorts.descending("step_count"));
		
		return (it.cursor().hasNext()? it.first().getLong("step_count") : 0);		
	}
	
	// get running records between between 2 dates from activfit table running entries
	public static ArrayList<RunningRecord> getRunningRecords(String date) throws ParseException {
		Date dateObject = ReadRecords.parseISODate(date);
		Date nextDate = DateUtils.addDays(dateObject, 1);
		
		// list to hold all records
		ArrayList<RunningRecord> runningRecords = new ArrayList<>();
		
		MongoDatabase db = ConnectDB.connectMongoDB();
		
		// get running dates from activfit collection
	    MongoCollection<Document> collection = db.getCollection("activfit"); 
	    FindIterable<Document> it = collection
	    		.find(and(gte("start_time", dateObject), lt("start_time", nextDate),
	    				eq("activity", "running")));
	    MongoCursor<Document> cursor = it.cursor();
	    
	    // get step count with help from the helper function
		while (cursor.hasNext()) {
		     Document doc = cursor.next();
		     Timestamp startTime = new Timestamp(doc
		    		 .getDate("start_time").getTime());
		     Timestamp endTime = new Timestamp(doc
		    		 .getDate("end_time").getTime());
		     long steps = getSteps(dateObject, nextDate);
		     RunningRecord record = new RunningRecord(startTime, endTime, steps);
				runningRecords.add(record);
		}
		
		return runningRecords;
		
	}
	public static void main(String[] args) throws SQLException, ParseException {
		System.out.println(getTotalSteps("2016-06-07"));
	}
}
