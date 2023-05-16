package us.piit.pages.nopCommerce;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

public class HomePage extends CommonAPI {
    Logger log = LogManager.getLogger(HomePage.class.getName());
    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    //locators
    @FindBy(xpath = "//span[contains(text(),'Products')]")
    WebElement mainHeader;

    @FindBy(css = "#react-burger-menu-btn")
    WebElement hamburgerMenu;

    @FindBy(css = "#logout_sidebar_link")
    WebElement logoutLink;

    //reusable methods
    public String getHeaderText(){
        String text = getElementText(mainHeader);
        log.info("user logged in success");
        return text;
    }
    public void clickOnHamburgerMenu(){
        clickOn(hamburgerMenu);
        log.info("click on hamburger menu success");
    }
    public void hoverOverOnAndClickLogoutLink(){
        hoverOverAndClickOn(logoutLink);
        log.info("click on logout link success");
    }

}
