package marketapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import marketapp.model.Product;

public class ShowProduct {

	public static void main(String[] args) {
		System.out.println("Listing products");

		final String CONNECTION_URL = "jdbc:mysql://localhost/supermercado";
		final String USER = "root";
		final String PASSWORD = "secret";
		final String SQL = " SELECT id, nombre FROM producto ORDER BY id ASC; ";

		try {

			// Checking if the connector is properly working (was added in Maven pom.xml)
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("The driver is working ok!!");

			// Connection to DB
			Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
			System.out.println("DB connection is ok!!");

			// Query DB
			PreparedStatement pst = connection.prepareStatement(SQL);
			ResultSet rs = pst.executeQuery();

			System.out.println("------ Show a product from the DB ------");

			// Check the results one by one until not exists any more
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("nombre");

				Product p = new Product(name);
				p.setId(id);

				// Showing the results
				// System.out.println("Id: " + id + " Name: " + name);
				System.out.println(p); // It's the same than above, but we are calling the toString() in Product class

			}

		} catch (Exception e) {

			// TODO: handle exception
		}

	}

}
