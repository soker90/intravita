package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import seleniumAux.Driver;
import seleniumAux.GeneralAux;

public class LoginCorrecto {
	private static WebDriver driver;
    private static String root;
    public static void main(String[] args)
    {
    	root = Driver.getRoot();
        driver = Driver.getDriver();
        
        GeneralAux.redirect(root, "/login");
        GeneralAux.login("super.admin", "admin", "");
        GeneralAux.logout();
        
        driver.quit();
        
    }

}
