package com.mensubiqua.intravita.dao;

import java.util.ArrayList;

import com.mensubiqua.intravita.model.Like;
import com.mensubiqua.intravita.model.Publicacion;

public interface LikeDAO {
    public ArrayList<Like> selectAll();

}
