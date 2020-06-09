package servicesTools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import bd.Database;

public class FriendTools {

	public static void addFriend(Connection c, int userId, int friendId) throws SQLException {
		String query = "insert into friend(userID, friendID) values (\""
				+ userId + "\",\"" + friendId + "\")";
		System.out.println(query);
		Statement st = c.createStatement();
		st.executeUpdate(query);
		st.close();
	}
	
	
	public static void removeFriend(Connection c, int userId, int friendId) throws SQLException {
		String query= "delete from friend where userID=\""+userId+"\" and friendID=\""+friendId+"\";";
		Statement st = c.createStatement();
		st.executeUpdate(query);
		st.close();
	}
	
	public static List<String> listFriend(Connection c, int userID) throws SQLException{
		List<String> friendList = new ArrayList<String>();
		String query= "select friendID from friend where userID=\""+userID+"\";";
		Statement st = c.createStatement();
		ResultSet rs= st.executeQuery(query);
		while(rs.next()){
			friendList.add(rs.getString("friendID"));
		}
		rs.close();
		st.close();
		return friendList;
	}

}
