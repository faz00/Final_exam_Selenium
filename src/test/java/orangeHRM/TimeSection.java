package orangeHRM;

import com.sun.jna.win32.W32APIFunctionMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.orangeHRM.DashbordPage;
import us.piit.pages.orangeHRM.LoginPage;
import us.piit.pages.orangeHRM.TimePage;
import us.piit.utility.Utility;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Properties;

public class TimeSection extends CommonAPI {

    Logger log = LogManager.getLogger(TimeSection.class.getName());

    @DataProvider(name = "loginCredentials")
    public Object[][] getLoginCredentials() {
        Properties prop = Utility.loadProperties();
        String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
        String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));

        return new Object[][]{
                {validUsername, validPassword}

        };
    }
    @DataProvider(name = "addCustomer")
    public Object[][] newCustomerCredentials() {
        Properties prop = Utility.loadProperties();
        String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
        String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));
        String name = "PNT";
        String description="This is a simple test";
        return new Object[][]{
                {validUsername, validPassword,name,description}

        };
    }
    @DataProvider(name = "addCustomer1")
    public Object[][] newCustomerCredentials1() {
        Properties prop = Utility.loadProperties();
        String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
        String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));
        String name = "PNT1";
        String description="This is a simple test";
        return new Object[][]{
                {validUsername, validPassword,name,description}

        };
    }
    @DataProvider(name = "addCustomer2")
    public Object[][] newCustomerCredentials2() {
        Properties prop = Utility.loadProperties();
        String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
        String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));
        String name = "PNT2";
        String description="This is a simple test";
        return new Object[][]{
                {validUsername, validPassword,name,description}

        };
    }
      @Test(dataProvider = "loginCredentials", priority = 1, groups = "Time")
        public void editCustomerInfo(String validUsername, String validPassword) {
        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        TimePage timePAge = new TimePage(getDriver());

        String expectedTitle = "OrangeHRM";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);


        //enter username,enter password, and click on login button

        loginPage.enterUsername(validUsername);


        loginPage.enterPassword(validPassword);

        loginPage.clickOnLoginBtn();

        //check user is logged in
        String expectedHomePage = "Dashboard";
        String actualHomePage = dashbordPage.getHraderText();
        Assert.assertEquals(expectedHomePage, actualHomePage);



        waitFor(5);
        //click on buzz
        dashbordPage.searchOptionOnSearchBar("Time");

        dashbordPage.clickOnTimeOption();

        waitFor(5);
        //check the Time`s page
        String expectedTimeTitle = "Time";
        String actualTimeTitle = timePAge.getActualTimeTitle();
        Assert.assertEquals(expectedTimeTitle, actualTimeTitle);


        timePAge.clickOnProjectInfoBtn();
        timePAge.clickOnCustomerOption();
        timePAge.clickOnEditIcon();


        waitFor(8);
        timePAge.clearWrittenText();

         waitFor(3);

        timePAge.typeTextInCustomerInfo("This is a test to edit customer information");
        waitFor(5);


        timePAge.clickOnSubmitBtn();
        waitFor(5);

        String expectedDescription="This is a test to edit customer information";
        String actualDescription=timePAge.getActualDescription();
        Assert.assertEquals(expectedDescription,actualDescription);

    }



    @Test(dataProvider = "addCustomer", priority = 2, groups = "Time")
        public void addCustomer(String validUsername, String validPassword,String name,String description){
        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        TimePage timePAge = new TimePage(getDriver());

        String expectedTitle = "OrangeHRM";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);


        //enter username,enter password, and click on login button

        loginPage.enterUsername(validUsername);


        loginPage.enterPassword(validPassword);

        loginPage.clickOnLoginBtn();

        //check user is logged in
        String expectedHomePage = "Dashboard";
        String actualHomePage = dashbordPage.getHraderText();
        Assert.assertEquals(expectedHomePage, actualHomePage);



        waitFor(5);
        //click on TIME
        dashbordPage.searchOptionOnSearchBar("Time");

        dashbordPage.clickOnTimeOption();

        waitFor(5);
        //check the Time`s page
        String expectedTimeTitle = "Time";
        String actualTimeTitle = timePAge.getActualTimeTitle();
        Assert.assertEquals(expectedTimeTitle, actualTimeTitle);

        timePAge.clickOnProjectInfoBtn();
        timePAge.clickOnCustomerOption();

        timePAge.clickOnAddCustomerBtn();
        timePAge.typeCustomerNameField(name);
        timePAge.typeCustomerDescriptionField(description);
        timePAge.clickOnSaveNewCustomerBtn();
            waitFor(5);

            String expectedName = name;
            String actualName=timePAge.getActualName();
            Assert.assertEquals(expectedName,actualName);
    }


    @Test(dataProvider = "addCustomer2", priority = 3, groups = "Time")
        public void deleteAddedCustomer(String validUsername, String validPassword,String name,String description){
        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        TimePage timePAge = new TimePage(getDriver());

        String expectedTitle = "OrangeHRM";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);


        //enter username,enter password, and click on login button

        loginPage.enterUsername(validUsername);


        loginPage.enterPassword(validPassword);

        loginPage.clickOnLoginBtn();

        //check user is logged in
        String expectedHomePage = "Dashboard";
        String actualHomePage = dashbordPage.getHraderText();
        Assert.assertEquals(expectedHomePage, actualHomePage);



        waitFor(5);
        //click on TIME
        dashbordPage.searchOptionOnSearchBar("Time");

        dashbordPage.clickOnTimeOption();

        waitFor(5);
        //check the Time`s page
        String expectedTimeTitle = "Time";
        String actualTimeTitle = timePAge.getActualTimeTitle();
        Assert.assertEquals(expectedTimeTitle, actualTimeTitle);

        timePAge.clickOnProjectInfoBtn();
        timePAge.clickOnCustomerOption();

        timePAge.clickOnAddCustomerBtn();
        timePAge.typeCustomerNameField(name);
        timePAge.typeCustomerDescriptionField(description);
        timePAge.clickOnSaveNewCustomerBtn();
        waitFor(5);

        String expectedName = name;
        String actualName=timePAge.getActualName2();
        Assert.assertEquals(expectedName,actualName);


        timePAge.clickOnCheckBoxToDelete();
        timePAge.clickOnDeleteBtn();
        timePAge.clickOnYesConfirmationDelete();

        List<WebElement> customerNames = driver.findElements(By.xpath("//table[@id='resultTable']//td[2]"));


        // Specify the name you want to check
        String nameToCheck = name;


        // Assert that the name does not exist in the list of customer names
        boolean nameExists = customerNames.stream().anyMatch(element -> element.getText().equals(nameToCheck));
        Assert.assertFalse(nameExists, "The name '" + nameToCheck + "' should not exist in the list of customers.");

    }


       @Test(dataProvider = "addCustomer1", priority = 4, groups = "Time")
        public void addAnExistingCustomer(String validUsername, String validPassword,String name,String description){
        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        TimePage timePAge = new TimePage(getDriver());

        String expectedTitle = "OrangeHRM";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);


        //enter username,enter password, and click on login button

        loginPage.enterUsername(validUsername);


        loginPage.enterPassword(validPassword);

        loginPage.clickOnLoginBtn();

        //check user is logged in
        String expectedHomePage = "Dashboard";
        String actualHomePage = dashbordPage.getHraderText();
        Assert.assertEquals(expectedHomePage, actualHomePage);



        waitFor(5);
        //click on TIME
        dashbordPage.searchOptionOnSearchBar("Time");

        dashbordPage.clickOnTimeOption();

        waitFor(5);
        //check the Time`s page
        String expectedTimeTitle = "Time";
        String actualTimeTitle = timePAge.getActualTimeTitle();
        Assert.assertEquals(expectedTimeTitle, actualTimeTitle);

        timePAge.clickOnProjectInfoBtn();
        timePAge.clickOnCustomerOption();

        timePAge.clickOnAddCustomerBtn();
        timePAge.typeCustomerNameField(name);
        timePAge.typeCustomerDescriptionField(description);
        timePAge.clickOnSaveNewCustomerBtn();
        waitFor(5);

        String expectedName = name;
        String actualName=timePAge.getActualName1();
        Assert.assertEquals(expectedName,actualName);

        timePAge.clickOnAddCustomerBtn();
        timePAge.typeCustomerNameField(name);
        timePAge.typeCustomerDescriptionField(description);
        timePAge.clickOnSaveNewCustomerBtn();


        String expectedError="Already exists";
        String actualError= timePAge.getErrorMsg();
        Assert.assertEquals(expectedError,actualError);


    }
}
