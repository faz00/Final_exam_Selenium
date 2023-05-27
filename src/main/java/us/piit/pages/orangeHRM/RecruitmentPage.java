package us.piit.pages.orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

public class RecruitmentPage extends CommonAPI {

    Logger log = LogManager.getLogger(RecruitmentPage.class.getName());
    public RecruitmentPage
            (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//i[@class='oxd-icon bi-eye-fill'])[1]")
    WebElement viewIcon;

    @FindBy(css = "div[role='table'] div:nth-child(1) div:nth-child(1) div:nth-child(3) div:nth-child(1) button:nth-child(1) i:nth-child(1)")
    WebElement editIcon;

    @FindBy(css = "textarea[placeholder='Type here']")
    WebElement noteField;


    @FindBy(css = "button[type='submit']")
    WebElement submitBtn;

    @FindBy(css=".oxd-text.oxd-text--h6.orangehrm-main-title")
    WebElement actualPage;
    @FindBy(xpath = "//a[normalize-space()='Vacancies']")
    WebElement vacanciesBtn;
    @FindBy(xpath="//button[normalize-space()='Add']")
    WebElement addVacanciesBtn;
    @FindBy(css = "body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > form:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)")
    WebElement vacancyNameField;
    @FindBy(css = ".oxd-select-text-input")
    WebElement jobTitleDropDownList;
    @FindBy(css = "input[placeholder='Type for hints...']")
    WebElement hiringManagerField;


    @FindBy(css = "button[type='submit']")
    WebElement submitNewVacancy;

    @FindBy(xpath = "(//h6[normalize-space()='Edit Vacancy'])[1]")
    WebElement actualResult;

    @FindBy(xpath = "(//label)[10]")
    WebElement checkBoxToDelete;
    @FindBy(xpath = "//button[normalize-space()='Delete Selected']")
    WebElement btnToDeleteSelectedVacancy;
    @FindBy(xpath = "//button[normalize-space()='Yes, Delete']")
    WebElement yesDeleteBtn;
    public void clickOnViewIcon(){
        clickOn(viewIcon);
        log.info("view Icon clicked on success");
    }
    public void clickOnEditIcon(){
        clickOn(editIcon);
        log.info("Edit Icon clicked on success");
    }

    public void typeNoteInCandidatesInterview(String note){
        type(noteField,note);
    }
    public void clickOnSubmitBtn(){
        clickOn(submitBtn);
        log.info("Submit Button clicked on success");
    }
    public String getActualPage(){
        return getElementText(actualPage);
    }
    public void clickOnVacancies(){
        clickOn(vacanciesBtn);
        log.info("vacancies button Clicked on success");
    }
    public void clickOnAddVacanciesBtn(){
        clickOn(addVacanciesBtn);
        log.info("Add vacancies button Clicked on success");
    }
    public void typeVacancyName(String name){
        type(vacancyNameField,name);
        log.info("name typed on success");
    }

    public void clickOnjobTitleDropDownList(){
        clickOn(jobTitleDropDownList);
        waitFor(2);
        jobTitleDropDownList.sendKeys(Keys.ARROW_DOWN);
        waitFor(2);
        jobTitleDropDownList.sendKeys(Keys.ENTER);
    }


    public void typeHiringManagerField(){
        type(hiringManagerField,"Odis Adalwin");
        waitFor(5);
        hiringManagerField.sendKeys(Keys.ARROW_DOWN);
        waitFor(2);
        hiringManagerField.sendKeys(Keys.ENTER);
        log.info("hiring manager added on success");
    }

    public void clickOnSubmitVacancy(){
        clickOn(submitNewVacancy);
        log.info("submit button clicked on success");
    }
    public String getActualResultAddVacancy(){
        return getElementText(actualResult);
    }
    public void clickOnCheckBox(){
        clickOn(checkBoxToDelete);
        log.info("CheckBox Checked");
    }
    public void clickOnDeleteVacancy(){
        clickOn(btnToDeleteSelectedVacancy);
        log.info("Button clicked on success");
    }
    public void clickOnYesDeleteBtn(){
         clickOn(yesDeleteBtn);
         log.info("click On Yes Delete success");
    }
}
