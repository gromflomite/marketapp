package marketapp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

	// SQL queries
	// --------------------------------------------------------------------------------
	// executeQuery -> returns -> ResulSet

	private final String SQL_GETALL = " SELECT u.id, u.nombre, u.contrasenia, u.id_rol, u.imagen, r.nombre AS 'nombre_rol' FROM usuario AS u INNER JOIN rol AS r ON u.id_rol = r.id ORDER BY u.id DESC; ";
	private final String SQL_GETBYID = " SELECT u.id, u.nombre, u.contrasenia, u.id_rol, u.imagen, r.nombre AS 'nombre_rol' FROM usuario AS u INNER JOIN rol AS r ON u.id_rol = r.id WHERE u.id = ? ; ";
	private final String SQL_GETALLBYNAME = " SELECT u.id, u.nombre, u.contrasenia, u.id_rol, u.imagen, r.nombre AS 'nombre_rol' FROM usuario AS u INNER JOIN rol AS r ON u.id_rol = r.id WHERE u.nombre LIKE ? ; ";
	private final String SQL_CHECKLOGIN = " SELECT u.id, u.nombre, u.contrasenia, u.id_rol, r.nombre AS 'nombre_rol' FROM usuario AS u INNER JOIN rol AS r ON u.id_rol = r.id WHERE u.nombre = ? AND u.contrasenia = ? ; ";

	// executeUpdate -> returns -> integer with the number of affected rows
	private final String SQL_DELETE = " DELETE FROM usuario WHERE id = ?; ";
	private final String SQL_INSERT = " INSERT INTO usuario (nombre, contrasenia, id_rol, imagen) VALUES (?,?,?,?); ";
	private final String SQL_UPDATE = " UPDATE usuario SET nombre = ?, contrasenia = ?, id_rol = ?, imagen = ? WHERE id = ?; ";
	// ---------------------------------------------------------------------------------------------

	// Singleton pattern ------------------------------------------------------
	private static UserDAOImpl INSTANCE = null;

	// Constructor
	private UserDAOImpl() {
		super();
	}

	public static UserDAOImpl getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new UserDAOImpl();
		}

		return INSTANCE;
	} // End Singleton pattern ------------------------------------------------

	@Override
	public ArrayList<User> getAll() {

		ArrayList<User> registers = new ArrayList<User>();

		try (Connection dbConnection = ConnectionManager.getConnection();
				PreparedStatement pst = dbConnection.prepareStatement(SQL_GETALL);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {

				// Getting values from the DB
				int id = rs.getInt("id");
				String name = rs.getString("nombre");
				String password = rs.getString("contrasenia");
				int roleId = rs.getInt("id_rol");
				String roleName = rs.getString("nombre_rol");
				String image = rs.getString("imagen");

				Role role = new Role(); // DECLARE MUST BE IN HERE, NOT OUTSIDE THE while

				// Adding role values to "role" object
				role.setId(roleId);
				role.setName(roleName);

				User u = new User(); // DECLARE MUST BE IN HERE, NOT OUTSIDE THE while

				// Adding the data to "u" user object
				u.setId(id);
				u.setName(name);
				u.setPassword(password);
				u.setImage(image);
				u.setRole(role);

				// Add the "u" user object to "registers" ArrayList
				registers.add(u);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Returning the ArrayList
		return registers;
	}

	@Override
	public User getById(int id) throws Exception {

		// In opposite with getAll, we can declare the objects outside try because we will have just one record from DB
		User register = new User();
		Role role = new Role();

		try (Connection dbConnection = ConnectionManager.getConnection();
				PreparedStatement pst = dbConnection.prepareStatement(SQL_GETBYID);) {

			// Replacing ? in the SQL query
			pst.setInt(1, id);			
			
			// Executing the query against the DB and getting the ResultSet with the values
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {

				int idById = rs.getInt("id");
				String name = rs.getString("nombre");
				String password = rs.getString("contrasenia");
				int roleId = rs.getInt("id_rol");
				String roleName = rs.getString("nombre_rol");
				String image = rs.getString("imagen");				

				// Adding role values to "role" object
				role.setId(roleId);
				role.setName(roleName);
				
				// Adding the data to "register" user object
				register.setId(idById);
				register.setName(name);
				register.setPassword(password);
				register.setRole(role); 				// Role object
				register.setImage(image);

			} else {
				throw new Exception("The inserted ID (" + id + ") does not exists in the DB");
			}

		}

		return register;
	}

	@Override
	public User delete(int id) throws Exception {

		User register = getById(id);

		try (Connection dbConnection = ConnectionManager.getConnection();
				PreparedStatement pst = dbConnection.prepareStatement(SQL_DELETE);) {

			// Replace ? in the SQL query
			pst.setInt(1, id);

			int affectedRows = pst.executeUpdate();

			if (affectedRows != 1) {
				throw new Exception("The register with id " + id + " cannot be deleted.");
			}

		}

		return register;
	}

	@Override
	public User insert(User pojo) throws Exception {

		try (Connection dbConnection = ConnectionManager.getConnection();
				/**
				 * @see We use RETURN_GENERATED_KEYS to be able to get the id number that the DB
				 *      has assigned to the new created entry
				 */
				PreparedStatement pst = dbConnection.prepareStatement(SQL_INSERT,
						PreparedStatement.RETURN_GENERATED_KEYS);) {

			// Replace ? in the SQL query
			pst.setString(1, pojo.getName());
			pst.setString(2, pojo.getPassword());
			pst.setInt(3, pojo.getRole().getId());		// Role id	
			pst.setString(4, pojo.getImage());

			int affectedRows = pst.executeUpdate();

			if (affectedRows == 1) {

				// Knowing and getting the id number that DB has assigned to the new created
				// register
				try (ResultSet rsNewAssignedId = pst.getGeneratedKeys()) {

					// Check and save the results from the ResulSet from RETURN_GENERATED_KEYS
					if (rsNewAssignedId.next()) {
						int id = rsNewAssignedId.getInt(1); // Column position (one-based index in SQL, NOT zero-based)
															// to retrive the id number
						pojo.setId(id);
					}
				}

			} else {
				throw new Exception("The register " + pojo.getName() + " has not been created");
			}

		}

		return pojo;
	}

	@Override
	public User update(User pojo) throws Exception {

		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQL_UPDATE);) {

			// Replace ? in the SQL query
			pst.setString(1, pojo.getName());
			pst.setString(2, pojo.getPassword());			
			pst.setInt(3, pojo.getRole().getId());		// Role id			
			pst.setString(4, pojo.getImage());
			pst.setInt(5, pojo.getId());

			if (pst.executeUpdate() != 1) {

				throw new Exception("The register " + pojo.getName() + " can not be updated");

			}

		}

		return pojo;
	}

	@Override // TODO Implement role	
	public ArrayList<User> getAllByName(String searchWord) {

		ArrayList<User> registers = new ArrayList<User>();

		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQL_GETALLBYNAME);) {

			// Replace ? in the SQL query
			pst.setString(1, "%" + searchWord + "%");

			try (ResultSet rs = pst.executeQuery()) {

				// Iterate over rs (ResulSet) to check all results
				while (rs.next()) {

					// Extract the id (column "id") and name (column "nombre")
					int id = rs.getInt("id");
					String name = rs.getString("nombre");
					
					// Create new User object (using User class)
					User u = new User();
					
					// Put the id and the name in the User object
					u.setId(id);
					u.setName(name);
					
					// Add the objet User (u) to the ArrayList (for return it)
					registers.add(u);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return registers;
	}

	@Override
	public User checkLogin(String userName, String password) {

		// Creating an object to put the user data from the DB and set to null
		User userLogin = null;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_CHECKLOGIN);) {

			pst.setString(1, userName);
			pst.setString(2, password);

			try (ResultSet rs = pst.executeQuery()) {

				if (rs.next()) {
					userLogin = new User();
					userLogin.setId(rs.getInt("id"));
					userLogin.setName(rs.getString("nombre"));
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return userLogin;
	}

}
