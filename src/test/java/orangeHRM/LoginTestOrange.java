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

import java.util.List;
import java.util.Properties;

import static us.piit.utility.ConnectDB.getTableColumnData;


public class LoginTestOrange extends CommonAPI {
    Logger log = LogManager.getLogger(LoginTestOrange.class.getName());

    List<String> password = getTableColumnData("select * from orangeHRM;","validCodedPassword");
    List<String> username = getTableColumnData("select * from orangeHRM;","validCodedUsername");

    @DataProvider(name = "loginTestData")
    public Object[][] provideLoginTestData() {
        Properties prop = Utility.loadProperties();
        String validUsername = Utility.decode(username.get(0));
        String validPassword = Utility.decode( password.get(0));

        return new Object[][]{
                {validUsername, validPassword},
                // Add more test data sets as needed
        };
    }
    @Test(dataProvider = "loginTestData", priority = 1, groups = "login")
    public void validCred(String username, String password) {

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

    }
    @DataProvider(name = "invalidCred")
    public Object[][] provideLoginTestDataInvalidCred() {
        Properties prop = Utility.loadProperties();
        String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));

        return new Object[][]{
                {validUsername},
                // Add more test data sets as needed
        };
    }
    @Test(dataProvider = "invalidCred", priority = 2, groups = "login")
    public void invalidCredentials(String username) {

        LoginPage loginPage = new LoginPage(getDriver());

        String expectedTitle = "OrangeHRM";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);


        //enter username,enter password, and click on login button

        loginPage.enterUsername(username);


        loginPage.enterPassword("WrongPswrd");

        loginPage.clickOnLoginBtn();

        String expectedError = "Invalid credentials";
        String actualError = loginPage.getErrorMessage() ;
        Assert.assertEquals(expectedError,actualError);



    }

    @Test(priority = 3, groups = "login")
    public void noCredentials() {

        LoginPage loginPage = new LoginPage(getDriver());

        String expectedTitle = "OrangeHRM";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);


        //enter username,enter password, and click on login button

        loginPage.enterUsername("");


        loginPage.enterPassword("");

        loginPage.clickOnLoginBtn();


        String expectedError = "Required";
        String actualError = loginPage.getErrorMisingCred() ;
        Assert.assertEquals(expectedError,actualError);
        log.info("Validate error access");

    }

}



