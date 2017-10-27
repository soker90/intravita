package com.mensubiqua.intravita.dao;

import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.model.Publicacion;
import com.mensubiqua.intravita.model.User;
import com.mongodb.DBCursor;
import com.mongodb.client.MongoCollection;

import java.util.ArrayList;
import java.util.Collection;

import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class PublicacionDAOImpl implements PublicacionDAO{

    private final String COLLECTION = "publicaciones";
    private final String ID = ""; //TODO

    public void insert(Publicacion p) {
        //DBBroker.get().insertOne(user, COLLECTION);
    }

    public void delete(int id) {
        //DBBroker.get().deleteOne(ID, nickname, COLLECTION);
    }

    public Publicacion find(int id) {
        /*Document document = DBBroker.get().find(ID, nickname, COLLECTION);
        User user = null;

        if (document != null) 
        	user = new User(Funciones.decrypt(document.getString("nombre")), Funciones.decrypt(document.getString("apellido")),
        		Funciones.decrypt(document.getString("email")), document.getString("password"), document.getString("rol"),
        		Funciones.decrypt(document.getString("nickname")));

        return user;*/
    	return null;
    }

	public ArrayList<Publicacion> selectAll() {
		/*MongoCollection<Document> collection = DBBroker.get().selectAll(COLLECTION);
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

		
		return users;*/
		
		return null;
	}
}
