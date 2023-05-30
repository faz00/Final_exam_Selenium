package nopCommerce;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.nopCommerce.LoginPage;
import us.piit.pages.nopCommerce.MyAccountPage;
import us.piit.utility.Utility;

import java.util.Properties;

public class MyAccountTest extends CommonAPI {
    Logger log = LogManager.getLogger(LoginTest.class.getName());

    Properties prop = Utility.loadProperties();
    String ValidEmail = Utility.decode(prop.getProperty("nopCommerce.username"));
    String validPassword = Utility.decode(prop.getProperty("nopCommerce.password"));

    @Test
    public void EditPersonalInformation(){
        LoginPage loginPage = new LoginPage(getDriver());
        MyAccountPage myAccountPage = new MyAccountPage(getDriver());
        //enter  username, password, and click on login button
        loginPage.goToLoginPage();
        loginPage.enterUsername(ValidEmail);
        loginPage.enterPassword(validPassword);
        loginPage.checkRememberMebox();
        loginPage.clickOnLoginBtn();

        // Edit personal information
        myAccountPage.goToMyAccountPage();
        myAccountPage.updatePersonalInfo();
    }

    @Test
    public void EditAddress(){
        LoginPage loginPage = new LoginPage(getDriver());
        MyAccountPage myAccountPage = new MyAccountPage(getDriver());
        //enter  username, password, and click on login button
        loginPage.goToLoginPage();
        loginPage.enterUsername(ValidEmail);
        loginPage.enterPassword(validPassword);
        loginPage.checkRememberMebox();
        loginPage.clickOnLoginBtn();

        // Edit Address information
        myAccountPage.goToMyAccountPage();
        myAccountPage.updateAddress();
    }

    @Test
    public void ChangePassword(){
        LoginPage loginPage = new LoginPage(getDriver());
        MyAccountPage myAccountPage = new MyAccountPage(getDriver());
        //enter  username, password, and click on login button
        loginPage.goToLoginPage();
        loginPage.enterUsername(ValidEmail);
        loginPage.enterPassword(validPassword);
        loginPage.checkRememberMebox();
        loginPage.clickOnLoginBtn();

        // Change password
        myAccountPage.goToMyAccountPage();
        myAccountPage.changePassword();
    }
}
