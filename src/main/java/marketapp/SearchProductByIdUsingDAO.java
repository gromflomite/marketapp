package marketapp;

import java.util.Scanner;

import marketapp.model.Product;
import marketapp.model.ProductDAOImpl;

public class SearchProductByIdUsingDAO {

	public static void main(String[] args) {

		ProductDAOImpl dao = ProductDAOImpl.getInstance();

		try (Scanner sc = new Scanner(System.in)) {

			System.out.println("Enter the id of the product to show: ");
			int id = Integer.parseInt(sc.nextLine());

			Product g = dao.getById(id);
			System.out.println(g);

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

	}

}
