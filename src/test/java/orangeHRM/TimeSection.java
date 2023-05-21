package orangeHRM;

import com.sun.jna.win32.W32APIFunctionMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
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

    Properties prop = Utility.loadProperties();
    String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
    String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));



    public void editCustomerInfo() {
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
        String actualTimeTitle = getElementText("(//h6[normalize-space()='Time'])[1]");
        Assert.assertEquals(expectedTimeTitle, actualTimeTitle);

        clickOn("//span[normalize-space()='Project Info']");
        log.info("Project info clicked on success");

        clickOn("header[class='oxd-topbar'] li:nth-child(1) a:nth-child(1)");
        log.info("Customer option clicked on success");

        clickOn("div[role='table'] div:nth-child(1) div:nth-child(1) div:nth-child(4) div:nth-child(1) button:nth-child(2)");
        log.info("Edit icon clicked on success");


        waitFor(8);
        WebElement element = driver.findElement(By.cssSelector("textarea[placeholder='Type description here']"));
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        log.info("Text delete success");

//        clickOn("textarea[placeholder='Type description here']");
//        driver.findElement(By.xpath("//textarea[@placeholder='Type description here']")).clear();
          waitFor(3);
        type("textarea[placeholder='Type description here']","This is a test to edit customer information");

        waitFor(5);

        clickOn("button[type='submit']");
        waitFor(5);

        String expectedDescription="This is a test to edit customer information";
        String actualDescription=getElementText("//div[contains(text(),'This is a test to edit customer information')]");
        Assert.assertEquals(expectedDescription,actualDescription);

    }


    @Test
    public void addCustomer(){
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
        String actualTimeTitle = getElementText("(//h6[normalize-space()='Time'])[1]");
        Assert.assertEquals(expectedTimeTitle, actualTimeTitle);

        clickOn("//span[normalize-space()='Project Info']");
        log.info("Project info clicked on success");

        clickOn("header[class='oxd-topbar'] li:nth-child(1) a:nth-child(1)");
        log.info("Customer option clicked on success");



            clickOn("//button[normalize-space()='Add']");
            log.info("Add button clicked on success");

            type("div[class='oxd-input-group oxd-input-field-bottom-space'] div input[class='oxd-input oxd-input--active']", "PNT");
            log.info("Name added successfully");

            type("//textarea[@placeholder='Type description here']", "This is a simple test");
            log.info("description added successfully");

            clickOn("//textarea[@placeholder='Type description here']");
            log.info("Save button clicked on success");


            clickOn("button[type='submit']");

            waitFor(5);



            String expectedName = "PNT";
            String actualName=getElementText("//div[contains(text(),'PNT')]");
            Assert.assertEquals(expectedName,actualName);


    }


    public void deleteAddedCustomer(){
//        addCustomer();



        clickOn("//div[@role='columnheader']//i[@class='oxd-icon bi-check oxd-checkbox-input-icon']");

            log.info("Check box is checked successfully");


        clickOn("button[class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-horizontal-margin']");
        log.info("Delete selection button clicked in success");

        clickOn("//button[normalize-space()='Yes, Delete']");
        log.info("Button clicked in success");


        List<WebElement> customerNames = driver.findElements(By.xpath("//table[@id='resultTable']//td[2]"));

// Specify the name you want to check
        String nameToCheck = "PNT";

// Assert that the name does not exist in the list of customer names
        boolean nameExists = customerNames.stream().anyMatch(element -> element.getText().equals(nameToCheck));
        Assert.assertFalse(nameExists, "The name '" + nameToCheck + "' should not exist in the list of customers.");







    }


    public void addAnExistingCustomer(){
        addCustomer();

        clickOn("//button[normalize-space()='Add']");
        log.info("Add button clicked on success");

        type("div[class='oxd-input-group oxd-input-field-bottom-space'] div input[class='oxd-input oxd-input--active']","PNT");
        log.info("Name added successfully");

        type("//textarea[@placeholder='Type description here']","This is a simple test");
        log.info("description added successfully");

        clickOn("//textarea[@placeholder='Type description here']");
        log.info("Save button clicked on success");

        String expectedError="Already exists";
        String actualError= getElementText(".oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message");
        Assert.assertEquals(expectedError,actualError);


    }
}
