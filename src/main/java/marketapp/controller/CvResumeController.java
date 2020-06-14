package marketapp.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CvResume
 */
@WebServlet("/CvResume")
public class CvResumeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// ArrayList to save the messages to return to the user if some field is not
	// properly filled
	ArrayList<String> validations = new ArrayList<>();

	try {

	    // Get parameters from view (form)
	    String name = request.getParameter("name");
	    String surname = request.getParameter("surname");
	    String ageReceived = request.getParameter("age");
	    String studies = request.getParameter("studies");	   
	    String[] shift = request.getParameterValues("shift");
	    String comments = request.getParameter("comments");

	    // Validations
	    // Validation - Age
	   String age = "";
	    if ("".equalsIgnoreCase(ageReceived)) {
		validations.add("The age field can't be empty");
	    } else {
		age = ageReceived;
	    } 

	    // Validation - Name
	    if ("".equalsIgnoreCase(name)) {
		validations.add("The name field can't be empty");
	    }

	    // Validation - Surname
	    if ("".equalsIgnoreCase(surname)) {
		validations.add("The surname field can't be empty");
	    }
	    
	    // Selections - Convert parameters codes (u/v/ft..._) to full string ("University"/"Vocational training"/"Full time"...)
	    // Selections - Studies
	    if (studies.equalsIgnoreCase("u")) {
		studies = "University";
	    } else if (studies.equalsIgnoreCase("v")) {
		studies = "Vocational training";
	    } else {
		studies = "Others";
	    }

	    // Selections - Shift
	    ArrayList<String> shiftsSelected = new ArrayList<String>();

	    if (shift != null) { // Check if the user has not selected any option in the select (to avoid crash)
		for (String selection : shift) {

		    if (selection.equalsIgnoreCase("ft")) {
			shiftsSelected.add("Full time");
		    } else if (selection.equalsIgnoreCase("pt")) {
			shiftsSelected.add("Part time");
		    } else {
			shiftsSelected.add("Others");
		    }
		}
	    } else {
		shiftsSelected.add("Others"); // If nothing selected, add "Others" by default.
	    }

	    // Selections - Gender
	    String gender = request.getParameter("gender");
	    if (gender.equalsIgnoreCase("m")) {
		gender = "Male";
	    } else if (gender.equalsIgnoreCase("f")) {
		gender = "Female";
	    } else {
		gender = "Other";
	    }

	    // Return the values to the resume view
	    request.setAttribute("name", name);
	    request.setAttribute("surname", surname);
	    request.setAttribute("age", age);
	    request.setAttribute("studies", studies);	    
	    request.setAttribute("shift", shift);
	    request.setAttribute("comments", comments);
	    request.setAttribute("gender", gender);
	    request.setAttribute("shiftsSelected", shiftsSelected);

	} catch (Exception e) {

	    request.setAttribute("errorFeedback", "We had a problem with the form, please try again" + e.getMessage());

	} finally {

	    // If the ArrayList "validations" its not empty, means that exists some
	    // validation problem

	    // "validations" is empty, so every validation is ok
	    if (validations.isEmpty()) {
		// Send all back to the view
		request.getRequestDispatcher("curriculum-resume.jsp").forward(request, response);

		// "validations" is not empty, there are validations errors
	    } else {
		// Returning the ArrayList "validations" with the errors strings
		request.setAttribute("validations", validations);
		request.getRequestDispatcher("curriculum.jsp").forward(request, response);
	    }

	}

    }

}
