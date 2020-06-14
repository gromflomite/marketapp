package marketapp.model;

import java.util.ArrayList;

/**
 * This interface adds additional methods to the extended (see below) CrudAble.
 * 
 * We can use this "intermediate interface" to create additional methods that we
 * have to use in our project but we can not create them in the main interface
 * (CrudAble).
 * 
 */

public interface ProductDAO extends CrudAble<Product> {

	ArrayList<Product> getAllByName(String name);
	
	ArrayList<Product> getAllByRangePrice(int minPrice, int maxPrice);	
	
}
