package bd;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;



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
	
}
