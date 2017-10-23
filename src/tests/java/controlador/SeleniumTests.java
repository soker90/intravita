package tests.java.controlador;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTests {
    private static WebDriver driver = null;
    public static void main(String[] args) {

        if(System.getProperty("os.name").equals("Linux"))
            System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
        else
            System.setProperty("webdriver.gecko.driver", "lib/geckodriver.exe");

        driver = new FirefoxDriver();

        LoginWebTests.run();
        HomeWebTests.run();

        driver.quit();

    }

    public static WebDriver getDriver()
    {
        return SeleniumTests.driver;
    }

}