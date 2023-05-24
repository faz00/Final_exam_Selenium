package orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.Command;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.orangeHRM.DashbordPage;
import us.piit.pages.orangeHRM.LoginPage;
import us.piit.pages.orangeHRM.MaintenancePage;
import us.piit.utility.Utility;

import java.io.File;
import java.time.Duration;
import java.util.Properties;

public class MaintenanceSection extends CommonAPI {

    Logger log = LogManager.getLogger(MaintenanceSection.class.getName());

    @DataProvider(name = "loginCredentials")
    public Object[][] getLoginCredentials() {
        Properties prop = Utility.loadProperties();
        String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
        String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));

        String downloadsDir = System.getProperty("user.home") + File.separator + "Downloads";

        return new Object[][]{
                {validUsername, validPassword,downloadsDir}

        };
    }
    @Test(dataProvider = "loginCredentials", priority = 1, groups = "MaintenanceSection")
    public void DownloadEmployeesRecord(String validUsername,String validPassword,String downloadsDir){
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
    dashbordPage.searchOptionOnSearchBar("Maintenance");

    waitFor(5);
    dashbordPage.clickOnMaintenanceOption();


    waitFor(5);

    maintenancePage.enterPassword(validPassword);

    maintenancePage.clickOnSubmitBtn();

    waitFor(2);

    String expectedPage="Maintenance";
    String actualPage = maintenancePage.getHeaderText();
    Assert.assertEquals(expectedPage,actualPage);


    //download records
        maintenancePage.clickOnAccessRecordBtn();
        String name = dashbordPage.getUsernameName();
        maintenancePage.selectNAmeFromDropDownList(name);
        maintenancePage.clickOnSearchEmployeeBtn();


    String expectedEmployeeDisplayed="Selected Employee";
    String actualEmployeeDisplayed =maintenancePage.getActualEmployeeDisplayed();
    Assert.assertEquals(expectedEmployeeDisplayed,actualEmployeeDisplayed);


        maintenancePage.clickOnDownloadEmployee();

    log.info(name);
    log.info("Download dir"+downloadsDir);

    String filePath =downloadsDir+"\\Paul  Collings.json";
    waitFor(10);

        log.info(filePath);
        File file = new File(filePath);
        Assert.assertTrue(file.exists(), "The document file was downloaded successfully.");






    }

}
