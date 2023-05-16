package us.piit.pages.tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

public class ForgottenPasswordHomePage extends CommonAPI {

    Logger log = LogManager.getLogger(ForgottenPasswordHomePage.class.getName());
    public ForgottenPasswordHomePage(WebDriver driver){

        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//*[@id='content']/h1")
    WebElement forgetPassHeader;
    @FindBy(css = "#input-email")
    WebElement emailInput;

    @FindBy(css = "input[value='Continue']")
    WebElement continueButton;

    @FindBy(css = ".alert-success")
    WebElement successMessage;

    @FindBy(css = ".alert-danger")
    WebElement errorMessage;

    @FindBy(css = ".pull-left > a")
    WebElement backButton;


public String getForgPassHeaderTitle() {
    return forgetPassHeader.getText();
}
    public String getEmailInput(){
        log.info("the email address is displayed in the email input field");
        return emailInput.getAttribute("placeholder");

    }
    public void resetUserPassword(String email) {
        type(emailInput,email);
        log.info("the user enters email successfully");
        continueButton.click();
        log.info("the button clicked successfully");

    }
    public void clickContinueButton(){
        clickOn(continueButton);
    }
    public boolean isResetPasswordSuccessMessageDisplayed() {
        log.info("a success message is displayed");
        return successMessage.isDisplayed();

    }

    public boolean isResetPasswordErrorMessageDisplayed() {
        log.info("an error message is displayed");
        return errorMessage.isDisplayed();
    }

    public void clickBackButton() {
        backButton.click();
        log.info("the back button clicked successfully");
    }

    public String getURL() {
        log.info("the URL of the page is :"+driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }



}




