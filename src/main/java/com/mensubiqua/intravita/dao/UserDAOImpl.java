package com.mensubiqua.intravita.dao;

import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.model.User;
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
	
	 public void updateRole(String nickname, String rol) {
		   String nicknameDB = Funciones.encrypt(nickname);
		   DBBroker.get().updateRole(ID, nicknameDB, COLLECTION, rol);
		   
	   }

	public void update(User user) {
		// TODO Auto-generated method stub
		// Entra un usuario con sus atributos sin encriptar y se tiene que actualizar
		// en la BBDD en base a su nick
		
	}
	
	  
}
