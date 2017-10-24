package com.mensubiqua.intravita.dao;

import com.mensubiqua.intravita.model.User;

public interface UserDAO {

    public void insert(User user);
    public void delete(String nickname);
    public User find(String nickname);


}
