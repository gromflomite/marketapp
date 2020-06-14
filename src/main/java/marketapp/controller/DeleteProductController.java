package marketapp.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketapp.model.Product;
import marketapp.model.ProductDAOImpl;

@WebServlet(name = "DeleteProduct", urlPatterns = { "/DeleteProduct" })
public class DeleteProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Creating new feedback object
		Feedback feedback = new Feedback();

		// Get parameter from view
		// Statement that we are using in the view:
		// <td><a href="DeleteStudent?id=<%=u.getId()%>">Delete</a></td>

		String parameterId = request.getParameter("id");
		int id = Integer.parseInt(parameterId);

		// Call model (DAO)
		ProductDAOImpl dao = ProductDAOImpl.getInstance();		

		try {

			dao.delete(id);
			
			feedback = new Feedback("success", "The product has been properly deleted");

		} catch (Exception e) {
			
			feedback = new Feedback("warning", "There was an error trying to delete the product: " + e.getMessage());
		}

		// Send data back to view

		// We have to send back all the registers of the DB (next two lines copied from
		// ProductTableController):
		// Creating an ArrayList with the registers obtained by dao.getAll() method from
		// the DB
		ArrayList<Product> products = dao.getAll();
		// Send the ArrayList to view (MVC pattern)
		request.setAttribute("products", products);

		// Sending the feedback
		request.setAttribute("feedback", feedback);

		// Go to view
		request.getRequestDispatcher("products-table.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// We are not using doPost()
		doGet(request, response);

	}

}
