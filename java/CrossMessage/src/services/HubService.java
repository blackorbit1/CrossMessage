package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.json.JSONException;
import org.json.JSONObject;

import bd.ConnectionTools;
import bd.Database;
import servicesTools.HubTools;
import servicesTools.ServiceRefused;
import servicesTools.UserTools;

public class HubService {
	
	
	public static JSONObject createHub(String hubName) throws JSONException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
		JSONObject ret = new JSONObject();
		if(hubName==null){
			ret=ServiceRefused.serviceRefused("blank field", 2);
			return ret;
		}
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection c= ConnectionTools.getMySQLConnection();
		if(UserTools.userExists(c, "")){
			ret=ServiceRefused.serviceRefused("user already exists", 1);
		}
		else{
			HubTools.addBDHub(c, hubName);
			ret.put("status", "OK");
			ret.put("effect", "hub created");
		}
		c.close();
		return ret;
	}

}