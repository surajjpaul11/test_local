
package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.WebDriverManager2;

/**
 * Hooks for setting up and tearing down the test environment.
 */
public class Hooks {
    @Before
    public void setUp() {
        // Any setup steps before each scenario
    }

    @After
    public void tearDown() {
        WebDriverManager2.quitDriver();
    }
}
