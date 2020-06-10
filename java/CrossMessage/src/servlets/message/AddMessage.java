package servlets.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

@WebServlet( name="AddMessage", urlPatterns = "/AddMessage")
public class AddMessage extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse rep ) throws IOException{
		String key = req.getParameter("key");
		String message = req.getParameter("message");
		String hub_id = req.getParameter("hub_id");
		PrintWriter out = rep.getWriter();
		rep.setContentType("text/plain");
		JSONObject ret = new JSONObject();

			try {
				ret = services.MessageService.addMessage(key, message, hub_id);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		out.print(ret.toString());
	}
}
