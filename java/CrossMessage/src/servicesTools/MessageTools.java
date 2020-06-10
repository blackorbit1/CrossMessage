package servicesTools;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

//import com.mongodb.BasicDBObject;
//import com.mongodb.DBCollection;
//import com.mongodb.DBCursor;
//import com.mongodb.DBObject;

import bd.ConnectionTools;
import bd.Database;

public class MessageTools {
		
	
	public static void addMessage(Connection c, String user, String message, String hub_id) throws UnknownHostException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		String query= "insert into message(id_hub, message_content, user) values (\""+hub_id+"\",\""+message+"\" , \""+user+"\");";
		Statement st = c.createStatement();
		st.executeUpdate(query);
		st.close();
	}
	
	public static JSONObject getMessage(String requete, List<Integer> userIdList){
		JSONObject ret = new JSONObject();
		return ret;
	}

	

}
