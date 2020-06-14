package marketapp.model;

import java.util.ArrayList;

/**
 * This interface adds additional methods to the extended (see above) CrudAble.
 * 
 * We can use this "intermediate interface" to create additional methods that we
 * have to use in our project but we can not create them in the main interface
 * (CrudAble).
 * 
 */
public interface UserDAO extends CrudAble<User> {
	
	ArrayList<User> getAllByName(String searchWord);

	
	/**
	 * 
	 * Method to check against the DB the user login
	 * 
	 *  @param username
	 *  @param password
	 *  @return User data if exists or null if not	 
	 * 
	 */	
	User checkLogin ( String userName, String password );
}
