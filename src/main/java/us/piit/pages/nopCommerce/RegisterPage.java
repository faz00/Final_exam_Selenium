package us.piit.pages.nopCommerce;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import us.piit.base.CommonAPI;
import us.piit.utility.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class RegisterPage extends CommonAPI {
    Logger log = LogManager.getLogger(LoginPage.class.getName());
    protected WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    Properties prop = Utility.loadProperties();
    String ValidEmail = Utility.decode(prop.getProperty("nopCommerce.username"));
    String validPassword = Utility.decode(prop.getProperty("nopCommerce.password"));

    @FindBy(xpath = "//a[@href=\"/register?returnUrl=%2F\"]")
    WebElement RegisterLink;

    @FindBy(css = "[id=\"gender-male\"]")
    WebElement genderMale;

    @FindBy(css = "[id=\"gender-female\"]")
    WebElement genderFemale;

    @FindBy(id = "FirstName")
    WebElement FirstNameInputField;

    @FindBy(id = "LastName")
    WebElement LastNameInputField;

    @FindBy(id = "Email")
    WebElement EmailInputField;

    @FindBy(id = "Password")
    WebElement PasswordInputField;

    @FindBy(id = "ConfirmPassword")
    WebElement ConfirmPasswordInputField;

    @FindBy(id = "Company")
    WebElement CompanyNameInputField;


    @FindBy(id = "register-button")
    WebElement RegisterButton;

    @FindBy(xpath = "//div[text()='Your registration completed']")
    WebElement RegistrationCompletedSuccessMessage;

    @FindBy(xpath = "//a[@class=\"button-1 register-continue-button\"]")
    WebElement ContinueButton;

    @FindBy(xpath = "//h2[text()='Welcome to our store']")
    WebElement WelcomeNote;


    public void goToRegisterPage() {
        RegisterLink.click();
        log.info("User is on Register page success");
    }

    public void registerNewUser(String Case) {
        switch (Case) {
            case "All details":
                waitFor(1);
                isVisible(genderMale);
                genderMale.click();
                type(FirstNameInputField, "nop");
                type(LastNameInputField, "Commerce");
                selectDateOfBirth();
                type(EmailInputField, ValidEmail);
                type(CompanyNameInputField, "SQA_Company");
                type(PasswordInputField, validPassword);
                type(ConfirmPasswordInputField, validPassword);
                RegisterButton.click();
                if (driver.findElements(By.xpath("//li[text()='The specified email already exists']")).size() < 0) {
                    isVisible(RegistrationCompletedSuccessMessage);
                    Assert.assertTrue(RegistrationCompletedSuccessMessage.isDisplayed());
                    Assert.assertEquals(RegistrationCompletedSuccessMessage.getText(), "Your registration completed");
                    break;
                } else {
                    log.info("This Email Aleady Exists");

                    // enter new email for registration
                    EmailInputField.clear();
                    type(EmailInputField, generateTestEmail());
                    PasswordInputField.clear();
                    type(PasswordInputField, validPassword);
                    ConfirmPasswordInputField.clear();
                    type(ConfirmPasswordInputField, validPassword);
                    waitFor(1);
                    RegisterButton.click();
                    isVisible(RegistrationCompletedSuccessMessage);
                    Assert.assertTrue(RegistrationCompletedSuccessMessage.isDisplayed());
                    Assert.assertEquals(RegistrationCompletedSuccessMessage.getText(), "Your registration completed");
                    break;
                }
            case "No company details":
                waitFor(1);
                isVisible(genderMale);
                genderMale.click();
                type(FirstNameInputField, "nop");
                type(LastNameInputField, "Commerce");
                selectDateOfBirth();
                type(EmailInputField, ValidEmail);
                type(PasswordInputField, validPassword);
                type(ConfirmPasswordInputField, validPassword);
                RegisterButton.click();
                if (driver.findElements(By.xpath("//li[text()='The specified email already exists']")).size() < 0) {
                    isVisible(RegistrationCompletedSuccessMessage);
                    Assert.assertTrue(RegistrationCompletedSuccessMessage.isDisplayed());
                    Assert.assertEquals(RegistrationCompletedSuccessMessage.getText(), "Your registration completed");
                } else {
                    log.info("This Email Aleady Exists");

                    // enter new email for registration
                    EmailInputField.clear();
                    type(EmailInputField, generateTestEmail());
                    PasswordInputField.clear();
                    type(PasswordInputField, validPassword);
                    ConfirmPasswordInputField.clear();
                    type(ConfirmPasswordInputField, validPassword);
                    waitFor(1);
                    RegisterButton.click();
                    isVisible(RegistrationCompletedSuccessMessage);
                    Assert.assertTrue(RegistrationCompletedSuccessMessage.isDisplayed());
                    Assert.assertEquals(RegistrationCompletedSuccessMessage.getText(), "Your registration completed");

                }
                break;
            case "Empty details":
                waitFor(1);
                RegisterButton.click();
                waitFor(1);
                verifyAllMentadoryFieldErrorAppears();
                break;
        }
    }

    public void selectDateOfBirth() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // select Day
        js.executeScript("document.querySelector('[name=\"DateOfBirthDay\"]').options[1].selected = true");

        // select month
        js.executeScript("document.querySelector('[name=\"DateOfBirthMonth\"]').options[1].selected = true");

        // select Year
        js.executeScript("document.querySelector('[name=\"DateOfBirthYear\"]').options[1].selected = true");
    }

    public void verifyAllMentadoryFieldErrorAppears() {
        List<String> error = new ArrayList<>();
        // Add elements to the list
        error.add("First name is required.");
        error.add("Last name is required.");
        error.add("Email is required.");
        error.add("Password is required.");
        error.add("Password is required.");

        List<WebElement> fieldErrors = driver.findElements(By.cssSelector("[class=\"field-validation-error\"]"));
        int index = 0;
        for (WebElement fieldError : fieldErrors) {
            log.info(fieldError.getText());
            Assert.assertEquals(fieldError.getText(), error.get(index));
            index += 1;
        }
    }

}
