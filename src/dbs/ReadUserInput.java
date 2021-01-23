package dbs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.ocpsoft.prettytime.nlp.PrettyTimeParser;


public class ReadUserInput {
	
	public static String readInputDate(String inputStr) {
		// get date from user input
		List<Date> dates = new PrettyTimeParser().parse(inputStr);
	    System.out.println(dates);
	    // format date into given format to match format used in dbs
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String strDate = formatter.format(dates.get(0)); 
	    
	    return strDate;
	}
	
	public static boolean getUserIntent(String inputStr, String intent) {
        for (String s : inputStr.split(" ")) {
            if (s.contains(intent)) return true;
        }
        return false;
 	
	}

}
