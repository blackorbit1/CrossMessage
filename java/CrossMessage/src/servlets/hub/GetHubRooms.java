package servlets.hub;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;


@WebServlet( name="GetHubRooms", urlPatterns = "/GetHubRooms")
public class GetHubRooms {
    protected void doGet(HttpServletRequest req, HttpServletResponse rep ) throws IOException{
		String hubName = req.getParameter("hubName");
		String roomName = req.getParameter("roomName");
		String idDiscordRoom = req.getParameter("idDiscordRoom");
		
		PrintWriter out = rep.getWriter();
        rep.setContentType("text/plain");
        
		JSONObject ret = new JSONObject();


		// utiliser getHubRooms()
		
		try {
			ret = services.HubService.getHubRooms(roomName, hubName, idDiscordRoom);
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