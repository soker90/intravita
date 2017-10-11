package com.mensubiqua.intravita.service;

import com.mensubiqua.intravita.domain.User;

import java.io.Serializable;
import java.util.List;

public class UserManager implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}