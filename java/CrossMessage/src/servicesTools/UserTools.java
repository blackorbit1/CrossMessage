package servicesTools;

import java.security.SecureRandom;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import bd.Database;

public class UserTools {
	
	public static boolean userExists(Connection c, String user) throws ClassNotFoundException, SQLException{
		boolean ret;
		String query= "select * from user where login=\""+user+"\";";
		Statement st = c.createStatement();
		ResultSet rs= st.executeQuery(query);
		if(rs.next()) ret= true;
		else ret=false;
		rs.close();
		st.close();
		return ret;
	}
	
	public static boolean userExists(Connection c,  int userID) throws ClassNotFoundException, SQLException{
		boolean ret;
		String query= "select * from user where userID=\""+userID+"\";";
		Statement st = c.createStatement();
		ResultSet rs= st.executeQuery(query);
		if(rs.next()) ret= true;
		else ret=false;
		rs.close();
		st.close();
		return ret;
	}
	
	public static void addBDUser(Connection c, String nom, String prenom, String user, String mdp) throws ClassNotFoundException, SQLException{
		String query= "insert into user(login, nom, prenom, password, isRoot) values (\""+user+"\",\""+nom+"\" , \""+prenom+"\",PASSWORD(\""+mdp+"\"),0);";

//		System.out.println("insert into user values (\""+user+"\",\""+nom+"\" , \""+prenom+"\",\""+mdp+"\")");
		Statement st = c.createStatement();
		st.executeUpdate(query);
		st.close();
		
	}
	
	public static boolean checkPassword(Connection c, String user, String pwd) throws ClassNotFoundException, SQLException{
		boolean ret;
		String query=  "select * from user where login=\""+user+"\" AND password=PASSWORD(\""+pwd+"\");";
		Statement st = c.createStatement();
		ResultSet rs= st.executeQuery(query);
		if(!rs.next()) ret=false;
		else ret=true;
		rs.close();
		st.close();
		return ret;
	}
	
	public static String insererConnexion(Connection c, String login, boolean root) throws SQLException, ClassNotFoundException{
		SecureRandom r = new SecureRandom();
		String str="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String key = "";
		for(int i=0; i<32; i++) {
			key += str.charAt(r.nextInt(str.length()));
		}
		int tmp =(root)?1:0;
		int userID = Database.getUserIdFromLogin(c, login);
		String query= "insert into session(userID, isRoot, cle) values ("+userID+","+tmp+",\""+key+"\");";
		Statement st = c.createStatement();
		st.executeUpdate(query);
		st.close();
		return key;
	}
	
	public static boolean userIsRoot(Connection c, String login) throws SQLException, ClassNotFoundException{
		boolean ret;
		String query= "select isRoot from user where login=\""+login+"\";";
		Statement st = c.createStatement();
		ResultSet rs= st.executeQuery(query);
		if(!rs.next()) throw new SQLException("utilisateur etrangement inexistant");
		int root = rs.getByte("isRoot");
		ret = (root==0)?false:true;
		rs.close();
		st.close();
		return ret;
	}
	
	public static boolean keyIsRoot(Connection c, String key) throws SQLException, ClassNotFoundException{
		boolean ret;
		String query= "select isRoot from session where cle=\""+key+"\";";
		Statement st = c.createStatement();
		ResultSet rs= st.executeQuery(query);
		if(!rs.next()) throw new SQLException("utilisateur etrangement inexistant");
		int root = rs.getByte("isRoot");
		ret = (root==0)?false:true;
		rs.close();
		st.close();
		return ret;
	}
	
	public static boolean connectionExists(Connection c, String key) throws ClassNotFoundException, SQLException{
		boolean ret;
		String query= "select * from session where cle=\""+key+"\";";
		Statement st = c.createStatement();
		ResultSet rs= st.executeQuery(query);
		if(rs.next()) ret= true;
		else ret=false;
		rs.close();
		st.close();
		return ret;
	}
	
	public static void rmConnection(Connection c, String key) throws ClassNotFoundException, SQLException{
		//TODO faudrait-il plutot actualiser le timestamp fin à NOW()  ?
		String query= "delete from session where cle=\""+key+"\";";
//		System.out.println(query);
		Statement st = c.createStatement();
		st.executeUpdate(query);
		st.close();
	}
}
