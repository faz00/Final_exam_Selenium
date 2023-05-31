package us.piit.pages.orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

public class MyInfoPage extends CommonAPI {
    Logger log = LogManager.getLogger(MyInfoPage.class.getName());
    public MyInfoPage
            (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//a[normalize-space()='Contact Details'])[1]")
    WebElement contactDetailsOption;

    @FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > form:nth-child(3) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)")
    WebElement street1Field;

    @FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > form:nth-child(3) > div:nth-child(3) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)")
    WebElement cityNameField;

    @FindBy(css = "div:nth-child(4) div:nth-child(1) div:nth-child(2) input:nth-child(1)")
    WebElement stateNameField;

    @FindBy(xpath = "//div[5]//div[1]//div[2]//input[1]")
    WebElement zipCodeField;
    @FindBy(css = ".oxd-select-text-input")
    WebElement countryDropDownList;

    @FindBy(css = "button[type='submit']")
    WebElement submitBtnContactInfo;

    @FindBy(xpath = "(//a[normalize-space()='Dependents'])[1]")
    WebElement dependentsOption;

    @FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > button:nth-child(2) > i:nth-child(1)")
    WebElement addDependentsBtn;

    @FindBy(css = "body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > form:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)")
    WebElement nameField;
    @FindBy(css = ".oxd-select-text-input")
    WebElement dependentsRelationShipDropDownList;
    @FindBy(css = "input[placeholder='yyyy-mm-dd']")
    WebElement dateField;
    @FindBy(css = "button[type='submit']")
    WebElement saveDependentsBtn;

    @FindBy(xpath = "(//div[normalize-space()='Name'])[1]")
    WebElement actualName;
    public void clickOnDetailsOption(){
        clickOn(contactDetailsOption);
        log.info("button clicked on success");
    }
    public void typeStreet1Name(String street){
        type(street1Field,street);
        log.info("Street name added successfully");
    }

    public void typeCityName(String city){
        type(cityNameField,city);
        log.info("City name added successfully");
    }

    public void typeStateName(String state){
        type(stateNameField,state);
        log.info("State name added successfully");
    }
    public void typeZipCodeName(String zipCode){
        type(zipCodeField,zipCode);
        log.info("State name added successfully");
    }

    public void clickOnCountry(){
        clickOn(countryDropDownList);
        waitFor(2);
        countryDropDownList.sendKeys(Keys.ARROW_DOWN);
        waitFor(2);
        countryDropDownList.sendKeys(Keys.ENTER);
    }

    public void clickOnSubmitContactInfo(){
        clickOn(submitBtnContactInfo);
        log.info("submit button clicked on success");
    }

    public void clickOnDependentsInfo(){
        clickOn(dependentsOption);
        log.info("dependents button clicked on success");
    }
    public void clickOnAddDependentsBtn(){
        clickOn(addDependentsBtn);
        log.info("Add new dependents button clicked on success");
    }
    public void typeNameField(String name){
        type(nameField,name);
        log.info("name field typed on success");
    }
    public void clickOnRelationship(){
        clickOn(dependentsRelationShipDropDownList);
        waitFor(2);
        dependentsRelationShipDropDownList.sendKeys(Keys.ARROW_DOWN);
        waitFor(2);
        dependentsRelationShipDropDownList.sendKeys(Keys.ENTER);
    }

    public void typeDateField(String date){
        type(dateField,date);
        log.info("date field typed on success");
    }

    public void clickOnSaveDependents(){
        clickOn(saveDependentsBtn);
        log.info("button clicked on success");
    }
    public String getActualName(){
        return getElementText(actualName);
    }
}
