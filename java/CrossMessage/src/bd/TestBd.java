package bd;

import java.net.UnknownHostException;
import java.sql.SQLException;

import org.json.JSONException;


import services.MessageService;
import services.UserService;
import servicesTools.FriendTools;


public class TestBd {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, JSONException, SQLException {
		System.out.println(UserService.createUser("Romain", "Demangeon", "KazouSalad", "azerty").toString());
//			try {
////				System.out.println(MessageService.addMessage("eyR17BD9u3NbiEruFvEx0UMS3u4ps4hn", "y a-t-il des commentaires").toString());
//				System.out.println(MessageService.deleteMessage("V9ZRXKPgfkND2vbAI2y0VPZk3KDTLwuX", "5ae5d7c9c8e446c00f84d7ba").toString());
//////				System.out.println(UserService.login("EH", "mdp").toString());
//////				System.out.println(UserService.logout("UFBUI5g1OlIPgJ30D25M2h3CWrlSM3GR"));
//////				System.out.println(UserService.logout("mvTNmT8DhJIDfUMRoXTkRgavcMohw6X2"));
//////				System.out.println(UserService.createUser("Ethan", "Hawk", "EH", "mdp").toString());
//			}
//			catch (UnknownHostException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		

	}

}
