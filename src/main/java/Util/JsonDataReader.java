package Util;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonDataReader {
    private final String jsonFolderPath;

    public JsonDataReader(String jsonFolderPath) {
        this.jsonFolderPath = jsonFolderPath;
    }

    // Locator bilgilerini çekme
    public By getLocator(String elementName) {
        File folder = new File(jsonFolderPath);
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
        if (files != null) {
            for (File file : files) {
                try {
                    JSONParser parser = new JSONParser();
                    Object obj = parser.parse(new FileReader(file));
                    JSONObject jsonElements = (JSONObject) obj;

                    if (jsonElements.containsKey("elements")) {
                        JSONArray elements = (JSONArray) jsonElements.get("elements");
                        for (Object elem : elements) {
                            JSONObject element = (JSONObject) elem;
                            String elementNameFromJson = (String) element.get("elementName");
                            if (elementNameFromJson.equals(elementName)) {
                                String locatorType = (String) element.get("locatorType");
                                String locatorValue = (String) element.get("locatorValue");

                                switch (locatorType.toLowerCase()) {
                                    case "xpath":
                                        return By.xpath(locatorValue);
                                    case "id":
                                        return By.id(locatorValue);
                                    // Diğer locator türleri burada eklenebilir
                                    default:
                                        throw new RuntimeException("Locator type not supported: " + locatorType);
                                }
                            }
                        }
                    }
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new RuntimeException("Locator not found for element: " + elementName);
    }

    // Test verilerini çekme
    public String getTestData(String elementName) {
        File folder = new File(jsonFolderPath);
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
        if (files != null) {
            for (File file : files) {
                try {
                    JSONParser parser = new JSONParser();
                    Object obj = parser.parse(new FileReader(file));
                    JSONObject jsonElements = (JSONObject) obj;

                    if (jsonElements.containsKey("testData")) {
                        JSONArray testData = (JSONArray) jsonElements.get("testData");
                        for (Object data : testData) {
                            JSONObject test = (JSONObject) data;
                            String elementNameFromJson = (String) test.get("elementName");
                            if (elementNameFromJson.equals(elementName)) {
                                return (String) test.get("value");
                            }
                        }
                    }
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new RuntimeException("Test data not found for element: " + elementName);
    }
}


