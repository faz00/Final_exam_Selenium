package orangeHRM;

import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.orangeHRM.AdminPage;
import us.piit.pages.orangeHRM.DashbordPage;
import us.piit.pages.orangeHRM.LoginPage;
import us.piit.pages.orangeHRM.RecruitmentPage;
import us.piit.utility.Utility;

import java.util.Properties;

public class AdminSection extends CommonAPI {
    Properties prop = Utility.loadProperties();
    String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
    String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));

    @Test
    public void addJobTitle(){

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
        adminPage.typeUsernameField("Username");

        adminPage.typePassword("Fazia2000");
        adminPage.typeConfirmPassword("Fazia2000");

        adminPage.clickOnSubmitJobTitle();

        waitFor(2);
        String expectedCreatedUser="System Users";
        String actualCreatedUser = adminPage.geetActuslCreatedsSer();
        Assert.assertEquals(expectedCreatedUser,actualCreatedUser);

    }
}
