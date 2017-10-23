package main.java.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import main.java.modelo.Producto;

@Component
public class OperacionesDAOImpl implements OperacionesDAO {
	
	public String show () throws Exception{
		brokerDB broker = brokerDB.get();
		Connection connection = broker.getConnection();
		String cadena ="";
        ResultSet result = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement("select * from Productos;");
            result = pstmt.executeQuery();
            while (result.next()) {
            	cadena = cadena + result.getString(1)+"\n";
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
		return cadena;
    }
	
	public void saveProduct (Producto producto) throws Exception{ 
		brokerDB broker = brokerDB.get();
		Connection connection = broker.getConnection();
		try {
			PreparedStatement pstmt = connection.prepareStatement("insert into Productos (Basicos) values (?);");
			pstmt.setString(1,producto.getNombre());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void deleteProduct (Producto producto) throws Exception {
		brokerDB broker = brokerDB.get();
		Connection connection = broker.getConnection();
		try {
			PreparedStatement pstmt = connection.prepareStatement("delete from Productos where Basicos = ?;");
			pstmt.setString(1,producto.getNombre());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
	
}
