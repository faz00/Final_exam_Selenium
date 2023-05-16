package us.piit.pages.nopCommerce;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import us.piit.base.CommonAPI;

public class LoginPage extends CommonAPI {
    Logger log = LogManager.getLogger(LoginPage.class.getName());
    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public @FindBy(xpath = "//a[@href=\"/login?returnUrl=%2F\"]")
    WebElement LoginLink;

    @FindBy(xpath = "")
    WebElement UserNameInputField;

    @FindBy(id = "Email")
    WebElement EmailInputField;

    @FindBy(id = "Password")
    WebElement PasswordInputField;

    @FindBy(id = "RememberMe")
    WebElement RememberMeCheckBox;

    @FindBy(xpath = "//button[@class=\"button-1 login-button\"]")
    WebElement LoginButton;

    @FindBy(xpath = "")
    WebElement HomePageText;

    @FindBy(css = "span[id=\"Email-error\"]")
    WebElement EmailError;

    @FindBy(css = "div[class=\"message-error validation-summary-errors\"]")
    WebElement ValidationError;

    @FindBy(xpath = "")
    WebElement PasswordError;

    @FindBy(xpath = "//a[@href=\"/logout\"]")
    WebElement LogOutButton;

    @FindBy(xpath = "//div[@class=\"page home-page\"]//div[@class=\"topic-block-title\"]//h2")
    WebElement WelcomeGreetMessage;

    //reusable methods
    public void enterUsername(String Email){
        // got signIn page
        type(EmailInputField, Email);
        log.info("enter username success");
    }

    public void goToLoginPage(){
        waitFor(1);
        LoginLink.click();
        Assert.assertTrue(EmailInputField.isDisplayed());
        log.info("Login Page displayed success");
    }
    public void enterPassword(String password){
        type(PasswordInputField, password);
        log.info("enter password success");
    }
    public void checkRememberMebox(){
        clickOn(RememberMeCheckBox);
        log.info("Remember me box checked success");
    }
    public void clickOnLoginBtn(){
        clickOn(LoginButton);
        log.info("click on login button Success");
    }
    public String getErrorMessage(){
        String text = getElementText(ValidationError);
        log.info("get error message text success");
        return text;
    }
    public boolean checkPresenceOfLoginPageHeader(){
        boolean loginPageHeaderIsDisplayed = isVisible(ValidationError);
        log.info("login page header presence "+loginPageHeaderIsDisplayed);
        return loginPageHeaderIsDisplayed;
    }
    public String getLoginPageHeaderText(){
        String loginPageHeaderText = getElementText(ValidationError);
        log.info("login page header text is "+loginPageHeaderText);
        return loginPageHeaderText;
    }

    public boolean WelcomeGreetMessage(){
        return WelcomeGreetMessage.getText().contains("Welcome");
    }

    public String emptyFieldsErrorMessage(){
        return EmailError.getText();
    }

    public void clickOnLogoutBtn(){
        Assert.assertTrue(LogOutButton.isDisplayed());
        waitFor(2);
        LogOutButton.click();
        log.info("Logout button clicked success");
    }
}
