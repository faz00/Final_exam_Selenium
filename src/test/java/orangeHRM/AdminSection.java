package orangeHRM;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.orangeHRM.AdminPage;
import us.piit.pages.orangeHRM.DashbordPage;
import us.piit.pages.orangeHRM.LoginPage;
import us.piit.utility.ExcelReader;
import us.piit.utility.Utility;
import java.io.File;
import static us.piit.utility.Utility.currentDir;

public class AdminSection extends CommonAPI {
//    String path=currentDir+ File.separator+"manualTestCases\\OrangeHRMTest.xlsx";

    String path=currentDir+"\\manualTestCases\\OrangeHRMTest.xlsx";
    ExcelReader excelReader =new ExcelReader(path);
        String validUsername = Utility.decode(excelReader.getDataFromCell("DataProvider",1,0));
        String validPassword = Utility.decode(excelReader.getDataFromCell("DataProvider",1,1));

        @Test
    public void addUser(){

        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        AdminPage adminPage =new AdminPage(getDriver());


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
        dashbordPage.searchOptionOnSearchBar("Admin");

        waitFor(5);

        dashbordPage.clickOnAdminOption();

        adminPage.clickOnAddBtn();
        adminPage.clickOnUserRoleDropDownList();
        String name = dashbordPage.getUsernameName();
        adminPage.addEmployeeNAme(name);

        adminPage.clickOnStatus();
        adminPage.typeUsernameField("JohnDoeee");

        adminPage.typePassword("123qwerty");
        adminPage.typeConfirmPassword("123qwerty");

        adminPage.clickOnSubmitJobTitle();

        adminPage.clickOnUserManagementBtn();
        adminPage.clickOnUserOption();


        waitFor(2);
        String expectedCreatedUser="System Users";
        String actualCreatedUser = adminPage.geetActuslCreatedsSer();
        Assert.assertEquals(expectedCreatedUser,actualCreatedUser);

    }
}
