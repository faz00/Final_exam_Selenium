package tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import us.piit.utility.Utility;
import us.piit.base.CommonAPI;
import us.piit.pages.tutorialsNinja.LoginHomePage;
import us.piit.pages.tutorialsNinja.LoginPage;

import java.time.Duration;
import java.util.Properties;

import static org.testng.Assert.*;
public class LoginTest  extends CommonAPI {

    Logger log = LogManager.getLogger(LoginTest.class.getName());
    Properties prop = Utility.loadProperties();
    String validEmail = Utility.decode(prop.getProperty("tutorialsninja.validEmail"));
    String validPassword = Utility.decode(prop.getProperty("tutorialsninja.validPassword"));
    String invalidPassword = Utility.decode(prop.getProperty("tutorialsninja.invalidPassword"));
    String invalidEmail = Utility.decode(prop.getProperty("tutorialsninja.invalidEmail"));
    String password = Utility.decode(prop.getProperty("tutorialsninja.password"));


    @Test(priority=1,groups="navigationTest")
    public void vrfyUsrNvgtTloginHmPge(){

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        LoginPage loginPage = new LoginPage(getDriver());

        LoginHomePage loginHomePage = new LoginHomePage(getDriver());

        // Enter email and password
        loginPage.setEmail(validEmail);

        loginPage.setPassword(validPassword);

        loginPage.clickLoginButton();

        //verify the user navigates to the 'My Account' home page
Assert.assertTrue(loginHomePage.isAccountLinkDisplayed(),"the link is not displayed");
    }

    @DataProvider(name = "loginData")
    public Object[][] provideLoginData() {
        return new Object[][] {
                {validEmail, validPassword},     // Valid email and valid password
                {invalidEmail, invalidPassword}, // Invalid email and invalid password
                {invalidEmail, validPassword},   // Invalid email and valid password
                {validEmail, invalidPassword},   // Valid email and invalid password
                {"", ""}                         // Empty email and password
        };
    }

    @Test(priority = 6, groups = "loginWithCredTest", dataProvider = "loginData")
    public void testLoginWithCredentials(String email, String password) {


        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        LoginPage loginPage = new LoginPage(getDriver());

        LoginHomePage loginHomePage = new LoginHomePage(getDriver());

        // Enter email and password from the data provider
        loginPage.setEmail(email);

        loginPage.setPassword(password);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        loginPage.clickLoginButton();

        // Assert if the user  logged in or an error message is displayed
        if (email.equals(validEmail) && password.equals(validPassword)) {
            assertTrue(loginHomePage.isAccountLinkDisplayed(), "The user is not logged in");
        } else {
            assertTrue(loginPage.isErrorMessageDisplayed(), " no error message displayed the user is logged in");
        }

    }
    @Test(priority = 2, groups = "unsuccessfulLoginAttemptsTest")
    public void verifyTheNumberOfUnsuccessfulLoginAttempts() {

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        LoginPage loginPage = new LoginPage(getDriver());

        // Enter invalid email address and invalid password
        loginPage.setEmail(invalidEmail);

        loginPage.setPassword(invalidPassword);

        //count the number of unsuccessful login attempts
        loginPage.unsfLoginAttempts();

        // Click the login button
        loginPage.clickLoginButton();

        // Verify that an error message is displayed
        boolean expectedErMsg = loginPage.LoginCredenErrMsgDisplayed();

        assertTrue(expectedErMsg, "the user doesn't received any LoginCredenErrMsg ");

    }


    @Test(priority = 3, groups = "pswrdVisibilityTest")
    public void checkPasswordVisibility(){

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        LoginPage loginPage = new LoginPage(getDriver());

        // Enter any given password into the password text field
        loginPage.setPassword(password);

        loginPage.clickLoginButton();

// Assert if the password is visible to the page source
        takeScreenshot("TutorialsNinja","checkPswrdVisibility");

        loginPage.htmlScrenShot(getDriver());

     Assert.assertTrue(loginPage.isPasswordFieldVisible(password), "Password is visible to the page source");

    }
    @Test(priority = 4, groups = {"placeHoldersTest"})

    public void testLoginFieldsPlaceholders() {

        LoginPage loginPage = new LoginPage(getDriver());

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        // Check that the email and password fields have placeholder text
        String expectedEmailPlaceholder = "E-Mail Address";

        String actualEmailPlaceholder = loginPage.getEmailPlaceholderText();

        assertEquals(actualEmailPlaceholder, expectedEmailPlaceholder,"the expectedEmailPlaceholder is not equal actualEmailPlaceholder");

        String expectedPasswordPlaceholder = "Password";

        String actualPasswordPlaceholder = loginPage.getPasswordPlaceholderText();

        assertEquals(actualPasswordPlaceholder, expectedPasswordPlaceholder,"the expectedPasswordPlaceholder is not equal actualPasswordPlaceholder");

    }

//verify if the 'forgotten password'link is displayed and works
@Test(priority=5,groups="pswrdLinkTest")
          public void verifyForgottenPasswrdLinkDsp(){

        LoginPage loginPage = new LoginPage(getDriver());

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

       //check the 'forgotten password' link displayed
            assertTrue(loginPage.isFrgtnPawrdLnkDsp(),"the forgotten password link is not displayed");






    }
}










