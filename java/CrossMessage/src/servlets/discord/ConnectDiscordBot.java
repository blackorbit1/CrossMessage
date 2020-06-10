package servlets.discord;

import javax.security.auth.login.LoginException;

import java.sql.Connection;
import java.sql.SQLException;

import bd.ConnectionTools;
import discordManagement.Listener;
import discordManagement.Token;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
//import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class ConnectDiscordBot {
	
		private static JDA jda;
		private static Connection co;

		public ConnectDiscordBot(){
			JDABuilder builder = new JDABuilder (AccountType.BOT);
			builder.setToken(Token.TOKEN);
			builder.setAutoReconnect(true);
			builder.setStatus(OnlineStatus.ONLINE);
			
			try {
				jda = builder.build();
				jda.addEventListener(new Listener());
			} catch (LoginException e) {
				System.out.println("------> Erreur, le bot l'a pas pu etre chargé !");
				e.printStackTrace();
			}

			System.out.println("Bot OK");


			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				this.co = ConnectionTools.getMySQLConnection();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("BDD OK");
		}

		public static JDA getJDA(){
			return jda;
		}
		public static Connection getDBConnection(){
			return co;
		}
}