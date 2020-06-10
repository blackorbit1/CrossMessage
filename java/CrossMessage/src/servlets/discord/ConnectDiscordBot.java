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
				System.out.println("Bot OK");
			} catch (LoginException e) {
				System.out.println("------> Erreur, le bot l'a pas pu etre chargé !");
				e.printStackTrace();
			}

			


			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				this.co = ConnectionTools.getMySQLConnection();
				System.out.println("BDD OK");
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				System.out.println("------> Erreur, la base de données n'a pas pu etre chargé !");
				e.printStackTrace();
			}

			
		}

		public static JDA getJDA(){
			return jda;
		}
		public static Connection getDBConnection(){
			return co;
		}
}