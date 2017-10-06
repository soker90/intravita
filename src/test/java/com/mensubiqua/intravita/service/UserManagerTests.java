package com.mensubiqua.intravita.service;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import com.mensubiqua.intravita.domain.User;
import org.junit.Before;
import org.junit.Test;

public class UserManagerTests {

    private UserManager userManager;
    private List<User> users;

    private static int USER_COUNT = 2;

    //USER1
    private static String USER1_USERNAME = "edu";
    private static String USER1_PASSWORD_CHECKSUM = "ccjhkdf777sjflg";
    private static String USER1_NOMBRE = "Eduardo";
    private static String USER1_APELLIDOS = "Parra";

    //USER2
    private static String USER2_USERNAME = "roto2";
    private static String USER2_PASSWORD_CHECKSUM = "cyhisdf63jflg";
    private static String USER2_NOMBRE = "Romualdo";
    private static String USER2_APELLIDOS = "Ronaldo Elenco";

    @Before
    public void setUp() throws Exception {
        userManager = new UserManager();
        users = new ArrayList<User>();

        User user = new User();
        user.setUsername(USER1_USERNAME);
        user.setPassword_checksum(USER1_PASSWORD_CHECKSUM);
        user.setNombre(USER1_NOMBRE);
        user.setApellidos(USER1_APELLIDOS);
        users.add(user);

        user = new User();
        user.setUsername(USER2_USERNAME);
        user.setPassword_checksum(USER2_PASSWORD_CHECKSUM);
        user.setNombre(USER2_NOMBRE);
        user.setApellidos(USER2_APELLIDOS);
        users.add(user);

        userManager.setUsers(users);
    }

    @Test
    public void testGetUsersWithNoUsers() {
        userManager = new UserManager();
        assertNull(userManager.getUsers());
    }

    @Test
    public void testGetProducts() {
        List<User> users = userManager.getUsers();
        assertNotNull(users);
        assertEquals(USER_COUNT, userManager.getUsers().size());

        User user = users.get(0);
        assertEquals(USER1_USERNAME, user.getUsername());
        assertEquals(USER1_PASSWORD_CHECKSUM, user.getPassword_checksum());
        assertEquals(USER1_NOMBRE, user.getNombre());
        assertEquals(USER1_APELLIDOS, user.getApellidos());

        user = users.get(1);
        assertEquals(USER2_USERNAME, user.getUsername());
        assertEquals(USER2_PASSWORD_CHECKSUM, user.getPassword_checksum());
        assertEquals(USER2_NOMBRE, user.getNombre());
        assertEquals(USER2_APELLIDOS, user.getApellidos());
    }
}