package servlets.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import services.MessageService;

//import com.mongodb.BasicDBObject;


@WebServlet( name="ListMessage", urlPatterns = "/ListMessage")
public class ListMessage extends HttpServlet{
//	protected void doGet(HttpServletRequest req, HttpServletResponse rep ) throws IOException{
//		Map<String,String[]> pars = req.getParameterMap();
//		
//		String userId = req.getParameter("userId");
//		List<Integer> listeId = new ArrayList<Integer>();
//
//		PrintWriter out = rep.getWriter();
//		rep.setContentType("text/plain");
//		BasicDBObject ret = new BasicDBObject();
//		if(pars.containsKey("userId")){
//			try {
//				ret = MessageService.listMessages(Integer.parseInt(pars.get("userId").toString()), null, null);
//			} catch (NumberFormatException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//		} else
//			try {
//				ret = MessageService.listMessages(-1, null, null);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//	
//	
//		out.print(ret.toString());
//	}
}
