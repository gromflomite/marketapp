package marketapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marketapp.model.Product;
import marketapp.model.ProductDAOImpl;

@WebServlet("/new-edit-product")
public class NewEditProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// RETRIEVING product from products form (using edit option) via doGet

		Product product = new Product();

		// Getting and parsing the ID number
		// It comes with forced "0" from the view (index.jsp)

		try {

			int idParameter = Integer.parseInt(request.getParameter("id"));

			if (idParameter > 0) {

				// Instanciating DAO
				ProductDAOImpl dao = ProductDAOImpl.getInstance();

				// Getting the student registry by ID from DB
				product = dao.getById(idParameter);
			}

		} catch (Exception e) {

			// TODO Do something funny here
			e.printStackTrace();

		} finally {

			// Setting the attribute (user object)
			request.setAttribute("product", product);

			// Calling the JSP forwarding the request
			request.getRequestDispatcher("new-edit-product-form.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// CREATING or EDITING product via doPost()

		// Creating new feedback object
		Feedback feedback = new Feedback();
		
		// Creating new product object
		Product product = new Product();

		try {

			// Getting and parsing the ID number
			int idParameter = Integer.parseInt(request.getParameter("id"));

			// Getting the values from the form (new-edit-product-form.jsp)
			String name = request.getParameter("productName");

			String priceParameter = request.getParameter("productPrice");
			float price = Float.parseFloat(priceParameter);

			String image = request.getParameter("productImage");

			// Instanciating DAO (Singleton pattern used in DAO)
			ProductDAOImpl dao = ProductDAOImpl.getInstance();

			// Setting the ID and name on the object
			product.setId(idParameter);
			product.setName(name);
			product.setPrice(price);
			product.setImage(image);

			// if id == 0, the registry does not exists in the DB, so create it
			if (idParameter == 0) {
				// Create the registry in the DB
				dao.insert(product);
			} else { // if id != 0, the registry already exists in the DB, so update it
				// Update the registry in the DB
				dao.update(product);
			}

			// Creating some feedback to the user
			feedback = new Feedback("success", "Product properly saved with ID: " + product.getId());

		} catch (Exception e) {

			feedback = new Feedback("danger", "We were not able to save the product: " + e.getMessage());

		} finally {

			// Send the feedback back to the view (new-product-form.jsp)
			request.setAttribute("feedback", feedback);

			// Send the product back to the view to get the form fields filled  after submit (new-product-form.jsp)
			request.setAttribute("product", product);

			// Go back to the view (new-product-form.jsp)
			request.getRequestDispatcher("new-edit-product-form.jsp").forward(request, response);

		}

	}

}
