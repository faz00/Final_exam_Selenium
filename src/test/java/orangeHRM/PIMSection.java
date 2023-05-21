package orangeHRM;
import com.beust.ah.A;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.orangeHRM.DashbordPage;
import us.piit.pages.orangeHRM.LoginPage;
import us.piit.pages.orangeHRM.PIMPage;
import us.piit.pages.orangeHRM.TimePage;
import us.piit.utility.Utility;

import java.util.Properties;

public class PIMSection extends CommonAPI {
    Logger log = LogManager.getLogger(PIMSection.class.getName());

    Properties prop = Utility.loadProperties();
    String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
    String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));

    String imagePath = "C:\\Users\\DELL G5\\IdeaProjects\\Final_exam_Selenium\\screenShots\\orangeHRMScreenshots\\DeletePostInBuzzscreenshot.png";



    public void searchEmployee(){
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
        //click on PIM
        dashbordPage.searchOptionOnSearchBar("PIM");

        dashbordPage.clickOnPimOption();

        String name=getElementText("//p[@class='oxd-userdropdown-name']");
        log.info("USer name copied successfully");

        WebElement element = driver.findElement(By.cssSelector("body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input:nth-child(2)"));
        type("body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input:nth-child(2)",name);
        waitFor(5);
        element.sendKeys(Keys.ARROW_DOWN);
        waitFor(5);
        element.sendKeys(Keys.ENTER);


        clickOn("button[type='submit']");
        log.info("Submit button clicked on success");

        String expectedResult ="(1) Record Found";
        String actualResult=getElementText("div[class='orangehrm-horizontal-padding orangehrm-vertical-padding'] span[class='oxd-text oxd-text--span']");
        Assert.assertEquals(expectedResult,actualResult);



    }

    public void addEmployee(){
        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        PIMPage pimPage = new PIMPage(getDriver());

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
        //click on PIM
        dashbordPage.searchOptionOnSearchBar("PIM");

        dashbordPage.clickOnPimOption();


        clickOn("button[class='oxd-button oxd-button--medium oxd-button--secondary']");
        log.info("Button clicked on success");

        type("input[placeholder='First Name']","Fazia");
        type("input[placeholder='Middle Name']","Sid");
        type("input[placeholder='Last Name']","Ali");


        //add profile picture

        waitFor(5);
        pimPage.setThePathOfImage(imagePath);

        waitFor(10);

        clickOn(".oxd-switch-input.oxd-switch-input--active.--label-right");
        log.info("Button checked");

        type("body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > form:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)","FaziaSid");
        log.info("Username Added");

        type("div[class='oxd-grid-item oxd-grid-item--gutters user-password-cell'] div[class='oxd-input-group oxd-input-field-bottom-space'] div input[type='password']","Fazia2000");
        type("div[class='oxd-grid-item oxd-grid-item--gutters'] div[class='oxd-input-group oxd-input-field-bottom-space'] div input[type='password']","Fazia2000");
        log.info("Password Added success");

        clickOn("button[type='submit']");
        log.info("Button clicked on success");

        String expectedUsername ="FaziaSid";
        String actualUsername=getElementText(".oxd-text.oxd-text--h6.--strong");
        Assert.assertEquals(expectedUsername,actualUsername);

    }

    @Test
    public void editEmployeeInformations(){
        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        PIMPage pimPage = new PIMPage(getDriver());

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
        //click on PIM
        dashbordPage.searchOptionOnSearchBar("PIM");

        dashbordPage.clickOnPimOption();

        clickOn("(//i[@class='oxd-icon bi-pencil-fill'])[1]");
        log.info("Edit icon added successfully");

        waitFor(2);
        //Qualification
        clickOn("//a[normalize-space()='Qualifications']");
        log.info("qualification clicked on success");

        //add Experience
        waitFor(5);
        clickOn("body div[id='app'] div[class='oxd-layout'] div[class='oxd-layout-container'] div[class='oxd-layout-context'] div[class='orangehrm-background-container'] div[class='orangehrm-card-container'] div[class='orangehrm-edit-employee'] div[class='orangehrm-edit-employee-content'] div:nth-child(2) div:nth-child(1) div:nth-child(1) button:nth-child(1)");
        log.info("add experience clicked on success");

        type("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input[1]","Amazon");
        log.info("Company name added");

        type("body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > form:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)","QA Tester");
        log.info("Title added in success");

        type("body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > form:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)","2022-02-03");
        type("body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > form:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)","2023-04-04");

        log.info("Date Added");

        clickOn("button[type='submit']");


        String expectedResultAddedExperience="(1) Record Found";
        String actualResultAddedExperience=getElementText("//span[normalize-space()='(1) Record Found']");
        Assert.assertEquals(expectedResultAddedExperience,actualResultAddedExperience);
    }
}

