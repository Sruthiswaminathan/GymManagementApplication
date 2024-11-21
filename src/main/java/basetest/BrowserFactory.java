package basetest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BrowserFactory {

    // Private Constructor as a part of Singleton Design Pattern
    private BrowserFactory() {
        // Prevents instantiation
    }

    // Single WebDriver instance
    private static WebDriver driver = null;

    // Method to get the instance of WebDriver
    public static synchronized WebDriver getDriver() {
        if (driver == null) {

               //System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
                driver = new ChromeDriver();
        }
        return driver;
    }

    // Method to close and reset the WebDriver instance
    public static synchronized void cleanUp() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}