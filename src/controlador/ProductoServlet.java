package controlador;

import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import modelo.Producto;
import persistencia.OperacionesDAOImpl;

@Controller //Indica que es un controlador
public class ProductoServlet {
	@Autowired //Indica al container que proporcione una instancia del servicio
	OperacionesDAOImpl operacionesDao;
	
	@RequestMapping("mostrar.do") //El metodo respondera las request que el reciba el server 
									//con tal "nombre.do"
	public void mostrar(HttpServletResponse response) throws Exception {
		String listaCompra="";
		listaCompra= operacionesDao.show();
		response.getOutputStream().println("LISTA DE PRODUCTOS\n------------------\n"+listaCompra);
		
	}
	
	@RequestMapping("descargar.do") //El metodo respondera las request que el reciba el server 
	//con tal "nombre.do"
	public void descargar(HttpServletResponse response) throws Exception {
		String result="";
		JSONObject jso = new JSONObject();
		result = operacionesDao.show();
		jso.put("ListaCompra", result);
		try {

			FileWriter file = new FileWriter("C:\\Users\\ulise\\Desktop\\ListaCompra.json");
			file.write(jso.toString());
			file.flush();
			file.close();
			response.getOutputStream().println("Lista de la compra descargada en JSON.");

		} catch (IOException e) {
			//manejar error
		}

}
	
	@RequestMapping("anadir.do")//El metodo respondera las request que el reciba el server 
	//con tal "nombre.do"
	public void anadir(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		Producto producto = new Producto();
		producto.setNombre(request.getParameter("txtProductAdd"));
		String listaProducto="";
		listaProducto = operacionesDao.show();
		
		if (listaProducto.contains(producto.getNombre())) {
			listaProducto = operacionesDao.show();
			response.getOutputStream().println("LISTA DE PRODUCTOS\n------------------\n"+listaProducto);
			response.getOutputStream().println("El producto '"+producto.getNombre()+"' ya se encuentra en la lista.");
		}
		
		else {
			operacionesDao.saveProduct(producto);
			listaProducto = operacionesDao.show();
			response.getOutputStream().println("LISTA DE PRODUCTOS\n------------------\n"+listaProducto);
			response.getOutputStream().println("El producto '"+producto.getNombre()+"' ha sido añadido a su lista.");
		}
		
	}
	
	@RequestMapping("eliminar.do")//El metodo respondera las request que el reciba el server 
	//con tal "nombre.do"
	public void eliminar(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		Producto producto = new Producto();
		producto.setNombre(request.getParameter("txtProductDel"));
		String listaProducto="";
		listaProducto = operacionesDao.show();
		
		if (!listaProducto.contains(producto.getNombre())) {
			listaProducto = operacionesDao.show();
			response.getOutputStream().println("LISTA DE PRODUCTOS\n------------------\n"+listaProducto);
			response.getOutputStream().println("El producto '"+producto.getNombre()+"' no esta en la lista.");
		}
		
		else {
			operacionesDao.deleteProduct(producto);
			listaProducto = operacionesDao.show();
			response.getOutputStream().println("LISTA DE PRODUCTOS\n------------------\n"+listaProducto);
			response.getOutputStream().println("El producto '"+producto.getNombre()+"' ha sido eliminado a su lista.");
		}
		
	}
}
