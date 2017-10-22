package persistencia;

import modelo.Producto;

public interface OperacionesDAO {

	public String show () throws Exception;
	public void saveProduct(Producto producto) throws Exception;
	public void deleteProduct (Producto producto) throws Exception;
	
}
