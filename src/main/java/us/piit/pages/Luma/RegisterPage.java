package us.piit.pages.Luma;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import us.piit.base.CommonAPI;
import us.piit.utility.Utility;

import java.util.Properties;

public class RegisterPage extends CommonAPI {

    Logger log = LogManager.getLogger(LoginPage.class.getName());
    protected WebDriver driver;
    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    Properties prop = Utility.loadProperties();
    String ValidEmail = generateTestEmail();
    String validPassword = prop.getProperty("luma.password");
    String firstName = prop.getProperty("first.name");
    String lastName = prop.getProperty("last.name");

    @FindBy(xpath = "(//a[text()='Create an Account'])[1]")
    WebElement CreateAnAccountLink;

    @FindBy(id = "firstname")
    WebElement FirstName;

    @FindBy(id = "lastname")
    WebElement LastName;

    @FindBy(id = "email_address")
    WebElement EmailInput;

    @FindBy(id = "password")
    WebElement PasswordInput;

    @FindBy(id = "password-confirmation")
    WebElement ConfirmPassword;

    @FindBy(xpath = "//button[@class=\"action submit primary\"]")
    WebElement CreateAccountButton;

    @FindBy(xpath = "//div[@class=\"box box-information\"]//div[@class=\"box-content\"]")
    WebElement UserInfo;

    @FindBy(xpath = "//div[text()='Thank you for registering with Main Website Store.']")
    WebElement WelcomeMessage;

    public void goToCreateUserPage(){
        CreateAnAccountLink.click();
        waitFor(1);
        Assert.assertTrue(EmailInput.isDisplayed());
    }

    public void FillUserDetailForm(String Case){
        switch (Case){
            case "All details":
                Assert.assertTrue(FirstName.isDisplayed());
                FirstName.sendKeys("Luma");
                LastName.sendKeys(".");
                EmailInput.sendKeys(ValidEmail);
                PasswordInput.sendKeys(validPassword);
                ConfirmPassword.sendKeys(validPassword);
                waitFor(1);
        }
    }

    public void clickOnCreateAccountBtn(){
        Assert.assertTrue(CreateAccountButton.isEnabled());
        CreateAccountButton.click();
        waitFor(2);
    }

    public boolean verifyThatAccountIsCreated(){
        Assert.assertTrue(UserInfo.getText().contains(ValidEmail));
        Assert.assertTrue(WelcomeMessage.isDisplayed());
        return WelcomeMessage.getText().contains("Thank you for registering with Main Website Store.");
    }
}
