package marketapp.model;

import java.util.ArrayList;

// G -> generic class
public interface CrudAble<G> {

	ArrayList<G> getAll();

	G getById(int id) throws Exception;

	G delete(int id) throws Exception;

	G insert(G pojo) throws Exception;

	G update(G pojo) throws Exception;
}