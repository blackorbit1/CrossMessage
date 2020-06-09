package bd;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

//import com.mongodb.DB;
//import com.mongodb.DBCollection;
//import com.mongodb.Mongo;

public class ConnectionTools {

	public static Connection getMySQLConnection() throws SQLException {
		Database database = null;
		if(DBStatic.mysql_pooling==false){
			return (DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host + "/"+DBStatic.mysql_db,DBStatic.mysql_username, DBStatic.mysql_password));
		} else {
			if(database==null) {
				database= new Database("jdbc/db");
			}
			return (database.getConnection());
		}
	}
	
//	public static DBCollection getMongoCollection(String nom_collection) throws UnknownHostException{
//		Mongo m = new Mongo(DBStatic.mongo_url);
//		DB db = m.getDB(DBStatic.mongo_db);
//		DBCollection message = db.getCollection(nom_collection);
//		return message;
//	}
}
