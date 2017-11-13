package com.mensubiqua.intravita.dao;

import java.util.ArrayList;

import com.mensubiqua.intravita.model.User;
import com.mensubiqua.intravita.model.UserCode;

/**
 * UserCodeDAO - Interfaz que declara los métodos que comunican los UserCode con la BBDD
 * para manejar el codigo que se le asigna a cada usuario para validar su cuenta.
 * 
 *
 * @author Ulises Ceca, Ignacio Dones, José María Simón, Miguel Ampuero, Eduardo Parra
 * @since 1.4
 * @version 1.8
 */
public interface UserCodeDAO {
	public void insert(UserCode uc);
    public void delete(String nickname);
    public UserCode find(String nickname);

}
