package tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import us.piit.utility.Utility;
import us.piit.base.CommonAPI;
import us.piit.pages.tutorialsNinja.RegisterHomePage;
import us.piit.pages.tutorialsNinja.RegisterPage;

import java.time.Duration;
import java.util.Properties;

import static org.testng.Assert.*;

public class RegisterTest extends CommonAPI {

    Logger log = LogManager.getLogger(RegisterTest.class.getName());
    Properties prop = Utility.loadProperties();
    String validFirstName = Utility.decode(prop.getProperty("tutorialsninja.validfirstName"));
    String validLastName = Utility.decode(prop.getProperty("tutorialsninja.validLastName"));
    String validEmail = Utility.decode(prop.getProperty("tutorialsninja.validEmail"));
    String validPhoneNumber = Utility.decode(prop.getProperty("tutorialsninja.ValidPhoneNumber"));
    String validPassword = Utility.decode(prop.getProperty("tutorialsninja.validPassword"));
    String validConPassword = Utility.decode(prop.getProperty("tutorialsninja.validConPassword"));
    String invalidPassword = Utility.decode(prop.getProperty("tutorialsninja.invalidPassword"));
    String invalidPhoneNumber = Utility.decode(prop.getProperty("tutorialsninja.invalidPhoneNumber"));
    String existingvalidEmail = Utility.decode(prop.getProperty("tutorialsNinja.existingValidEmail"));
    String invalidFirstName = Utility.decode(prop.getProperty("tutorialsNinja.invalidFirstName"));
    String invalidLastName = Utility.decode(prop.getProperty("tutorialsNinja.invalidLastName"));
    String invalidConPaswrd = Utility.decode(prop.getProperty("tutorialsNinja.invalidConPaswrd"));
    String invalidEmail = Utility.decode(prop.getProperty("tutorialsninja.invalidEmail"));

     @Test(priority=8,groups="nvgteTRgstrPageTest")
    public void vrfyUsrNvgtRgstrHmPge() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));

        RegisterPage registerPage = new RegisterPage(getDriver());
        RegisterHomePage registerHomePage = new RegisterHomePage(getDriver());

        //click on the continue button
        registerPage.clickContinueButton();

        //Assert the Register home page displayed
        String expectedTitle = "Register Account";
        String actualTitle = registerHomePage.getRgstrHmPgTtl(getDriver());
        assertEquals(expectedTitle, actualTitle);


    }

   @DataProvider(name = "RegistrationData")
    public Object[][] provideRegistrationData() {
        return new Object[][]{
                {validFirstName, validLastName, validEmail, validPhoneNumber, validPassword, validConPassword},
                {invalidFirstName, invalidLastName, invalidEmail, invalidPhoneNumber, invalidPassword, invalidConPaswrd},

        };
    }

  @Test(priority = 1, groups = "registerWthCredTest", dataProvider = "RegistrationData")
    public void registerWithCredentials(String firstName, String lastName, String email, String phoneNumber,
                                        String password, String confirmPassword) {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));

        RegisterPage registerPage = new RegisterPage(getDriver());
        RegisterHomePage registerHomePage = new RegisterHomePage(getDriver());

        //click on the continue button
        registerPage.clickContinueButton();

        // Enter firstName, lastName, phoneNumber, password, email and click on continue button
        registerPage.enterFirstName(firstName);
        registerPage.enterLastName(lastName);
        registerPage.enterEmail(email);
        registerPage.enterPhoneNumber(phoneNumber);
        registerPage.enterPassword(password);
        registerPage.enterConfirmPassword(confirmPassword);
        registerPage.selectPrivacyPolicyCheckbox();
        registerPage.clickSubmitButton();

// Verify registration success or failure based on the provided data
        assertTrue(registerHomePage.isRegisterHomePageTitleDisplayed(),
                "no register home page title is displayed when registering with invalid credentials");

        registerPage.invCredMsg(firstName, lastName, email, phoneNumber, password, confirmPassword);
    }


  @DataProvider(name = "invalidPhoneNumberData")
    public Object[][] provideInvalidPhoneNumberData() {
        return new Object[][]{
                {validFirstName, validLastName, validEmail, invalidPhoneNumber, validPassword, validConPassword},

        };
    }

   @Test(priority = 2, groups = "registerWithInvPhoNmbrTest", dataProvider = "invalidPhoneNumberData")
    public void registerWithInvalidPhoneNumber(String firstName, String lastName, String email, String phoneNumber,
                                               String password, String confirmPassword) {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));

        RegisterPage registerPage = new RegisterPage(getDriver());

        RegisterHomePage registerHomePage = new RegisterHomePage(getDriver());

        //click on the continue button
        registerPage.clickContinueButton();

        // Enter firstName, lastName, phoneNumber, password, email and click on continue button
        registerPage.enterFirstName(firstName);
        registerPage.enterLastName(lastName);
        registerPage.enterEmail(email);
        registerPage.enterPhoneNumber(phoneNumber);
        registerPage.enterPassword(password);
        registerPage.enterConfirmPassword(confirmPassword);
        registerPage.selectPrivacyPolicyCheckbox();
        registerPage.clickSubmitButton();

        // Verify if the user can register with invalid phone number
        assertFalse(registerHomePage.isRegisterHomePageTitleDisplayed(), "The user is able to register with an invalid phone number");
    }

    //verify different ways to navigate to the register page
  @Test(priority = 5, groups = "differentWysTNavgToRgstrTest")

    public void VerifyDifferentWaysToNavigateToTheRegisterPage() {

        RegisterPage registerPage = new RegisterPage(getDriver());

        //assert that the user can navigate to the Register forum in different ways
        boolean isOnRegisterPage = registerPage.VerifyDifferentWaysToNavigateToTheRegisterPage(driver);

        assertTrue(isOnRegisterPage, "The user did not navigate to the register page.");
    }


    //verify if the password entered is visible to the source page
    @Test(priority = 4, groups = "passwordVisibilityTest")
    public void VerifyThePasswordNotVisibleToTheSourcePage() {

        RegisterPage registerPage = new RegisterPage(getDriver());

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));

        //click on the continue button
        registerPage.clickContinueButton();

        //enter any given password in the password field
        registerPage.enterPassword(validPassword);
        registerPage.clickSubmitButton();
        registerPage.htmlScrenShot(getDriver());

        // Assert if the password field is  visible in the source page
        assertTrue(registerPage.isPasswordFieldVisible(validPassword), "the password is visible");


    }

    @DataProvider(name = "existingEmailAddressData")
    public Object[][] provideExistingEmailAddressData() {
        return new Object[][]{
                {validFirstName, validLastName, existingvalidEmail, validPhoneNumber, validPassword, validConPassword},

        };
    }

    @Test(priority = 3, groups = "rgstrWthExstngEmailAddrssTest", dataProvider = "existingEmailAddressData")
    public void verifyRegisterWithAnExistingEmailAddress(String firstName, String lastName, String email, String phoneNumber,
                                                         String password, String confirmPassword) {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));

        RegisterPage registerPage = new RegisterPage(getDriver());

        //click on the continue button
        registerPage.clickContinueButton();

        // Enter firstName, lastName, phoneNumber, password, email and click on continue button
        registerPage.enterFirstName(firstName);
        registerPage.enterLastName(lastName);
        registerPage.enterEmail(email);
        registerPage.enterPhoneNumber(phoneNumber);
        registerPage.enterPassword(password);
        registerPage.enterConfirmPassword(confirmPassword);
        registerPage.selectPrivacyPolicyCheckbox();
        registerPage.clickSubmitButton();

        // Assert if an error message will be displayed
        assertTrue(registerPage.isAlertMessageDisplayed(), "The alert message is not displayed for existing email address");
    }


    @DataProvider(name = "privacyCheckBox")
    public Object[][] selectprivacyPolicyData() {
        return new Object[][]{
                {"yes"},
                {"No"},
        };
    }

     @Test(priority = 6, groups = "prvcyPlcyChckBxTest", dataProvider = "privacyCheckBox")
    public void checkPrivacyPolicyCheckBox(String privacyOption) {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));

        RegisterPage registerPage = new RegisterPage(getDriver());

        RegisterHomePage registerHomePage = new RegisterHomePage(getDriver());


        // Click on the continue button
        registerPage.clickContinueButton();

        // Enter firstName, lastName, phoneNumber, password, email, and click on the continue button
        registerPage.enterFirstName(validFirstName);
        registerPage.enterLastName(validLastName);
        registerPage.enterEmail(validEmail);
        registerPage.enterPhoneNumber(validPhoneNumber);
        registerPage.enterPassword(validPassword);
        registerPage.enterConfirmPassword(validConPassword);

        // Select privacy policy checkbox based on the provided option
        if (privacyOption.equalsIgnoreCase("yes")) {
            registerPage.selectPrivacyPolicyCheckbox();
        }

        registerPage.clickSubmitButton();

        // Assert if the user can register successfully based on the privacy policy checkbox selection

        if (privacyOption.equalsIgnoreCase("yes")) {
            assertTrue(registerHomePage.isRegisterHomePageTitleDisplayed(), "The user should be able to register when the privacy policy checkbox is selected");
        } else {
            assertFalse(registerHomePage.isRegisterHomePageTitleDisplayed(), "The user should not be able to register when the privacy policy checkbox is not selected");
        }
    }

    @Test(priority=7,groups="invalidCnfrmtnPswrdTest")
    public void rgstrWthDffrntCnfrmtnPswrd() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));

        RegisterPage registerPage = new RegisterPage(getDriver());

        RegisterHomePage registerHomePage = new RegisterHomePage(getDriver());

        // Click on the continue button
        registerPage.clickContinueButton();

        // Enter password  and confirm password and click on the continue button

        registerPage.enterPassword(validPassword);
        registerPage.enterConfirmPassword(invalidConPaswrd);
        registerPage.clickSubmitButton();

        //verify if an error message got displayed
        String pswrdErrMsg="Password confirmation does not match password!";
        Assert.assertEquals(registerPage.getPswrdErrMsg(),pswrdErrMsg,"no error message is displayed");

    }

}














