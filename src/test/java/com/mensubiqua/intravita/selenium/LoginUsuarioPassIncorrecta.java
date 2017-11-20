package com.mensubiqua.intravita.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import seleniumAux.Driver;
import seleniumAux.LoginAux;

public class LoginUsuarioPassIncorrecta {
	private static WebDriver driver;
    private static String root;
    public static void main(String[] args)
    {
    	root = Driver.getRoot();
        driver = Driver.getDriver();
        
        LoginAux.redirect(root, "/login");
        LoginAux.login("super.admin", "false", "Contraseña incorrecta");
        
        driver.quit();
        
    }

}
