package com.mensubiqua.intravita.dao;

import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.model.Publicacion;
import com.mensubiqua.intravita.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.MongoCollection;

import java.util.ArrayList;
import java.util.Collection;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class PublicacionDAOImpl implements PublicacionDAO{

    private final String COLLECTION = "publicaciones";
    private final String ID = "_id"; //TODO

    public void insert(Publicacion p) {
        DBBroker.get().insertOne(p, COLLECTION);
    }

    public void delete(String id) {
        DBBroker.get().deleteOne(ID, new ObjectId(id), COLLECTION);
    }

    public Publicacion find(String id) {
        Document document = DBBroker.get().find(ID, new ObjectId(id), COLLECTION);
        Publicacion p = null;

        if (document != null) { 
        	p = new Publicacion(document.getString("nickname"), document.getString("texto"),
        		document.getString("privacidad"), document.getString("fecha"));
        	p.setId(document.getObjectId("_id").toString());
        }
        return p;
    }

	public ArrayList<Publicacion> selectAll() {
		MongoCollection<Document> collection = DBBroker.get().selectAll(COLLECTION);
		ArrayList<Publicacion> ps = new ArrayList<Publicacion>();
		Publicacion p = null;
		
		for (Document document : collection.find()) { 
			p = new Publicacion(document.getString("nickname"), document.getString("texto"), document.getString("privacidad"), document.getString("fecha"));
			p.setId(document.getObjectId("_id").toString());
			ps.add(0,p);
		}
		return ps;
	}
	
	public void update(Publicacion p) {			

		BasicDBObject values = new BasicDBObject();
		values.append("nickname", p.getNickname());
		values.append("texto", p.getTexto());
		values.append("privacidad", p.getPrivacidad());
		values.append("fecha", p.getFecha());
		BasicDBObject set = new BasicDBObject();
		set.append("$set", values);
		//crear query de busqueda
		BasicDBObject searchQuery = new BasicDBObject().append(ID, new ObjectId(p.getId()));
		//llamada a dbbroker
		DBBroker.get().update(set, searchQuery, COLLECTION);

	}

}
