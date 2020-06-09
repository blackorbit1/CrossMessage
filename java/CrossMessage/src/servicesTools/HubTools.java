package servicesTools;

import java.security.SecureRandom;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import bd.Database;

public class HubTools {


    public static void addBDHub(Connection c, String hubName) throws ClassNotFoundException, SQLException{
		//String query= "insert into user(login, nom, prenom, password, isRoot) values (\""+user+"\",\""+nom+"\" , \""+prenom+"\",PASSWORD(\""+mdp+"\"),0);";

//		System.out.println("insert into user values (\""+user+"\",\""+nom+"\" , \""+prenom+"\",\""+mdp+"\")");
		Statement st = c.createStatement();
		//st.executeUpdate(query);
		st.close();
		
	}
}