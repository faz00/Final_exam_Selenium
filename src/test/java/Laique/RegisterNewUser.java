package Laique;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.SetUp;

public class RegisterNewUser extends SetUp {


    public static @FindBy(xpath = "//a[@href=\"/register?returnUrl=%2F\"]")
    WebElement RegisterLink;

    public static @FindBy(css = "[id=\"gender-male\"]")
    WebElement genderMale;

    public static @FindBy(css = "[id=\"gender-female\"]")
    WebElement genderFemale;

    public static @FindBy(id = "FirstName")
    WebElement FirstNameInputField;

    public static @FindBy(id = "LastName")
    WebElement LastNameInputField;

    public static @FindBy(id = "Email")
    WebElement EmailInputField;

    public static @FindBy(id = "Password")
    WebElement PasswordInputField;

    public static @FindBy(id = "ConfirmPassword")
    WebElement ConfirmPasswordInputField;

    public static @FindBy(id = "Company")
    WebElement CompanyNameInputField;


    public static @FindBy(id = "register-button")
    WebElement RegisterButton;

    public static @FindBy(xpath = "//div[text()='Your registration completed']")
    WebElement RegistrationCompletedSuccessMessage;

    public static @FindBy(xpath = "//a[@class=\"button-1 register-continue-button\"]")
    WebElement ContinueButton;

    public static @FindBy(xpath = "//h2[text()='Welcome to our store']")
    WebElement WelcomeNote;

    public static @FindBy(xpath = "//li[text()='The specified email already exists']")


    String TestEmail = "test002@gmail.com";
    String TestPassword = "qwerty@123";

    @Test(priority = 0)
    public void RegisterNewUser(){
        // Verifying Page title
        Assert.assertEquals(getCurrentTitle(), "nopCommerce demo store");

        isVisible(RegisterLink);
        RegisterLink.click();

        // Verifying Page title
        Assert.assertEquals(getCurrentTitle(), "nopCommerce demo store. Register");

        // function registers a new user
        registerNewUser();
    }

    public void registerNewUser(){
        genderMale.click();
        FirstNameInputField.sendKeys("Laique");
        LastNameInputField.sendKeys("Jamal");
        selectDateOfBirth();
        EmailInputField.sendKeys(TestEmail);
        CompanyNameInputField.sendKeys("SQA_Company");
        PasswordInputField.sendKeys(TestPassword);
        ConfirmPasswordInputField.sendKeys(TestPassword);
        RegisterButton.click();
        if(driver.findElements(By.xpath("//li[text()='The specified email already exists']")).size()<0) {
            isVisible(RegistrationCompletedSuccessMessage);
            Assert.assertTrue(RegistrationCompletedSuccessMessage.isDisplayed());
            Assert.assertEquals(RegistrationCompletedSuccessMessage.getText(), "Your registration completed");
        }else {
            System.out.println("This Email Aleady Exists");
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
}
