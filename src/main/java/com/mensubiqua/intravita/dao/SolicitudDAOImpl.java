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

@Component
public class SolicitudDAOImpl implements SolicitudDAO{

    private final String COLLECTION = "solicitudes";
    private final String ID = "_id"; //TODO

    public void insert(Solicitud s) {
        DBBroker.get().insertOne(s, COLLECTION);
    }

    public void delete(String id) {

    }


}
