package marketapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class UpdateProductById {

	public static void main(String[] args) {

		final String DBUSER = "sql7337548";
		final String DBPASS = "qkIBD2UPuH";
		final String DBURL = "jdbc:mysql://sql7.freesqldatabase.com/sql7337548";

		final String SQLQUERY = "UPDATE producto SET nombre = ? WHERE id = ?;";

		String productName = "";
		int id = 0;

		try {

			// Check if the DB driver connector .jar is properly working (was added in Maven
			// pom.xml)
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("The driver is working ok!!");

			// Connection to DB
			Connection dbConnection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			System.out.println("The connection to the DB is ok!!");

			// Create the PreparedStatement
			//PreparedStatement pst = dbConnection.prepareStatement(SQLQUERY);

			// Ask for the product ID and new name
			//Scanner sc = new Scanner(System.in);

			System.out.println("Enter the ID product number: ");
			//id = sc.nextInt();
			// CAST

			System.out.println("Enter the new name of the product: ");
			//productName = sc.nextLine();

			System.out.println(id + productName);

//			pst.setString(1, productName);
//			pst.setInt(2, id);
//			
//			int affectedRows = pst.executeUpdate();
//			System.out.println(affectedRows);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
