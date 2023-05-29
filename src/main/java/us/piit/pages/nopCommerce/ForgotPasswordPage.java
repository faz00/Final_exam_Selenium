package us.piit.pages.nopCommerce;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import us.piit.base.CommonAPI;
import us.piit.utility.Utility;

import java.util.Properties;

public class ForgotPasswordPage extends CommonAPI {
    Logger log = LogManager.getLogger(LoginPage.class.getName());
    @FindBy(xpath = "//a[@href=\"/passwordrecovery\"]")
    WebElement ForgotPasswordButton;
    @FindBy(id = "Email")
    WebElement EmailInputField;
    @FindBy(name = "send-email")
    WebElement RecoverButton;
    @FindBy(xpath = "//div[@class=\"bar-notification success\"]")
    WebElement EmailSentSuccessMessage;
    @FindBy(css = "[id=\"bar-notification\"]")
    WebElement EmailNotFoundError;


    Properties prop = Utility.loadProperties();
    String ValidEmail = Utility.decode(prop.getProperty("nopCommerce.username"));
    public ForgotPasswordPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void resetPassword(String Case) {
        switch (Case) {
            case "invalid email":
                ForgotPasswordButton.click();
                isVisible(EmailInputField);
                EmailInputField.sendKeys("invalidemail@gmail.com");
                RecoverButton.click();
                waitFor(3);
                isVisible(EmailNotFoundError);
                Assert.assertTrue(EmailNotFoundError.isDisplayed());
                Assert.assertEquals(EmailNotFoundError.getText(), "Email not found");
            case "valid email":
                ForgotPasswordButton.click();
                isVisible(EmailInputField);
                EmailInputField.sendKeys(ValidEmail);
                RecoverButton.click();
                waitFor(3);
                isVisible(EmailSentSuccessMessage);
                Assert.assertTrue(EmailSentSuccessMessage.isDisplayed());
                Assert.assertEquals(EmailSentSuccessMessage.getText(), "Email with instructions has been sent to you.");
        }
    }
}
