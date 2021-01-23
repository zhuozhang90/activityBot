package dbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class ConnectDB {
	// return connection to mysql db
	public static Connection connectMySQL() throws SQLException {
		return DriverManager.getConnection
				(Constants.DB_URL, Constants.USER, Constants.PASSWORD);
	}
	
	// return connection to mongo db
	public static MongoDatabase connectMongoDB() {
		// initialize the client object
		 @SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient();
		 // get the 'test' dataset
		 MongoDatabase dbObj = mongoClient.getDatabase(Constants.MONGO_DB_PATH);
		return dbObj; 
	}
	
}
