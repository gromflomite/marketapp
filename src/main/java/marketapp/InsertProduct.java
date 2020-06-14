package marketapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertProduct {

	public static void main(String[] args) {
		final String CONNECTION_URL = "jdbc:mysql://localhost/supermercado";
		final String USER = "root";
		final String PASSWORD = "secret";
		final String SQL = " INSERT INTO producto (nombre, id_usuario) VALUES (? , 1); ";

		try {

			// Checking if the connector is properly working (was added in Maven pom.xml)
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("The driver is working ok!!");

			// Connection to DB
			Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
			System.out.println("DB connection is ok!!");

			// Query DB
			PreparedStatement pst = connection.prepareStatement(SQL);

			System.out.println("------ Insert a product in the DB ------");

			// Statement to insert a new product in the DB
			// The number 1 references the question mark (?) on the SQL query (constant SQL
			// above)
			// INSERT INTO producto (nombre, id_usuario) VALUES (? , 1);

			pst.setString(1, "salted cookies");

			// Saving in a variable the number of affected rows while using the
			// executeUpdate
			int affectedRows = pst.executeUpdate();

			System.out.println("Number of affected rows: " + affectedRows);

		} catch (Exception e) {

			// TODO: handle exception
		}

	}

}
