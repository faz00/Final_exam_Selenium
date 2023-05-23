package orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import us.piit.utility.Utility;
import us.piit.base.CommonAPI;
import us.piit.pages.orangeHRM.DashbordPage;
import us.piit.pages.orangeHRM.LoginPage;
import us.piit.pages.orangeHRM.PIMPage;

import java.util.Properties;

public class ChangePassword extends CommonAPI {


    Properties prop = Utility.loadProperties();

    String pathScreenPackage = "orangeHRMScreenshots";

    @DataProvider(name = "passwordData")
    public Object[][] providePasswordData() {
        return new Object[][] {
                { Utility.decode(prop.getProperty("orangeHRM.username")),
                        Utility.decode(prop.getProperty("orangeHRM.password")),
                        Utility.decode(prop.getProperty("orangeHRM.newPassword")) }
                // Add more test data sets as needed
        };
    }

    @Test(dataProvider = "passwordData")
    public void ChangePassword(String validUsername, String validPassword, String newPassword) {
        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        PIMPage pimPage = new PIMPage(getDriver());

        String expectedTitle = "OrangeHRM";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);

        // Enter username, enter password, and click on the login button
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword(validPassword);
        loginPage.clickOnLoginBtn();

        // Check if the user is logged in
        String expectedHomePage = "Dashboard";
        String actualHomePage = dashbordPage.getHraderText();
        Assert.assertEquals(expectedHomePage, actualHomePage);

        dashbordPage.clickOnUserDropdwon();
        dashbordPage.clickOnChangePassword();

        pimPage.enterCurrentPassword(validPassword);
        pimPage.enterNewPassword(newPassword);
        pimPage.enterConfirmNewPassword(newPassword);
        pimPage.clickOnChangePasswordBtn();

        waitFor(2);

        // Take a screenshot
        takeScreenshot(pathScreenPackage, "UpadtePswrdScreenshot");

        // Log out
        dashbordPage.ClickOnMenuButton();
        dashbordPage.clickOnLogoutBtn();

        waitFor(5);

        String expectedLoginPage = "OrangeHRM";
        String actualLoginPage = driver.getTitle();
        Assert.assertEquals(expectedLoginPage, actualLoginPage);

        // Try to log in with the new password
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword(newPassword);
        loginPage.clickOnLoginBtn();

        // Check if the user is logged in
        String expectedError = "Dashboard";
        String actualError = loginPage.getHeaderLogin();
        Assert.assertEquals(expectedError, actualError);
    }
}
