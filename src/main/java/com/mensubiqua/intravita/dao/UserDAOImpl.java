package com.mensubiqua.intravita.dao;

import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.MongoCollection;

import java.util.ArrayList;
import java.util.Collection;

import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class UserDAOImpl implements UserDAO{

    private final String COLLECTION = "users";
    private final String ID = "nickname";

    public void insert(User user) {
        DBBroker.get().insertOne(user, COLLECTION);
    }

    public void delete(String nickname) {
        DBBroker.get().deleteOne(ID, nickname, COLLECTION);
    }

    public User find(String nickname) {
        Document document = DBBroker.get().find(ID, nickname, COLLECTION);
        User user = null;

        if (document != null) 
        	user = new User(Funciones.decrypt(document.getString("nombre")), Funciones.decrypt(document.getString("apellido")),
        		Funciones.decrypt(document.getString("email")), document.getString("password"), document.getString("rol"),
        		Funciones.decrypt(document.getString("nickname")));

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
	        		nickname));
		}

		
		return users;
	}
	
	 public void update(User user) {			

			//encriptar las nuevas variables
			String nicknameDB = Funciones.encrypt(user.getNickname());
			String nombreDB = Funciones.encrypt(user.getNombre());
			String apellidoDB = Funciones.encrypt(user.getApellido());
			String emailDB = Funciones.encrypt(user.getEmail());
			String nicknameNuevo = Funciones.encrypt(user.getNombre().toLowerCase() + "." + user.getApellido().toLowerCase());
			

			//crear documento de los nuevos valores
			BasicDBObject values = new BasicDBObject();
			values.append("nickname", nicknameNuevo);
			values.append("nombre", nombreDB);
			values.append("apellido", apellidoDB);
			values.append("email", emailDB);
			BasicDBObject set = new BasicDBObject();
			set.append("$set", values);
			//crear query de busqueda
			BasicDBObject searchQuery = new BasicDBObject().append(ID, nicknameDB);
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
	
	  
}
