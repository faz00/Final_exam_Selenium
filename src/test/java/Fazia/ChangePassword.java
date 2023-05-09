package Fazia;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.SetUp;

import java.time.Duration;

public class ChangePassword extends SetUp{

    Logger log = LogManager.getLogger(LogOutTest.class.getName());
    @Test
    public  void ChangePassword()  {


        String expectedTitle = "OrangeHRM";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);


        //enter username,enter password, and click on login button

        type("input[placeholder='Username']","Admin");
        log.info("Enter username , success");

        type("input[placeholder='Password']","admin123");
        log.info("Enter password , success");

        driver.findElement(By.cssSelector("button[type='submit']")).click();
        log.info("click on button Login Success");

        //check user is logged in
        String expectedHomePage = "Dashboard";
        String actualHomePage =  getElementText("//h6[normalize-space()='Dashboard']");
        Assert.assertEquals(expectedHomePage,actualHomePage);
        log.info("user logged in success");


        clickOn(".oxd-userdropdown-name");
        log.info("click on the Menu Success");

        clickOn("//a[normalize-space()='Change Password']");
        log.info("click on change password button Success");


        type("div[class='oxd-form-row'] div[class='oxd-grid-2 orangehrm-full-width-grid'] div[class='oxd-grid-item oxd-grid-item--gutters'] div[class='oxd-input-group oxd-input-field-bottom-space'] div input[type='password']", "admin123");
        log.info("Password entered in Current Password field");

        type("//div[@class='oxd-grid-item oxd-grid-item--gutters user-password-cell']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@type='password']","Admin2000*");
        log.info("Password entered in New Password field");

        type("div[class='oxd-form-row user-password-row'] div[class='oxd-grid-2 orangehrm-full-width-grid'] div[class='oxd-grid-item oxd-grid-item--gutters'] div[class='oxd-input-group oxd-input-field-bottom-space'] div input[type='password'","Admin2000*");
        log.info("Password entered in Confirm Password field ");

        clickOn("button[type='submit' i]");
        log.info("Password updated");


        //Log out
        clickOn(".oxd-userdropdown-name");
        log.info("click on the Menu Success");

        clickOn("//a[normalize-space()='Logout']");
        log.info("click on the logOut button Success");

        waitFor(5);

        String expectedLoginPage = "OrangeHRM";
        String actualLoginPage = driver.getTitle();
        Assert.assertEquals(expectedLoginPage,actualLoginPage);

        //Try to log in with the new password


        type("input[placeholder='Username']","Admin");
        log.info("Enter username , success");

        type("input[placeholder='Password']","Admin2000*");
        log.info("Enter password , success");

        clickOn("button[type='submit']");
        log.info("click on button Login Success");

        //check user is logged in
        String expectedError = "Invalid credentials";
        String actualError = getElementText("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']") ;
        Assert.assertEquals(expectedError,actualError);
        log.info("Password is not updated");

    }



}
