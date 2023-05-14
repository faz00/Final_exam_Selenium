package nopCommerce;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;

public class LoginTest extends CommonAPI {
    String ValidationErrorMessage = "Login was unsuccessful. Please correct the errors and try again.\n" +
            "No customer account found";

    public @FindBy(xpath = "//a[@href=\"/login?returnUrl=%2F\"]")
    WebElement LoginLink;

    public @FindBy(xpath = "")
    WebElement UserNameInputField;

    public  @FindBy(id = "Email")
    WebElement EmailInputField;

    public  @FindBy(id = "Password")
    WebElement PasswordInputField;

    public static @FindBy(id = "RememberMe")
    WebElement RememberMeCheckBox;

    public static @FindBy(xpath = "//button[@class=\"button-1 login-button\"]")
    WebElement LoginButton;

    public  @FindBy(xpath = "")
    WebElement HomePageText;

    public  @FindBy(css = "span[id=\"Email-error\"]")
    WebElement EmailError;

    public  @FindBy(css = "div[class=\"message-error validation-summary-errors\"]")
    WebElement ValidationError;

    public  @FindBy(xpath = "")
    WebElement PasswordError;

    public  @FindBy(xpath = "//a[@href=\"/logout\"]")
    WebElement LogOutButton;


    @Test
    public void LoginWithWrongCredentials(){
        // Verifying Page title
        Assert.assertEquals(getCurrentTitle(), "nopCommerce demo store");

        // Verify login link on home page
        isVisible(LoginLink);
        LoginLink.click();

        Login("randomemail@gmail.com", "randomPassword");
        isVisible(ValidationError);
        Assert.assertEquals(ValidationError.getText(), ValidationErrorMessage);
    }

    @Parameters({"Email", "Password"})
    @Test(priority = 1)
    public void LoginWithValidCredentials(String Email, String Password){
        // Verifying Page title
        Assert.assertEquals(getCurrentTitle(), "nopCommerce demo store. Login");

        isVisible(LoginLink);
        LoginLink.click();

        // Method is called which actually logs in a user
        Login(Email, Password);
        isVisible(LogOutButton);
    }


    // This method actually login the user
    public void Login(String Email, String Password){
        EmailInputField.sendKeys(Email);
        PasswordInputField.sendKeys(Password);
        RememberMeCheckBox.click();
        LoginButton.click();
    }

}
