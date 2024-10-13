
package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.junit.Assert;
import pageObjects.SearchPage;
import utils.WebDriverManager2;
import io.cucumber.java.en.*;


public class SearchStepDefinitions{
    WebDriver driver = WebDriverManager2.getDriver();
    SearchPage searchpg = new SearchPage(driver);

    @Then("User searches for {string}")
    public void userSearchesFor(String searchString) {
        searchpg.searchForString(searchString);
        System.out.println("Searched for " + searchString);
    }

    @Then("User clicks on first result on page")
    public void userClicksOnFirstResultOnPage() {
        searchpg.clickOnFirstResultOnPage();
        System.out.println("Clicked on first link on Page");
    }

    @Given("User is on {string} site")
    public void userIsOnSite(String url) {
        driver.get(url);
    }

    @When("title of page is {string}")
    public void titleOfPageIs(String title) {
        Assert.assertEquals(driver.getTitle(),title);
    }

    @Then("title of page contains {string}")
    public void titleOfPageContains(String title_val) {
        Assert.assertTrue(driver.getTitle().contains(title_val));
    }
}
