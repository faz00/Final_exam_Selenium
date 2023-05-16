package luma;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.Luma.LoginPage;
import us.piit.utility.Utility;

import java.util.Properties;

public class LoginTest extends CommonAPI {

    Logger log = LogManager.getLogger(nopCommerce.LoginTest.class.getName());

    Properties prop = Utility.loadProperties();
    String ValidEmail = prop.getProperty("luma.username");
    String validPassword = prop.getProperty("luma.password");


    @Test
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
}
