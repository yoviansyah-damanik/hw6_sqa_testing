package swagLabs.cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/swagLabs/cucumber/resources/features",
        glue = "swagLabs/cucumber/stepDef",
        plugin = {"html:target/Testing_Report.html"}
)

public class runTest {}
