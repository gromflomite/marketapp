package marketapp;

import marketapp.model.Product;
import marketapp.model.ProductDAOImpl;

public class ShowProductsUsingDAO {

	public static void main(String[] args) {

		ProductDAOImpl dao = ProductDAOImpl.getInstance();

		for (Product p : dao.getAll()) {
			System.out.println(p);
		}

	}

}
