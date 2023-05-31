package us.piit.pages.orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

public class AdminPage extends CommonAPI {
    Logger log = LogManager.getLogger(AdminPage.class.getName());
    public AdminPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement addBtn;
    @FindBy(xpath = "//div[contains(text(),'-- Select --')]")
    WebElement userRoleDropDownList;

    @FindBy(css = "input[placeholder='Type for hints...']")
    WebElement employeeNameField;
    @FindBy(css = "body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > form:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)")
    WebElement statusField;

    @FindBy(css = "div[class='oxd-form-row'] div[class='oxd-grid-2 orangehrm-full-width-grid'] div[class='oxd-grid-item oxd-grid-item--gutters'] div[class='oxd-input-group oxd-input-field-bottom-space'] div input[class='oxd-input oxd-input--active']" )
    WebElement usernameField;
    @FindBy(xpath = "(//input[@type='password'])[1]")
    WebElement passwordField;

    @FindBy(xpath ="(//input[@type='password'])[2]" )
    WebElement confirmPasswordField;


    @FindBy(css = "button[type='submit']")
    WebElement submitBtn;

    @FindBy(xpath = "(//h5[normalize-space()='System Users'])[1]")
    WebElement actualCreatedUser;
    @FindBy(xpath = "//span[normalize-space()='User Management']")
    WebElement userMAnagementBtn;

    @FindBy(xpath = "(//li)[14]")
    WebElement userOption;

    @FindBy(xpath="//span[normalize-space()='Qualifications']")
    WebElement qualificationBtn;

    @FindBy(css = "header[class='oxd-topbar'] li:nth-child(1) a:nth-child(1)")
    WebElement skillsOption;
    @FindBy(xpath = "//i[@class='oxd-icon bi-plus oxd-button-icon']")
    WebElement addSkillBtn;

    @FindBy(xpath = "//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']")
    WebElement nameOfSkillField;
    @FindBy(css = "textarea[placeholder='Type description here']")
    WebElement descriptionOsSkillField;
    @FindBy(xpath = "(//h6[normalize-space()='Skills'])[1]")
    WebElement actualAddedSkill;

    @FindBy(xpath = "(//i[@class='oxd-icon bi-trash'])[1]")
    WebElement deleteBtn;

    @FindBy(xpath = "//button[normalize-space()='Yes, Delete']")
    WebElement yesDelete;

    public void clickOnUserRoleDropDownList(){
        clickOn(userRoleDropDownList);
        waitFor(5);
        userRoleDropDownList.sendKeys(Keys.ARROW_DOWN);
        waitFor(2);
        userRoleDropDownList.sendKeys(Keys.ENTER);
        log.info("User Role  added on success");
    }

    public void addEmployeeNAme(String name){
        type(employeeNameField,name);
        waitFor(5);
        employeeNameField.sendKeys(Keys.ARROW_DOWN);
        waitFor(2);
        employeeNameField.sendKeys(Keys.ENTER);
        log.info("Employee Name added on success");
    }
    public void clickOnStatus(){
        clickOn(statusField);
        waitFor(5);
        statusField.sendKeys(Keys.ARROW_DOWN);
        waitFor(2);
        statusField.sendKeys(Keys.ENTER);
    }

    public void typeUsernameField(String username){

        type(usernameField,username);
        log.info("username added on success");
    }

    public void typePassword(String password){
        type(passwordField,password);
        log.info("Password added successfully");
    }

    public void typeConfirmPassword(String password){
        type(confirmPasswordField,password);
        log.info("Password added successfully");
    }
    public void clickOnSubmitBtn(){
        clickOn(submitBtn);
        log.info("button clicked on success");
    }

    public String geetActuslCreatedsSer(){
        return getElementText(actualCreatedUser);
    }
    public void clickOnAddBtn(){

        clickOn(addBtn);
    }
    public void clickOnUserManagementBtn(){
        clickOn(userMAnagementBtn);
    }
    public void clickOnUserOption(){
        clickOn(userOption);
    }
    public void clickOnQualificationBtn(){
        clickOn(qualificationBtn);
        log.info("qualification Button clicked on success");
    }
    public void clickOnSkillsBtn(){
        clickOn(skillsOption);
        log.info("Skills option clicked on success");
    }
    public void clickOnAddSkillBtn(){
        clickOn(addSkillBtn);
        log.info("Add Skill Btn clicked on ssuccess");
    }
    public void typeSkillName(String skillName){
        type(nameOfSkillField,skillName);
        log.info("Skill name added successfully");
    }
    public void typeDescriptionOfSkill(String descriptionOfSkill){
        type(descriptionOsSkillField,descriptionOfSkill);
        log.info("description of Skill added successfully");
    }

    public String getActualAddedSkill(){
        return getElementText(actualAddedSkill);
    }

    public void clickOnDeleteSkillBtn(){
        clickOn(deleteBtn);
        log.info("Delete button clicked on success");
    }
    public void clickOnYesDeleteBtn(){
        clickOn(yesDelete);
        log.info("yes Delete Button clicked on success");

    }
}
