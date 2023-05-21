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

    Properties prop = Utility.loadProperties();
    String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
    String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));

    String downloadsDir = System.getProperty("user.home") + File.separator + "Downloads";
    @Test

    public void DownloadEmployeesRecord(){
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
    clickOn("a[class='oxd-topbar-body-nav-tab-item']");
    log.info("Access Records clicked on success");

    String name=getElementText("//p[@class='oxd-userdropdown-name']");
    log.info("USer name copied successfully");

    WebElement element = driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
    type("//input[@placeholder='Type for hints...']",name);
    waitFor(5);
    element.sendKeys(Keys.ARROW_DOWN);
    waitFor(5);
    element.sendKeys(Keys.ENTER);

    clickOn("button[type='submit']");
    log.info("Button clicked on success");

    String expectedEmployeeDisplayed="Selected Employee";
    String actualEmployeeDisplayed =getElementText("div[class='orangehrm-background-container'] h6[class='oxd-text oxd-text--h6 orangehrm-main-title']");
    Assert.assertEquals(expectedEmployeeDisplayed,actualEmployeeDisplayed);

    clickOn("div[class='orangehrm-background-container'] button[type='submit']");
    log.info("Button download clicked on success");




    log.info(name);
    log.info("Download dir"+downloadsDir);
    String path=downloadsDir+File.separator+name+".json";
    log.info("PAth "+path);
        //driver.findElement(By.linkText("Download Document")).click();
        String filePath =downloadsDir+"\\Paul  Collings.json";



        waitFor(10);


        log.info(filePath);
        File file = new File(filePath);
        Assert.assertTrue(file.exists(), "The document file was downloaded successfully.");






    }

}
