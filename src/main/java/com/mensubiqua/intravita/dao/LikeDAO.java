package com.mensubiqua.intravita.dao;

import java.util.ArrayList;

import com.mensubiqua.intravita.model.Like;
import com.mensubiqua.intravita.model.Publicacion;
import com.mensubiqua.intravita.model.User;

public interface LikeDAO {
    public Like find(Publicacion p, User u) throws Exception;

}
