package servicesTools;

import java.security.SecureRandom;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import bd.Database;

public class RoomTools {
    private static boolean debug = true;

    public static void addBDRoom(Connection c, String roomName, String hubName, String idDiscord) throws ClassNotFoundException, SQLException{
        String id_hub = "";
        String query_hub = "select id from hub where name=\""+hubName+"\";";

        if(debug)
            System.out.println(query_hub);

        Statement st_hub = c.createStatement();
        ResultSet rs_hub = st_hub.executeQuery(query_hub);
        if(rs_hub.next()){
            id_hub = rs_hub.getString("id");
            String query_room = "select id_discord_room from room where id_hub=\""+id_hub+"\";";
            if(debug)
                System.out.println(query_room);
            Statement st_room = c.createStatement();
            ResultSet rs_room = st_hub.executeQuery(query_room);
            rs_room.close();
            st_room.close();
        } 
        rs_hub.close();
        st_hub.close();

        String query= "insert into room(id_hub, id_discord_room, name) values (\"" + id_hub + "\", \"" + idDiscord + "\", \"" + roomName + "\");"; 
        if(debug)
            System.out.println(query);
        Statement st = c.createStatement();
        st.executeUpdate(query);
        st.close();
    }

    public static void rmBDRoom(Connection c, String idDiscord) throws SQLException{
        String query= "delete from room where id_discord_room =\"" + idDiscord + "\";" ; 
        if(debug)
            System.out.println(query);
        Statement st = c.createStatement();
        st.executeUpdate(query);
        st.close();
    }

    public static boolean roomExist(Connection c,  String roomName) throws ClassNotFoundException, SQLException{
        boolean ret;
        String query= "select * from room where name=\""+ roomName +"\";";
        if(debug)
            System.out.println(query);
        Statement st = c.createStatement();
        ResultSet rs= st.executeQuery(query);
        ret = rs.next(); // s'il existe au moins une ligne
        rs.close();
        st.close();
        return ret;
    }

    public static boolean roomExistId(Connection c,  String roomId) throws ClassNotFoundException, SQLException{
        boolean ret;
        String query= "select * from room where id_discord_room=\""+ roomId +"\";";
        if(debug)
            System.out.println(query);
        Statement st = c.createStatement();
        ResultSet rs= st.executeQuery(query);
        ret = rs.next(); // s'il existe au moins une ligne
        rs.close();
        st.close();
        return ret;
    }
}