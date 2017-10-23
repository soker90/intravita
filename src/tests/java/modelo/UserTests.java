package tests.java.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTests {

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
    }

    @Test
    public void testSetAndGetUsername() {
        String testUsername = "aUsername";
        assertNull(user.getUsername());
        user.setUsername(testUsername);
        assertEquals(testUsername, user.getUsername());
    }

    @Test
    public void testSetAndGetPassword_checksum() {
        String testPassword_checksum = "aPassword_checksum";
        assertNull(user.getPassword_checksum());
        user.setPassword_checksum(testPassword_checksum);
        assertEquals(testPassword_checksum, user.getPassword_checksum());
    }

    @Test
    public void testSetAndGetNombre() {
        String testNombre = "aNombre";
        assertNull(user.getNombre());
        user.setNombre(testNombre);
        assertEquals(testNombre, user.getNombre());
    }

    @Test
    public void testSetAndGetApellidos() {
        String testApellidos = "aApellidos";
        assertNull(user.getApellidos());
        user.setApellidos(testApellidos);
        assertEquals(testApellidos, user.getApellidos());
    }

}