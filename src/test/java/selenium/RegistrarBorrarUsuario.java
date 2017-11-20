package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.auxiliar.Variables;
import com.mensubiqua.intravita.dao.UserCodeDAOImpl;
import com.mensubiqua.intravita.dao.UserDAOImpl;
import com.mensubiqua.intravita.model.User;

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
        validar("11111");
        
        GeneralAux.login("11111", "11111", "");
        driver.get(root + "/user/configuracion");
        
        driver.findElement(By.id("borrarCuenta")).click();
        driver.findElement(By.id("confirmarBorrar")).click();
        
        if(driver.getCurrentUrl().contains("/login"))
        {
        	System.out.println("CORRECTO: Borrado de cuenta correcto");
        }
        else {
        	System.out.println("FALLO: Borrado de cuenta incorrecto");
        }
                
        driver.quit();
        
    }
    
    public static void validar(String nick)
    {
    	UserDAOImpl dao = new UserDAOImpl();
    	UserCodeDAOImpl daoCode = new UserCodeDAOImpl();
    
    	User u = dao.find(Funciones.encrypt(nick));
		if(u != null){
    			u.setValidado(true);
    			dao.updateValidacion(u);
    			daoCode.delete(nick);
		}
    }

}
