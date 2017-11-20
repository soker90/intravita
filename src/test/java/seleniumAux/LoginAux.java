package seleniumAux;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class LoginAux {
	private static WebDriver driver = Driver.getDriver();
    private static String root = Driver.getRoot();
	
	public static void login(String username, String pass, String msg_esperado)
    {
        driver.get(root);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("form-username")).sendKeys(username);
        driver.findElement(By.id("form-password")).sendKeys(pass);
        driver.findElement(By.id("form-login")).click();
        
        String msg;
        try {
        	msg = driver.findElement(By.id("msglogin")).getText();
        } catch (Exception e) {
			msg = "";
		}
        
        if(msg.equals(msg_esperado))
        	System.out.println("CORRECTO: " + msg);
        else
        	System.out.println("MENSAJE DE LOGIN INESPESRADO: " + msg);
    }
	
	public static void redirect(String ruta, String contiene)
    {
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ruta);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if(driver.getCurrentUrl().contains(contiene))
            System.out.println("CORRECTO: Redirección a " + contiene + " correcta");
        else
            System.out.println("Fallo: Redirección a " + contiene + " fallada");
    }
	
	public static void logout()
    {
        driver.get(root + "/default");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("logout")).click();
        System.out.println("CORRECTO: Logout correcto");
    }
    
    /*public static void register(String name, String apellido, String email, String password)
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
}*/

}
