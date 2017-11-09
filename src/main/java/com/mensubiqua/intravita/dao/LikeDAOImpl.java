package com.mensubiqua.intravita.dao;

import java.util.ArrayList;

import org.bson.Document;
import org.springframework.stereotype.Component;

import com.mensubiqua.intravita.model.Like;
import com.mensubiqua.intravita.model.Publicacion;
import com.mongodb.client.MongoCollection;

@Component
public class LikeDAOImpl implements LikeDAO{
	
	private final String COLLECTION = "like";

	public ArrayList<Like> selectAll() {
		
		MongoCollection<Document> collection = DBBroker.get().selectAll(COLLECTION);
		ArrayList<Like> ps = new ArrayList<Like>();
		Publicacion p = null;
		
		for (Document document : collection.find()) { 
			p = new Publicacion(document.getString("nickname"), document.getString("texto"), document.getString("privacidad"), document.getString("fecha"));
			p.setId(document.getObjectId("_id").toString());
			ps.add(0,p);
		}
		return ps;
	}

}
