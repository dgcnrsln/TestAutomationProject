package StepDefinitions;

import Functions.TestFunctions;
import Util.drivers.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class MyStepdefs {

    WebDriver driver = DriverFactory.getDriver();
    TestFunctions testFunctions = new TestFunctions(driver);


    @Given("Go to {string}")
    public void goTo(String url) {
        testFunctions.goToUrl(url);
    }

    @When("Set the {string} on the {string}")
    public void setTheOnThe(String text, String element) {
        testFunctions.setTheValueOnTheElement(text, element);
    }

    @When("Click the {string}")
    public void clickThe(String element) {
        testFunctions.clickTheElement(element);
    }

    @Then("Verify the {string}")
    public void verifyThe(String element) {
        testFunctions.verifyTheElement(element);
    }

    @When("Clear the {string}")
    public void clearThe(String element) {
        testFunctions.clearTheElement(element);
    }

    @When("Wait for {string} seconds")
    public void waitForSeconds(String element) {
        testFunctions.waitForValueSeconds(element);
    }

    @When("Scroll to {string}")
    public void scrollTo(String element) {
        testFunctions.scrollToElement(element);
    }

    @When("Wait until you see the {string} in {string} Seconds")
    public void waitUntilYouSeeTheInSeconds(String element, String text) {
        testFunctions.waitUntilYouSeeTheElementInValueSeconds(element, text);
    }

    @When("Wait until you see the {string} label within {string} seconds if it exists")
    public void waitUntilYouSeeTheLabelWithinSecondsIfItExists(String element, String text) {
        testFunctions.waitUntilYouSeeTheElementLabelWithinValueSecondsIfItExist(element, text);
    }

    @Then("Verify that there is no {string}")
    public void verifyThatThereIsNo(String element) {
        testFunctions.verifyThatThereIsNoElement(element);
    }
}
