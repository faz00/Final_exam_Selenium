package orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.orangeHRM.DashbordPage;
import us.piit.pages.orangeHRM.LoginPage;
import us.piit.utility.ExcelReader;
import us.piit.utility.Utility;
import java.io.File;

import static us.piit.utility.Utility.currentDir;

public class LogOutTest extends CommonAPI {

    Logger log = LogManager.getLogger(LogOutTest.class.getName());


    String path=currentDir+File.separator+"manualTestCases\\OrangeHRMTest.xlsx";
    @DataProvider(name = "loginCredentials")
    public Object[][] getLoginCredentials() {
        String validUsername = Utility.decode(ExcelReader.getDataFromCell(path,"DataProvider",1,0));
        String validPassword = Utility.decode(ExcelReader.getDataFromCell(path,"DataProvider",1,1));

        return new Object[][]{
                {validUsername, validPassword}

        };
    }
    @Test(dataProvider = "loginCredentials", priority = 1, groups = "Logout")
    public void logout(String validUsername, String validPassword)  {

        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());


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


            //Log Out
          dashbordPage.ClickOnMenuButton();

          dashbordPage.clickOnLogoutBtn();




            String expectedLoginPage = "OrangeHRM";
            String actualLoginPage = driver.getTitle();
            Assert.assertEquals(expectedLoginPage,actualLoginPage);




        }
    }

