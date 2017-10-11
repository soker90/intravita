package com.mensubiqua.intravita.service;

import com.mensubiqua.intravita.domain.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserManager implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}