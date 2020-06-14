package marketapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Getting session
		HttpSession session = request.getSession();

		// Creating variables to store the values
		String selectedLang = "";
		String checkRememberMe = "";

		// Retrieve and iterate the cookies to retrieve values
		Cookie[] cookies = request.getCookies(); // Getting the existing cookies
		if (cookies != null) { // If no cookies protection

			for (Cookie c : cookies) {

				if (c.getName().equals("rememberMe")) { // Get the cookie by name
					checkRememberMe = c.getValue(); // Store the value of "RememberMe" ("on" -> selected / null -> not
													// selected)
				}

				if (c.getName().equals("selectedLang")) { // Get the cookie by name
					selectedLang = c.getValue(); // Store the value of the language selected at login form
				}
			}
		}

		// The user checked "Remember me" -> Don't delete cookies
		if ("on".equals(checkRememberMe)) {

			// Creating feedback according to selected language
			switch (selectedLang) {

			case "es":
				request.setAttribute("feedback",
						new Feedback("success", "Sesión finalizada, hasta pronto!! (Mantenemos las cookies)"));
				break;

			case "en":
				request.setAttribute("feedback",
						new Feedback("success", "Session ended, see you soon!! (Cookies remain)"));
				break;

			case "eu":
				request.setAttribute("feedback",
						new Feedback("success", "EU!! Session ended, see you soon!! (Cookies remain)"));
				break;

			// WE DO NOT DELETE COOKIES VALUES
			}

			
		} else { // NOT checked "Remember me" -> DELETE cookies value and set them to expiry inmediatly

			// Creating the cookies again with null
			Cookie CLoginTime = new Cookie("loginTime", null);
			Cookie CSelectedLang = new Cookie("selectedLang", null);
			Cookie CRememberMe = new Cookie("rememberMe", null);

			// Setting negative values to delete the content of the cookies (see doc)
			CLoginTime.setMaxAge(-1);
			CSelectedLang.setMaxAge(-1);
			CRememberMe.setMaxAge(-1);

			// Sending the data to response
			response.addCookie(CLoginTime);
			response.addCookie(CSelectedLang);
			response.addCookie(CRememberMe);

			// Creating feedback according to selected language
			switch (selectedLang) {

			case "es":
				request.setAttribute("feedback",
						new Feedback("success", "Sesión finalizada, hasta pronto!! (Cookies eliminadas)"));
				break;

			case "en":
				request.setAttribute("feedback",
						new Feedback("success", "Session ended, see you soon!! (Cookies deleted)"));
				break;

			case "eu":
				request.setAttribute("feedback",
						new Feedback("success", "Saioa amaitu da, laster arte!! (Cookies ezabatuak)"));
				break;

			}

		}

		// Ending session
		session.invalidate();

		// Calling back index
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

}
