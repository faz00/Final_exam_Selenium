package us.piit.pages.tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

public class LoginPage extends CommonAPI {

    Logger log = LogManager.getLogger(LoginPage.class.getName());
    public LoginPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }
    @FindBy(css ="#input-email")
    WebElement emailField;

    @FindBy(xpath ="//input[@id='input-password']")
    WebElement passwordField;

    @FindBy(xpath ="//input[@class='btn btn-primary']")
    WebElement loginButton;

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    WebElement invalidloginCredenErMsg;

    public void setEmail(String email) {
        type(emailField,email);
        log.info("the email address entered successfully in the email input field");
    }

    public void setPassword(String password) {
        type(passwordField,password);
        log.info("Entered password success");
    }

    public void clickLoginButton() {
        clickOn(loginButton);
        log.info("Clicked login button success");
    }

    public boolean LoginCredenErrMsgDisplayed(){
        log.info("an error message should be displayed when entering invalid credentials ");
        return invalidloginCredenErMsg.isDisplayed();

    }
    public boolean isPasswordVisibleInPageSource(String password,WebDriver driver) {

        setPassword(password);
        String pageSource = driver.getPageSource();
        return !pageSource.contains(password);
    }
    public String getEmailPlaceholderText() {
        return emailField.getAttribute("placeholder");
    }

    public String getPasswordPlaceholderText() {
        return passwordField.getAttribute("placeholder");
    }

}
















