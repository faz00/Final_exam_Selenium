package nopCommerce;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
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

    @DataProvider(name = "validLoginData")
    public Object[][] getValidLoginData(){
        return new Object[][]{
                {ValidEmail, validPassword}
        };
    }

    @DataProvider(name = "inValidLoginData")
    public Object[][] getInValidLoginData(){
        return new Object[][]{
                {"invaliduser@gmail.com", "invalidpassword"}
        };
    }

    @Test(priority = 1, groups = "login", dataProvider = "validLoginData")
    public void validCredential(String email, String password) {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "nopCommerce demo store");
        waitFor(3);

        //enter  username, password, and click on login button
        loginPage.goToLoginPage();
        loginPage.enterUsername(email);
        loginPage.enterPassword(password);
        loginPage.checkRememberMebox();
        loginPage.clickOnLoginBtn();

        //check user is logged in
        waitFor(2);
        Assert.assertTrue(loginPage.WelcomeGreetMessage());
        waitFor(3);
    }

    @Test(priority = 0, groups = "login", dataProvider = "inValidLoginData")
    public void invalidEmail(String inValidEmail, String inValidPass){
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "nopCommerce demo store");
        waitFor(3);

        //enter  username, password, and click on login button
        loginPage.goToLoginPage();
        loginPage.enterUsername(inValidEmail);
        loginPage.enterPassword(inValidPass);
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
