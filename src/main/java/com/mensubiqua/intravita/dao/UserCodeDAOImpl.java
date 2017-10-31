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
    private final String ID = "nickname";
	
	public void insert(UserCode uc) {
		DBBroker.get().insertOne(uc, COLLECTION);
	}
	
    public void delete(String nickname) {
    	DBBroker.get().deleteOne(ID, nickname, COLLECTION);
    }
    
    public UserCode find(String nickname) {
    	Document document = DBBroker.get().find(ID, nickname, COLLECTION);
        UserCode uc = null;

        if (document != null) 
        	uc = new UserCode(Funciones.decrypt(document.getString("nickname")), document.getString("code"));

        return uc;
    }

}
