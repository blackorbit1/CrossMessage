package discordManagement;

import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bd.ConnectionTools;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import servicesTools.HubTools;
import servlets.discord.ConnectDiscordBot;

public class DiscordAPI {
	
    public static void sendMessage(String user, String message, String room){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.GRAY);
        eb.setAuthor(user, null, null);
        eb.setDescription(message);

        TextChannel tc = ConnectDiscordBot.getJDA().getTextChannelById(room);
        tc.sendMessage(eb.build()).complete();
    }


    public static void sendMessageToHub(String user, String message, String hub) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        //ArrayList<TextChannel> listTextChannels = ConnectDiscordBot.getJDA().getTextChannels();
    	Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection c = ConnectionTools.getMySQLConnection();
        List<String> listTextChannels = HubTools.getHubRooms(c, hub);
        c.close();
        
        
        // On va envoyer le message dans chaque room
        for(String tc_id : listTextChannels){
            sendMessage(user, message, tc_id);
        }
    }
}