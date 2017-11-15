package com.mensubiqua.intravita.dao;

import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.model.Publicacion;
import com.mensubiqua.intravita.model.Solicitud;
import com.mensubiqua.intravita.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import java.util.ArrayList;
import java.util.Collection;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

/**
 * SolicitudDAOImpl - Clase que implementa los métodos de la interfaz SolicitudDAO
 * 
 *
 * @author Ulises Ceca, Ignacio Dones, José María Simón, Miguel Ampuero, Eduardo Parra
 * @since 1.8
 * @version 2.0
 */
@Component
public class SolicitudDAOImpl implements SolicitudDAO{

    private final String COLLECTION = "solicitudes";
    private final String ID = "_id"; //TODO

    public void insert(Solicitud s) {
        DBBroker.get().insertOne(s, COLLECTION);
    }
    
    public void update(String solicitante, String solicitado, boolean aceptado)
    {
    	BasicDBObject values = new BasicDBObject();
        values.append("solicitante", solicitante);
        values.append("solicitado", solicitado);
        values.append("aceptado", aceptado);
        BasicDBObject set = new BasicDBObject();
        set.append("$set", values);
        //crear query de busqueda
        BasicDBObject searchQuery = new BasicDBObject().append("solicitante", solicitante).append("solicitado", solicitado);
        //llamada a dbbroker
        DBBroker.get().update(set, searchQuery, COLLECTION);
    }

    public void delete(String solicitante, String solicitado) {
    	ArrayList<Solicitud> solicitudes = selectAll();
    	for(Solicitud s : solicitudes) {
    		if(s.getSolicitante().equals(solicitante) && s.getSolicitado().equals(solicitado)) {
    	        DBBroker.get().delete("solicitante", solicitante, COLLECTION);
    		}
    	}
    }

	public ArrayList<User> findAmigos(String nickname) {
		UserDAOImpl userDAO = new UserDAOImpl();
		ArrayList<User> amigos = new ArrayList<User>();
		
		FindIterable<Document> documents1 = DBBroker.get().findAll("solicitante", nickname, COLLECTION);
		FindIterable<Document> documents2 = DBBroker.get().findAll("solicitado", nickname, COLLECTION);
		User u = null;
		
		for(Document document : documents1) {
			if(document.getBoolean("aceptado")) {
				u = userDAO.find(Funciones.encrypt(document.getString("solicitado")));
				amigos.add(u);
			}
		}
		for(Document document : documents2) {
			if(document.getBoolean("aceptado")) {
				u = userDAO.find(Funciones.encrypt(document.getString("solicitante")));
				amigos.add(u);
			}
		}
		
		return amigos;
	}
	
	public boolean isAmigo(String nick1, String nick2)
	{
		FindIterable<Document> documents1 = DBBroker.get().findAll("solicitante", nick1, COLLECTION);
		FindIterable<Document> documents2 = DBBroker.get().findAll("solicitante", nick2, COLLECTION);
		
		for(Document document : documents1) {
			if(document.getString("solicitado").equals(nick2) && document.getBoolean("aceptado")) {
				return true;
			}
		}
		for(Document document : documents2) {
			if(document.getString("solicitado").equals(nick1) && document.getBoolean("aceptado")) {
				return true;
			}
		}
    	
		return false;
	}
	
	public boolean isPendiente(String nick1, String nick2)
	{
        BasicDBObject searchQuery = new BasicDBObject().append("solicitante", nick1).append("solicitado", nick2);
		Document document = DBBroker.get().findFilter(searchQuery, COLLECTION);
		if(document.getBoolean("aceptado")) {
			return false;
		}

		return true;
	}
	
	public ArrayList<Solicitud> selectAll()
	{
		MongoCollection<Document> collection = DBBroker.get().selectAll(COLLECTION);
		ArrayList<Solicitud> solicitudes = new ArrayList<Solicitud>();
		Solicitud s = null;
		for (Document document : collection.find()) {
			s =  new Solicitud(document.getString("solicitante"), document.getString("solicitado"), document.getBoolean("aceptado"));
			solicitudes.add(s);
		}
				
		
		return solicitudes;
	}
	
	public ArrayList<User> solicitudesPendientes(String nick)
	{
		UserDAOImpl userDAO = new UserDAOImpl();
		
		ArrayList<User> usuarios = new ArrayList<User>();
		FindIterable<Document> documents = DBBroker.get().findAll("solicitado", nick, COLLECTION);
		User u = null;
		
		for(Document document : documents) {
			if(!document.getBoolean("aceptado")) {
				u = userDAO.find(Funciones.encrypt(document.getString("solicitante")));
				usuarios.add(u);
			}
		}
		
		return usuarios;
	}

	public int countAmigos(String nickname) {
		ArrayList<User> amigos = findAmigos(nickname);
		return amigos.size();
		
	}

	public int countPendientes(String nickname) {
		ArrayList<User> pendientes = solicitudesPendientes(nickname);
		return pendientes.size();
	}


}
