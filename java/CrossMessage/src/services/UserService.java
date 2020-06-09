package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.json.JSONException;
import org.json.JSONObject;

import bd.ConnectionTools;
import bd.Database;
import servicesTools.ServiceRefused;
import servicesTools.UserTools;

public class UserService {
	
	
	public static JSONObject createUser(String nom, String prenom, String user, String pwdhash) throws JSONException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
		JSONObject ret = new JSONObject();
		if(nom==null || prenom==null || user==null || pwdhash==null){
			ret=ServiceRefused.serviceRefused("blank field", 2);
			return ret;
		}
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection c= ConnectionTools.getMySQLConnection();
		if(UserTools.userExists(c, user)){
			ret=ServiceRefused.serviceRefused("user already exists", 1);
		}
		else{
			UserTools.addBDUser(c, nom, prenom, user, pwdhash);
			ret.put("status", "OK");
			ret.put("effect", "user created");
		}
		c.close();
		return ret;
	}
	
	public static JSONObject login(String login, String pwd) throws JSONException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
		JSONObject ret = new JSONObject();
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection c= ConnectionTools.getMySQLConnection();
		if(login==null || pwd==null){
			ret=ServiceRefused.serviceRefused("blank field", 2);
		}
		
		else if(!UserTools.userExists(c, login)){
			ret=ServiceRefused.serviceRefused("user does not exist", 3);
		}
		else if(!UserTools.checkPassword(c, login, pwd)){
			ret=ServiceRefused.serviceRefused("incorrect password", 4);
		}
		else{
			String key =UserTools.insererConnexion(c, login, UserTools.userIsRoot(c, login));
			ret.put("status", "ok").put("id",Database.getUserId(c,key)).put("key",key).put("login", login);
		}
		c.close();
		return ret;
	}
	
	public static JSONObject logout(String key) throws JSONException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		JSONObject ret= new JSONObject();
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection c= ConnectionTools.getMySQLConnection();
		if (key==null) {
			ret=ServiceRefused.serviceRefused("blank field", 2);
		}
		else if (UserTools.connectionExists(c, key)) {
			UserTools.rmConnection(c, key);
			ret.put("status", "ok");
			ret.put("effect", "disconnected");
			
		} else {
			ret=ServiceRefused.serviceRefused("unknown connexion key",5);
		}
		c.close();
		return ret;
	}
}
