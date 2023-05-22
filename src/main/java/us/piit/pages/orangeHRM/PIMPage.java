package us.piit.pages.orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;



public class PIMPage extends CommonAPI {
    Logger log = LogManager.getLogger(PIMPage.class.getName());
    public PIMPage(WebDriver driver){

        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[class='oxd-icon-button oxd-icon-button--solid-main employee-image-action']")
    WebElement inputFieldOfTheDialog;


    @FindBy(css ="div[class='oxd-form-row'] div[class='oxd-grid-2 orangehrm-full-width-grid'] div[class='oxd-grid-item oxd-grid-item--gutters'] div[class='oxd-input-group oxd-input-field-bottom-space'] div input[type='password']")
    WebElement currentPassword;

    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters user-password-cell']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@type='password']")
    WebElement newPassword;

    @FindBy(css="div[class='oxd-form-row user-password-row'] div[class='oxd-grid-2 orangehrm-full-width-grid'] div[class='oxd-grid-item oxd-grid-item--gutters'] div[class='oxd-input-group oxd-input-field-bottom-space'] div input[type='password']")
    WebElement confirmPassword;

    @FindBy(css = "button[type='submit' i]")
    WebElement submitChangedPassword;

    @FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input:nth-child(2)")
    WebElement nameField;
    @FindBy(css="button[type='submit'")
    WebElement submitBtnSearchForAnEmployee;

    @FindBy(css="button[class='oxd-button oxd-button--medium oxd-button--secondary']")
    WebElement  addEmployeeBtn;

    @FindBy(css="input[placeholder='First Name']")
    WebElement firstNameOfEmployee;

    @FindBy(css="input[placeholder='Middle Name']")
    WebElement lastNameOfEmployee;
    @FindBy(css="input[placeholder='Last Name']")
    WebElement middleNameOfEmployee;

    @FindBy(css = ".oxd-switch-input.oxd-switch-input--active.--label-right")
    WebElement checkboxToAddMoreInfo;

    @FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > form:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)")
    WebElement usernameField;

    @FindBy(css="div[class='oxd-grid-item oxd-grid-item--gutters user-password-cell'] div[class='oxd-input-group oxd-input-field-bottom-space'] div input[type='password']")
    WebElement passwordField;

    @FindBy(css="div[class='oxd-grid-item oxd-grid-item--gutters'] div[class='oxd-input-group oxd-input-field-bottom-space'] div input[type='password']")
    WebElement confirmPasswordField;

    @FindBy(css="button[type='submit']")
    WebElement submitNewUserBtn;

    @FindBy(css=".oxd-text.oxd-text--h6.--strong")
    WebElement actualUserName;

    @FindBy(xpath = "(//i[@class='oxd-icon bi-pencil-fill'])[1]")
    WebElement editIcon;

    @FindBy(xpath = "//a[normalize-space()='Qualifications']")
    WebElement qualificationOption;

    @FindBy(xpath = "(//i)[7]")
    WebElement addExperienceBtn;

    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input[1]")
    WebElement companyNameField;

    @FindBy(css = "body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > form:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)")
    WebElement titleField;

    @FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > form:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)")
    WebElement beginningDateField;
    @FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > form:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)")
    WebElement endDateField;

    @FindBy(css="button[type='submit']")
    WebElement submitExperienceBtn;

    @FindBy(xpath="//div[contains(text(),'Amazon')]")
    WebElement actualResultAddedExperience;

    @FindBy(xpath = "//a[normalize-space()='Tax Exemptions']")
    WebElement taxExemptionBtn;

    @FindBy(xpath = "(//div[contains(text(),'-- Select --')])[1]")
    WebElement taxExemptionDropDownMenu1;
    @FindBy(xpath = "(//div[contains(text(),'-- Select --')])[2]")
    WebElement taxExemptionDropDownMenu2;
    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[4]/div[1]/div[2]/div[1]/div[1]/div[1]")
    WebElement taxExemptionDropDownMenu3;

    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]")
    WebElement taxExemptionDropDownMenu4;

    @FindBy(xpath = "(//input)[2]")
    WebElement taxExemption1;

    @FindBy(xpath = "(//input)[3]")
    WebElement taxExemption2;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitTaxExemption;

    @FindBy(css = "body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(5) > div:nth-child(1) > div:nth-child(9) > div:nth-child(1) > button:nth-child(1)")
    WebElement deleteIcon;

    @FindBy(xpath = "//button[normalize-space()='Yes, Delete']")
    WebElement deleteConfirmation;


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
    public void setThePathOfImage(String path){
        inputFieldOfTheDialog.sendKeys(path);
        log.info("path sent with success");
    }

    public void searchEmployeeName(String name){
        type(nameField,name);
        nameField.sendKeys(Keys.ARROW_DOWN);
        waitFor(5);
        nameField.sendKeys(Keys.ENTER);
        log.info("Name of employee selected successfully");
    }

    public void submitSearchEmployeeBtn(){
        clickOn(submitBtnSearchForAnEmployee);
        log.info("Submit button clicked on success");
    }

    public void clickOnAddEmployeeBtn(){
        clickOn(addEmployeeBtn);
        log.info("Button clicked on success");
    }

    public void addFirstName(String firstName){
        type(firstNameOfEmployee,firstName);
    }
    public void addLastName(String lastName){
        type(lastNameOfEmployee,lastName);
    }
    public void addMidleName(String middleName){
        type(middleNameOfEmployee,middleName);
    }

    public void clickCheckBox(){
        clickOn(checkboxToAddMoreInfo);
        log.info("Button checked");
    }

    public void addUsername(String username){
        type(usernameField,username);
        log.info("Username Added");
    }

    public void addPasswordForNewUser(String password){
        type(passwordField,password);
        log.info("Password Added success");
    }
    public void confirmPasswordForNewUser(String password){
        type(confirmPasswordField,password);
        log.info("Confirm Password Added success");
    }

    public void clickOnSubmitNewUser(){
        clickOn(submitNewUserBtn);
        log.info("Button clicked on success");
    }

    public String getActualUsername(){
        return getElementText(actualUserName);
    }

    public void clickOnEditBtn(){
        clickOn(editIcon);
        log.info("Edit icon clicked successfully");
    }

    public void clickOnQualificationOption(){
        clickOn(qualificationOption);
        log.info("qualification clicked on success");
    }

    public void clickOnAddExperienceBtn(){
        clickOn(addExperienceBtn);
        log.info("add experience clicked on success");
    }

    public void typeCompanyName(String company){
        type(companyNameField,company);
        log.info("Company name added");
    }
    public void typeTitleJobField(String title){
        type(titleField,title);
        log.info("Title job added ");
    }
    public void typeBeginningDate(String date){
        type(beginningDateField,date);
        log.info("Date Added");
    }
    public void typeEndDate(String date){
        type(endDateField,date);
        log.info("Date Added");
    }

    public void clickSubmitBtn(){
        clickOn(submitExperienceBtn);
    }

    public String getActualResultAddedExperience(){
        return getElementText(actualResultAddedExperience);
    }

    public void clickOnTaxExemptionBtn(){
        clickOn(taxExemptionBtn);
        log.info("Emergency Contacts clicked on success");
    }

    public void typeTaxExemption(String num){
        type(taxExemption1,num);
        log.info("Tax Exemption added");
    }
    public void typeTaxExemptions2(String num){
        type(taxExemption2,num);
        log.info("Tax Exemption added");
    }
    public void dropDownList1(){

        clickOn(taxExemptionDropDownMenu1);
        waitFor(5);
        taxExemptionDropDownMenu1.sendKeys(Keys.ARROW_DOWN);
        waitFor(5);
        taxExemptionDropDownMenu1.sendKeys(Keys.ENTER);
        log.info("Option selected in success");
    }
    public void dropDownList2(){


        clickOn(taxExemptionDropDownMenu2);
        waitFor(5);
        taxExemptionDropDownMenu2.sendKeys(Keys.ARROW_DOWN);
        waitFor(5);
        taxExemptionDropDownMenu2.sendKeys(Keys.ENTER);
        log.info("Option selected in success");
    }
    public void dropDownListOption3(){


        clickOn(taxExemptionDropDownMenu3);
        waitFor(5);
        taxExemptionDropDownMenu3.sendKeys(Keys.ARROW_DOWN);
        waitFor(5);
        taxExemptionDropDownMenu3.sendKeys(Keys.ENTER);
        log.info("Option selected in success");
    }
    public void dropDownList4(){


        clickOn(taxExemptionDropDownMenu4);
        waitFor(5);
        taxExemptionDropDownMenu4.sendKeys(Keys.ARROW_DOWN);
        waitFor(5);
        taxExemptionDropDownMenu4.sendKeys(Keys.ENTER);
        log.info("Option selected in success");
    }


    public void clickOnSubmitButtonTaxExemption(){
        clickOn(submitTaxExemption);
        log.info("Button clicked on success");
    }


    public void clickOnDeleteIcon(){
        clickOn(deleteIcon);
        log.info("Delete icon clicked on success");
    }

    public void clickOnYesDeleteButton(){
        clickOn(deleteConfirmation);
        log.info("click on 'yes Delete' success");
    }
}
