package marketapp.controller.uf2406exercice1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Raúl
 * 
 * UF2406 - Ejercicio práctico 1 - 02-06-2020
 *
 */

@WebServlet("/apartado-a")

public class ApartadoAController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// We are not going to use this method
		doPost(request, response);
	
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Declaring variables to get parameters from view
		String userName = request.getParameter("userName");
		String favouriteColor = request.getParameter("favouriteColor");
		
		// Creating cookies with user values
		Cookie cUserName = new Cookie("userName", userName);
		Cookie cFavouriteColor = new Cookie("favouriteColor", favouriteColor);
		
		// Adding the data to response
		response.addCookie(cUserName);
		response.addCookie(cFavouriteColor);		

		// Calling the view passing the data via redirect
		response.sendRedirect("resultadoA.jsp");		
		
	}

}
