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
import us.piit.pages.tutorialsNinja.ForgottenPasswordHomePage;
import us.piit.pages.tutorialsNinja.ForgottenPasswordPage;

public class ForgottenPasswordTest extends CommonAPI {


    Logger log = LogManager.getLogger(ForgottenPasswordTest.class.getName());
    Properties prop = Utility.loadProperties();
    String validEmail = Utility.decode(prop.getProperty("tutorialsninja.validEmail"));
    String invalidEmail = Utility.decode(prop.getProperty("tutorialsninja.invalidEmail"));

    //verify if the user is able to reset the password
    @Test
    public void verifyUserNavigatesToTheForgotPasswordPage() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        ForgottenPasswordPage forgottenPasswordPage = new  ForgottenPasswordPage(getDriver());
        ForgottenPasswordHomePage forgottenPasswordHomePage = new  ForgottenPasswordHomePage(getDriver());
        //click on the 'forgot Password link'
        forgottenPasswordPage.navigateToForgotPasswordPage();
        forgottenPasswordHomePage.resetUserPassword(validEmail);

// Verify that the user landed on the 'Forgot Your Password?'home page
        String expectedHomPgeTitle="Forgot Your Password?";
        String actHomPgeTitle=forgottenPasswordHomePage.getForgPassHeaderTitle();
        assertEquals(expectedHomPgeTitle,actHomPgeTitle,"the home page header title is not the same");
    }
    //verify if the user is able to reset the password
    @Test
    public void restUserPassword() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        ForgottenPasswordPage forgottenPasswordPage = new  ForgottenPasswordPage(getDriver());
        ForgottenPasswordHomePage forgottenPasswordHomePage = new  ForgottenPasswordHomePage(getDriver());
        //click on the 'forgot Password link'
        forgottenPasswordPage.navigateToForgotPasswordPage();
        forgottenPasswordHomePage.resetUserPassword(validEmail);

// Verify that the success message is displayed
        assertTrue(forgottenPasswordHomePage.isResetPasswordSuccessMessageDisplayed());
    }

    //verify resetting password for non registred users
    @Test
    public void resetPassForNonRegistredUsers() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        ForgottenPasswordPage forgottenPasswordPage = new  ForgottenPasswordPage(getDriver());
        ForgottenPasswordHomePage forgottenPasswordHomePage = new  ForgottenPasswordHomePage(getDriver());

        // Navigate to the forgot password page
        forgottenPasswordPage.navigateToForgotPasswordPage();

        // Enter the non registered email and  click on submit
        forgottenPasswordHomePage.resetUserPassword(invalidEmail);

        // Verify an Error Mesaage gets displayed
        assertTrue(forgottenPasswordHomePage.isResetPasswordErrorMessageDisplayed());

    }

    //verify the resetting password without providing an email
    @Test
    public void resetPasswordWithoutEmail() {
        waitFor(30);
        ForgottenPasswordPage forgottenPasswordPage = new  ForgottenPasswordPage(getDriver());
        ForgottenPasswordHomePage forgottenPasswordHomePage = new  ForgottenPasswordHomePage(getDriver());

        // Navigate to the forgot password page
        forgottenPasswordPage.navigateToForgotPasswordPage();

//Click on the continue button without providing email
        forgottenPasswordHomePage.clickContinueButton();

        //assert an error message gets displayed
        assertTrue(forgottenPasswordHomePage.isResetPasswordErrorMessageDisplayed());
    }


    //verify placehold text of email address is displayed
    @Test
    public void verifyThePlaceHoldText() {
        waitFor(30);
        ForgottenPasswordPage forgottenPasswordPage = new ForgottenPasswordPage(getDriver());
        ForgottenPasswordHomePage forgottenPasswordHomePage = new ForgottenPasswordHomePage(getDriver());

        // Navigate to the forgot password page
        forgottenPasswordPage.navigateToForgotPasswordPage();

        //verify the placeholder text of Email address
        String expectedPlaceholder = "E-Mail Address";
        assertEquals(expectedPlaceholder,forgottenPasswordHomePage.getEmailInput());
    }


    //verify the back button of the reset password page

    @Test
    public void verifyBackButtonOnForgotPassword() {
        waitFor(30);
        ForgottenPasswordPage forgottenPasswordPage = new  ForgottenPasswordPage(getDriver());
        ForgottenPasswordHomePage forgottenPasswordHomePage = new ForgottenPasswordHomePage(getDriver());

        // Navigate to the forgot password page
        forgottenPasswordPage.navigateToForgotPasswordPage();

        // Click on the Back button
        forgottenPasswordHomePage.clickBackButton();

        // Verify that the user is taken back to the Login page
        String expectedURL = "https://tutorialsninja.com/demo/index.php?route=account/login";
        String actualURL = forgottenPasswordHomePage.getURL() ;
        assertEquals(expectedURL, actualURL);
    }






}