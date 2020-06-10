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
//		//recuperation du user_id a partir de la key
//		int userID = Database.getUserId(key);
//		//recuperation des messages
//		DBCollection message= ConnectionTools.getMongoCollection("message");
//		//creation du message
//		BasicDBObject dbo = new BasicDBObject();
//		dbo.put("userID", userID);
//		dbo.put("Date",Database.getDate(0).toString());
//		dbo.put("content", content);
//		dbo.put("comments", null);
//		message.insert(dbo);
//		System.out.println(dbo.toString());
		String query= "insert into message(id_hub, message_content, user) values (\""+hub_id+"\",\""+message+"\" , \""+user+"\");";
		Statement st = c.createStatement();
		st.executeUpdate(query);
		st.close();
	}
	
	
	
	public static boolean messageBelongsToUser(String key, String idMessage) throws ClassNotFoundException, SQLException, UnknownHostException, InstantiationException, IllegalAccessException{
//		int user_id = Database.getUserId(key);
//		DBCollection message= ConnectionTools.getMongoCollection("message");
//		BasicDBObject idm = new BasicDBObject("_id", new ObjectId(idMessage));
//		BasicDBObject uid = new BasicDBObject("userID", user_id);
//		List<BasicDBObject> lo= new ArrayList<>();
//		lo.add(idm);
//		lo.add(uid);
//		if(message.find(new BasicDBObject("$and", lo ))!=null) {
//			return true;
//		}
		return false;
	}
	
	
	public static void deleteMessage(String idMessage) throws UnknownHostException{
//		DBCollection message= ConnectionTools.getMongoCollection("message");
//		message.remove(new BasicDBObject("_id", new ObjectId(idMessage)));
		
	}

//	public static BasicDBObject getMessage() throws UnknownHostException{
//		DBCollection message= ConnectionTools.getMongoCollection("message");
//		BasicDBObject dbo = new BasicDBObject();
//		dbo.put("messages",message.find());
//		return dbo;
//	}
	
	
//	public static BasicDBObject getMessage(int userId) throws UnknownHostException{
//		DBCollection message= ConnectionTools.getMongoCollection("message");
//		BasicDBObject dbo = new BasicDBObject();
//		DBObject query = new BasicDBObject("user_id", userId);
//		dbo.put("messages",message.find(query) );
//		return dbo;
//	}
	
	
	public static JSONObject getMessage(String requete, List<Integer> userIdList){
		JSONObject ret = new JSONObject();
		return ret;
	}

	

}
