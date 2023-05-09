package com.ninjaTutorials;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.SetUp;

public class RegisterTest  extends SetUp {

    Logger log = LogManager.getLogger(RegisterTest.class.getName());
//Register by filling up all the mandatory fields of the registration form
    @Test
    public void fillupAllTheMandatoryFields() {

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        //click on the continue button
        clickOn("//*[text()='Continue']");
        log.info("the continue button is clicked");

        // Fill up all the MandatoryFields of the registration form
        type("#input-firstname","sunflower");
        log.info("the user enters firstname successfully ");

        type("#input-lastname", "yellow");
        log.info("the user enters lastname in success");

        type("#input-email","sunyellow"+generateTimeStamp()+"@gmail.com");
        log.info("email entered success");

        type("//input[@name='telephone']","1234567890");
        log.info("phoneNumber entered success");

        type("//input[@id='input-password']","sun123");
        log.info("password entered success");

        type("//input[@name='confirm']","sun123");
        log.info("password confirmed success");

        WebElement privacyPolicyCheckbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
        Assert.assertFalse(privacyPolicyCheckbox.isSelected(), "Privacy Policy Checkbox is not selected");
        log.info("the checkbox is found unselected");
        privacyPolicyCheckbox.click();
        log.info("the user selects the checkbox");

        clickOn("//input[@type='submit']");
        log.info("the user clicks on the continue button");
        log.info("the continue button has clicked");

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));

        //verify that the user landed in the home success page
        WebElement headingTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/h1"));
        assertTrue(headingTitle.isDisplayed());
        log.info("the user has successfully registered and logged in to the home page");
    }

    //verify registration using different passwords
    @Test
    public void registerWithDifferentPassword() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //click on the continue button
        clickOn("//*[text()='Continue']");

        type("#input-firstname","sunflower");
        log.info("firstName entered success");

        type("#input-lastname", "yellow");
        log.info("lastName entered success");

        type("#input-email","sunyellow@gmail.com");
        log.info("email entered success");

        type("//input[@name='telephone']","1234567890");
        log.info("phoneNumber entered success");

        type("//input[@id='input-password']","sun123");
        log.info("password entered success");

        type("//input[@name='confirm']","sun12");
        log.info("password confirmed success");

        clickOn("//input[@type='checkbox']");
        log.info("the checkBox is selected");

        clickOn("//input[@type='submit']");
        log.info("the continue button has clicked");

        // Assert that the error message about the passwords not matching is displayed
        WebElement divAlert = driver.findElement(By.xpath("//div[@class='text-danger']"));
        String expectedErrorMessage = "Password confirmation does not match password!";
        String actualErrorMessage = divAlert.getText().trim();
        assertEquals(expectedErrorMessage, actualErrorMessage);
        log.info("the user can not register with different passwords");
    }

    //Verify if the password follows complexity standards
    @Test
    public void verifyIfThePasswordFollowsComplexityStandards() {

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //click on the continue button
        clickOn("//*[text()='Continue']");

        type("#input-firstname","sunflower");
        log.info("firstName entered success");

        type("#input-lastname", "yellow");
        log.info("lastName entered success");

        type("#input-email","sunyellow"+generateTimeStamp()+"@gmail.com");
        log.info("email entered success");

        type("//input[@name='telephone']","1234567890");
        log.info("phoneNumber entered success");

        type("//input[@id='input-password']","@45jhg12355498764hgdggdgs");
        log.info("password entered success");

        type("//input[@name='confirm']","@45jhg12355498764hgdggdgs");
        log.info("password confirmed success");

        clickOn("//input[@type='checkbox']");
        log.info("the checkBox is selected");

        //type("//input[@type='submit']","");
        clickOn("//input[@type='submit']");
        log.info("the continue button has clicked");


        // Assert that the error message about invalid password syntax is displayed
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"account-register\"]/div[1]"));
        assertTrue(errorMessage.isDisplayed());
        log.info("The password entered doesnt follow password complexity");

        // Assert that the user cannot register with a non strong password
        WebElement successMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/h1"));
        assertEquals("Your Account Has Been Created!", successMessage.getText());
        log.info("The user can register with a non strong password");
        log.info("the password doesn't follow password complexity standards");


    }

    //register with invalid phone number //fail (expect error mesg but the account created)
    @Test
    public void registerWithInvalidPhoneNumber() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //click on the continue button
        clickOn("//*[text()='Continue']");

        // Fill up all the MondatoryFields of the registration form
        type("#input-firstname","sunflower");
        log.info("firstName entered success");

        type("#input-lastname", "yellow");
        log.info("lastName entered success");

        type("#input-email","sunyellow"+generateTimeStamp()+"@gmail.com");
        log.info("email entered success");

        type("//input[@name='telephone']","abcd");
        log.info("phoneNumber entered success");

        type("//input[@id='input-password']","sun123");
        log.info("password entered success");

        type("//input[@name='confirm']","sun123");
        log.info("password confirmed success");

        clickOn("//input[@type='checkbox']");
        log.info("the checkBox is selected");

        clickOn("//input[@type='submit']");
        log.info("the continue button has clicked");

        // Assert that the error message about invalid phone number is displayed
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"account-register\"]/div[1]"));
        assertTrue(errorMessage.isDisplayed());
        log.info("The error message is displayed");

        // Assert that the user cannot register with an invalid phone number but the result was home page
        WebElement successMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/h1"));
        assertEquals("Your Account Has Been Created!", successMessage.getText());
        log.info("The user can register with an invalid phone number");


    }


    //register with an invalid email address format
    @Test

    public void verifyRegisterWithInvalidEmailAddressFormat() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //click on the continue button
        clickOn("//*[text()='Continue']");

        // Fill up all the MondatoryFields of the registration form
        type("#input-firstname","sunflower");
        log.info("firstName entered success");

        type("#input-lastname", "yellow");
        log.info("lastName entered success");

        type("#input-email","1245"+generateTimeStamp()+".com");
        log.info("email entered success");

        type("//input[@name='telephone']","1234567890");
        log.info("phoneNumber entered success");

        type("//input[@id='input-password']","sun123");
        log.info("password entered success");

        type("//input[@name='confirm']","sun123");
        log.info("password confirmed success");

        clickOn("//input[@type='checkbox']");
        log.info("the checkBox is selected");

        clickOn("//input[@type='submit']");
        log.info("the continue button has clicked");

        //verify if an error message will be displayed
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"account-register\"]/div[1]"));
        assertTrue(errorMessage.isDisplayed());
        log.info("The error message is displayed");
        log.info("the user can not register with invalid email address format");

    }

//verify the different ways to navigate to the Register page
    @Test
    public void verifyDifferentWaysNavigateToRegisterPage(){
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

// Method 1: Click the "My Account" drop-down menu and click "Register" from the options
      clickOn( "//*[@class='fa fa-user']");
       log.info("My Account button is clicked success");

        linkclickOn("Register");
        log.info("the Register button is clicked ");

        // Validate that the page title contains the expected text "Register - Ninja Tutorials"
        String expectedTitle = "Register Account";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "The page title is incorrect.");
      log.info("the first method to navigate to the register page");
        log.info("the title is the same: Register Account");

        // Navigate back to the homepage
        driver.navigate().back();

        // Method 2: Click the "My Account" drop-down menu and click "Login" from the options
        clickOn("//*[@class='fa fa-user']");
        log.info("My Account button is clicked success");

        linkclickOn("Login");
        log.info("the Login button is clicked ");


        // Click the "Continue" button inside the "New Customer" box
        clickOn("//*[text()='Continue']");
        log.info("the continue button is clicked ");

        // Validate that the page title contains the expected text "Register - Ninja Tutorials"
        expectedTitle = "Register Account";
        actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "The page title is incorrect.");
        log.info("the second way to navigate to the register page");
        log.info("the title of the page is Register Account");

        // Navigate back to the homepage
        driver.navigate().back();

        // Method 3: Click the "My Account" drop-down menu and click "Login" from the options
        clickOn("//*[@class='fa fa-user']");
        log.info("My Account button is clicked success");


        linkclickOn("Login");
        log.info("the Login button is clicked ");


        // Click the "Register" button in the right column options
        clickOn("//*[@id=\"column-right\"]/div/a[2]");
        log.info("the Register button is clicked ");

        // Validate that the page title contains the expected text "Register - Ninja Tutorials"
        expectedTitle = "Register Account";
        actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "The page title is incorrect.");
log.info("the third method to navigate to the register page");



    }













}













