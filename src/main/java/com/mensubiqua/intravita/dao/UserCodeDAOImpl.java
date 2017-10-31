package com.mensubiqua.intravita.dao;

import org.springframework.stereotype.Component;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.MongoCollection;

import java.util.ArrayList;
import java.util.Collection;

import org.bson.Document;

import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.model.UserCode;

@Component
public class UserCodeDAOImpl implements UserCodeDAO{
	
	private final String COLLECTION = "userCode";
    private final String ID = "code";
	
	public void insert(UserCode uc) {
		DBBroker.get().insertOne(uc, COLLECTION);
	}
	
    public void delete(String code) {
    	DBBroker.get().deleteOne(ID, code, COLLECTION);
    }
    
    public UserCode find(String code) {
    	Document document = DBBroker.get().find(ID, code, COLLECTION);
        UserCode uc = null;

        if (document != null) 
        	uc = new UserCode(document.getString("nickname"), document.getString("code"));

        return uc;
    }

}
