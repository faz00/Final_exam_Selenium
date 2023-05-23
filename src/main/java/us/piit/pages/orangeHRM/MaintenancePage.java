package us.piit.pages.orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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






}
