package us.piit.pages.tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

public class RegisterHomePage extends CommonAPI {

    Logger log = LogManager.getLogger(RegisterHomePage.class.getName());
    public RegisterHomePage(WebDriver driver){

        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//*[@id=\"content\"]/h1")
    WebElement registerHomePageTitle;

    public boolean isRegisterHomePageTitleDisplayed() {
log.info("the user is able to see the title of the registerHomePage");
        return registerHomePageTitle.isDisplayed() ;
    }

}







