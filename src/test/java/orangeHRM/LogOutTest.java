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

import java.util.Properties;

public class LogOutTest extends CommonAPI {


    @DataProvider(name = "logoutTestData")
    public Object[][] provideLogoutTestData() {
        Properties prop = Utility.loadProperties();
        String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
        String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));

        return new Object[][]{
                {validUsername, validPassword},
                // Add more test data sets as needed
        };
    }

    @Test(dataProvider = "logoutTestData", priority = 1, groups = "LogOutTest")
    public void logout(String username, String password)  {

        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());


            String expectedTitle = "OrangeHRM";
            String actualTitle = driver.getTitle();
            Assert.assertEquals(expectedTitle,actualTitle);


            //enter username,enter password, and click on login button

        loginPage.enterUsername(username);


        loginPage.enterPassword(password);

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

