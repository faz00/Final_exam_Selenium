package com.ninjaTutorials;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import us.piit.SetUp;


public class LoginTest  extends SetUp {

    Logger log = LogManager.getLogger(LoginTest.class.getName());

    @Test
    public void testLoginWithValidInputs() {
        //click on the login
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Fill in the login form
        type("#input-email","sunflower"+ generateTimeStamp()+"@gmail.com");
        log.info("the user-email entered success");

        type("#input-password","sun123");
        log.info("the user-password entered success");


        // Click the login button
        clickOn("input[value='Login']");

        // Check that the user is redirected to the account dashboard page
        String expectedUrl = "https://tutorialsninja.com/demo/index.php?route=account/login";
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }
    @Test
    public void testLoginWithInValidEmail() {
        //click on the login
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Fill in the login form
        type("#input-email","su"+ generateTimeStamp()+"@g");
        log.info("the user-email entered success");

        type("#input-password","sun123");
        log.info("the user-password entered success");


        // Click the login button
        clickOn("input[value='Login']");

        // Check that the user has got an error message
        String errorMassage=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String expectedErmsg="Warning: No match for E-Mail Address and/or Password.";
        assertEquals(errorMassage,expectedErmsg );
    }
    @Test

    public void testLoginWithInValidPassword() {
        //click on the login
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Fill in the login form
        type("#input-email","sunflower"+ generateTimeStamp()+"@gmail.com");
        log.info("the user-email entered success");

        type("#input-password","abcd");
        log.info("the user-password entered success");


        // Click the login button
        clickOn("input[value='Login']");

        // Check that the user has got an error message
        String errorMassage=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String expectedErmsg="Warning: No match for E-Mail Address and/or Password.";
        assertEquals(errorMassage,expectedErmsg );
    }
    @Test

    public void testLoginWithNoInputs() {
        //click on the login
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Click the login button
        clickOn("input[value='Login']");

        // Check that the user has got an error message
        String errorMassage=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String expectedErmsg="Warning: No match for E-Mail Address and/or Password.";
        assertEquals(errorMassage,expectedErmsg );

    }
    @Test

    public void testLoginWithInvalidInputs() {
        //click on the login
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Fill in the login form
        type("#input-email","wbjhhbdh526"+ generateTimeStamp()+"@dnnjd.com");
        log.info("the user-email entered success");

        type("#input-password","abcd789");
        log.info("the user-password entered success");


        // Click the login button
        clickOn("input[value='Login']");

        // Check that the user has got an error message
        String errorMassage=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String expectedErmsg="Warning: No match for E-Mail Address and/or Password.";
        assertEquals(errorMassage,expectedErmsg );

    }

    //verify that the user is not logged out from the login page when browsing back
    @Test

    public void VerifyThatTheUserNotLoggedoutByBrowsingBack() {

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Fill in the login form
        type("#input-email","sunflower"+ generateTimeStamp()+"@gmail.com");
        log.info("the user-email entered success");

        type("#input-password","sun123");
        log.info("the user-password entered success");


        // Click the login button
        clickOn("input[value='Login']");
        // Verify that the user is  on the dashboard page
        assertTrue(driver.getCurrentUrl().contains("index.php?route=account/account"));

        // Click the browser back button
        driver.navigate().back();

        // Wait for the previous page to load
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        // Verify that the user is not on the login page
        assertFalse(driver.getCurrentUrl().contains("index.php?route=account/login"));

        // Verify that you are still logged in
        driver.navigate().forward();
        assertTrue(driver.findElement(By.cssSelector("a[title='My Account']")).isDisplayed());

        // Close the browser
        driver.quit();
    }



    //verify the number of unsuccessful login attempts
    @Test

    public void numberOfUnsuccessfulLoginAttempts() {

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        int maxAttempts=3;

        for (int i = 1; i <= maxAttempts; i++) {

            // enter incorrect login credentials

            type("#input-email","s"+ generateTimeStamp()+"gmail.com");
            log.info("the user-email entered success");

            type("#input-password","sK");
            log.info("the user-password entered success");

            // Click the login button
            clickOn("input[value='Login']");


            // assert the error message is displayed
            WebElement errorMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"));
            assertEquals(errorMessage.getText(), "Warning: No match for E-Mail Address and/or Password.");

            // navigate back to the login page
            driver.navigate().back();

        }
    }


    //verify the password is not visible to the source page
    @Test

    public void verifyPasswordVisibilityToThePageSource() {

        // Find the password input field and get its type attribute
        WebElement passwordField = driver.findElement(By.id("input-password"));
        String passwordFieldType = passwordField.getAttribute("type");

        // Assert that the password field type is "password"
        assertEquals("password", passwordFieldType);

        // Retrieve the page source and check if the password field is visible
        String pageSource = driver.getPageSource();
        assertFalse(pageSource.contains("input-password"));



    }




}








