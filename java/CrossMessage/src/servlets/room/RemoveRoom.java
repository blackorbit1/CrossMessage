package servlets.room;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import org.json.JSONException;
import org.json.JSONObject;
@WebServlet( name="RemoveRoom", urlPatterns = "/RemoveRoom")

public class RemoveRoom extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse rep ) throws IOException{

		String idDiscordRoom = req.getParameter("id_discord_room"); 
		PrintWriter out = rep.getWriter();
		rep.setContentType("text/plain");
		JSONObject ret = new JSONObject();
		
		try {
			ret = services.RoomService.removeRoom(idDiscordRoom);
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
