package com.mensubiqua.intravita.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public abstract class HomeWebTests {
    private static WebDriver driver;
    public static void run()
    {
         driver = SeleniumTests.getDriver();
        default_test();
        admin_index();
        user_index();
    }

    private static void default_test()
    {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://localhost:8443/default");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if(driver.getCurrentUrl().contains("/login"))
            System.out.println("CORRECTO: Redirección de /default correcta para usuarios Anonimo");
        else
            System.out.println("FALLA: Redirección de /default correcta para usuarios Anonimo");

        LoginWebTests.login("user", "user");
        driver.get("https://localhost:8443/default");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if(driver.getCurrentUrl().contains("/user/index"))
            System.out.println("CORRECTO: Redirección de /default para usuarios normales");
        else
            System.out.println("FALLA: Redirección de /default para usuarios normales");
        LoginWebTests.logout();

        LoginWebTests.login("admin", "admin");
        driver.get("https://localhost:8443/default");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if(driver.getCurrentUrl().contains("/admin/index"))
            System.out.println("CORRECTO: Redirección de /default para administradores");
        else
            System.out.println("FALLA: Redirección de /default para administradores");
        LoginWebTests.logout();
    }

    private static void admin_index()
    {
        LoginWebTests.login("admin", "admin");
        driver.get("https://localhost:8443/admin/index");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(driver.findElement(By.id("title")).getText());
        if(driver.findElement(By.id("title")).getText().contains("Página para admins"))
            System.out.println("CORRECTO: Carga del index de administradores");
        else
            System.out.println("FALLA: Carga del index de administradores");

        driver.get("https://localhost:8443/user/index");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(driver.findElement(By.id("title")).getText());
        if(driver.findElement(By.id("title")).getText().contains("Web para usuarios"))
            System.out.println("CORRECTO: Carga del index de usuarios para administradores");
        else
            System.out.println("FALLA: Carga del index de usuarios para administradores");
        LoginWebTests.logout();

    }

    private static void user_index()
    {
        LoginWebTests.login("user", "user");
        driver.get("https://localhost:8443/user/index");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(driver.findElement(By.id("title")).getText());
        if(driver.findElement(By.id("title")).getText().contains("Web para usuarios"))
            System.out.println("CORRECTO: Carga del index de usuarios");
        else
            System.out.println("FALLA: Carga del index de usuarios");
        LoginWebTests.logout();

    }

}
