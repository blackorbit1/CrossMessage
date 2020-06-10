package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.json.JSONArray;
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
			ret=ServiceRefused.serviceRefused("hubName can\"t be empty", 2);
			return ret;
		}
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection c = ConnectionTools.getMySQLConnection();
		if(HubTools.hubExist(c, hubName)){
			ret=ServiceRefused.serviceRefused("hubName:" + hubName + " already exists", 1);
		}
		else{
			HubTools.addBDHub(c, hubName);
			ret.put("status", "OK");
			ret.put("effect", "hub created");
		}
		c.close();
		return ret;
	}

	public static JSONObject getHubs() throws JSONException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection c = ConnectionTools.getMySQLConnection();

		// On récupere tous les hubs de la base de données
		List<String> list_hubs = HubTools.getHubs(c);
		JSONObject hubs = new JSONObject();

		// on crée un JSON de la forme {"hubs" : ["hub1", "hub2", "hub3"]}
		hubs.put("hubs", new JSONArray(list_hubs));

		c.close();
		return hubs;
	}




}