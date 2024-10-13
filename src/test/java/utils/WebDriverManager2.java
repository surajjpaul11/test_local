
package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Singleton WebDriver manager.
 */
public class WebDriverManager2 {
    private static WebDriver driver;

    private WebDriverManager2() {
        // Private constructor to prevent instantiation
    }

    public static WebDriver getDriver() {
        // Set Chrome options for running in headless mode in CI environments
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Run in headless mode
        options.addArguments("--no-sandbox");  // Bypass OS security model
        options.addArguments("--disable-dev-shm-usage");  // Overcome limited resource problems
        options.addArguments("--disable-gpu");  // Applicable for Windows environments
        options.addArguments("--window-size=1920,1080");  // Optional: set window size
        
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
