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

@WebServlet("/product-table")
public class ProductTableController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// We are not going to use the doGet method
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Instantiation (Singleton pattern) UserDAOImpl
		ProductDAOImpl dao = ProductDAOImpl.getInstance();

		// Creating an ArrayList with the registers obtained by dao.getAll() method from the DB
		ArrayList<Product> products = dao.getAll();

		// Send the ArrayList to view (MVC pattern)
		request.setAttribute("products", products);

		// Link to .jsp file
		request.getRequestDispatcher("products-table.jsp").forward(request, response);

	}

}
