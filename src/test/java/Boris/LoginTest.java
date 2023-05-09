package Boris;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import us.piit.SetUp;

public class LoginTest extends SetUp {

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



    @Parameters({"Email", "Password"})
    @Test
    public void SignIn(String Email, String Password){

        // Verify that user is on Home page
        Assert.assertEquals(getCurrentTitle(), "Home Page");
        isVisible(SignInLink);

        // Click on SignIn button
        SignInLink.click();

        //Enter email and password to login
        EmailInput.sendKeys(Email);
        PasswordInput.sendKeys(Password);
        SignInButton.click();
        waitFor(2);
        Assert.assertTrue(WelcomeGreetMessage.getText().contains("Welcome"));

        // Logout here
        waitFor(2);
        SignOutDropDown.click();
        waitFor(1);
        SignOutButton.click();
        waitFor(3);
    }
}
