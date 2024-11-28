package Functions;

import Util.ElementHelper;
import Util.JsonDataReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class TestFunctions {

    static WebDriver driver;
    static ElementHelper elementHelper;
    WebDriverWait wait;

    public TestFunctions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.elementHelper = new ElementHelper(driver);
    }


    // Data Reader
    JsonDataReader jsonLocatorReader = new JsonDataReader("src/test/java/Locators/");
    JsonDataReader jsonDataReader = new JsonDataReader("src/test/java/TestData/");

    public void goToUrl(String url) {
        try {
            // URL test verisini JSON'dan al
            String urlData = jsonDataReader.getTestData(url); // Test verisini çek
            driver.get(urlData);
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void setTheValueOnTheElement(String text, String element) {
        try {
            // Text verisini ve element locatörünü JSON'dan al
            String value = jsonDataReader.getTestData(text); // Test verisini çek
            By locator = jsonLocatorReader.getLocator(element); // Element locatörünü çek
            elementHelper.sendKey(value, locator); // Değeri elemana yaz
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void clickTheElement(String element) {
        try {
            // Element locatörünü JSON'dan al
            By locator = jsonLocatorReader.getLocator(element); // Element locatörünü çek
            elementHelper.clickElement(locator); // Elemanı tıkla
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void verifyTheElement(String element) {
        try {
            // Element locatörünü JSON dosyasından al
            By locator = jsonLocatorReader.getLocator(element); // Element locatörünü çek
            elementHelper.presenceElement(locator); // Elementin varlığını doğrula
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void clearTheElement(String element) {
        try {
            // Element locatörünü JSON'dan al
            By locator = jsonLocatorReader.getLocator(element);  // Locator bilgisi JSON'dan çekiliyor
            // Elementi temizle
            elementHelper.clear(locator);
        } catch (Exception e) {
            Assert.fail(e.getMessage()); // Hata durumunda başarısızlık mesajı ver
        }
    }

    public void waitForValueSeconds(String element) {
        try {
            elementHelper.waitForSeconds(element);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void scrollToElement(String element) {
        try {
            // Element locatörünü JSON'dan al
            By locator = jsonLocatorReader.getLocator(element);  // Locator bilgisi JSON'dan çekiliyor
            // Elemente kaydırma işlemi yapılır
            elementHelper.scroll(locator);
        } catch (Exception e) {
            Assert.fail(e.getMessage()); // Hata durumunda başarısızlık mesajı ver
        }
    }

    public void waitUntilYouSeeTheElementInValueSeconds(String element, String text) {
        try {
            // Element locatörünü JSON'dan al
            By locator = jsonLocatorReader.getLocator(element);  // Locator bilgisi JSON'dan çekiliyor
            // Elementin belirtilen süre içinde görünmesini bekle
            elementHelper.waitForElement(locator, text);
        } catch (Exception e) {
            Assert.fail(e.getMessage()); // Hata durumunda başarısızlık mesajı ver
        }
    }

    public void waitUntilYouSeeTheElementLabelWithinValueSecondsIfItExist(String element, String text) {
        try {
            // Element locatörünü JSON'dan al
            By locator = jsonLocatorReader.getLocator(element);  // Locator bilgisi JSON'dan çekiliyor
            // Elementin belirtilen süre içinde var olup olmadığını kontrol et
            elementHelper.waitForElementIfExist(locator, text);
        } catch (Exception e) {
            Assert.fail(e.getMessage()); // Hata durumunda başarısızlık mesajı ver
        }
    }

    public void verifyThatThereIsNoElement(String element) {
        try {
            // Element locatörünü JSON'dan al
            By locator = jsonLocatorReader.getLocator(element); // Locator bilgisi JSON'dan çekiliyor
            // Elementin var olup olmadığını kontrol et
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            // Eğer element bulunursa, başarısızlık mesajı ver
            throw new AssertionError("Element with ID '" + element + "' is present");
        } catch (TimeoutException | NoSuchElementException e) {
            // Element bulunamazsa veya zaman aşımı olursa, test başarılı kabul edilir
        } catch (Exception e) {
            // Genel bir hata durumu
            Assert.fail(e.getMessage());
        }
    }


}
