package discordManagement;

import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class Listener implements EventListener {
	@Override
	public void onEvent(GenericEvent event) {
		if(event instanceof MessageReceivedEvent){
			onMessage((MessageReceivedEvent) event);
		}
	}
	private void onMessage(MessageReceivedEvent event){ // TRIEUR DE MESSAGES
        if(event.getAuthor().equals(event.getJDA().getSelfUser())) return; // >>> le bot ne s'écoute pas lui même
        
        
        String message = event.getMessage().getContentDisplay(); // <<< -- Message reçu
        
        // traiter le message

		
	}


}