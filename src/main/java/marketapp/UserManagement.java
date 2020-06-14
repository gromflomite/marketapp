package marketapp;

import java.util.ArrayList;
import java.util.Scanner;

import marketapp.model.User;
import marketapp.model.UserDAOImpl;

/**
 * 
 * Ejercicio prueba 06-05-2020
 * 
 * @author Raúl
 *
 */

public class UserManagement {

	static int userSelection = 0;
	static boolean keepOn = true;
	static Scanner sc = new Scanner(System.in);

	// Instanciating the UserDAOImpl to use it in every method
	static UserDAOImpl dao = UserDAOImpl.getInstance();

	// Main method
	public static void main(String[] args) {

		do {

			showMenu();

			switch (userSelection) {

			case 1:
				showAllUsers();
				break;

			case 2:
				getById();
				break;

			case 3:
				deleteUser();
				break;

			case 4:
				insertUser();
				break;

			case 5:
				updateUser();
				break;

			case 6:
				userByName();
				break;

			case 7:
				System.out.println("\nProgram ended, thank you!!");
				sc.close();
				keepOn = false;
				break;
			}

		} while (keepOn);
	} // End main method

	// Show all users method
	private static void showAllUsers() {

		ArrayList<User> users = dao.getAll();

		System.out.println("---------- User list ----------");

		for (User user : users) {
			System.out.println(user);
		}

		System.out.println("-------------------------------");
	} // End show all users method

	// Get user by ID method
	private static void getById() {

		try {

			System.out.println("Enter the ID of the user: ");
			int id = Integer.parseInt(sc.nextLine());

			User g = dao.getById(id);

			System.out.println(g);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	} // End get user by ID method

	// Delete user method
	private static void deleteUser() {

		// List the users in the database
		showAllUsers();

		try {

			System.out.println("Enter the ID number of the register to delete: ");
			int id = Integer.parseInt(sc.nextLine());

			User userDeleted = dao.delete(id);
			System.out.println("The ID with number " + userDeleted.getId() + " (" + userDeleted.getName()
					+ ") has been properly deleted from the database");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}// End delete user method

	// Insert user method
	private static void insertUser() {

		boolean continueAsking = true;

		try {

			do {

				try {

					System.out.println("Enter the user name: ");
					String name = sc.nextLine();

					User newUser = new User();
					newUser.setName(name);

					dao.insert(newUser);

					System.out.println("The user with name " + newUser.getName()
							+ " has been saved in the DB with the ID " + newUser.getId());

					continueAsking = false;

				} catch (Exception e) {
					System.out.println("The entered name already exists in the DB, enter new one.");
				}

			} while (continueAsking);

		} catch (Exception e) {
			// TODO: handle exception
		}

	} // End insert user method

	// Update user method
	private static void updateUser() {

		try {

			// Show on the console all the registers
			showAllUsers();

			// Get and save the ID number of the register to update
			System.out.println("Enter the ID of the user to update: ");
			int id = Integer.parseInt(sc.nextLine());

			// TODO Check first if the ID exists - try DRY (Dont Repeat Yourself)

			// Ask and save the new name
			System.out.println("Enter the new name: ");
			String newName = sc.nextLine();

			// Create a User object
			User user = new User();

			// Push to the object the new name and the entered ID
			user.setName(newName);
			user.setId(id);

			// Method to execute the INSERT against the DB
			dao.update(user);

			// Ok feedback to the user
			System.out.println(
					"The name of the register with ID " + user.getId() + ", has been updated to " + user.getName());

		} catch (Exception e) {
			e.printStackTrace();
		}

	} // End update user method

	private static void userByName() {

		System.out.println("Enter the word to search: ");
		String searchWord = sc.nextLine();

		ArrayList<User> list = dao.getAllByName(searchWord);

		if (list.size() == 0) {
			System.out.println("There are no coincidences in the DB for the word " + searchWord);
		} else {

			for (User user : list) {
				System.out.println(user);
			}

		}

	}

	// Showmenu method
	private static void showMenu() {
		System.out.println("\n************************************************");
		System.out.println("******************* Menu ***********************");
		System.out.println("************************************************");
		System.out.println("\n 1. Show all users");
		System.out.println("\n 2. Show a user by ID");
		System.out.println("\n 3. Delete user");
		System.out.println("\n 4. Insert user");
		System.out.println("\n 5. Update user");
		System.out.println("\n 6. Show all users searching for name");
		System.out.println("\n 7. Exit");
		System.out.println("\n************************************************");
		System.out.println("\nSelect an option (1-6): ");
		userSelection = Integer.parseInt(sc.nextLine());

	} // End showMenu method
}
