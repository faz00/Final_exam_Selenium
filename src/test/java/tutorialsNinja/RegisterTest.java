package tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import us.piit.utility.Utility;
import us.piit.base.CommonAPI;
import us.piit.pages.tutorialsNinja.RegisterHomePage;
import us.piit.pages.tutorialsNinja.RegisterPage;

import java.time.Duration;
import java.util.Properties;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RegisterTest extends CommonAPI {
    Logger log = LogManager.getLogger(RegisterTest.class.getName());
    Properties prop = Utility.loadProperties();
    String validFirstName = Utility.decode(prop.getProperty("tutorialsninja.validfirstName"));
    String validLastName = Utility.decode(prop.getProperty("tutorialsninja.validLastName"));
    String validEmail = Utility.decode(prop.getProperty("tutorialsninja.validEmail"));
    String validPhoneNumber = Utility.decode(prop.getProperty("tutorialsninja.ValidPhoneNumber"));
    String validPassword = Utility.decode(prop.getProperty("tutorialsninja.validPassword"));
    String invalidPassword = Utility.decode(prop.getProperty("tutorialsninja.invalidPassword"));
    String invalidPhoneNumber = Utility.decode(prop.getProperty("tutorialsninja.invalidPhoneNumber"));
    String validConPassword = Utility.decode(prop.getProperty("tutorialsninja.validConPassword"));
    String existingvalidEmail = Utility.decode(prop.getProperty("tutorialsNinja.existingvalidConPassword"));

    @Test
    public void fillupAllTheMandatoryFields() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));

        RegisterPage registerPage = new RegisterPage(getDriver());
        RegisterHomePage registerHomePage = new RegisterHomePage(getDriver());

        //click on the continue button
        registerPage.clickContinueButton();

        // Enter firstName, lastName, phoneNumber, password, email and click on continue button
        registerPage.enterFirstName(validFirstName);
        registerPage.enterLastName(validLastName);
        registerPage.enterEmail(validEmail);
        registerPage.enterPhoneNumber(validPhoneNumber);
        registerPage.enterPassword(validPassword);
        registerPage.enterConfirmPassword(validConPassword);
        registerPage.selectPrivacyPolicyCheckbox();
        registerPage.clickSubmitButton();

        // Verify that the user is landed on the register home page
        assertTrue(registerHomePage.isRegisterHomePageTitleDisplayed());
        log.info("the user is able to register when filling up all the mandatory fields");
    }


    @Test
    public void registerWithInvalidPhoneNumber() {
        RegisterPage registerPage = new RegisterPage(getDriver());
        RegisterHomePage registerHomePage = new RegisterHomePage(getDriver());

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));


        //click on the continue button
        registerPage.clickContinueButton();

        // Enter firstName, lastName, phoneNumber, password, email and click on continue button
        registerPage.enterFirstName(validFirstName);
        registerPage.enterLastName(validLastName);
        registerPage.enterEmail(validEmail);
        registerPage.enterPhoneNumber(invalidPhoneNumber);
        registerPage.enterPassword(validPassword);
        registerPage.enterConfirmPassword(validConPassword);
        registerPage.selectPrivacyPolicyCheckbox();
        registerPage.clickSubmitButton();

        // Verify if the user can register with invalid phone number
        /*String expectedErMessage = "invalid phone number";
        String actualErMessage = registerHomePage.getRegisterHomePageTitle();
        assertNotEquals(expectedErMessage, actualErMessage);*/
     //Takescreenshot
        takeScreenshot("tutorialsNinja","rgstrwithinvaphonum");
       //assert if the user navigates to the home page
        assertFalse(registerHomePage.isRegisterHomePageTitleDisplayed());

    }

    //verify different ways to navigate to the register page
    @Test
    public void VerifyDifferentWaysToNavigateToTheRegisterPage() {

        RegisterPage registerPage = new RegisterPage(getDriver());

        //assert that the user can navigate to the Register forum in different ways
        boolean isOnRegisterPage = registerPage.VerifyDifferentWaysToNavigateToTheRegisterPage(driver);
        assertTrue(isOnRegisterPage, "The user did not navigate to the register page.");
    }

    //verify if the password entered is visible to the source page
    @Test
    public void VerifyThePasswordNotVisibleToTheSourcePage() {
        RegisterPage registerPage = new RegisterPage(getDriver());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));

        //click on the continue button
        registerPage.clickContinueButton();

        //enter any given password in the password field
        registerPage.enterPassword(validPassword);

        // Assert if the password field is  visible in the source page

        assertTrue(registerPage.isPasswordFieldVisible(validPassword), "the password is visible");
        //takescreenshot
       takeScreenshot("tutorialsNinja","pwVisiblToSrcPge");

    }

    //verify register with an existing email address
    @Test
    public void verifyRegisterWithAnExistingEmailAddress() {

        RegisterPage registerPage = new RegisterPage(getDriver());
        RegisterHomePage registerHomePage = new RegisterHomePage(getDriver());

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));


        //click on the continue button
        registerPage.clickContinueButton();

        // Enter firstName, lastName, phoneNumber, password, email and click on continue button
        registerPage.enterFirstName(validFirstName);
        registerPage.enterLastName(validLastName);
        registerPage.enterEmail(existingvalidEmail);
        registerPage.enterPhoneNumber(invalidPhoneNumber);
        registerPage.enterPassword(validPassword);
        registerPage.enterConfirmPassword(validConPassword);
        registerPage.selectPrivacyPolicyCheckbox();
        registerPage.clickSubmitButton();

        //assert if an error message will be displayed
        assertTrue(registerPage.isAlertMessageDisplayed(),"the alert message is not displayed");
//takeScreenshot
        takeScreenshot("tutorialsNinja","rgstrWthExisEmail");



    }

}