package orangeHRM;

import com.github.dockerjava.core.exec.WaitContainerCmdExec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.orangeHRM.*;
import us.piit.utility.Utility;

import java.util.Properties;

public class LeaveSection  extends CommonAPI {

    @DataProvider(name = "loginCredentials")
    public Object[][] getLoginCredentials() {
        Properties prop = Utility.loadProperties();
        String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
        String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));

        return new Object[][]{
                {validUsername, validPassword}

        };
    }


    @Test(dataProvider = "loginCredentials", priority = 1, groups = "Leave")
    public void approveLeaveRecordOfAnEmployee(String validUsername,String validPassword){
        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        LeavePage leavePage =new LeavePage(getDriver());


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

        //Maintenance
        dashbordPage.searchOptionOnSearchBar("Leave");

        waitFor(5);
        dashbordPage.clickOnLeaveOption();
        leavePage.clickOnLeaveListOption();
        leavePage.clickonThreeDots();
        leavePage.clickOnLeaveDetailsOpion();
        leavePage.clickOnApproveBtn();


        String expectedStatus="Taken";
        String actualStatus=leavePage.getActualApproveStatus();
        Assert.assertEquals(expectedStatus,actualStatus);
    }


    @Test(dataProvider = "loginCredentials", priority = 2, groups = "Leave")
    public void rejectLeaveRecordOfAnEmployee(String validUsername,String validPassword){
        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        LeavePage leavePage =new LeavePage(getDriver());

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

        //Maintenance
        dashbordPage.searchOptionOnSearchBar("Leave");

        waitFor(5);
        dashbordPage.clickOnLeaveOption();
        leavePage.clickOnLeaveListOption();
        waitFor(5);
        leavePage.clickonThreeDots();

        waitFor(5);
        leavePage.clickOnLeaveDetailsOpion();

       leavePage.clickOnRejectBtn();

        String expectedStatus="Rejected";
        String actualStatus=leavePage.getActualRejectStatus();
        Assert.assertEquals(expectedStatus,actualStatus);

    }


    @Test(dataProvider = "loginCredentials", priority = 3, groups = "Leave")
    public void addCommentForAnEmployeeRecord(String validUsername,String validPassword){
        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        LeavePage leavePage =new LeavePage(getDriver());

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

        //Maintenance
        dashbordPage.searchOptionOnSearchBar("Leave");

        waitFor(5);
        dashbordPage.clickOnLeaveOption();

        waitFor(2);
        leavePage.clickOnLeaveListOption();
        waitFor(2);
        leavePage.clickonThreeDots();
        leavePage.clickOnAddCommentOption();
        leavePage.addComment();
        waitFor(5);
        leavePage.clickOnSaveComment();

        leavePage.clickOnLeaveListOption();

        String expectedComment="This is just a comment";
        String actualComment= leavePage.getActualComment();
        Assert.assertEquals(expectedComment,actualComment);



    }

    @Test(dataProvider = "loginCredentials", priority = 4, groups = "Leave")
     public void generateReportOfAnEmployee(String validUsername,String validPassword){
        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
         LeavePage leavePage =new LeavePage(getDriver());
        ReportsLeavePage reportsLeavePage = new ReportsLeavePage(getDriver());

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

        //Maintenance
        dashbordPage.searchOptionOnSearchBar("Leave");

        waitFor(5);
        dashbordPage.clickOnLeaveOption();

        waitFor(2);
        //Go to report

        reportsLeavePage.clickOnReportBtn();

        reportsLeavePage.clickOnLeaveEntitlementBtn();
        reportsLeavePage.checkEmployeeCheckbox();

        String name = dashbordPage.getUsernameName();
        reportsLeavePage.chooseEmployeeName(name);

        reportsLeavePage.clickOnGenerateBtn();


        String expectedReport="Leave Type";
        String actualReport=reportsLeavePage.getActualReport();
        Assert.assertEquals(expectedReport,actualReport);
    }


    @Test(dataProvider = "NewHoliday", priority = 5, groups = "Leave")
    public void AddHolidayInHolidaysList(String validUsername,String validPassword,String holidayName,String date){
        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        LeavePage leavePage =new LeavePage(getDriver());

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

        //Maintenance
        dashbordPage.searchOptionOnSearchBar("Leave");

        waitFor(5);
        dashbordPage.clickOnLeaveOption();

        leavePage.clickOnConfigueOption();
        leavePage.clickOnHolidayOption();
        waitFor(2);
        leavePage.clickOnAddHolidayBtn();
        leavePage.typeHolidayName(holidayName);
        leavePage.typeHolidayDate(date);
        leavePage.clickOnCheckBox();
        leavePage.clickOnSubmitBtn();


        String expectedHoliday=holidayName;
        String actualHoliday=leavePage.getActualAddedHoliday();
        Assert.assertEquals(expectedHoliday,actualHoliday);

    }

    @DataProvider(name = "NewHoliday")
    public Object[][] addNewHoliday() {
        Properties prop = Utility.loadProperties();
        String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
        String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));
        String holidayName="Eid El Adha";
        String date="2023-06-28";
        return new Object[][]{
                {validUsername, validPassword,holidayName,date}

        };
    }

}
