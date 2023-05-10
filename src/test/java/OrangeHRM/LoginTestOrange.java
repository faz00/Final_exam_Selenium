package OrangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.SetUp;


public class LoginTestOrange extends SetUp {
    Logger log = LogManager.getLogger(OrangeHRM.LoginTestOrange.class.getName());

    @Test
    public  void validCred()  {


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

    }

    @Test

    public void InavlidCeredentials(){

        String expectedTitle = "OrangeHRM";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);


        //enter username,enter password, and click on login button

        type("input[placeholder='Username']", "OrangeHRM");
        log.info("Enter username , success");

        type("input[placeholder='Password']","Wrong");
        log.info("Enter password , success");

        driver.findElement(By.cssSelector("button[type='submit']")).click();
        log.info("click on button Login Success");


        String expectedError = "Invalid credentials";
        String actualError = getElementText("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']") ;
        Assert.assertEquals(expectedError,actualError);
        log.info("Validate error access");


    }

    @Test
    public void noCred(){

        //body > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > form:nth-child(2) > div:nth-child(2) > div:nth-child(1) > span:nth-child(3

        String expectedTitle = "OrangeHRM";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);


        //enter username,enter password, and click on login button

        type("input[placeholder='Username']","");
        log.info("Enter username , success");

        type("input[placeholder='Password']","");
        log.info("Enter password , success");

        clickOn("button[type='submit']");
        log.info("click on button Login Success");


        String expectedError = "Required";
        String actualError = getElementText("body > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > form:nth-child(2) > div:nth-child(2) > div:nth-child(1) > span:nth-child(3") ;
        Assert.assertEquals(expectedError,actualError);
        log.info("Validate error access");

    }

}



