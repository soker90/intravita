package com.mensubiqua.intravita.dao;

import java.util.ArrayList;

import com.mensubiqua.intravita.model.Publicacion;
import com.mensubiqua.intravita.model.User;

public interface PublicacionDAO {

    public void insert(Publicacion p);
    public void delete(String id);
    public Publicacion find(String id);
    public ArrayList<Publicacion> selectAll();
    public void update(Publicacion p);
    public void deleteUser(String nick);
}
