package dbs;

public class Constants {

	// connection info for mysql
	public static final String DB_URL = "jdbc:mysql://localhost:3306/met622?useTimezone=true&serverTimezone=UTC";
	public static final String USER = "root";
	public static final String PASSWORD = "root";
	
	// connection info for mongodb
	public static final String MONGO_DB_PATH = "met622";
	
	// sql create table statements
	public static final String ACTIVFIT_SQL = 
			"CREATE TABLE activfit" +
			"(id INT AUTO_INCREMENT PRIMARY KEY," +
			"sensor_name VARCHAR(20)," +
			"start_time DATETIME," +
			"end_time DATETIME," +
			"activity VARCHAR(20)," +
			"duration INT)";
	
	public static final String ACTIVITY_SQL = 
			"CREATE TABLE activity" +
			"(id INT AUTO_INCREMENT PRIMARY KEY," +
			"sensor_name VARCHAR(20)," +
			"time DATETIME," +
			"step_count INT," +
			"step_delta INT)";
 	
	public static final String BATTERY_SQL =
			"CREATE TABLE battery" +
			"(id INT AUTO_INCREMENT PRIMARY KEY," +
			"sensor_name VARCHAR(20)," +
			"time TIMESTAMP," +
			"percentage INT," +
			"charging BOOLEAN)";
	
	public static final String HEARTRATE_SQL =
			"CREATE TABLE heartrate" +
			"(id INT AUTO_INCREMENT PRIMARY KEY," +
			"sensor_name VARCHAR(20)," +
			"time DATETIME," +
			"bpm INT)";
	
	public static final String BLUETOOTH_SQL =
			"CREATE TABLE bluetooth" +
			"(id INT AUTO_INCREMENT PRIMARY KEY," +
			"sensor_name VARCHAR(20)," +
			"time DATETIME," +
			"state VARCHAR(20))";
	
	public static final String LIGHT_SQL =
			"CREATE TABLE light" +
			"(id INT AUTO_INCREMENT PRIMARY KEY," +
			"sensor_name VARCHAR(20)," +
			"time DATETIME," +
			"lux DOUBLE)";
	
	public static final String SCREEN_SQL =
			"CREATE TABLE screen" +
			"(id INT AUTO_INCREMENT PRIMARY KEY," +
			"start_time DATETIME," +
			"end_time DATETIME," +
			"mins_elapsed DOUBLE)";
}
