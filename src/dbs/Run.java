package dbs;

import dbs.lucene.LuceneSearch;
import dbs.mysql.*;
import dbs.mongodb.*;
import dbs.plain.RunPlainText;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.ocpsoft.prettytime.nlp.PrettyTimeParser;


public class Run {
	
	// create dbs and insert data
	public static void setUpDB() throws SQLException, IOException, ParseException, java.text.ParseException {
		RunMySQL.createAllTables();
		RunMySQL.insertAllData();
		RunMongoDB.insertAllData();
	}

	public static String mysqlSearch(String inputStr) throws SQLException, java.text.ParseException {
		long startTime, endTime, elapsedTime;
		startTime = System.currentTimeMillis();
		List<Date> dates = new PrettyTimeParser().parse(inputStr);
		String strDate = null;
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    
	    String output;
	    
	    if (dates.size() > 0) {
	    	strDate = formatter.format(dates.get(0));  
	    } else {
	    	return "please include a date.";
	    }
	    	    
	    // get user intent
	    // check if asked about running
	    if (ReadUserInput.getUserIntent(inputStr, "run")) {
			output = RunMySQL.getRunningData(strDate);
			// check if asked about heartrate
	    } else if (ReadUserInput.getUserIntent(inputStr, "step") ||
	    	ReadUserInput.getUserIntent(inputStr, "stepcount") ||
				ReadUserInput.getUserIntent(inputStr, "walk")) {
	    	output = RunMySQL.getTotalSteps(strDate);
	    } else if (ReadUserInput.getUserIntent(inputStr, "activity") ||
				ReadUserInput.getUserIntent(inputStr, "activities")) {
			output = RunMySQL.getTotalSteps(strDate);
			output += ",\n";
			output += RunMySQL.getRunningData(strDate);
		} else {
	    	output = "Sorry, I did understand that. ";
	    }
	    endTime = System.currentTimeMillis();
	    elapsedTime = endTime - startTime;

	    output += " [in " + elapsedTime + " ms]";
	    return output;
	}
	
	public static String mongodbSearch(String inputStr) throws SQLException, java.text.ParseException {
		long startTime, endTime, elapsedTime;
		startTime = System.currentTimeMillis();

		List<Date> dates = new PrettyTimeParser().parse(inputStr);
		String strDate = null;
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
	    
	    String output;
	    
	    if (dates.size() > 0) strDate = formatter.format(dates.get(0));  
	    else {
	    	return "please include a date.";
	    }
	    	    
	    // get user intent
	    // check if asked about running
	    if (ReadUserInput.getUserIntent(inputStr, "run")) {
			output = RunMongoDB.getRunningData(strDate);
			// check if asked about heartrate
	    } else if (ReadUserInput.getUserIntent(inputStr, "step") ||
	    		ReadUserInput.getUserIntent(inputStr, "stepcount") ||
				ReadUserInput.getUserIntent(inputStr, "walk")) {
			output = RunMongoDB.getTotalSteps(strDate);
	    } else if (ReadUserInput.getUserIntent(inputStr, "activity")||
				ReadUserInput.getUserIntent(inputStr, "activities")) {
			output = RunMongoDB.getTotalSteps(strDate);
			output += ",\n";
			output += RunMongoDB.getRunningData(strDate);
		}  else {
	    	output = "Sorry, I did understand that. ";
	    }
		endTime = System.currentTimeMillis();
		elapsedTime = endTime - startTime;
		output += " [in " + elapsedTime + " ms]";
		return output;
	}
	
	public static String plainSearch(String inputStr) throws IOException {
		long startTime, endTime, elapsedTime;
		startTime = System.currentTimeMillis();
		List<Date> dates = new PrettyTimeParser().parse(inputStr);
		String strDate = null;
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
	    
	    String output;
	    
	    if (dates.size() > 0) strDate = formatter.format(dates.get(0));  
	    else {
	    	return "please include a date.";
	    }
	    	    
	    // get user intent
	    // check if asked about running
	    if (ReadUserInput.getUserIntent(inputStr, "run")) {
			output = RunPlainText.getRunningData(strDate);
			// check if asked about heartrate
	    } else if (ReadUserInput.getUserIntent(inputStr, "step") ||
	    		ReadUserInput.getUserIntent(inputStr, "stepcount") ||
				ReadUserInput.getUserIntent(inputStr, "walk")) {
			output = RunPlainText.getTotalSteps(strDate);
	    } else if (ReadUserInput.getUserIntent(inputStr, "activity")||
				ReadUserInput.getUserIntent(inputStr, "activities")) {
			output = RunPlainText.getTotalSteps(strDate);
			output += ",\n";
			output += RunPlainText.getRunningData(strDate);
		}  else {
	    	output = "Sorry, I did not understand that. ";
	    }
		endTime = System.currentTimeMillis();
		elapsedTime = endTime - startTime;
		output += " [in " + elapsedTime + " ms]";
	    return output;
	}

	public static String luceneSearch(String inputStr) throws IOException, org.apache.lucene.queryparser.classic.ParseException {
		long startTime, endTime, elapsedTime;
		startTime = System.currentTimeMillis();
		List<Date> dates = new PrettyTimeParser().parse(inputStr);
		String strDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		String output;

		if (dates.size() > 0) strDate = formatter.format(dates.get(0));
		else {
			return "please include a date.";
		}

		// get user intent
		// check if asked about running
		if (ReadUserInput.getUserIntent(inputStr, "run")) {
			output = LuceneSearch.getRunningData(strDate);
			// check if asked about heartrate
		} else if (ReadUserInput.getUserIntent(inputStr, "step") ||
				ReadUserInput.getUserIntent(inputStr, "stepcount") ||
				ReadUserInput.getUserIntent(inputStr, "walk")){
			output = LuceneSearch.getTotalSteps(strDate);
		} else if (ReadUserInput.getUserIntent(inputStr, "activity")||
				ReadUserInput.getUserIntent(inputStr, "activities")) {
			output = LuceneSearch.getTotalSteps(strDate);
			output += ",\n";
			output += LuceneSearch.getRunningData(strDate);
		}  else {
			output = "Sorry, I did not understand that. ";
		}
		endTime = System.currentTimeMillis();
		elapsedTime = endTime - startTime;
		output += " [in " + elapsedTime + " ms]";
		return output;
	}
	@SuppressWarnings("resource")


	public static String run(String input, String database) throws SQLException, java.text.ParseException, IOException,
			org.apache.lucene.queryparser.classic.ParseException {
		/* database could be -
			brute
			lucene
			mongo
			mysql
		*/
		switch (database) {
			case "brute":
				return plainSearch(input);
			case "lucene":
				return luceneSearch(input);
			case "mongo":
				return mongodbSearch(input);
			case "mysql":
				return mysqlSearch(input);
			default:
				return plainSearch(input);
		}
	}


	public static void main(String[] args) throws SQLException, IOException, ParseException, java.text.ParseException,
			org.apache.lucene.queryparser.classic.ParseException {
		
		// comment this out if dbs already set up
//		setUpDB();
//		String inputStr = "how many steps on june 07 2016";
//
//		System.out.println(mysqlSearch(inputStr));
//		System.out.println(mongodbSearch(inputStr));
//		System.out.println(plainSearch(inputStr));
//		System.out.println(luceneSearch(inputStr));


	}
	

}
