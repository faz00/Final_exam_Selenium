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


public class LogoutTest extends CommonAPI {
    Logger log = LogManager.getLogger(LogoutTest.class.getName());

    Properties prop = Utility.loadProperties();
    String ValidEmail = prop.getProperty("nopCommerce.username");
    String validPassword = prop.getProperty("nopCommerce.password");


    @Test
    public void logout() throws InterruptedException {
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

        // click logout button to logout the user
        loginPage.clickOnLogoutBtn();
        waitFor(1);
        log.info("Logout success");
    }

}
