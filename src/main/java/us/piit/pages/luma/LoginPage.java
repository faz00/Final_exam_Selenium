package us.piit.pages.luma;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import us.piit.base.CommonAPI;

import java.util.List;

public class LoginPage extends CommonAPI {

    Logger log = LogManager.getLogger(LoginPage.class.getName());
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//li[@class='authorization-link']//a)[1]")
    WebElement SignInLink;

    @FindBy(id = "email")
    WebElement EmailInput;

    @FindBy(id = "pass")
    WebElement PasswordInput;

    @FindBy(xpath = "//button[@class=\"action login primary\"]")
    WebElement SignInButton;

    @FindBy(xpath = "(//li[@class=\"greet welcome\"])[1]")
    WebElement WelcomeGreetMessage;

    @FindBy(xpath = "(//button[@data-action=\"customer-menu-toggle\"])[1]")
    WebElement SignOutDropDown;

    @FindBy(xpath = "(//a[@href=\"https://magento.softwaretestingboard.com/customer/account/logout/\"])[1]")
    WebElement SignOutButton;

    @FindBy(css = "[data-ui-id=\"message-error\"]")
    WebElement loginErrorMessage;



    public void goToLoginPage(){
        Assert.assertTrue(SignInLink.isEnabled());
        SignInLink.click();
        waitFor(2);
    }

    public void enterUsername(String Email){
        // got signIn page
        type(EmailInput, Email);
        log.info("enter username success");
    }


    public void enterPassword(String password){
        type(PasswordInput, password);
        log.info("enter password success");
    }

    public void clickOnSignInBtn(){
        clickOn(SignInButton);
        log.info("click on login button Success");
    }

    public boolean  verifyUserIsLoggedIn(){
        waitFor(2);
        WelcomeGreetMessage.isDisplayed();
        return WelcomeGreetMessage.getText().contains("Welcome");
    }

    public void Login(String Email, String Password){
        // Click on SignIn button
        SignInLink.click();

        //Enter email and password to login
        EmailInput.sendKeys(Email);
        PasswordInput.sendKeys(Password);
        SignInButton.click();
        waitFor(2);
        Assert.assertTrue(WelcomeGreetMessage.getText().contains("Welcome"));
    }

    public boolean  verifyValidationError(){
        waitFor(2);
        loginErrorMessage.isDisplayed();
        return loginErrorMessage.getText().contains("Incorrect CAPTCHA");
    }

    public boolean verifyFieldErros(){
        waitFor(2);
        List<WebElement> fieldErrors = driver.findElements(By.xpath("//*[text()='This is a required field.']"));
        for(WebElement error : fieldErrors){
            error.isDisplayed();
            return error.getText().contains("This is a required field.");
        }
        return true;
    }
}
