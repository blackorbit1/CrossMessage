package servicesTools;

import org.json.JSONException;
import org.json.JSONObject;

public class ServiceRefused {
	
	
	public static JSONObject serviceRefused(String m, int idError) throws JSONException{
		JSONObject rep = new JSONObject();
		rep.put("status", "KO").put("message", m).put("idError", idError);
		return rep;
	}
}
