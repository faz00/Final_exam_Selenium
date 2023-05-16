package us.piit.pages.tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

import static org.testng.Assert.assertEquals;

public class RegisterPage  extends CommonAPI {

    Logger log = LogManager.getLogger(RegisterPage.class.getName());

    public RegisterPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[text()='Continue']")
    WebElement continueButton;

    @FindBy(css = "#input-firstname")
    WebElement firstNameField;

    @FindBy(css = "#input-lastname")
    WebElement lastNameField;

    @FindBy(css = "#input-email")
    WebElement emailField;

    @FindBy(xpath = "//input[@name='telephone']")
    WebElement phoneNumberField;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement passwordField;

    @FindBy(xpath = "//input[@name='confirm']")
    WebElement confirmPasswordField;

    @FindBy(xpath = "//input[@type='checkbox']")
    WebElement privacyPolicyCheckbox;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement submitButton;

    @FindBy(xpath = "//*[@class='fa fa-user']")
    WebElement myAccountButton;

    @FindBy(linkText = "Login")
    WebElement loginLink;
    @FindBy(linkText = "Register")
    WebElement registerLink;
    @FindBy(xpath = "//*[@id=\"column-right\"]/div/a[2]")
    WebElement rightColRegisterLink;

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    WebElement alertMessage;
    public void clickContinueButton() {
        clickOn(continueButton);
        log.info("the continue button is clicked successfully");
    }



    public void accountButton() {
        clickOn(myAccountButton);
        log.info("the user clicks on My Account button successfully");
    }

    public void enterFirstName(String firstName) {
        type(firstNameField, firstName);
        log.info("the first name entered successfully");
    }

    public void loginLinkClick() {
        clickOn(loginLink);
        log.info("the user clicks on the login link on My Account in success");
    }

    public void rightColRegisterLink() {
        clickOn(rightColRegisterLink);
        log.info("the user clicks on the register from the right column successfully");
    }

    public void enterLastName(String lastName) {
        type(lastNameField, lastName);
        log.info("the last name entered successfully");
    }

    public void registerLink() {
        clickOn(registerLink);
        log.info("the user clicks on the register link");
    }

    public void enterEmail(String email) {
        type(emailField, email);
        log.info("the email entered successfully");
    }

    public void enterPhoneNumber(String phoneNumber) {
        type(phoneNumberField, phoneNumber);
        log.info("the phone number entered successfully");
    }

    public void enterPassword(String password) {
        type(passwordField, password);
        log.info("the password entered successfully");
    }

    public void enterConfirmPassword(String password) {
        type(confirmPasswordField, password);
        log.info("the confirmation password entered successfully");
    }

    public void selectPrivacyPolicyCheckbox() {
        clickOn(privacyPolicyCheckbox);
        log.info("the privacy policy checkbox is selected successfully");
    }

    public void clickSubmitButton() {
        clickOn(submitButton);
        log.info("the submit button is clicked successfully");
    }

    public boolean isPasswordFieldVisible(String password) {

        String passwordFieldType = passwordField.getAttribute("value");
        return !passwordFieldType.equals(password);

    }


   public boolean VerifyDifferentWaysToNavigateToTheRegisterPage( WebDriver driver) {

        // Method 1: Click the "My Account" drop-down menu and click "Register" from the options
        clickOn(myAccountButton);
        log.info("\' My Account\' button clicked successfully ");

        clickOn(registerLink);
        log.info("the \'Register\' link  clicked successfully ");

        // Check if the user is on the register page
        if (!driver.getTitle().contains("Register Account")) {
            return false;
        }
        // Navigate back to the homepage
        driver.navigate().back();
        log.info("the user navigates back to the home page");

        // Method 2: Click the "My Account" drop-down menu and click "Login" from the options
        clickOn(myAccountButton);
        log.info("\' My Account\' button clicked successfully ");

        clickOn(loginLink);
        log.info("the \'Login\' link  clicked successfully ");

        // Click the "Continue" button inside the "New Customer" box
        clickOn(continueButton);
        log.info("the \'Continue\' button  is clicked ");

        // Check if the user is on the register page
        if (!driver.getTitle().contains("Register Account")) {
            return false;
        }

        // Navigate back to the homepage
        driver.navigate().back();

        // Method 3: Click the "My Account" drop-down menu and click "Register" from the options in the right columns side

        // Click the "Register" button in the right column options
        clickOn(rightColRegisterLink);
        log.info("the \'Register\' button  is clicked ");

        // Check if the user is on the register page
        if (!driver.getTitle().contains("Register Account")) {
            return false;
        }
     log.info("all the three methods navigates to the Register page forum");
        return true;
    }


public boolean isAlertMessageDisplayed(){
        log.info("the alert message is displayed when registering with an existing email address");
        return alertMessage.isDisplayed();
}


}










