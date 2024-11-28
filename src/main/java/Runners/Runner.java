package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(

        features = {"src/test/java/Features"},
        glue = {"StepDefinitions","Util"},
        tags = "@run",
        plugin = {
                "summary","pretty","html:Reports/CucumberReport/Reports.html"
        }

)

public class Runner extends AbstractTestNGCucumberTests{
}