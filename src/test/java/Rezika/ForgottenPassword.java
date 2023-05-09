package Rezika;
import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import us.piit.SetUp;

public class ForgottenPassword  extends SetUp {


    Logger log = LogManager.getLogger(ForgottenPassword.class.getName());


    //verify if the user is able to reset the password
    @Test
    public void restUserPassword() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to the forgot password page
        clickOn("//a[normalize-space()='Forgotten Password']");
        log.info("Clicked on the 'Forgotten Password' link success");

        // Enter the email and submit
        type("#input-email","sunyellow@gmail.com" );
        clickOn("//input[@value='Continue']");
        log.info("Submitted the email to reset the password.");

        // Verify that the success message is displayed
        WebElement successMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-success')]"));
        String actualSuccessMessage = successMessage.getText();
        String expectedSuccessMessage = "An email with a confirmation link has been sent your email address.";
        assertEquals(actualSuccessMessage, expectedSuccessMessage);
    }

    //verify resetting password for non registred users
    @Test
    public void resetPassForNonRegistredUsers() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to the forgot password page
        clickOn("//a[normalize-space()='Forgotten Password']");
        log.info("Clicked on the 'Forgotten Password' link success");

        // Enter the non registered email and  click on submit
        type("#input-email","abc123@gmail.com" );
        clickOn("//input[@value='Continue']");
        log.info("Submitted the email to reset the password.");

        // Retrieve the confirmation message
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));
        String actualConfirmationMessage = confirmationMessage.getText();
        String expectedErrorMessage="unregistred Email";
        // Assert that the confirmation message matches the expected error message
        assertEquals(actualConfirmationMessage,expectedErrorMessage);

    }

    //verify the resetting password without providing an email
    @Test
    public void resetPasswordWithoutEmail() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to the forgot password page
        clickOn("//a[normalize-space()='Forgotten Password']");
        log.info("Clicked on the 'Forgotten Password' link success");
        clickOn("//input[@value='Continue']");

        //assert that the email message is displayed
        String expectedErrorMessage = "Warning: The E-Mail Address was not found in our records, please try again!";
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"));
        String actualErrorMessage = errorMessage.getText();
        assertEquals(expectedErrorMessage, actualErrorMessage);

    }
    //verify placehold text of email address is displayed
    @Test
    public void verifyThePlaceHoldText() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to the forgot password page
        clickOn("//a[normalize-space()='Forgotten Password']");
        log.info("Clicked on the 'Forgotten Password' link success");

        //verify the placehold text of EMail address
        WebElement emailInput = driver.findElement(By.xpath("//input[@id='input-email']"));
        String actualPlaceholder = emailInput.getAttribute("placeholder");
        String expectedPlaceholder = "E-Mail Address";
        assertEquals(actualPlaceholder, expectedPlaceholder);


    }
    //verify the back button of the reset password page

    @Test
    public void verifyBackButtonOnForgotPassword() {
        // Navigate to the Forgot Password page
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to the forgot password page
        clickOn("//a[normalize-space()='Forgotten Password']");
        log.info("Clicked on the 'Forgotten Password' link success");

        // Click on the Back button
        clickOn(".pull-left > a");


        // Verify that the user is taken back to the Login page
        String expectedTitle = "Account Login";
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle, expectedTitle);
        log.info("the user is taken back to the login page");
    }






}
