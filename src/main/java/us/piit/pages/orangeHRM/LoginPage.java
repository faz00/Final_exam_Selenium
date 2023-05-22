package us.piit.pages.orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

public class LoginPage extends CommonAPI {
    Logger log = LogManager.getLogger(LoginPage.class.getName());

    //constructor
    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(css ="input[placeholder='username']")
    WebElement usernameField;

    @FindBy(css ="input[placeholder='password']")
    WebElement passwordField;
    @FindBy(css ="button[type='submit']")
    WebElement loginBtn;
    @FindBy(xpath ="//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")
    WebElement errorLoginMsg;

    @FindBy(css = "body > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > form:nth-child(2) > div:nth-child(2) > div:nth-child(1) > span:nth-child(3)")
    WebElement errorCredentialsRequired;

    @FindBy(css=".oxd-text.oxd-text--h5.orangehrm-login-title")
    WebElement loginHeader;


    //reusable methods
    public void enterUsername(String username){
        type(usernameField, username);
        log.info("enter username success");
    }
    public void enterPassword(String password){
        type(passwordField, password);
        log.info("enter password success");
    }
    public void clickOnLoginBtn(){
        clickOn(loginBtn);
        log.info("click on login button Success");
    }
    public String getErrorMessage(){
        String text = getElementText(errorLoginMsg);
        log.info("get error message text success");
        return text;
    }

    public String getErrorMisingCred(){
        String text = getElementText(errorCredentialsRequired);
        log.info("get error message text success");
        return text;
    }
    public String getHeaderLogin(){
        String text = getElementText(loginHeader);
        log.info("get Header text success");
        return text;
    }




}
