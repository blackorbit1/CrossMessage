package bd;

import java.net.UnknownHostException;
import java.sql.SQLException;

import org.json.JSONException;



import services.UserService;



public class TestBd {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, JSONException, SQLException {
		System.out.println(UserService.createUser("Romain", "Demangeon", "KazouSalad", "azerty").toString());


	}

}
