package us.piit.pages.tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

public class ForgottenPasswordPage  extends CommonAPI {

    Logger log = LogManager.getLogger(ForgottenPasswordPage.class.getName());

    public ForgottenPasswordPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[normalize-space()='Forgotten Password']")
    WebElement forgetPasswordLink;

    public void navigateToForgotPasswordPage() {

        clickOn(forgetPasswordLink);

    }
}