package servlets.hub;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

@WebServlet( name="CreateHub", urlPatterns = "/CreateHub")
public class CreateHub extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse rep ) throws IOException{
		String hubName = req.getParameter("hubName"); // il y a juste le nom
		PrintWriter out = rep.getWriter();
		rep.setContentType("text/plain");
		JSONObject ret = new JSONObject();
		
		try {
			ret = services.HubService.createHub(hubName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.print(ret.toString());
	}
	
}
