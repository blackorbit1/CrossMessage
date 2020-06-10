package services;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import com.mongodb.BasicDBObject;

import bd.ConnectionTools;
import bd.Database;
import servicesTools.MessageTools;
import servicesTools.ServiceRefused;
import servicesTools.UserTools;

public class MessageService {

	public static JSONObject addMessage(String key, String message, String hub_id) throws JSONException, UnknownHostException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
		JSONObject ret = new JSONObject();
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection c = ConnectionTools.getMySQLConnection();
		String user = Database.getPseudoFromKey(key);
		MessageTools.addMessage(c, user, message, hub_id);
		c.close();
		ret.put("status", "OK");
		ret.put("effect", "posted !");
		return ret;
	}
	
//	public static JSONObject deleteMessage(String key, String idMessage) throws JSONException, ClassNotFoundException, SQLException, UnknownHostException, InstantiationException, IllegalAccessException{
//		Class.forName("com.mysql.jdbc.Driver").newInstance();
//		Connection c = ConnectionTools.getMySQLConnection();
//		if(MessageTools.messageBelongsToUser(key, idMessage) || UserTools.keyIsRoot(c, key)){
//			JSONObject ret = new JSONObject();
//			MessageTools.deleteMessage(idMessage);
//			ret.put("status", "OK");
//			ret.put("effect", "message deleted");
//			return ret;
//		}else
//			return ServiceRefused.serviceRefused("message does not belong to user", 7);
//	}
	
}
