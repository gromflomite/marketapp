package marketapp;

import java.util.Scanner;

import marketapp.model.Product;
import marketapp.model.ProductDAOImpl;

public class InsertProductUsingDAO {

	public static void main(String[] args) {

		boolean continueAsking = true;

		ProductDAOImpl dao = ProductDAOImpl.getInstance();

		try (Scanner sc = new Scanner(System.in)) {

			do {

				try {
					System.out.println("Enter the name of the product: ");
					String name = sc.nextLine();

					Product product = new Product();
					product.setName(name);

					dao.insert(product);
					continueAsking = false;

				} catch (Exception e) {

					System.out.println("The entered name already exists in the DB, enter new one.");

				}

			}

			while (continueAsking);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
