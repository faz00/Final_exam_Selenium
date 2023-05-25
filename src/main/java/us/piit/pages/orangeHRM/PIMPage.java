package us.piit.pages.orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;



public class PIMPage extends CommonAPI {
    Logger log = LogManager.getLogger(DashbordPage.class.getName());
    public PIMPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(css ="div[class='oxd-form-row'] div[class='oxd-grid-2 orangehrm-full-width-grid'] div[class='oxd-grid-item oxd-grid-item--gutters'] div[class='oxd-input-group oxd-input-field-bottom-space'] div input[type='password']")
    WebElement currentPassword;

    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters user-password-cell']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@type='password']")
    WebElement newPassword;

    @FindBy(css="div[class='oxd-form-row user-password-row'] div[class='oxd-grid-2 orangehrm-full-width-grid'] div[class='oxd-grid-item oxd-grid-item--gutters'] div[class='oxd-input-group oxd-input-field-bottom-space'] div input[type='password']")
    WebElement confirmPassword;

    @FindBy(css = "button[type='submit' i]")
    WebElement submitChangedPassword;

    public void clickOnChangePasswordBtn(){
        clickOn(submitChangedPassword);
        log.info("Password updated success");
    }

    public  void enterCurrentPassword(String password){
        type(currentPassword,password);
    }
    public void enterNewPassword(String Password){
        type(newPassword,Password);
        log.info("Password entered in New Password field");
    }
    public void enterConfirmNewPassword(String Password){
        type(confirmPassword,Password);
        log.info("Password entered in Confirm Password field ");
    }


}
