package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class brokerDB {

	private static brokerDB yo;
	private Connection connect;
	private String url = "jdbc:sqlite:"+"C:\\Users\\ulise\\Desktop\\ListaCompra.db";
	private String driver = "org.sqlite.JDBC";
	
	private brokerDB() throws ClassNotFoundException {
		conectar();
	}
	
	public static brokerDB get() throws ClassNotFoundException {
		if (yo==null) {
			yo = new brokerDB();
		}
		return yo;
	}
	
	private void conectar () throws ClassNotFoundException {
		try {
			Class.forName(driver);
			this.connect = DriverManager.getConnection(url);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cerrar() throws Exception{
		this.connect.close();
	}
	
	
	public Connection getConnection () {
		return this.connect;
	}
}
	

