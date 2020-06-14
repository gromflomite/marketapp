package marketapp.controller.uf2406exercice1;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author Raúl
 * 
 * UF2406 - Ejercicio práctico 1 - 02-06-2020
 *
 */

@WebServlet("/apartado-b")

public class ApartadoBController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// We are not going to use this method
		doPost(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Declaring variables to get parameters from view
		String userName = request.getParameter("userName");
		String loginTime = "";

		// Getting the system hour and format it to human readable string
		LocalDateTime rawLoginTime = LocalDateTime.now();
		loginTime = rawLoginTime.format(DateTimeFormatter.ofPattern("HH:mm" + " (" + "dd MMMM yyyy" + ")"));

		// Creating new session to store user data
		HttpSession session = request.getSession();

		// Saving data to the session
		session.setAttribute("userName", userName);
		session.setAttribute("loginTime", loginTime);
		
		// Calling the view (we are NOT sending data back to the view, all the info needed in the view
		// will be recovered from session)
		request.getRequestDispatcher("uf2406/resultadoB.jsp").forward(request, response);		

	}

}
