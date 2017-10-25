package com.mensubiqua.intravita.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class User {

    private String nickname;
    private String nombre;
    private String apellido;
    private String password;
    private String foto;
    private String rol;
    private String email;

    public User(String nombre, String apellido, String email, String password, String rol, String nickname) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.rol = rol;
    }

    public User() {

    }

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getRol() {return rol;}
    public void setRol(String rol) {this.rol = rol;}
    public String getNombre() {return nombre;}
    public String getApellido() {return apellido;}
    public String getNickname() {return nickname;}
    public String getPassword() {return password;}
    public String getFoto() {return foto;}
    public void setNickname(String nickname) {this.nickname = nickname;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setApellido(String apellido) {this.apellido = apellido;}
    public void setPassword(String password) {this.password = password;}
    public void setFoto(String foto) {this.foto = foto;}
}
