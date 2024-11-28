package Util;

import Util.drivers.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class Hooks {

    WebDriver driver;

    @Before
    public void before() {
        String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
        driver = DriverFactory.initializeDriver(browser);
        //driver.get("https://www.google.com/");
    }

    @After
    public void after() {
        driver.quit();
    }

}
