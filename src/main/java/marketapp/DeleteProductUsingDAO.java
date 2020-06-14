package marketapp;

import java.util.Scanner;

import marketapp.model.Product;
import marketapp.model.ProductDAOImpl;

public class DeleteProductUsingDAO {

	public static void main(String[] args) {

		ProductDAOImpl dao = ProductDAOImpl.getInstance();

		System.out.println("Product list in the database");

		for (Product g : dao.getAll()) {
			System.out.println(g);
		}

		try (Scanner sc = new Scanner(System.in)) {

			System.out.println("Enter the id of the product to delete: ");
			int id = Integer.parseInt(sc.nextLine());

			Product gDeleted = dao.delete(id);
			System.out.println("Register with id " + id + "(" + gDeleted.getName() + ") deleted properly.");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
