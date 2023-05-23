package us.piit.pages.orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

public class LeavePage extends CommonAPI {

    Logger log = LogManager.getLogger(LeavePage.class.getName());
    public LeavePage(WebDriver driver){

        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath="(//button[@type='button'])[6]")
    WebElement threeDotsBtn;

    @FindBy(xpath = "//p[normalize-space()='View Leave Details']")
    WebElement leaveDetailsOption;
    @FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(7) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(7) > div:nth-child(1) > button:nth-child(1)")
    WebElement approveBtn;
    @FindBy(xpath = "//div[contains(text(),'Taken')]")
    WebElement actualApproveStatus;

    @FindBy(css="button[class='oxd-button oxd-button--medium oxd-button--label-danger oxd-table-cell-action-space']")
    WebElement rejectBtn;

    @FindBy(xpath = "//div[contains(text(),'Rejected')]")
    WebElement actualRejectStatus;
    @FindBy(xpath = "//a[normalize-space()='Leave List']")
    WebElement leaveListOption;

    @FindBy(xpath = "(//p[normalize-space()='Add Comment'])[1]")
    WebElement addCommentOption;

    @FindBy(css="textarea[placeholder='Comment here']")
    WebElement addCommentField;
    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement saveCommentBtn;

    @FindBy(xpath = "//div[contains(text(),'This is just a comment')]")
    WebElement actualComment;

    @FindBy(xpath = "//span[normalize-space()='Configure']")
    WebElement configueOption;
    @FindBy(xpath ="//a[normalize-space()='Holidays']")
    WebElement holidayOption;
    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement addHolidayBtn;
    @FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > form:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)")
    WebElement holidayNameField;
    @FindBy(css = "input[placeholder='yyyy-mm-dd']")
    WebElement holidayDateField;
    @FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > form:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > span:nth-child(2)")
    WebElement yesCheckBox;
    @FindBy(css="button[type='submit']")
    WebElement saveHolidayBtn;

    @FindBy(xpath = "(//div[contains(text(),'Eid El Adha')])[1]")
    WebElement actualAddedHoliday;

    public void clickonThreeDots(){
        clickOn(threeDotsBtn);
        log.info("Three dots clicked on success");
    }
    public void clickOnLeaveDetailsOpion(){
        clickOn(leaveDetailsOption);
        log.info("View Leave Details option clicked on success");
    }
    public void clickOnApproveBtn(){
        clickOn(approveBtn);
        log.info("Clicked on approve in success");
    }
    public String getActualApproveStatus(){
        return getElementText(actualApproveStatus);
    }
    public void clickOnRejectBtn(){
        clickOn(rejectBtn);
        log.info("Clicked on reject in success");
    }
    public String getActualRejectStatus(){
        return getElementText(actualRejectStatus);
    }

    public void clickOnLeaveListOption(){
        clickOn(leaveListOption);
    }

    public void clickOnAddCommentOption(){
        clickOn(addCommentOption);
        log.info("Add comment option clicked on success");
    }
    public void addComment(){
        type(addCommentField,"This is just a comment");
        log.info("Comment added successfully");
    }

    public void clickOnSaveComment(){
        clickOn(saveCommentBtn);
        log.info("Save button clicked on success");
    }
    public String getActualComment(){
        return getElementText(actualComment);
    }

    public void clickOnConfigueOption(){
        clickOn(configueOption);
        log.info("Clicked on Configue button successfully");
    }
    public void clickOnHolidayOption(){
        clickOn(holidayOption);
        log.info("Add button clicked on success");
    }
    public void clickOnAddHolidayBtn(){
        clickOn(addHolidayBtn);
        log.info("Add button clicked on success");

    }
    public void typeHolidayName(String name){
        type(holidayNameField,name);
        log.info("Name added in success");
    }
    public void typeHolidayDate(String date){
        type(holidayDateField,date);
        log.info("Date added");
    }
    public void clickOnCheckBox(){
        clickOn(yesCheckBox);
        log.info("YES checked");
    }
    public void clickOnSubmitBtn(){
        clickOn(saveHolidayBtn);
        log.info("Save button Clicked on success");

    }

    public String getActualAddedHoliday(){
        return getElementText(actualAddedHoliday);
    }
}
