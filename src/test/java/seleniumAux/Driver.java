package seleniumAux;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
	private static WebDriver driver = null;
	private static String root = "https://localhost:8443/intravita";
    /*public static void main(String[] args) {
    	String root = "https://localhost:8443/intravita";

        if(System.getProperty("os.name").equals("Linux"))
            System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        else
            System.setProperty("webdriver.gecko.driver", "lib/geckodriver.exe");

        driver = new FirefoxDriver();

        //LoginWebTests.run(root);
        GeneralWebTests.run(root);

        driver.quit();

    }*/

    public static WebDriver getDriver()
    {
    	if (Driver.driver == null)
    	{
    		if(System.getProperty("os.name").equals("Linux"))
                System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
            else
                System.setProperty("webdriver.gecko.driver", "lib/geckodriver.exe");

    		Driver.driver = new FirefoxDriver();

    	}
    	
        return Driver.driver;
    }
    
    public static void quit()
    {
    	driver.quit();
    }
    
    public static String getRoot()
    {
    	return root;
    }
}
