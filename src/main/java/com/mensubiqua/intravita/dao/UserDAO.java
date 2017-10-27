package com.mensubiqua.intravita.dao;

import java.util.ArrayList;

import com.mensubiqua.intravita.model.User;

public interface UserDAO {

    public void insert(User user);
    public void delete(String nickname);
    public User find(String nickname);
    public ArrayList<User> selectAll();
    public void update(User user);

}
