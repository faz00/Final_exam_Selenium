package orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import us.piit.utility.ConnectDB;
import us.piit.utility.Utility;
import us.piit.base.CommonAPI;
import us.piit.pages.orangeHRM.DashbordPage;
import us.piit.pages.orangeHRM.LoginPage;
import us.piit.pages.orangeHRM.PIMPage;

import java.util.List;
import java.util.Properties;

import static us.piit.utility.ConnectDB.getTableColumnData;

public class ChangePassword extends CommonAPI {


    Properties prop = Utility.loadProperties();

    List<String> username = getTableColumnData("select * from orangeHRM;","validCodedUsername");
    List<String> newPassword = getTableColumnData("select * from orangeHRM;","validCodedNewPassword");
    List<String> password = getTableColumnData("select * from orangeHRM;","validCodedPassword");


    String pathScreenPackage = "orangeHRMScreenshots";

    @DataProvider(name = "passwordData")
    public Object[][] providePasswordData() {
        return new Object[][] {
                { Utility.decode(username.get(0)),
                        Utility.decode(password.get(0)),
                        Utility.decode(newPassword.get(0)) }

        };
    }

    @Test(dataProvider = "passwordData", priority = 1, groups = "ChangePassword")
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
