package us.piit.pages.orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

public class MaintenancePage extends CommonAPI {

    Logger log = LogManager.getLogger(DashbordPage.class.getName());
    public MaintenancePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="input[name='password']")
    WebElement passwordField;

    @FindBy(css="button[type='submit']")
    WebElement submitButton;

    @FindBy(css=".oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module")
    WebElement headerPageMaintenance;

    @FindBy(css=".oxd-text.oxd-text--p.oxd-alert-content-text")
    WebElement errorMsg;

    @FindBy(css="a[class='oxd-topbar-body-nav-tab-item']")
    WebElement accessRecordBtn;
   @FindBy(xpath = "//input[@placeholder='Type for hints...']")
   WebElement selectNameDropDown;
   @FindBy(css = "button[type='submit']")
   WebElement searchEmployeeBtn;

   @FindBy(css = "div[class='orangehrm-background-container'] h6[class='oxd-text oxd-text--h6 orangehrm-main-title']")
   WebElement actualEmployeeDisplayed;

   @FindBy(css = "div[class='orangehrm-background-container'] button[type='submit']")
   WebElement downloadBtn;


    public void enterPassword(String password){
        type(passwordField,password);
        log.info("Password entered in success");

    }

    public void clickOnSubmitBtn(){
        clickOn(submitButton);
        log.info("submit button clicked on success");
    }

    public String getHeaderText(){
        String text = getElementText(headerPageMaintenance);
        log.info("user logged in success to Maintenance");
        return text;
    }
    public String getErrorMsg(){
        String text = getElementText(errorMsg);
        log.info("error message displayed in success ");
        return text;
    }

    public void clickOnAccessRecordBtn(){
        clickOn(accessRecordBtn);
        log.info("Access Records clicked on success");
    }

    public void selectNAmeFromDropDownList(String name){
        type(selectNameDropDown,name);
        waitFor(5);
        selectNameDropDown.sendKeys(Keys.ARROW_DOWN);
        waitFor(5);
        selectNameDropDown.sendKeys(Keys.ENTER);   }

    public void clickOnSearchEmployeeBtn(){
        clickOn(searchEmployeeBtn);
        log.info("Submit clicked on success");
    }

    public String getActualEmployeeDisplayed(){
        return getElementText(actualEmployeeDisplayed);
    }
    public void clickOnDownloadEmployee(){
        clickOn(downloadBtn);
        log.info("Employee Records downloaded");
    }





}
