package luma;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.luma.LoginPage;
import us.piit.pages.luma.MyAccountPage;
import us.piit.utility.Utility;

import java.util.Properties;

public class MyAccountTest extends CommonAPI {

    Properties prop = Utility.loadProperties();
    String ValidEmail = prop.getProperty("luma.username");
    String validPassword = prop.getProperty("luma.password");

    @Test
    public void SeeAccountInfo(){
        LoginPage loginPage = new LoginPage(getDriver());
        MyAccountPage myAccountPage = new MyAccountPage(getDriver());

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

        // Go to account detail page and see accounts details
        myAccountPage.exploreAccountDetails();
    }

    @Test
    public void EditAccountDetails(){
        LoginPage loginPage = new LoginPage(getDriver());
        MyAccountPage myAccountPage = new MyAccountPage(getDriver());

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

        // Go to account detail update user info
        myAccountPage.goToAccountDetailPage();
        myAccountPage.updateUserInfo();
    }

    @Test
    public void subscribe_to_news_letter(){
        LoginPage loginPage = new LoginPage(getDriver());
        MyAccountPage myAccountPage = new MyAccountPage(getDriver());

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

        // subscribe to newsletter
        myAccountPage.goToAccountDetailPage();
        myAccountPage.SubscribeNewLetter();
        waitFor(1);
        myAccountPage.Un_SubscribeNewLetter();
    }
}
