package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.json.JSONException;
import org.json.JSONObject;

import bd.ConnectionTools;
import bd.Database;
import servicesTools.HubTools;
import servicesTools.RoomTools;
import servicesTools.ServiceRefused;
import servicesTools.UserTools;

public class RoomService {
	
	public static JSONObject createRoom(String roomName, String hubName, String idDiscordRoom) throws JSONException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
		JSONObject ret = new JSONObject();
		if(hubName==null){
			ret=ServiceRefused.serviceRefused("hubName can\"t be empty", 2);
			return ret;
		}
		if(roomName==null){
			ret=ServiceRefused.serviceRefused("roomName can\"t be empty", 2);
			return ret;
		}
		if(idDiscordRoom==null){
			ret=ServiceRefused.serviceRefused("idDiscordRoom can\"t be empty", 2);
			return ret;
		}
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection c= ConnectionTools.getMySQLConnection();
		if(!HubTools.hubExist(c, hubName)){
			ret=ServiceRefused.serviceRefused("hubName:" + hubName + " not exists", 1);
		}
		else{
			RoomTools.addBDRoom(c, roomName, hubName, idDiscordRoom);
			ret.put("status", "OK");
			ret.put("effect", "room created");
		}
		c.close();
		return ret;
	}

	public static JSONObject removeRoom(String idDiscord) throws JSONException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
		JSONObject ret = new JSONObject();

		if(idDiscord==null){
			ret=ServiceRefused.serviceRefused("idDiscord can\"t be empty", 2);
			return ret;
		}
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection c= ConnectionTools.getMySQLConnection();
		
		RoomTools.rmBDRoom(c, idDiscord);
		ret.put("status", "OK");
		ret.put("effect", "hub created");
		
		c.close();
		return ret;
	}

}