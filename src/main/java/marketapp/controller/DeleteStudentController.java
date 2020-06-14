package marketapp.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketapp.model.User;
import marketapp.model.UserDAOImpl;

/**
 * Servlet implementation class DeleteStudentController
 */
@WebServlet(name = "DeleteStudent", urlPatterns = { "/DeleteStudent" })
public class DeleteStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Creating new feedback object
		Feedback feedback = new Feedback();

		// Get parameter from view
		// Statement that we are using in the view:
		// <td><a href="DeleteStudent?id=<%=u.getId()%>">Delete</a></td>

		String parameterId = request.getParameter("id");
		int id = Integer.parseInt(parameterId);

		// Call model (DAO)
		UserDAOImpl dao = UserDAOImpl.getInstance();		

		try {

			dao.delete(id);
			feedback = new Feedback("success", "The user has been properly deleted");

		} catch (Exception e) {

			feedback = new Feedback("warning", "There was an error trying to delete the user: " + e.getMessage());

		}

		// Send data back to view
		// We have to send back all the registers of the DB (next two lines copied from
		// StudentsTableController):
		ArrayList<User> students = dao.getAll();
		request.setAttribute("students", students);

		request.setAttribute("feedback", feedback);

		// Go to view
		request.getRequestDispatcher("students-table.jsp").forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// We are not using doPost()
		doGet(request, response);
	}

}
