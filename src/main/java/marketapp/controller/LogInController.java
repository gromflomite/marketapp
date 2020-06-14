package marketapp.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import marketapp.model.User;
import marketapp.model.UserDAOImpl;

@WebServlet("/login")
public class LogInController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Getting parameters from view
		// -----------------------------------------------------------------------
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		String selectedLang = request.getParameter("selectedLang");

		// Cookie parameters
		String rememberMe = request.getParameter("rememberMe");

		// Getting the time of user login init -- CURRENTLY NOT USING THIS VALUE --
		LocalDateTime rawLoginTime = LocalDateTime.now();
		String loginTime = rawLoginTime.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy_HH:mm"));

		// Getting Session
		HttpSession session = request.getSession();

		// Check (against th DB) if entered data is correct
		// Instantiating DAO
		UserDAOImpl dao = UserDAOImpl.getInstance();

		// Calling DAO and put user data in the object
		User userLogin = dao.checkLogin(user, password);

		if (userLogin != null) {
			
			// Setting attributes to session			
			session.setAttribute("userLogin", userLogin);

			// Cookies management
			// -------------------------------------------------------------------------
			Cookie cLoginTime = new Cookie("loginTime", loginTime);
			Cookie cSelectedLang = new Cookie("selectedLang", selectedLang);
			Cookie cRememberMe = new Cookie("rememberMe", rememberMe);

			// Setting the expiration values for the cookies
			// IMPORTANT: If the user has NOT selected "Remember me", when Logout, the
			// SetMaxAge will be set to -1 in order to delete the values of the cookies
			cLoginTime.setMaxAge(60 * 1 * 60 * 24 * 31); // 1 month
			cSelectedLang.setMaxAge(60 * 1 * 60 * 24 * 31); // 1 month
			cRememberMe.setMaxAge(60 * 1 * 60 * 24 * 31); // 1 month

			// Sending the data to response
			response.addCookie(cLoginTime);
			response.addCookie(cSelectedLang);
			response.addCookie(cRememberMe);
			// End cookies management ------------------------------------------------------

			// Creating feedback according to selected language ----------------------------
			switch (selectedLang) {

			case "es":
				request.setAttribute("feedback", new Feedback("success", "Bienvenid@ de nuevo!!"));
				break;

			case "en":
				request.setAttribute("feedback", new Feedback("success", "Welcome again!!"));
				break;

			case "eu":
				request.setAttribute("feedback", new Feedback("success", "Ongi etorri!!"));
				break;
			}
			// End feedback block
			// --------------------------------------------------------------

			// Calling index
			request.getRequestDispatcher("index.jsp").forward(request, response);

		} else { // If the entered details are not correct

			// End session
			session.invalidate();

			// Create feedback
			request.setAttribute("feedback", new Feedback("warning", "Username or password not correct!!"));

			// Call back the loging page
			request.getRequestDispatcher("login.jsp").forward(request, response);

		}

	}

}
