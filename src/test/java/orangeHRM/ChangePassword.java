package orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.utility.Utility;
import us.piit.base.CommonAPI;
import us.piit.pages.orangeHRM.DashbordPage;
import us.piit.pages.orangeHRM.LoginPage;
import us.piit.pages.orangeHRM.PIMPage;

import java.io.File;
import java.util.Properties;

public class ChangePassword extends CommonAPI {

    Logger log = LogManager.getLogger(ChangePassword.class.getName());
    Properties prop = Utility.loadProperties();
    String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
    String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));

    String newPassword = Utility.decode(prop.getProperty("orangeHRM.newPassword"));

    String pathScreenPackage="orangeHRMScreenshots";

    @Test
    public  void ChangePassword()  {

        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        PIMPage pimPage = new PIMPage(getDriver());


        String expectedTitle = "OrangeHRM";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);


        //enter username,enter password, and click on login button

        loginPage.enterUsername(validUsername);


        loginPage.enterPassword(validPassword);

        loginPage.clickOnLoginBtn();

        //check user is logged in
        String expectedHomePage = "Dashboard";
        String actualHomePage = dashbordPage.getHraderText();
        Assert.assertEquals(expectedHomePage,actualHomePage);


        dashbordPage.clickOnUserDropdwon();

        dashbordPage.clickOnChangePassword();

        pimPage.enterCurrentPassword(validPassword);

        pimPage.enterNewPassword(newPassword);
        pimPage.enterConfirmNewPassword(newPassword);

        pimPage.clickOnChangePasswordBtn();

        waitFor(2);


        //Take a screenShot
//        captureScreenshot(driver, "C:\\Users\\DELL G5\\IdeaProjects\\Final_exam_Selenium\\src\\test\\java\\orangeHRM\\orangeHRMScreenshots"+File.separator+"UpadtePswrdScreenshot.png");
        takeScreenshot(pathScreenPackage,"UpadtePswrdScreenshot");


        //Log out
        dashbordPage.ClickOnMenuButton();

        dashbordPage.clickOnLogoutBtn();


        waitFor(5);

        String expectedLoginPage = "OrangeHRM";
        String actualLoginPage = driver.getTitle();
        Assert.assertEquals(expectedLoginPage,actualLoginPage);

        //Try to log in with the new password


        loginPage.enterUsername(validUsername);


        loginPage.enterPassword(newPassword);

        loginPage.clickOnLoginBtn();

        //check user is logged in
        String expectedError = "Dashbord";
        String actualError = loginPage.getHeaderLogin() ;
        Assert.assertEquals(expectedError,actualError);


    }



}
