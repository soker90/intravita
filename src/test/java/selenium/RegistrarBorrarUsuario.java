package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import seleniumAux.Driver;
import seleniumAux.GeneralAux;

public class RegistrarBorrarUsuario {
	private static WebDriver driver;
    private static String root;
    public static void main(String[] args)
    {
    	root = Driver.getRoot();
        driver = Driver.getDriver();
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(root);
        GeneralAux.register("11111", "11111", "11111", "11111@test.test", "11111");
                
        driver.quit();
        
    }

}
