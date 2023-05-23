package us.piit.pages.luma;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import us.piit.base.CommonAPI;
import us.piit.utility.Utility;

import java.util.Properties;

public class MyAccountPage extends CommonAPI {
    Properties prop = Utility.loadProperties();
    String ValidEmail = prop.getProperty("luma.username");
    String validPassword = prop.getProperty("luma.password");
    public MyAccountPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//button[@data-action=\"customer-menu-toggle\"])[1]")
    WebElement SignOutDropDown;

    @FindBy(xpath = "(//*[@href=\"https://magento.softwaretestingboard.com/customer/account/\"])[1]")
    WebElement myAccountBtn;

    @FindBy(css = "[class=\"box box-information\"]")
    WebElement userDetailsSection;

    @FindBy(css = "[class=\"box box-newsletter\"]")
    WebElement newsLetterSection;

    @FindBy(xpath = "(//*[@class='action edit']//span[text()='Edit'])[2]")
    WebElement editUserInfoBtn;

    @FindBy(css= "[class=\"box box-newsletter\"] a")
    WebElement editNewsLetterBtn;

    @FindBy(id = "firstname")
    WebElement FirstName;

    @FindBy(id = "lastname")
    WebElement LastName;

    @FindBy(id = "change-email")
    WebElement change_email_checkBox;

    @FindBy(id = "email")
    WebElement EmailInput;

    @FindBy(id = "current-password")
    WebElement Current_PasswordInput;

    @FindBy(css = "[class=\"action save primary\"]")
    WebElement userInfoSaveBtn;

    @FindBy(css = "[data-bind=\"scope: 'messages'\"] [class=\"messages\"]")
    WebElement InfoUpdatedSuccessMessage;

    @FindBy(id = "subscription")
    WebElement subscription_Checkbox;

    @FindBy(css = "button[title=\"Save\"]")
    WebElement subscription_save_btn;

    public void exploreAccountDetails(){
        goToAccountDetailPage();
        Assert.assertTrue(userDetailsSection.getText().contains(ValidEmail));
    }

    public void goToAccountDetailPage(){
        waitFor(1);
        isInteractable(SignOutDropDown);
        SignOutDropDown.click();
        isInteractable(myAccountBtn);
        myAccountBtn.click();
        waitFor(1);
        Assert.assertTrue(userDetailsSection.isDisplayed());
    }
    public void updateUserInfo(){
        isInteractable(editUserInfoBtn);
        editUserInfoBtn.click();
        waitFor(1);
        isVisible(FirstName);
        FirstName.clear();
        type(FirstName, "Boris");
        LastName.clear();
        type(LastName, "Jamal");
        isInteractable(change_email_checkBox);
        change_email_checkBox.click();
        waitFor(1);
        isVisible(EmailInput);
        EmailInput.clear();
        type(EmailInput, ValidEmail);
        type(Current_PasswordInput, validPassword);
        waitFor(1);
        userInfoSaveBtn.click();
        waitFor(1);
        isVisible(InfoUpdatedSuccessMessage);
        Assert.assertTrue(InfoUpdatedSuccessMessage.getText().contains("You saved the account information."));
    }

    public void SubscribeNewLetter(){
        isInteractable(editNewsLetterBtn);
        editNewsLetterBtn.click();
        waitFor(1);
        handleSubscription();
        isVisible(newsLetterSection);
        Assert.assertTrue(newsLetterSection.getText().contains("You are subscribed to \"General Subscription\"."));
    }

    public void Un_SubscribeNewLetter(){
        isInteractable(editNewsLetterBtn);
        editNewsLetterBtn.click();
        waitFor(1);
        handleSubscription();
        isVisible(newsLetterSection);
        Assert.assertTrue(newsLetterSection.getText().contains("You aren't subscribed to our newsletter"));
    }
    public void handleSubscription(){
        isInteractable(subscription_Checkbox);
        subscription_Checkbox.click();
        isInteractable(subscription_save_btn);
        subscription_save_btn.click();
        waitFor(2);
    }
}
