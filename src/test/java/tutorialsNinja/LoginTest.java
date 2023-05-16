package tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import us.piit.utility.Utility;
import us.piit.base.CommonAPI;
import us.piit.pages.tutorialsNinja.LoginHomePage;
import us.piit.pages.tutorialsNinja.LoginPage;

import java.time.Duration;
import java.util.Properties;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class LoginTest extends CommonAPI {

    Logger log = LogManager.getLogger(LoginTest.class.getName());

    Properties prop = Utility.loadProperties();
    String validEmail = Utility.decode(prop.getProperty("tutorialsninja.validEmail"));
    String validPassword = Utility.decode(prop.getProperty("tutorialsninja.validPassword"));
    String invalidPassword = Utility.decode(prop.getProperty("tutorialsninja.invalidPassword"));
    String invalidEmail = Utility.decode(prop.getProperty("tutorialsninja.invalidEmail"));
    String password = Utility.decode(prop.getProperty("tutorialsninja.password"));



@Test
    public void testLoginWithValidInputs() {
        //click on the login
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        LoginPage loginPage = new LoginPage(getDriver());
        LoginHomePage loginHomePge = new LoginHomePage(getDriver());

        //enter valid email and valid password into the required fields
        loginPage.setEmail(validEmail);
        loginPage.setPassword(validPassword);
        waitFor(5);
        loginPage.clickLoginButton();

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    // Check that the user is redirected to the  login home page
        assertTrue(loginHomePge.isAccountLinkDisplayed() );
    }
    @Test
    public void testLoginWithInvalidCredentials() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        LoginPage loginPage = new LoginPage(getDriver());

        // Enter an invalid email address and an invalid password
        loginPage.setEmail(invalidEmail);
        loginPage.setPassword(invalidPassword);

        // Click the login button
        loginPage.clickLoginButton();

        // Verify that an error message is displayed for the invalid inputs
      boolean expectedErMsg=loginPage.LoginCredenErrMsgDisplayed();
        assertTrue(expectedErMsg);
    }
    @Test
    public void verifyLoginWithInvalidEmailAddress(){
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        LoginPage loginPage = new LoginPage(getDriver());

        // Enter an invalid email address and a valid password
        loginPage.setEmail(invalidEmail);
        loginPage.setPassword(validPassword);

        // Click the login button
        loginPage.clickLoginButton();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Verify that an error message is displayed for the invalid inputs
        boolean expectedErMsg=loginPage.LoginCredenErrMsgDisplayed();
        assertTrue(expectedErMsg);
    }

    @Test
    public void verifyLoginWithInvalidPassword(){
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        LoginPage loginPage = new LoginPage(getDriver());

        // Enter an valid email address and a invalid password
        loginPage.setEmail(validEmail);
        loginPage.setPassword(invalidPassword);

        // Click the login button
        loginPage.clickLoginButton();

        // Verify that an error message is displayed for the invalid inputs

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        boolean expectErMsg=loginPage.LoginCredenErrMsgDisplayed();
        assertTrue(expectErMsg);
    }
    @Test
    public void verifyLoginWithNoInputs(){
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        LoginPage loginPage = new LoginPage(getDriver());

        // Click the login button
        loginPage.clickLoginButton();

        // Verify that an error message is displayed
        boolean expectedErMsg=loginPage.LoginCredenErrMsgDisplayed();
        assertTrue(expectedErMsg);
    }

    @Test
    public void verifyTheNumberOfUnsuccessfulLoginAttempts() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        LoginPage loginPage = new LoginPage(getDriver());

        // Enter invalid email address and invalid password
        loginPage.setEmail(invalidEmail);
        loginPage.setPassword(invalidPassword);

        int maxAttempts = 3;
        int attemptsMade = 0;

        while (attemptsMade < maxAttempts) {
            // Click the login button
            loginPage.clickLoginButton();
            attemptsMade++;

            // Verify that an error message is displayed
            boolean expectedErMsg = loginPage.LoginCredenErrMsgDisplayed();
            assertTrue(expectedErMsg);


        }
    }

    @Test
    public void checkPasswordVisibility(){
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        LoginPage loginPage = new LoginPage(getDriver());

        // Enter any given password into the password text field

        loginPage.setPassword(password);

        // Retrieve the page source and check if the password field is visible
        boolean isPasswordVisible = loginPage.isPasswordVisibleInPageSource(password,driver);

// Assert that the password is visible in the page source
        assertTrue(isPasswordVisible, "Password is not visible in the page source");

    }
    @Test
    public void testLoginFieldsPlaceholders() {
        LoginPage loginPage = new LoginPage(getDriver());
        LoginHomePage loginHomePage = new LoginHomePage(getDriver());

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));


        // Check that the email and password fields have placeholder text
        String expectedEmailPlaceholder = "E-Mail Address";
        String actualEmailPlaceholder = loginPage.getEmailPlaceholderText();
        assertEquals(actualEmailPlaceholder, expectedEmailPlaceholder);

        String expectedPasswordPlaceholder = "Password";
        String actualPasswordPlaceholder = loginPage.getPasswordPlaceholderText();
        assertEquals(actualPasswordPlaceholder, expectedPasswordPlaceholder);


    }
    }
























