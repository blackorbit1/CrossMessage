package servelts;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/Hello")
public class Hello extends HttpServlet {
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("param");
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		out.print("HELLOOOOO\nPour tester les parametres, il faut append a la fin de l'url ?param=[text] et text sera afficher ci-dessous \n" + param);
	}

	

}
