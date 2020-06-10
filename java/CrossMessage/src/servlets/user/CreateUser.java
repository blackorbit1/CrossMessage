package servlets.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

@WebServlet( name="CreateUser", urlPatterns = "/CreateUser")
public class CreateUser extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse rep ) throws IOException{
		String fname = req.getParameter("fname");
		String name = req.getParameter("name");
		String login = req.getParameter("login");
		String pwdhash = req.getParameter("pwdhash");
		PrintWriter out = rep.getWriter();
		rep.setContentType("text/plain");
		JSONObject ret = new JSONObject();
		
			try {
				ret = services.UserService.createUser(fname, name, login, pwdhash);
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
