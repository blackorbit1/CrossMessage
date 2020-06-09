package servlets.user;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;



public class HelloWorld extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		/*JSONObject ret = new JSONObject();
		try {
			ret.put("hello", "world");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/plain");
		out.print("HELOOOOO");
	}
}


