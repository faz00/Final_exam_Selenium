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

    @FindBy(css = "#input-email")
    WebElement emailField;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement passwordField;

    @FindBy(xpath = "//input[@class='btn btn-primary']")
    WebElement loginButton;

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    WebElement invalidloginCredenErMsg;
    @FindBy(xpath = "//*[@id=\"content\"]/h2[1]")
    WebElement accountHeaderPage;
    @FindBy(linkText = "Forgotten Password")
    WebElement forgtnPasswrdLnk;


    public void setEmail(String email) {
        type(emailField, email);
        log.info("the email address entered successfully in the email input field");
    }

    public void setPassword(String password) {
        type(passwordField, password);
        log.info("Entered password success");
    }

    public void clickLoginButton() {
        clickOn(loginButton);
        log.info("Clicked login button success");
    }

    public boolean LoginCredenErrMsgDisplayed() {
        log.info("an error message should be displayed when entering invalid credentials ");
        return invalidloginCredenErMsg.isDisplayed();

    }

    public boolean isPasswordFieldVisible(String enteredPassword) {
        String passwordFieldValue = passwordField.getAttribute("value");
        log.info("Entered password value: " + enteredPassword);
        log.info("Password field value: " + passwordFieldValue);
        return !enteredPassword.equals(passwordFieldValue);
    }

    public String getEmailPlaceholderText() {
        log.info("the email placeHolder text is " + emailField.getAttribute("placeholder"));
        return emailField.getAttribute("placeholder");
    }

    public String getPasswordPlaceholderText() {
        log.info("the password placeHolder text is " + passwordField.getAttribute("placeholder"));
        return passwordField.getAttribute("placeholder");
    }

    public boolean isLoggedIn() {
        log.info("the account header page is displayed");
        return accountHeaderPage.isDisplayed();
    }

    public boolean isErrorMessageDisplayed() {
        log.info("invalid login error message is displayed");
        return invalidloginCredenErMsg.isDisplayed();
    }

    public String getPgUrl() {
        log.info("the URL of the page is " + driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    public boolean isFrgtnPawrdLnkDsp() {
        log.info("the forgotten password link is displayed");
        return forgtnPasswrdLnk.isDisplayed();
    }

    public void unsfLoginAttempts() {
        int maxAttempts = 3;
        int attemptsMade = 0;

        while (attemptsMade < maxAttempts) {
            attemptsMade++;

            log.info("an error message should be displayed when acceeding the permitted number of attempts");
        }

    }
}


























