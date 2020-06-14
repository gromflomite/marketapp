package marketapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketapp.model.Role;
import marketapp.model.User;
import marketapp.model.UserDAOImpl;

@WebServlet("/new-edit-student")
public class NewEditStudentController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// Instanciating DAO (Singleton pattern used in DAO)
	private static UserDAOImpl dao = UserDAOImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// RETRIEVING user from form (using edit option) via doGet

		User student = new User();

		try {

			// Getting and parsing the ID number
			// It comes with forced "0" from the view (index.jsp)
			int idParameter = Integer.parseInt(request.getParameter("id"));

			if (idParameter > 0) {

				// Instanciating DAO
				UserDAOImpl dao = UserDAOImpl.getInstance();

				// Getting the student registry by ID from DB
				student = dao.getById(idParameter);

			}

		} catch (Exception e) {

			// TODO Do something funny here
			e.printStackTrace();

		} finally {

			// Setting the attribute (user object)
			request.setAttribute("student", student);

			// Calling the JSP forwarding the request
			request.getRequestDispatcher("new-edit-student-form.jsp").forward(request, response);

		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// CREATING or EDITING user via doPost()

		// Creating new feedback object
		Feedback feedback = new Feedback();

		// Creating new user object
		User student = new User();		

		try {

			// Getting the parameters
			int idParameter = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("studentName");			
			int idRol = Integer.parseInt(request.getParameter("idRol"));			
			String image = request.getParameter("image");			
			String password = request.getParameter("password");
			String passwordChange = request.getParameter("passwordChange");
			String passwordChangeConfirm = request.getParameter("passwordChangeConfirm");

			// Setting the values on User object
			student.setId(idParameter);
			student.setName(name);
			student.setImage(image);

			// Creating role Role object
			Role role = new Role();			
			// Setting the id in the Role object
			role.setId(idRol);
			// Setting the Role object to User "student" object
			student.setRole(role);			

			
			// Password management --------------------------------------------			
			if (password != null) { 													// New user password

				student.setPassword(password);

			} else {																	// User password change set	

				
				if ((passwordChange != null && passwordChangeConfirm != null) && (passwordChange.equals(passwordChangeConfirm))) {

					student.setPassword(passwordChange);

				} else {

					throw new Exception("Check the entered password");

				}

			}
			// End password management -----------------------------------------

			// if id == 0, the registry does not exists in the DB, so create it
			if (idParameter == 0) {
				// Create the registry in the DB
				dao.insert(student);
			} else { // if id != 0, the registry alreadt exists in the DB, so update it
				// Update the registry in the DB
				dao.update(student);
			}

			// Creating some feedback to the user
			feedback = new Feedback("success", "User properly saved with ID: " + student.getId());

		} catch (Exception e) {

			feedback = new Feedback("danger", "We were not able to save the user: " + e.getMessage());

		} finally {

			// Send the feedback back to the view (new-student-form.jsp)
			request.setAttribute("feedback", feedback);

			// Send the product back to the view to get the form fields filled after submit
			// (new-edit-student-form.jsp)
			request.setAttribute("student", student);

			// Go back to the view (new-student-form.jsp)
			request.getRequestDispatcher("new-edit-student-form.jsp").forward(request, response);

		}

	}

}
