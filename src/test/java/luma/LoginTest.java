package luma;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.luma.LoginPage;
import us.piit.utility.Utility;

import java.util.Properties;

public class LoginTest extends CommonAPI {

    Logger log = LogManager.getLogger(LoginTest.class.getName());

    Properties prop = Utility.loadProperties();
    String ValidEmail = prop.getProperty("luma.username");
    String validPassword = prop.getProperty("luma.password");

    @DataProvider(name = "validLoginData")
    public Object[][] getValidLoginData(){
        return new Object[][]{
                {ValidEmail, validPassword}
        };
    }

    @DataProvider(name = "inValidLoginData")
    public Object[][] getInValidLoginData(){
        return new Object[][]{
                {"invalid@gmail.com", "invalidpassword"}
        };
    }

    @Test(priority = 0, groups = "Login", dataProvider = "validLoginData")
    public void loginWithValidCredentials(){
        LoginPage loginPage = new LoginPage(getDriver());

        // Verify that user is on Home page
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "Home Page");
        waitFor(3);

        // go to login page
        loginPage.goToLoginPage();

        //enter  username, password, and click on login button
        loginPage.enterUsername(ValidEmail);
        loginPage.enterPassword(validPassword);
        loginPage.clickOnSignInBtn();

        // verify that user is logged in
        Assert.assertTrue(loginPage.verifyUserIsLoggedIn());
    }

    @Test(priority = 0, groups = "Login", dataProvider = "inValidLoginData")
    public void loginWithInValidCredentials(String invalid_email, String invalid_password){
        LoginPage loginPage = new LoginPage(getDriver());

        // Verify that user is on Home page
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "Home Page");
        waitFor(3);

        // go to login page
        loginPage.goToLoginPage();

        //enter  username, password, and click on login button
        loginPage.enterUsername(invalid_email);
        loginPage.enterPassword(invalid_password);
        loginPage.clickOnSignInBtn();

        // verify that user is logged in
        Assert.assertTrue(loginPage.verifyValidationError());
    }

    @Test
    public void loginWithEmptyCredentials(){
        LoginPage loginPage = new LoginPage(getDriver());

        // Verify that user is on Home page
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "Home Page");
        waitFor(3);

        // go to login page
        loginPage.goToLoginPage();

        //enter  username, password, and click on login button
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickOnSignInBtn();

        // verify that user is logged in
        Assert.assertTrue(loginPage.verifyFieldErros());
    }
}
