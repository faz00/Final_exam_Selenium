package orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.orangeHRM.DashbordPage;
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
        MaintenancePage maintenancePage =new MaintenancePage(getDriver());


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

        clickOn("(//button[@type='button'])[6]");
        log.info("Three dots clicked on success");

        clickOn("//p[normalize-space()='View Leave Details']");
        log.info("View Leave Details option clicked on success");

        clickOn("body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(7) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(7) > div:nth-child(1) > button:nth-child(1)");
        log.info("Clicked on info in success");

        String expectedStatus="Taken";
        String actualStatus=getElementText("//div[contains(text(),'Taken')]");
        Assert.assertEquals(expectedStatus,actualStatus);
    }

    public void rejectLeaveRecordOfAnEmployee(){     LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        MaintenancePage maintenancePage =new MaintenancePage(getDriver());


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

        clickOn("(//button[@type='button'])[6]");
        log.info("Three dots clicked on success");

        clickOn("//p[normalize-space()='View Leave Details']");
        log.info("View Leave Details option clicked on success");

        clickOn("button[class='oxd-button oxd-button--medium oxd-button--label-danger oxd-table-cell-action-space']");
        log.info("Clicked on Reject button in success");

        String expectedStatus="Rejected";
        String actualStatus=getElementText("//div[contains(text(),'Rejected')]");
        Assert.assertEquals(expectedStatus,actualStatus);

    }


    public void addCommentForAnEmployeeRecord(){
        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        MaintenancePage maintenancePage =new MaintenancePage(getDriver());


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

        clickOn("(//button[@type='button'])[6]");
        log.info("Three dots clicked on success");

        clickOn("(//p[normalize-space()='Add Comment'])[1]");
        log.info("Add comment option clicked on success");

        type("textarea[placeholder='Comment here']","This is just a comment");
        log.info("Comment added successfully");

        clickOn("//button[normalize-space()='Save']");
        log.info("Save button clicked on success");

        clickOn("//a[normalize-space()='Leave List']");

        String expectedComment="This is just a comment";
        String actualComment=getElementText("//div[contains(text(),'This is just a comment')]");
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
        MaintenancePage maintenancePage =new MaintenancePage(getDriver());


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

        clickOn("//span[normalize-space()='Configure']");
        log.info("Clicked on Configue button successfully");

        waitFor(2);
        clickOn("//a[normalize-space()='Holidays']");
        log.info("Holiday licked on success");

        waitFor(2);
        clickOn("//button[normalize-space()='Add']");
        log.info("Add button clicked on success");

        type("body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > form:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)","Eid El Adha");
        log.info("Name added in success");

        type("input[placeholder='yyyy-mm-dd']","2023-06-28");
        log.info("Date added");

        clickOn("body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > form:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > span:nth-child(2)");
        log.info("YES checked");

        clickOn("button[type='submit']");
        log.info("Save button Clicked on success");

        String expectedHoliday="Eid El Adha";
        String actualHoliday=getElementText("(//div[contains(text(),'Eid El Adha')])[1]");
        Assert.assertEquals(expectedHoliday,actualHoliday);

    }



}
