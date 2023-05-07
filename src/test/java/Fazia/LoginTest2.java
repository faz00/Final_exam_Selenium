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



    public class LoginTest2 extends SetUp {
        Logger log = LogManager.getLogger(Fazia.LoginTest2.class.getName());

       @Test
        public  void validCerd()  {


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
            String actualHomePage =  driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']")).getText();
            Assert.assertEquals(expectedHomePage,actualHomePage);
            log.info("user logged in success");

        }

    /*@Test

    public void invalidUserName(){
        String expectedTitle = "Swag Labs";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);


        type("#user-name","invalid_username");
        log.info("Enter username 2, success");

        type("#password","secret_sauce");
        log.info("Enter password 2, success");


        clickOn("#login-button");
        log.info("click on button Login Success");

        String expectedError = "Epic sadface: Username and password do not match any user in this service";
        String actualEror = getElementText("//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[3]/h3[1]") ;
        Assert.assertEquals(expectedError,actualEror);
        log.info("Validate error access");


    }

    @Test

    public void missingUsername(){
        String expectedTitle = "Swag Labs";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);


        //enter username,enter password, and click on login button
        type("#user-name","");


        type("#password","secret_saucee");
        log.info("Enter password 3, success");

        clickOn("#login-button");
        log.info("click on button Login Success");

        String expectedError = "Epic sadface: Password is required";
        String actualEror = getElementText("//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[3]/h3[1]");
        //Assert.assertEquals(expectedError,actualEror);
        log.info("Validate error access");

    }

    @Test

    public void missingPassword(){
        String expectedTitle = "Swag Labs";
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
        //enter  username, password, and click on login button
        type("#user-name","standard_user");
        log.info("enter username success");
        type("#password","");
        log.info("enter password success");
        clickOn("#login-button");
        log.info("click on login button Success");

        //validate the error message
        String expectedError = "Epic sadface: Password is required";
        String actualError = getElementText("//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[3]/h3[1]");
        Assert.assertEquals(expectedError, actualError);
        log.info("validate error success");

    }
*/
    }


