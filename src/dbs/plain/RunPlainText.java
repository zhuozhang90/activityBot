package dbs.plain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class RunPlainText {
	
	public static String getTotalSteps(String date) throws IOException {
		int totalSteps = 0;
		List<String> allLines = Files.readAllLines(Paths.get("C:\\Users\\zzhang\\Projects\\BU_MET\\eclipse-workspace\\cs622project\\activityplain.txt"));
		for (String line : allLines) {
			if (line.contains(date)) {
				int step = Integer.parseInt(line.split(",")[0].split(":")[1]);
				if (step > totalSteps) totalSteps = step;
			}
		}
		return "You had " + totalSteps + " steps on " + date;
	}
	
	public static String getRunningData(String date) throws IOException {
		List<String> allLines = Files.readAllLines(Paths.get("C:\\Users\\zzhang\\Projects\\BU_MET\\eclipse-workspace\\cs622project\\activfitplain.txt"));
		String res = "";
		for (String line : allLines) {
			if (line.contains(date) && line.contains("running")) {
				String start = line.split(",")[1].substring(13);
				String end = line.split(",")[3].substring(11);
				res += ("You ran from " + start + " to " + end + "\n");
			}
		}
		return (res.length() == 0) ? "You didn't run that day." : res;
	}

	public static void main(String[] args) throws IOException {
		System.out.println(getTotalSteps("2016-06-06"));
		System.out.println(getRunningData("2017-06-06"));
	}
}
