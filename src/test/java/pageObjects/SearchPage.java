package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SearchPage {
    WebDriver driver;

    // Constructor
    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // WebElements
    @FindBy(xpath = "//textarea[@aria-label='Search']")
    public WebElement searchTextBox;

    @FindBy(xpath = "(//div[@id='rso']//a)[1]")
    public WebElement firstSearchResultLink;

    public void searchForString(String searchString) {
        searchTextBox.sendKeys(searchString);
        searchTextBox.sendKeys(Keys.ENTER);
    }

    public void clickOnFirstResultOnPage() {
        firstSearchResultLink.click();
    }



}
