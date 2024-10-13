
package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.junit.Assert;
import pageObjects.LoginPage;
import utils.WebDriverManager2;
import io.cucumber.java.en.*;

public class LoginSteps {
    WebDriver driver = WebDriverManager2.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    
    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver.get("https://example.com/login");
    }


    @When("I enter valid credentials")
    public void i_enter_valid_credentials() {
        loginPage.enterUsername("testuser");
        loginPage.enterPassword("testpass");
        loginPage.clickLogin();
    }

    @Then("I should see the dashboard")
    public void i_should_see_the_dashboard() {
        String expectedUrl = "https://example.com/dashboard";
        Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
    }
}
