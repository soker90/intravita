package com.mensubiqua.intravita.dao;

import java.util.ArrayList;

import com.mensubiqua.intravita.model.Publicacion;
import com.mensubiqua.intravita.model.User;

public interface PublicacionDAO {

    public void insert(Publicacion p);
    public void delete(int id);
    public Publicacion find(int id);
    public ArrayList<Publicacion> selectAll();

}
