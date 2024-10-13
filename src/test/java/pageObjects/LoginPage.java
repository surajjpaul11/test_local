
package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object Model for the Login Page.
 */
public class LoginPage {
    WebDriver driver;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    // Web Elements
    @FindBy(id = "username")
    WebElement txtUsername;
    
    @FindBy(id = "password")
    WebElement txtPassword;
    
    @FindBy(id = "loginBtn")
    WebElement btnLogin;

    // Actions
    public void enterUsername(String username) {
        txtUsername.clear();
        txtUsername.sendKeys(username);
    }

    public void enterPassword(String password) {
        txtPassword.clear();
        txtPassword.sendKeys(password);
    }

    public void clickLogin() {
        btnLogin.click();
    }
}
