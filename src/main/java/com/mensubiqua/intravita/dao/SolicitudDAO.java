package com.mensubiqua.intravita.dao;

import java.util.ArrayList;

import com.mensubiqua.intravita.model.Publicacion;
import com.mensubiqua.intravita.model.Solicitud;
import com.mensubiqua.intravita.model.User;

/**
 * SolicitudDAO - Interfaz que declara los métodos que comunican las solicitudes con la BBDD
 * 
 *
 * @author Ulises Ceca, Ignacio Dones, José María Simón, Miguel Ampuero, Eduardo Parra
 * @since 1.4
 * @version 1.8
 */
public interface SolicitudDAO {

    public void insert(Solicitud p);
    public void delete(String solicitante, String solicitado);
    public ArrayList<User> findAmigos(String nickname);
    public boolean isAmigo(String nick1, String nick2);
    public boolean isPendiente(String nick1, String nick2);
    public ArrayList<User> solicitudesPendientes(String nick);
	public int countAmigos(String nickname);
	public int countPendientes(String nickname);
	public ArrayList<Solicitud> selectAll();
}
