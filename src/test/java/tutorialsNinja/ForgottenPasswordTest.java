package tutorialsNinja;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import us.piit.Utility.Utility;
import us.piit.base.CommonAPI;
import us.piit.pages.tutorialsNinja.ForgottenPasswordPage;

public class ForgottenPasswordTest extends CommonAPI {


    Logger log = LogManager.getLogger(ForgottenPasswordTest.class.getName());
    Properties prop = Utility.loadProperties();
    String validEmail = Utility.decode(prop.getProperty("tutorialsninja.validEmail"));
    String invalidEmail = Utility.decode(prop.getProperty("tutorialsninja.invalidEmail"));

    //verify if the user is able to reset the password
    @Test
    public void restUserPassword() {
        waitFor(30);
        ForgottenPasswordPage forgottenPasswordPage = new  ForgottenPasswordPage(getDriver());
        //click on the 'forgot Password link'
        forgottenPasswordPage.navigateToForgotPasswordPage();
        forgottenPasswordPage.resetUserPassword(validEmail);

// Verify that the success message is displayed
        assertTrue(forgottenPasswordPage.isResetPasswordSuccessMessageDisplayed());
    }

    //verify resetting password for non registred users
    @Test
    public void resetPassForNonRegistredUsers() {
        waitFor(30);
        ForgottenPasswordPage forgottenPasswordPage = new  ForgottenPasswordPage(getDriver());

        // Navigate to the forgot password page
        forgottenPasswordPage.navigateToForgotPasswordPage();

        // Enter the non registered email and  click on submit
        forgottenPasswordPage.resetUserPassword(invalidEmail);

        // Verify an Error Mesaage gets displayed
        assertTrue(forgottenPasswordPage.isResetPasswordErrorMessageDisplayed());

    }

    //verify the resetting password without providing an email
    @Test
    public void resetPasswordWithoutEmail() {
        waitFor(30);
        ForgottenPasswordPage forgottenPasswordPage = new  ForgottenPasswordPage(getDriver());

        // Navigate to the forgot password page
        forgottenPasswordPage.navigateToForgotPasswordPage();

//Click on the continue button without providing email
        forgottenPasswordPage.clickContinueButton();

        //assert an error message gets displayed
        assertTrue(forgottenPasswordPage.isResetPasswordErrorMessageDisplayed());
    }


    //verify placehold text of email address is displayed
    @Test
    public void verifyThePlaceHoldText() {
        waitFor(30);
        ForgottenPasswordPage forgottenPasswordPage = new  ForgottenPasswordPage(getDriver());

        // Navigate to the forgot password page
        forgottenPasswordPage.navigateToForgotPasswordPage();

        //verify the placeholder text of Email address
        String actualPlaceholder = forgottenPasswordPage.getEmailInput().getAttribute("placeholder");
        String expectedPlaceholder = "E-Mail Address";
        assertEquals(actualPlaceholder, expectedPlaceholder);
    }


    //verify the back button of the reset password page

    @Test
    public void verifyBackButtonOnForgotPassword() {
        waitFor(30);
        ForgottenPasswordPage forgottenPasswordPage = new  ForgottenPasswordPage(getDriver());

        // Navigate to the forgot password page
        forgottenPasswordPage.navigateToForgotPasswordPage();

        // Click on the Back button
        forgottenPasswordPage.clickBackButton();

        // Verify that the user is taken back to the Login page
        String expectedURL = "https://tutorialsninja.com/demo/index.php?route=account/login";
        String actualURL = forgottenPasswordPage.getURL() ;
        assertEquals(expectedURL, actualURL);
    }






}