package com.mensubiqua.intravita.dao;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BsonField;
import com.mongodb.client.result.DeleteResult;

import java.util.ArrayList;
import java.util.Collection;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;

public class DBBroker<T> {
    private MongoClient client;
    private MongoDatabase db;

    private DBBroker(){
        MongoClientURI uri  = new MongoClientURI("mongodb://intravita:intravita@ds113915.mlab.com:13915/intravita");
        this.client = new MongoClient(uri);
        this.db = client.getDatabase(uri.getDatabase());
    }

    //La clase interna SingletonHolder me permite implementar un singleton que no falle con concurrencia./

    public static class SingletonHolder{
        static DBBroker singleton = new DBBroker();
    }

    public static DBBroker get(){
        return SingletonHolder.singleton;
    }

    public void insertOne(T object, String collection){
        ObjectMapper mapper = new ObjectMapper();
        String json;
        Document document;

        try {
            json = mapper.writeValueAsString(object);
            document = Document.parse(json);
            this.db.getCollection(collection).insertOne(document);
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error con la bbdd");
        }
    }

    
    
    public void deleteOne(String field, String value, String collection){
        BasicDBObject query = new BasicDBObject(field, value);

        this.db.getCollection(collection).deleteOne(query);
    }
    
    public void deleteOne(String field, ObjectId value, String collection){
        BasicDBObject query = new BasicDBObject(field, value);

        this.db.getCollection(collection).deleteOne(query);
    }
    
    public void deleteMany(String field, String value, String collection){
        Bson filter = new Document(field, value);

        this.db.getCollection(collection).deleteMany(filter);
    }

    public Document find(String field, String value, String collection){
        BasicDBObject query = new BasicDBObject(field, value);

        return this.db.getCollection(collection).find(query).first();
    }
    
    public Document find(String field, ObjectId value, String collection){
        BasicDBObject query = new BasicDBObject(field, value);

        return this.db.getCollection(collection).find(query).first();
    }
    
    public MongoCollection<Document> selectAll(String collection)
    {
    	return this.db.getCollection(collection);
    }
    
    public FindIterable<Document> findAll(String field, String value, String collection)
    {
    	BasicDBObject query = new BasicDBObject(field, value);

        return this.db.getCollection(collection).find(query);
    }
    
    public void update(BasicDBObject newDocument, BasicDBObject searchQuery, String collection){       
    	 	
        this.db.getCollection(collection).updateOne(searchQuery, newDocument);
    }
    
    
}
