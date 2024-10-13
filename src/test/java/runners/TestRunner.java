
package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Test runner for Cucumber tests.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepDefinitions"},
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    monochrome = true
    // Uncomment the line below to include tags
    // tags = "@SmokeTest"
)
public class TestRunner {


}
