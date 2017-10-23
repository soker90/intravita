package main.java.persistencia;

import main.java.modelo.Producto;

public interface OperacionesDAO {

	public String show () throws Exception;
	public void saveProduct(Producto producto) throws Exception;
	public void deleteProduct (Producto producto) throws Exception;
	
}
