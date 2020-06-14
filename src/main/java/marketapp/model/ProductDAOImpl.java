
package marketapp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * This class manages all the work with the DB. Contains all the methods that we
 * need to insert, read, modify or delete entries in the database (via
 * ConnectionManager)
 *
 */

public class ProductDAOImpl implements ProductDAO {

	// SQL queries
	// --------------------------------------------------------------------------------
	// executeQuery -> returns -> ResulSet
	private final String SQL_GETALL = " SELECT id, nombre, precio, imagen FROM producto ORDER BY id ASC; ";
	private final String SQL_GETBYID = " SELECT id, nombre, precio, imagen FROM producto WHERE id = ?; ";	

	// executeUpdate -> returns -> integer with the number of affected rows
	private final String SQL_DELETE = " DELETE FROM producto WHERE id = ?; ";
	private final String SQL_INSERT = " INSERT INTO producto (nombre, id_usuario, precio, imagen) VALUES (?, 1, ?, ?); ";
	private final String SQL_UPDATE = " UPDATE producto SET nombre = ?, precio = ?, imagen = ? WHERE id = ?; ";
	// ---------------------------------------------------------------------------------------------

	// Singleton pattern
	// ---------------------------------------------------------------------------
	private static ProductDAOImpl INSTANCE = null;

	// Constructor
	private ProductDAOImpl() {
		super();
	}

	public static ProductDAOImpl getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new ProductDAOImpl();
		}

		return INSTANCE;
	} // End Singleton pattern
		// ---------------------------------------------------------------------

	// Method to show all the registers in the DB
	@Override
	public ArrayList<Product> getAll() {

		ArrayList<Product> registers = new ArrayList<Product>();

		try (Connection dbConnection = ConnectionManager.getConnection();
				PreparedStatement pst = dbConnection.prepareStatement(SQL_GETALL);
				ResultSet rs = pst.executeQuery()) {

			// Iterate each product in the DB
			while (rs.next()) {
				int id = rs.getInt("id");
				String productName = rs.getString("nombre");
				float price = rs.getFloat("precio");
				String image = rs.getString("imagen");

				Product product = new Product(productName);
				product.setId(id);
				product.setPrice(price);
				product.setImage(image);

				// Save each product to the "allProducts" array list
				registers.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return registers;
	} // End method to show all the registers in the DB

	// Method to show register by id
	@Override
	public Product getById(int id) throws Exception {

		Product register = new Product();

		try (Connection dbConnection = ConnectionManager.getConnection();
				PreparedStatement pst = dbConnection.prepareStatement(SQL_GETBYID);) {

			// Changing the ? in the SQL query
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {

				register.setId(rs.getInt("id"));
				register.setName(rs.getString("nombre"));
				register.setPrice(rs.getFloat("precio"));
				register.setImage(rs.getString("imagen"));

			} else {
				throw new Exception("Introduced id (" + id + ") not located.");
			}
		}

		return register;

	} // End method to show register by id

	// Method to delete register
	@Override
	public Product delete(int id) throws Exception {

		// Get the product before try to delete it
		Product register = getById(id);

		try (Connection dbConnection = ConnectionManager.getConnection();
				PreparedStatement pst = dbConnection.prepareStatement(SQL_DELETE);) {

			// Changing the ? in the SQL query
			pst.setInt(1, id);

			// Getting the number of affected rows after execute the query
			int affectedRows = pst.executeUpdate();

			if (affectedRows != 1) {
				throw new Exception("The register with id " + id + " cannot be deleted.");
			}
		}

		return register;

	} // End method to delete register

	// Method to create a new register in the database
	@Override
	public Product insert(Product object) throws Exception {

		try (Connection dbConnection = ConnectionManager.getConnection();
				/**
				 * @see We use RETURN_GENERATED_KEYS to be able to get the id number that the DB
				 *      has assigned to the new created entry
				 */
				PreparedStatement pst = dbConnection.prepareStatement(SQL_INSERT,
						PreparedStatement.RETURN_GENERATED_KEYS);) {

			// Changing the ? in the SQL query
			pst.setString(1, object.getName());
			pst.setFloat(2, object.getPrice());
			pst.setString(3, object.getImage());

			// Getting the number of affected rows after execute the query
			int affectedRows = pst.executeUpdate();

			if (affectedRows == 1) {

				// Knowing and getting the id number that DB has assigned to the new created
				// register
				try (ResultSet rsNewAssignedId = pst.getGeneratedKeys()) {

					// Check and save the results from the ResulSet from RETURN_GENERATED_KEYS
					if (rsNewAssignedId.next()) {
						int id = rsNewAssignedId.getInt(1); // Column position (one-based index in SQL, NOT zero-based)
															// to retrive the id number
						object.setId(id);
					}
				}

			} else {
				throw new Exception("The register " + object.getName() + " has not been created");
			}
		}

		return object;

	} // End method to create a new register in the database

	// Method to update an entry
	@Override
	public Product update(Product object) throws Exception {

		// object null protection
		if (object == null) {
			throw new Exception("The object is null, it can't be saved in the DB.");
		}

		try (Connection dbConnection = ConnectionManager.getConnection();
				PreparedStatement pst = dbConnection.prepareStatement(SQL_UPDATE);) {

			// Changing the ? in the SQL query
			pst.setString(1, object.getName());
			pst.setFloat(2, object.getPrice());
			pst.setString(3, object.getImage());
			pst.setInt(4, object.getId());

			int affectedRows = pst.executeUpdate();

			if (affectedRows != 1) {
				throw new Exception("It's not possible to update the register with the ID " + object.getId());
			}

			// Catching the SQL error if in the DB already exists one entry with the entered
			// name
		} catch (SQLException e) {
			throw new Exception("Already exists a register with the name " + object.getName());
		}

		return object;

	} // End method to update an entry

	@Override
	public ArrayList<Product> getAllByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Product> getAllByRangePrice(int minPrice, int maxPrice) {
		// TODO Auto-generated method stub
		return null;
	}	

} // Class end
