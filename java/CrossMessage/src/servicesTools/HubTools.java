package servicesTools;

import java.security.SecureRandom;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bd.Database;

public class HubTools {
	private static boolean debug = true;

    public static void addBDHub(Connection c, String hubName) throws ClassNotFoundException, SQLException{
		String query= "insert into hub(name) values (\""+hubName+"\");";
		if(debug)
			System.out.println(query);
		Statement st = c.createStatement();
		st.executeUpdate(query);
		st.close();
	}

	public static boolean hubExist(Connection c,  String hubName) throws ClassNotFoundException, SQLException{
		boolean ret;
		String query= "select * from hub where name=\""+hubName+"\";";
		if(debug)
			System.out.println(query);
		Statement st = c.createStatement();
		ResultSet rs= st.executeQuery(query);
		ret = rs.next(); // s'il existe au moins une ligne
		rs.close();
		st.close();
		return ret;
	}

	public static List<String> getHubRooms(Connection c, String hubName) throws ClassNotFoundException, SQLException {
		ArrayList<String> liste_rooms = new ArrayList<>();
		String id_hub = "";
		String query_hub = "select id from hub where name=\""+hubName+"\";";

		Statement st_hub = c.createStatement();
		ResultSet rs_hub = st_hub.executeQuery(query_hub);

		if(rs_hub.next()){
			id_hub = rs_hub.getString("id");
			String query_room = "select id_discord_room from room where id_hub=\""+id_hub+"\";";

			Statement st_room = c.createStatement();
			ResultSet rs_room = st_hub.executeQuery(query_room);

			while(rs_room.next()){
				liste_rooms.add(rs_room.getString("id_discord_room"));
			}
			rs_room.close();
			st_room.close();
		} 
		rs_hub.close();
		st_hub.close();	

		return liste_rooms;
	}

	
	public static String getHubFromRoom(Connection c, String room_id) throws ClassNotFoundException, SQLException {
		String id_hub = "";
		String query = "select id_hub from room where id=\"" + room_id + "\";";

		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(query);

		if(rs.next()) id_hub = rs.getString("id_hub");
		
		rs.close();
		st.close();	

		return id_hub;
	}
	


	public static List<String> getHubs(Connection c) throws ClassNotFoundException, SQLException {
		ArrayList<String> liste_hubs = new ArrayList<>();
		String query = "select name from hub";

		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(query);

		while(rs.next()){
			liste_hubs.add(rs.getString("name"));
		}
		rs.close();
		st.close();
		
		return liste_hubs;
	}


	public static List<String> getRoomsFromRoomViaHub(Connection c, String room_id) throws ClassNotFoundException, SQLException {
		ArrayList<String> liste_rooms = new ArrayList<>();
		String id_hub = "";
		String query_hub = "select id_hub from room where id_discord_room=\""+room_id+"\";";

		Statement st_hub = c.createStatement();
		ResultSet rs_hub = st_hub.executeQuery(query_hub);

		if(rs_hub.next()){
			id_hub = rs_hub.getString("id");
			String query_room = "select id_discord_room from room where id_hub=\""+id_hub+"\";";

			Statement st_room = c.createStatement();
			ResultSet rs_room = st_room.executeQuery(query_room);

			while(rs_room.next()){
				liste_rooms.add(rs_room.getString("id_discord_room"));
			}
			rs_room.close();
			st_room.close();
		} 
		rs_hub.close();
		st_hub.close();	


		return liste_rooms;
	}

}