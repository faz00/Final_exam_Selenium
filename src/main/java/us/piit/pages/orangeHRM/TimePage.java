package us.piit.pages.orangeHRM;

import io.opentelemetry.api.baggage.propagation.W3CBaggagePropagator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

import java.util.List;

public class TimePage extends CommonAPI {
    Logger log = LogManager.getLogger(TimePage.class.getName());
    public TimePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath="//div[contains(text(),'pnt')]")
    WebElement scrollDown;
    @FindBy(xpath="(//h6[normalize-space()='Time'])[1]")
    WebElement actualTimeTitle;
    @FindBy(xpath = "//span[normalize-space()='Project Info']")
    WebElement projectInfoBtn;
    @FindBy(css="header[class='oxd-topbar'] li:nth-child(1) a:nth-child(1)")
    WebElement customerOption;
    @FindBy(css="div[role='table'] div:nth-child(1) div:nth-child(1) div:nth-child(4) div:nth-child(1) button:nth-child(2)")
    WebElement editIcon;
    @FindBy(css="textarea[placeholder='Type description here']")
    WebElement writtenTextToEdit;
    @FindBy(css="button[type='submit']")
    WebElement submitEditBtn;
    @FindBy(xpath="//div[contains(text(),'This is a test to edit customer information')]")
    WebElement actualDescription;
    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement addCustomerBtn;
    @FindBy(css="div[class='oxd-input-group oxd-input-field-bottom-space'] div input[class='oxd-input oxd-input--active']")
    WebElement customerNameField;
    @FindBy(xpath = "//textarea[@placeholder='Type description here']")
    WebElement descriptionNameField;
    @FindBy(css = "button[type='submit']")
    WebElement saveNewCustomerBtn;
    @FindBy(xpath = "//div[contains(text(),'PNT')]")
    WebElement actualName;

    @FindBy(xpath = "//div[contains(text(),'PNT1')]")
    WebElement actualName1;

    @FindBy(xpath = "//div[contains(text(),'PNT2')]")
    WebElement actualName2;
    @FindBy(xpath = "//div[@role='columnheader']//i[@class='oxd-icon bi-check oxd-checkbox-input-icon']")
    WebElement checkBoxToDelete;

    @FindBy(css="button[class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-horizontal-margin']")
    WebElement deleteButton;
    @FindBy(xpath = "//button[normalize-space()='Yes, Delete']")
    WebElement yesDeleteConfirmationBtn;
    @FindBy(css = ".oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message")
    WebElement errorExistingCustomerMsg;

    public void scroll(){

        scrollToElement(scrollDown);
    }
    public String getActualTimeTitle(){
        return getElementText(actualTimeTitle);
    }
    public void clickOnProjectInfoBtn(){
        clickOn(projectInfoBtn);
        log.info("Project info clicked on success");
    }
    public void clickOnCustomerOption(){
        clickOn(customerOption);
        log.info("Customer option clicked on success");

    }
    public void clickOnEditIcon(){
        clickOn(editIcon);
        log.info("Edit icon clicked on success");
    }
    public void clearWrittenText(){
        writtenTextToEdit.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        log.info("Text delete success");
    }
    public void typeTextInCustomerInfo(String text){
        type(writtenTextToEdit,text);
    }
    public void clickOnSubmitBtn(){
        clickOn(submitEditBtn);
    }
    public String getActualDescription(){
        return getElementText(actualDescription);
    }
    public void clickOnAddCustomerBtn(){
        clickOn(addCustomerBtn);
        log.info("Add button clicked on success");
    }
    public void typeCustomerNameField(String name){
        type(customerNameField,name);
        log.info("Name added successfully");
    }
    public void typeCustomerDescriptionField(String desc){
        type(descriptionNameField,desc);
        log.info("Description added successfully");
    }

    public void clickOnSaveNewCustomerBtn(){
        clickOn(saveNewCustomerBtn);
        log.info("Save button clicked on success");
    }
    public String getActualName(){
        return getElementText(actualName);
    }

    public String getActualName1(){
        return getElementText(actualName1);
    }
    public String getActualName2(){
        return getElementText(actualName2);
    }
    public void clickOnCheckBoxToDelete(){
        clickOn(checkBoxToDelete);

        log.info("Check box is checked successfully");
    }
    public void clickOnDeleteBtn(){
        clickOn(deleteButton);
        log.info("Delete selection button clicked in success");
    }
    public void clickOnYesConfirmationDelete(){
        clickOn(yesDeleteConfirmationBtn);
        log.info("Button clicked in success");
    }

    public String getErrorMsg(){
        return getElementText(errorExistingCustomerMsg);
    }
}
