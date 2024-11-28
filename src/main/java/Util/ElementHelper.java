package Util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class ElementHelper {

    WebDriver driver;
    WebDriverWait wait;
    Actions action;
    JavascriptExecutor js;

    public ElementHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.action = new Actions(driver);

        if (driver instanceof JavascriptExecutor) {
            this.js = (JavascriptExecutor) driver;
        } else {
            throw new IllegalStateException("Driver does not support JavaScript execution.");
        }
    }


    // Verify Group

    public WebElement presenceElement(By key) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(key));
    }

    // Action Group

    public WebElement findElement(By key) {
        WebElement element = presenceElement(key);
        return element;
    }

    public void clickElement(By key) {
        findElement(key).click();
    }

    public void sendKey(String text, By key) {
        findElement(key).sendKeys(text);
    }

    public void clear(By key) {
        WebElement element = findElement(key);
        element.clear();
        js.executeScript("arguments[0].value = '';", element);
    }

    public void scroll(By key) {
        WebElement element = null;
        int scrollAttempt = 0;
        long lastHeight = (Long) js.executeScript("return document.body.scrollHeight");

        while (scrollAttempt < 10) {
            try {
                element = driver.findElement(key);
                if (element.isDisplayed()) {
                    js.executeScript("arguments[0].scrollIntoView(true);", element);
                    return;
                }
            } catch (NoSuchElementException e) {
                js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                long newHeight = (Long) js.executeScript("return document.body.scrollHeight");
                if (newHeight == lastHeight) {
                    break;
                }
                lastHeight = newHeight;
            }
            scrollAttempt++;
        }

        throw new NoSuchElementException("Element not found: " + key.toString());
    }

    public void waitForSeconds(String text) {
        int seconds = Integer.parseInt(text);
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted during sleep", e);
        }
    }

    public void waitForElement(By key, String text) {
        int seconds = Integer.parseInt(text);
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(key));
    }

    public void waitForElementIfExist(By key, String text) {
        int seconds = Integer.parseInt(text);
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(key));
        } catch (NoSuchElementException ignored) {
        }
    }

}
