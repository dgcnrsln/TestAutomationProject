package Util.drivers;

import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver initializeDriver(String browser) {
        IWebDriverManager webDriverManager;

        switch (browser.toLowerCase()) {
            case "chrome":
                webDriverManager = new ChromeDriverManager();
                break;
            case "firefox":
                webDriverManager = new FirefoxDriverManager();
                break;
            case "edge":
                webDriverManager = new EdgeDriverManager();
                break;
            default:
                throw new IllegalArgumentException("Geçersiz tarayıcı : " + browser);
        }

        driver = webDriverManager.createDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
