package us.piit.pages.nopCommerce;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import us.piit.base.CommonAPI;
import us.piit.utility.Utility;

import java.util.Properties;

public class MyAccountPage extends CommonAPI {
    Logger log = LogManager.getLogger(LoginPage.class.getName());
    public MyAccountPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    Properties prop = Utility.loadProperties();
    String ValidEmail = Utility.decode(prop.getProperty("nopCommerce.username"));
    String validPassword = Utility.decode(prop.getProperty("nopCommerce.password"));


    @FindBy(css = "[class=\"header-links\"] [href=\"/customer/info\"]")
    WebElement MyAccount;

    @FindBy(id = "FirstName")
    WebElement FirstNameInputField;

    @FindBy(id = "LastName")
    WebElement LastNameInputField;

    @FindBy(id = "Company")
    WebElement CompanyNameInputField;

    @FindBy(css = "[class='page account-page customer-info-page']")
    WebElement customerInfoPage;

    @FindBy(id = "save-info-button")
    WebElement InfoSaveBtn;

    @FindBy(xpath = "//div[@class=\"bar-notification success\"]")
    WebElement Successbar;

    @FindBy(css = "[class=\"customer-addresses inactive\"] [href=\"/customer/addresses\"]")
    WebElement AddressMenuOption;

    @FindBy(css = "[class=\"button-1 add-address-button\"]")
    WebElement AddNewAddressBtn;

    @FindBy(id = "Address_FirstName")
    WebElement Address_FirstName;

    @FindBy(id = "Address_LastName")
    WebElement Address_LastName;

    @FindBy(id = "Address_CountryId")
    WebElement Address_Country_dropdown;

    @FindBy(xpath = "//option[@value='1']")
    WebElement Address_Country_Option;

    @FindBy(id = "Address_StateProvinceId")
    WebElement Address_StateProvince_dropdown;

    @FindBy(css = "[id=\"Address_StateProvinceId\"] [value='1']")
    WebElement Address_StateProvince_Option;

    @FindBy(id = "Address_City")
    WebElement Address_City;

    @FindBy(id = "Address_Address1")
    WebElement Address_Address1;

    @FindBy(id = "Address_ZipPostalCode")
    WebElement Address_ZipPostalCode;

    @FindBy(id = "Address_PhoneNumber")
    WebElement Address_PhoneNumber;

    @FindBy(css = "[class=\"button-1 save-address-button\"]")
    WebElement AddressSaveBTN;

    @FindBy(id = "Address_Email")
    WebElement Address_Email;

    @FindBy(css = "[class=\"change-password active\"] [href=\"/customer/changepassword\"]")
    WebElement changePasswordMenuOption;

    @FindBy(id = "OldPassword")
    WebElement OldPassword_Input;

    @FindBy(id = "NewPassword")
    WebElement NewPassword_Input;

    @FindBy(id = "ConfirmNewPassword")
    WebElement ConfirmNewPassword_Input;

    @FindBy(css = "[class=\"button-1 change-password-button\"]")
    WebElement change_password_button;


    public void goToMyAccountPage(){
        isInteractable(MyAccount);
        MyAccount.click();
        waitFor(1);
        Assert.assertTrue(customerInfoPage.isDisplayed());
    }

    public void updatePersonalInfo(){
        FirstNameInputField.clear();
        type(FirstNameInputField, "Updated test");
        LastNameInputField.clear();
        type(LastNameInputField, "user");
        updateDateOfBirth();
        CompanyNameInputField.clear();
        type(CompanyNameInputField, "SQA");
        isInteractable(InfoSaveBtn);
        InfoSaveBtn.click();
        isVisible(Successbar);
        Assert.assertTrue(Successbar.getText().contains("The customer info has been updated successfully."));
    }

    public void updateAddress(){
        isVisible(AddressMenuOption);
        isInteractable(AddressMenuOption);
        AddressMenuOption.click();
        waitFor(1);
        if(driver.findElements(By.cssSelector("[class=\"no-data\"]")).size()>0){
            isInteractable(AddNewAddressBtn);
            AddNewAddressBtn.click();
            waitFor(1);
        }else {
            WebElement editAddressBtn = driver.findElement(By.cssSelector("[class=\"button-2 edit-address-button\"]"));
            editAddressBtn.click();
            waitFor(1);
        }
        Assert.assertTrue(Address_FirstName.isDisplayed());
        Address_FirstName.clear();
        type(Address_FirstName, "nopCommerce Test");
        Address_LastName.clear();
        type(Address_LastName,"user");
        Address_Email.clear();
        type(Address_Email, "test002@gmail.com");
        isInteractable(Address_Country_dropdown);
        Address_Country_dropdown.click();
        isInteractable(Address_Country_Option);
        Address_Country_Option.click();
        waitFor(1);
        isInteractable(Address_StateProvince_dropdown);
        Address_StateProvince_dropdown.click();
        Address_StateProvince_Option.click();
        Address_City.clear();
        type(Address_City,"Auburn");
        Address_Address1.clear();
        type(Address_Address1, "street 2, Auburn, US");
        Address_ZipPostalCode.clear();
        type(Address_ZipPostalCode, "34699");
        Address_PhoneNumber.clear();
        type(Address_PhoneNumber, "+19084585439");
        waitFor(1);
        isInteractable(AddressSaveBTN);
        AddressSaveBTN.click();
        waitFor(1);
    }


    public void changePassword(){
        waitFor(1);
        isInteractable(changePasswordMenuOption);
        changePasswordMenuOption.click();
        isVisible(OldPassword_Input);
        type(OldPassword_Input, validPassword);
        type(NewPassword_Input, "qwerty@321");
        type(ConfirmNewPassword_Input, "qwerty@321");
        isInteractable(change_password_button);
        change_password_button.click();
        waitFor(1);
    }

    public void updateDateOfBirth() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // select Day
        js.executeScript("document.querySelector('[name=\"DateOfBirthDay\"]').options[3].selected = true");

        // select month
        js.executeScript("document.querySelector('[name=\"DateOfBirthMonth\"]').options[3].selected = true");

        // select Year
        js.executeScript("document.querySelector('[name=\"DateOfBirthYear\"]').options[3].selected = true");
    }
}
