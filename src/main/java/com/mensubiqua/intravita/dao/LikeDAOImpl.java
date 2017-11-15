package com.mensubiqua.intravita.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.Document;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.model.Like;
import com.mensubiqua.intravita.model.Publicacion;
import com.mensubiqua.intravita.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

@Component
public class LikeDAOImpl implements LikeDAO{
	
	private final String COLLECTION = "likes";
	private final String ID = "_id";

		
	public Like find(Publicacion p, User u) throws Exception {
		DBBroker broker=DBBroker.get();
		Like l = null;
		UserDAOImpl udao = new UserDAOImpl();
		PublicacionDAOImpl pdao = new PublicacionDAOImpl();
		Document query = new Document();
		ObjectMapper mapper = new ObjectMapper();
		
		query.put("publicacion", Document.parse(mapper.writeValueAsString(p)));
		query.put("usuario", Document.parse(mapper.writeValueAsString(u)));
		
		MongoCollection<Document> likes = broker.selectAll(COLLECTION);
		FindIterable<Document> resultado = likes.find(query);
		if (resultado.first() != null) {
			l = new Like(p, u);
		}
		
		return l;
		
		
	}
	
	public void insert(Like l) {
        DBBroker.get().insertOne(l, COLLECTION);
    }
	
	public void delete(Like l) {
		Document query = new Document();
		ObjectMapper mapper = new ObjectMapper();
		try {
		query.put("usuario", Document.parse(mapper.writeValueAsString(l.getUsuario())));
		query.put("publicacion", Document.parse(mapper.writeValueAsString(l.getPublicacion())));
		} catch (Exception e) {
			System.out.println("error");
		}
		
        DBBroker.get().deleteOne(query, COLLECTION);        
        
    }
	
	public long contLikes(Publicacion p) {
		Document query = new Document();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			query.put("publicacion", Document.parse(mapper.writeValueAsString(p)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return DBBroker.get().count(query, COLLECTION);
	 }


}
