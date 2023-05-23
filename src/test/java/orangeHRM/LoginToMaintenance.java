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
import us.piit.pages.orangeHRM.MaintenancePage;

import java.util.Properties;

public class LoginToMaintenance extends CommonAPI {
     @DataProvider(name = "loginTestData")
    public Object[][] provideLoginTestData() {
        Properties prop = Utility.loadProperties();
        String username = Utility.decode(prop.getProperty("orangeHRM.username"));
        String password = Utility.decode(prop.getProperty("orangeHRM.password"));

        return new Object[][]{
                {username, password},
        };
    }
    @Test(dataProvider = "loginTestData")
    public void loginWithValidCred(String username,String password){
        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        MaintenancePage maintenancePage =new MaintenancePage(getDriver());


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

        //Maintenance
        dashbordPage.searchOptionOnSearchBar("Maintenance");

        waitFor(5);
        dashbordPage.clickOnMaintenanceOption();


        waitFor(5);

       maintenancePage.enterPassword(password);

      maintenancePage.clickOnSubmitBtn();

        waitFor(2);

        String expectedPage="Maintenance";
        String actualPage = maintenancePage.getHeaderText();
        Assert.assertEquals(expectedPage,actualPage);



    }

    @Test(dataProvider = "loginTestData")
    public void loginWithInvalidCred(String username,String password){
        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        MaintenancePage maintenancePage =new MaintenancePage(getDriver());

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

        //Maintenance
        dashbordPage.searchOptionOnSearchBar("Maintenance");

        waitFor(5);
        dashbordPage.clickOnMaintenanceOption();


        waitFor(5);

        maintenancePage.enterPassword("InvalidAdmin123");

        maintenancePage.clickOnSubmitBtn();

        waitFor(2);

        String expectedPage="Invalid credentials";
        String actualPage = maintenancePage.getErrorMsg();
        Assert.assertEquals(expectedPage,actualPage);


    }
}
