package servlets.discord;

import javax.security.auth.login.LoginException;

import discordManagement.Listener;
import discordManagement.Token;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
//import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class ConnectDiscordBot {
	
		public static JDA jda;
		public ConnectDiscordBot(){
			JDABuilder builder = new JDABuilder (AccountType.BOT);
			builder.setToken(Token.TOKEN);
			builder.setAutoReconnect(true);
			builder.setStatus(OnlineStatus.ONLINE);
			
			try {
				jda = builder.build();
				jda.addEventListener(new Listener());
			} catch (LoginException e) {
				e.printStackTrace();
			}
			System.out.println("Bot chargé");
		}
}