package marketapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class InsertProductAsking {

	public static void main(String[] args) {
		final String CONNECTION_URL = "jdbc:mysql://localhost/supermercado";
		final String USER = "root";
		final String PASSWORD = "secret";
		final String SQL = " INSERT INTO producto (nombre, id_usuario) VALUES (? , 1); ";
		boolean continueAsking = true;

		try {

			Scanner sc = new Scanner(System.in);

			// Checking if the connector is properly working (was added in Maven pom.xml)
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("The driver is working ok!!");

			// Connection to DB
			Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
			System.out.println("DB connection is ok!!");

			// Query DB
			PreparedStatement pst = connection.prepareStatement(SQL);

			System.out.println("------ Insert a product in the DB ------");

			System.out.println("Insert the name of the product: ");

			do {

				String productName = sc.nextLine();

				// cambiamos el 1º ? de la SQL por la varaiabel nombre
				// INSERT INTO producto (nombre, id_usuario) VALUES ( ? , 1) ;
				pst.setString(1, productName);

				try {

					// Saving in a variable the number of affected rows while using the
					// executeUpdate
					int affectedRows = pst.executeUpdate();

					if (affectedRows == 1) {
						System.out.println("Number of affected rows: " + affectedRows);
						System.out.println("The new product has been properly saved");
						sc.close();
						continueAsking = false;
					}

				} catch (Exception e) {
					// Catching the generic exception
					System.out.println("The product already exists in the DB, enter a new name: ");

				}

			} while (continueAsking);

		} catch (Exception e) {

			System.out.println("We have a problem: " + e.getMessage());
		}
	}

}
