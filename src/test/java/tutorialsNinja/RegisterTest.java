package tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    @DataProvider(name = "RegistrationData")
    public Object[][] provideRegistrationData() {
        return new Object[][] {
                {validFirstName, validLastName, validEmail, validPhoneNumber, validPassword, validConPassword},
                {invalidFirstName,invalidLastName,invalidEmail,invalidPhoneNumber,invalidPassword,invalidConPaswrd},
                {validFirstName,invalidLastName,validEmail,validPhoneNumber,validPassword,validConPassword},
        };
    }
    @Test(priority = 1, groups = "registration", dataProvider = "RegistrationData")
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

        // Verify that the user is landed on the register home page
        // assertTrue(registerHomePage.isRegisterHomePageTitleDisplayed(),"user can not register with invalid credentials");

        // Verify registration success or failure based on the provided data
        boolean isExpectedSuccess = !firstName.startsWith("invalid");
        assertEquals(isExpectedSuccess, registerHomePage.isRegisterHomePageTitleDisplayed(),
                "Registration success mismatch for credentials: " +
                        "firstName=" + firstName + ", lastName=" + lastName + ", email=" + email +
                        ", phoneNumber=" + phoneNumber + ", password=" + password + ", confirmPassword=" + confirmPassword);
    }

    @DataProvider(name = "invalidPhoneNumberData")
    public Object[][] provideInvalidPhoneNumberData() {
        return new Object[][] {
                {validFirstName, validLastName, validEmail, invalidPhoneNumber, validPassword, validConPassword},

        };
    }

    @Test(priority = 2, groups = "registration", dataProvider = "invalidPhoneNumberData")
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
    @Test(priority = 5, groups = "navigation")

    public void VerifyDifferentWaysToNavigateToTheRegisterPage() {

        RegisterPage registerPage = new RegisterPage(getDriver());

        //assert that the user can navigate to the Register forum in different ways
        boolean isOnRegisterPage = registerPage.VerifyDifferentWaysToNavigateToTheRegisterPage(driver);
        assertTrue(isOnRegisterPage, "The user did not navigate to the register page.");
    }

    //verify if the password entered is visible to the source page
    @Test(priority = 4, groups = "passwordSecurity")

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
    }
    @DataProvider(name = "existingEmailAddressData")
    public Object[][] provideExistingEmailAddressData() {
        return new Object[][] {
                {validFirstName, validLastName, existingvalidEmail, validPhoneNumber, validPassword, validConPassword},

        };
    }
    @Test(priority = 3, groups = "registration", dataProvider = "existingEmailAddressData")
    public void verifyRegisterWithAnExistingEmailAddress(String firstName, String lastName, String email, String phoneNumber,
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

        // Assert if an error message will be displayed
        assertTrue(registerPage.isAlertMessageDisplayed(), "The alert message is not displayed for existing email address");
    }
}