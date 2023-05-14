package nopCommerce;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;

public class ForgotPassword extends CommonAPI {

    public @FindBy(xpath = "//a[@href=\"/login?returnUrl=%2F\"]")
    WebElement LoginLink;

    public @FindBy(xpath = "//a[@href=\"/passwordrecovery\"]")
    WebElement ForgotPasswordButton;

    public  @FindBy(id = "Email")
    WebElement EmailInputField;

    public  @FindBy(name = "send-email")
    WebElement RecoverButton;

    public @FindBy(xpath = "//div[@class=\"bar-notification success\"]")
    WebElement EmailSentSuccessMessage;

    @Parameters({"Email"})
    @Test
    public void RecoverPassword(String Email){

        isVisible(LoginLink);
        LoginLink.click();

        isVisible(ForgotPasswordButton);
        Assert.assertTrue(ForgotPasswordButton.isDisplayed());
        ForgotPasswordButton.click();

        isVisible(EmailInputField);
        EmailInputField.sendKeys(Email);
        RecoverButton.click();
        waitFor(3);
        isVisible(EmailSentSuccessMessage);
        Assert.assertTrue(EmailSentSuccessMessage.isDisplayed());
        Assert.assertEquals(EmailSentSuccessMessage.getText(), "Email with instructions has been sent to you.");
    }
}
