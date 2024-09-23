package basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserFactoryDriver {
    private BrowserFactoryDriver() {
    }
    private static WebDriver driver = null;

    public static WebDriver getDriver() {
        if (driver == null) {
            /*System.setProperty("webdriver.chrome.logfile", "src\\test\\resources\\chromedriver.log");*/
           // System.setProperty("webdriver.chrome.driver", "C:\\Users\\hemavathi_v\\Downloads\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void cleanUp() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
