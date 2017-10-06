package com.mensubiqua.intravita.domain;

public class User {
    private String username;
    private String password_checksum;
    private String nombre;
    private String apellidos;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_checksum() {
        return password_checksum;
    }

    public void setPassword_checksum(String password_checksum) {
        this.password_checksum = password_checksum;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Username:'" + username + ";");
        buffer.append("Password Checksum:" + password_checksum + ";");
        buffer.append("Nombre='" + nombre + ";");
        buffer.append("Apellidos:'" + apellidos+ ";");
        return buffer.toString();
    }
}