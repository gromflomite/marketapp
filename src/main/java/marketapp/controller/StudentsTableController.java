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

@WebServlet("/students-table")
public class StudentsTableController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// We are not going to use the doGet method
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Instantiation (Singleton pattern) UserDAOImpl
		UserDAOImpl dao = UserDAOImpl.getInstance();

		// Creating an ArrayList with the registers obtained by dao.getAll() method from the DB
		ArrayList<User> students = dao.getAll();

		// Send the ArrayList to view (MVC pattern)
		request.setAttribute("students", students);		

		// Link to .jsp file
		request.getRequestDispatcher("students-table.jsp").forward(request, response);
	}
	
}
