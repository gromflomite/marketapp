package marketapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SumController
 */
@WebServlet("/sum")
public class SumController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get parameters from view (link)
		int parameter1 = Integer.parseInt(request.getParameter("operand1"));
		int parameter2 = Integer.parseInt(request.getParameter("operand2"));

		int sum = (parameter1 + parameter2);

		// Return some feedback
		request.setAttribute("donewith", "Done via GET: ");

		// Return the result of the sum
		request.setAttribute("result", sum);

		// Send all back to the view
		request.getRequestDispatcher("get-post.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// Get parameters from view (form)
			int parameter1 = Integer.parseInt(request.getParameter("operand1"));
			int parameter2 = Integer.parseInt(request.getParameter("operand2"));

			int sum = (parameter1 + parameter2);

			// Return the parameters entered by the user (to fill again the values in the form)
			request.setAttribute("operand1", parameter1);
			request.setAttribute("operand2", parameter2);

			// Return some feedback
			request.setAttribute("donewith", "Done via POST: ");

			// Return the result of the sum
			request.setAttribute("result", sum);

		} catch (Exception e) {
			request.setAttribute("errorfeedback", "We were not able to do the sum, please check the entered numbers.");

		} finally {
			// Send all back to the view
			request.getRequestDispatcher("get-post.jsp").forward(request, response);
		}

	}

}
