package orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.orangeHRM.DashbordPage;
import us.piit.pages.orangeHRM.LeavePage;
import us.piit.pages.orangeHRM.LoginPage;
import us.piit.pages.orangeHRM.MaintenancePage;
import us.piit.utility.Utility;

import java.util.Properties;

public class LeaveSection  extends CommonAPI {
    Logger log = LogManager.getLogger(LeaveSection.class.getName());

    Properties prop = Utility.loadProperties();
    String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
    String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));



    public void approveLeaveRecordOfAnEmployee(){
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


    public void rejectLeaveRecordOfAnEmployee(){
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


    public void addCommentForAnEmployeeRecord(){
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


//    public void generateReportFromLeave(){
//        LoginPage loginPage = new LoginPage(getDriver());
//        DashbordPage dashbordPage = new DashbordPage(getDriver());
//        MaintenancePage maintenancePage =new MaintenancePage(getDriver());
//
//
//        String expectedTitle = "OrangeHRM";
//        String actualTitle = driver.getTitle();
//        Assert.assertEquals(expectedTitle,actualTitle);
//
//
//        //enter username,enter password, and click on login button
//
//        loginPage.enterUsername(validUsername);
//
//
//        loginPage.enterPassword(validPassword);
//
//        loginPage.clickOnLoginBtn();
//
//        //check user is logged in
//        String expectedHomePage = "Dashboard";
//        String actualHomePage = dashbordPage.getHraderText();
//        Assert.assertEquals(expectedHomePage,actualHomePage);
//
//        //Maintenance
//        dashbordPage.searchOptionOnSearchBar("Leave");
//
//        waitFor(5);
//        dashbordPage.clickOnLeaveOption();
//
//    }
//
//    public void generatReportOfAnEmployee(){
//        LoginPage loginPage = new LoginPage(getDriver());
//        DashbordPage dashbordPage = new DashbordPage(getDriver());
//        MaintenancePage maintenancePage =new MaintenancePage(getDriver());
//
//
//        String expectedTitle = "OrangeHRM";
//        String actualTitle = driver.getTitle();
//        Assert.assertEquals(expectedTitle,actualTitle);
//
//
//        //enter username,enter password, and click on login button
//
//        loginPage.enterUsername(validUsername);
//
//
//        loginPage.enterPassword(validPassword);
//
//        loginPage.clickOnLoginBtn();
//
//        //check user is logged in
//        String expectedHomePage = "Dashboard";
//        String actualHomePage = dashbordPage.getHraderText();
//        Assert.assertEquals(expectedHomePage,actualHomePage);
//
//        //Maintenance
//        dashbordPage.searchOptionOnSearchBar("Leave");
//
//        waitFor(5);
//        dashbordPage.clickOnLeaveOption();
//
//    }


    @Test
    public void AddHolidayInHolidaysList(){
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
        leavePage.typeHolidayName("Eid El Adha");
        leavePage.typeHolidayDate("2023-07-28");
        leavePage.clickOnCheckBox();
        leavePage.clickOnSubmitBtn();


        String expectedHoliday="Eid El Adha";
        String actualHoliday=leavePage.getActualAddedHoliday();
        Assert.assertEquals(expectedHoliday,actualHoliday);

    }



}
