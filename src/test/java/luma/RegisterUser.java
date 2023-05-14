package luma;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;

public class RegisterUser extends CommonAPI {

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

    String TestEmail = generateTestEmail();

    @Parameters({"Password"})
    @Test
    public void CreateAnAccount(String Password){

        // Verify that user is on Home page
        Assert.assertEquals(getCurrentTitle(), "Home Page");
        isVisible(CreateAnAccountLink);

        //click on Create account button
        CreateAnAccountLink.click();

        // function which actually fill the registration form
        CreateNewUser(TestEmail, Password);
        waitFor(1);
        CreateAccountButton.click();
        waitFor(2);
        isVisible(UserInfo);
        Assert.assertTrue(UserInfo.getText().contains(TestEmail));
        Assert.assertTrue(WelcomeMessage.isDisplayed());
        Assert.assertEquals(WelcomeMessage.getText(), "Thank you for registering with Main Website Store.");

    }




    // Create user function
    public void CreateNewUser(String Email, String Password){

        Assert.assertTrue(FirstName.isDisplayed());

        FirstName.sendKeys("luma");
        LastName.sendKeys(".");
        EmailInput.sendKeys(TestEmail);
        PasswordInput.sendKeys(Password);
        ConfirmPassword.sendKeys(Password);
    }

}
