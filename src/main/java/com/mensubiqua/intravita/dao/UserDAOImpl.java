package com.mensubiqua.intravita.dao;

import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.model.Publicacion;
import com.mensubiqua.intravita.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.MongoCollection;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.bson.Document;
import org.springframework.stereotype.Component;

/**
 * UserDAOImpl - Clase que implementa los métodos de la interfaz UserDAO
 * 
 *
 * @author Ulises Ceca, Ignacio Dones, José Maréa Simón, Miguel Ampuero, Eduardo Parra
 * @since 1.8
 * @version 2.0
 */
@Component
public class UserDAOImpl implements UserDAO{

    private final String COLLECTION = "users";
    private final String ID = "nickname";

    public void insert(User user) {
        DBBroker.get().insertOne(user, COLLECTION);
    }

    public void delete(String nickname, File foto) {
    	PublicacionDAOImpl p = new PublicacionDAOImpl();
    	p.deleteUser(nickname);
        DBBroker.get().deleteOne(ID, Funciones.encrypt(nickname), COLLECTION);
        
        if(foto.exists() && !foto.isDirectory()) { 
            foto.delete();
        } 
        
    }

    public User find(String nickname) {
        Document document = DBBroker.get().find(ID, nickname, COLLECTION);
        User user = null;

        if (document != null){
        	user = new User(Funciones.decrypt(document.getString("nombre")), Funciones.decrypt(document.getString("apellido")),
        		Funciones.decrypt(document.getString("email")), document.getString("password"), document.getString("rol"),
        		Funciones.decrypt(document.getString("nickname")), document.getBoolean("validado"));
        	user.setFoto(document.getString("foto"));
        	
        
        }
        return user;
    }

	public ArrayList<User> selectAll() {
		MongoCollection<Document> collection = DBBroker.get().selectAll(COLLECTION);
		ArrayList<User> users = new ArrayList<User>();
		
		   
		for (Document document : collection.find()) {
			String nombre = Funciones.decrypt(document.getString("nombre"));
			String apellido = Funciones.decrypt(document.getString("apellido"));
			String email = Funciones.decrypt(document.getString("email"));
			String nickname = Funciones.decrypt(document.getString("nickname"));
			users.add(new User(nombre, apellido,
	        		email, document.getString("password"), document.getString("rol"), 
	        		nickname,document.getBoolean("validado")));
		}

		
		return users;
	}
	
	public ArrayList<User> search(String cadena) {
		MongoCollection<Document> collection = DBBroker.get().selectAll(COLLECTION);
		ArrayList<User> users = new ArrayList<User>();
		
		   
		for (Document document : collection.find()) {
			String nombre = Funciones.decrypt(document.getString("nombre"));
			String apellido = Funciones.decrypt(document.getString("apellido"));
			String nombrecompleto = nombre + " " + apellido; 
			if(!nombrecompleto.toLowerCase().contains(cadena))
				continue;
			
			String email = Funciones.decrypt(document.getString("email"));
			String nickname = Funciones.decrypt(document.getString("nickname"));
			users.add(new User(nombre, apellido,
	        		email, document.getString("password"), document.getString("rol"), 
	        		nickname,document.getBoolean("validado")));
		}

		
		return users;
	}
	
	public void update(User user, String rutaFoto, String nickViejo) {			
		PublicacionDAOImpl publicacionDAO = new PublicacionDAOImpl();
		//encriptar las nuevas variables
		String nickViejoDB = Funciones.encrypt(nickViejo);
		String nicknameDB = Funciones.encrypt(user.getNickname());
		String nombreDB = Funciones.encrypt(user.getNombre());
		String apellidoDB = Funciones.encrypt(user.getApellido());
		String emailDB = Funciones.encrypt(user.getEmail());	

		if(!user.getNickname().equals(nickViejo))
		{
			File fotoVieja = new File(rutaFoto + nickViejo + ".jpg");
			File fotoNueva = new File(rutaFoto + user.getNickname() + ".jpg");
			fotoVieja.renameTo(fotoNueva);

			ArrayList<Publicacion> publicaciones = publicacionDAO.selectAll();

			if(!publicaciones.isEmpty()) {
				for(Publicacion p : publicaciones) {
					if(p.getNickname().equals(nickViejo)) {
						p.setNickname(user.getNickname());
						publicacionDAO.update(p);
					}
				}
			}
		}

		//crear documento de los nuevos valores
		BasicDBObject values = new BasicDBObject();
		values.append("nickname", nicknameDB);
		values.append("nombre", nombreDB);
		values.append("apellido", apellidoDB);
		values.append("email", emailDB);
		BasicDBObject set = new BasicDBObject();
		set.append("$set", values);
		//crear query de busqueda
		BasicDBObject searchQuery = new BasicDBObject().append(ID, nickViejoDB);
		//llamada a dbbroker
		DBBroker.get().update(set, searchQuery, COLLECTION);
	}
	
	 public void updateRole(String nickname, String rol) {
		   String nicknameDB = Funciones.encrypt(nickname);
		   //DBBroker.get().updateRole(ID, nicknameDB, COLLECTION, rol);
		   BasicDBObject newDocument = new BasicDBObject();
	        if(rol == "ROLE_ADMIN") {
	        	newDocument.append("$set", new BasicDBObject().append("rol", "ROLE_ADMIN"));
	        }else if(rol == "ROLE_USER") {
	        	newDocument.append("$set", new BasicDBObject().append("rol", "ROLE_USER"));
	        }   	
	        
	        BasicDBObject searchQuery = new BasicDBObject().append(ID, nicknameDB);
	        DBBroker.get().update(newDocument, searchQuery, COLLECTION);	        
		   
	   }


	 
	 public void updatePassword(User user) {
		 String passwordDB = user.getPassword();
		 String nicknameDB = Funciones.encrypt(user.getNickname());
		 BasicDBObject set = new BasicDBObject();
		 set.append("$set", new BasicDBObject().append("password", passwordDB));
		 BasicDBObject searchQuery = new BasicDBObject().append(ID, nicknameDB);
		 DBBroker.get().update(set, searchQuery, COLLECTION);
		
		 
		 
	 }
	 public void updateValidacion (User user) {
		 boolean validadoDB = user.isValidado();
		 String nicknameDB = Funciones.encrypt(user.getNickname());
		 BasicDBObject set = new BasicDBObject();
		 set.append("$set", new BasicDBObject().append("validado", validadoDB));
		 BasicDBObject searchQuery = new BasicDBObject().append(ID, nicknameDB);
		 DBBroker.get().update(set, searchQuery, COLLECTION);
	
	 }
}
