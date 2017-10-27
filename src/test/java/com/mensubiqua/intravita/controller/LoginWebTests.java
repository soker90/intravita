package com.mensubiqua.intravita.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public abstract class LoginWebTests {
    private static WebDriver driver = null;
    private static String root;
    public static void run(String root)
    {
    	LoginWebTests.root = root;
        driver = SeleniumTests.getDriver();
        LoginWebTests.register("user", "user", "user@user", "user");
        LoginWebTests.login("user.user", "user");
        LoginWebTests.logout();
    }

    public static void login(String username, String pass)
    {
        driver.get(root);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("form-username")).sendKeys(username);
        driver.findElement(By.id("form-password")).sendKeys(pass);
        driver.findElement(By.id("form-login")).click();
        System.out.println("CORRECTO: Login como User");
    }

    public static void logout()
    {
        driver.get(root + "/default");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("logout")).click();
        System.out.println("CORRECTO: Logout correcto");
    }
    
    public static void register(String name, String apellido, String email, String password)
    {
    	driver.get(root + "/default");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("form-first-name")).sendKeys(name);
        driver.findElement(By.id("form-last-name")).sendKeys(apellido);
        driver.findElement(By.id("form-email")).sendKeys(email);
        driver.findElement(By.id("form-passwordd")).sendKeys(password);
        driver.findElement(By.id("form-password2")).sendKeys(password);
        driver.findElement(By.id("registrar")).click();
        System.out.println("CORRECTO: Registro");
    }
}
