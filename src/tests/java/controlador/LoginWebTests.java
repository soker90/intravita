package tests.java.controlador;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public abstract class LoginWebTests {
    private static WebDriver driver = null;
    public static void run()
    {
        driver = SeleniumTests.getDriver();
        LoginWebTests.login("user", "user");
        LoginWebTests.logout();
    }

    public static void login(String username, String pass)
    {
        driver.get("https://localhost:8443");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("form-username")).sendKeys(username);
        driver.findElement(By.id("form-password")).sendKeys(pass);
        driver.findElement(By.id("form-login")).click();
        System.out.println("CORRECTO: Login como User");
    }

    public static void logout()
    {
        driver.get("https://localhost:8443/default");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("logout")).click();
        System.out.println("CORRECTO: Logout correcto");
    }
}
