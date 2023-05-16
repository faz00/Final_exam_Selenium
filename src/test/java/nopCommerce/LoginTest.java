package nopCommerce;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.nopCommerce.HomePage;
import us.piit.pages.nopCommerce.LoginPage;
import us.piit.utility.Utility;

import java.util.Properties;

public class LoginTest extends CommonAPI{
    Logger log = LogManager.getLogger(LoginTest.class.getName());

    Properties prop = Utility.loadProperties();
    String ValidEmail = Utility.decode(prop.getProperty("nopCommerce.username"));
    String validPassword = Utility.decode(prop.getProperty("nopCommerce.password"));

    @Test
    public void validCredential() {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "nopCommerce demo store");
        waitFor(3);

        //enter  username, password, and click on login button
        loginPage.goToLoginPage();
        loginPage.enterUsername(ValidEmail);
        loginPage.enterPassword(validPassword);
        loginPage.checkRememberMebox();
        loginPage.clickOnLoginBtn();

        //check user is logged in
        waitFor(2);
        Assert.assertTrue(loginPage.WelcomeGreetMessage());
        waitFor(3);
    }

    @Test
    public void invalidEmail(){
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "nopCommerce demo store");
        waitFor(3);

        //enter  username, password, and click on login button
        loginPage.goToLoginPage();
        loginPage.enterUsername("invalid@gmail.com");
        loginPage.enterPassword("invalidPassword");
        loginPage.checkRememberMebox();
        loginPage.clickOnLoginBtn();

        //validate the error message
        String expectedError = "Login was unsuccessful. Please correct the errors and try again.\n" +
                "No customer account found";
        String actualError = loginPage.getErrorMessage();
        Assert.assertEquals(actualError, expectedError);
    }

    @Test
    public void emptyCredentials(){
        LoginPage loginPage = new LoginPage(getDriver());
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "nopCommerce demo store");
        //enter  username, password, and click on login button
        loginPage.goToLoginPage();
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.checkRememberMebox();
        loginPage.clickOnLoginBtn();

        //validate the error message
        String expectedError = "Please enter your email";
        String actualError = loginPage.emptyFieldsErrorMessage();
        Assert.assertEquals(expectedError, actualError);
    }
}
