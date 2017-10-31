package com.mensubiqua.intravita.dao;

import java.util.ArrayList;

import com.mensubiqua.intravita.model.User;
import com.mensubiqua.intravita.model.UserCode;

public interface UserCodeDAO {
	public void insert(UserCode uc);
    public void delete(String code);
    public UserCode find(String code);

}
