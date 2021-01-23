package dbs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Stream;

import org.json.simple.parser.ParseException;

public class ReadRecords {
	// read file into StringBuilder to process
	public static ArrayList<String> readFile(String filePath) throws IOException {
	    ArrayList<String> fileContent = new ArrayList<>();
	    
	    try (Stream<String> stream = Files.lines(Paths.get(filePath))){ 
	        stream.forEach(s -> fileContent.add(s)); // append each line to fileContent then new line
	    } catch (IOException e){
	        e.printStackTrace();
	    }
	    
	    return fileContent; 
	}

	// parse datetime format into Timestamp
	public static Timestamp parseDateTime(String timeString) throws java.text.ParseException, ParseException {
		// parse timestamp formatted string into timestamp to insert into mysql
		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy");
		Date date = formatter.parse(timeString);
		// convert date into timestamp
        Timestamp ts = new Timestamp(date.getTime());  
		return ts;
	}
	
	public static Date parseDate(String dateString) throws java.text.ParseException {
		// parse timestamp formatted string into timestamp to insert into mysql
		DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		Date date = formatter.parse(dateString);
		// convert date into timestamp
		return date;
	}
	
	// parse date into mongodb isodate format
	public static Date parseISODate(String dateString) throws java.text.ParseException {
		String dateStringMongo = dateString + "T00:00:00.000Z";
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS\'Z\'");
		Date date = formatter.parse(dateStringMongo);
		// convert date into timestamp
		return date;
	}
	
	// parse timestamp into mongodb isodate format
	public static Date timeStampToISODate(Timestamp ts) throws java.text.ParseException {
		Date d = new Date(ts.getTime());
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS\'Z\'");
		String dateString = formatter.format(d);
		System.out.println(dateString);
		Date date = formatter.parse(dateString);
		return date;
	}

}
