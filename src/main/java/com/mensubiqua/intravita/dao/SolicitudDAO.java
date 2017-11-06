package com.mensubiqua.intravita.dao;

import java.util.ArrayList;

import com.mensubiqua.intravita.model.Publicacion;
import com.mensubiqua.intravita.model.Solicitud;
import com.mensubiqua.intravita.model.User;

public interface SolicitudDAO {

    public void insert(Solicitud p);
    public void delete(String solicitante, String solicitado);
    public ArrayList<User> findAmigos(String nickname);
    public boolean isAmigo(String nick1, String nick2);
    public boolean isPendiente(String nick1, String nick2);
    public ArrayList<User> selectAll(String nick);
	public int countAmigos(String nickname);
	public int countPendientes(String nickname);
}
