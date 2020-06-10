package discordManagement;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;

import bd.ConnectionTools;

import java.sql.Connection;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import servicesTools.HubTools;
import servicesTools.MessageTools;
import servicesTools.RoomTools;
import servlets.discord.ConnectDiscordBot;

public class Listener implements EventListener {
	@Override
	public void onEvent(GenericEvent event) {
		System.out.println("-----> Nouvel evenement reçu !");
		System.out.println(event);
		if(event instanceof MessageReceivedEvent){
			try {
				onMessage((MessageReceivedEvent) event);
			} catch (Exception e) {
				System.out.println("-----> Erreur lors du traitement du message");
				e.printStackTrace();
			}
		}
	}

	private void onMessage(MessageReceivedEvent event) throws UnknownHostException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException { // TRIEUR DE MESSAGES
		if(event.getAuthor().equals(event.getJDA().getSelfUser())) return; // >>> le bot ne s'écoute pas lui même
		
		Connection c = ConnectDiscordBot.getDBConnection();
		String user = event.getAuthor().getName();
		String message = event.getMessage().getContentDisplay(); // <<< -- Message reçu
		String room_id = event.getChannel().getId();
		
		// vérifier que la room du message que l'on vient de recevoir existe dans les serveurs du site
		if(RoomTools.roomExistId(c, room_id)){
			String hub_id = HubTools.getHubFromRoom(c, room_id);

			// On enregistre le message dans la base de données
			MessageTools.addMessage(c, user, message, hub_id);

			// On transmet le message dans toutes les autre rooms de ce hub
			List<String> destination_rooms_id = HubTools.getRoomsFromRoomViaHub(c, room_id);

			for(String destination_room_id : destination_rooms_id){
				if(destination_room_id != room_id){
					DiscordAPI.sendMessage(user, message, room_id);
				}
			}
		}


		


		
	}
}