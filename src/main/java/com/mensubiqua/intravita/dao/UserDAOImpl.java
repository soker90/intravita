package com.mensubiqua.intravita.dao;

import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.model.User;
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
        DBBroker.get().deleteOne(ID, Funciones.encrypt(nickname), COLLECTION);
    }

    public User find(String nickname) {
        Document document = DBBroker.get().find(ID, nickname, COLLECTION);
        User user = null;

        if (document != null) user = new User(Funciones.decrypt(document.getString("nombre")), Funciones.decrypt(document.getString("apellido")),
        		Funciones.decrypt(document.getString("email")), document.getString("password"), document.getString("rol"), Funciones.decrypt(document.getString("nickname")));

        return user;
    }
}
