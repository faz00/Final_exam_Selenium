package tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    @Test(priority = 1, groups = {"loginTests"}, dataProvider = "loginData")
    public void testLoginWithCredentials(String email, String password) {
        // Click on the login
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        LoginPage loginPage = new LoginPage(getDriver());
        LoginHomePage loginHomePage = new LoginHomePage(getDriver());

        // Enter email and password from the data provider
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        // Assert that the user is logged in or an error message is displayed
        if (email.equals(validEmail) && password.equals(validPassword)) {
            assertTrue(loginHomePage.isAccountLinkDisplayed(), "The user is not logged in");
        } else {
            assertTrue(loginPage.isErrorMessageDisplayed(), "The user is logged in");
        }

    }
    @Test(priority =8 , groups = {"loginTests"})
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
    @Test(priority = 7, groups = {"loginTests"})
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

    @Test(priority = 6, groups = {"loginTests"})
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
    @Test(priority = 5, groups = {"loginTests"})
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

    @Test(priority = 2, groups = {"loginTests"})
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

    @Test(priority = 3, groups = {"loginTests"})
    public void checkPasswordVisibility(){
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        LoginPage loginPage = new LoginPage(getDriver());

        // Enter any given password into the password text field

        loginPage.setPassword(password);

        // Retrieve the page source and check if the password field is visible
        boolean isPasswordVisible = loginPage.isPasswordVisibleInPageSource(password,driver);

// Assert that the password is visible in the page source
        assertFalse(isPasswordVisible, "Password is visible in the page source");

    }
    @Test(priority = 4, groups = {"loginTests"})
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











