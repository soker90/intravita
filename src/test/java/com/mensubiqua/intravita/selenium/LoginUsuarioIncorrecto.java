package com.mensubiqua.intravita.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import seleniumAux.Driver;
import seleniumAux.LoginAux;

public class LoginUsuarioIncorrecto {
	private static WebDriver driver;
    private static String root;
    public static void main(String[] args)
    {
    	root = Driver.getRoot();
        driver = Driver.getDriver();
        
        LoginAux.redirect(root, "/login");
        LoginAux.login("falso", "false", "Este usuario no existe");
                
        driver.quit();
        
    }
    
    
    
    

    /*private static void default_test()
    {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(root + "/default");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if(driver.getCurrentUrl().contains("/login"))
            System.out.println("CORRECTO: Redirección de /default correcta para usuarios Anonimo");
        else
            System.out.println("FALLA: Redirección de /default correcta para usuarios Anonimo");

        LoginWebTests.login("user.user", "user");
        driver.get(root +  "/default");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if(driver.getCurrentUrl().contains("/user/index"))
            System.out.println("CORRECTO: Redirección de /default para usuarios normales");
        else
            System.out.println("FALLA: Redirección de /default para usuarios normales: " + driver.getCurrentUrl());
        LoginWebTests.logout();

        LoginWebTests.login("admin", "admin");
        driver.get(root +  "/default");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if(driver.getCurrentUrl().contains("/admin/index"))
            System.out.println("CORRECTO: Redirección de /default para administradores");
        else
            System.out.println("FALLA: Redirección de /default para administradores");
        LoginWebTests.logout();
    }*/

}
