package us.piit.pages.tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

public class WishListPage extends CommonAPI {
    Logger log = LogManager.getLogger(WishListPage.class.getName());

    public WishListPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='search']")
    WebElement searchField;
    @FindBy(xpath = "//*[@id=\"search\"]/span/button")
    WebElement searchButton;
    @FindBy(css = "#input-email")
    WebElement emailField;
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement passwordField;

    @FindBy(xpath = "//input[@class='btn btn-primary']")
    WebElement loginButton;
    @FindBy(xpath = "//*[@title='Shopping Cart']")
    WebElement shopngCrticon;

    @FindBy(xpath = "//*[@id='wishlist-total']")
    WebElement wishlistIcon;

    public void SearchField(String search) {
        type(searchField, search);
        log.info("the search product enters in success");
    }
    public void SearchButton() {
        clickOn(searchButton);
        log.info("the search button clicked");
    }
    public void setEmail(String email) {
        type(emailField, email);
        log.info("the email address entered successfully in the email input field");
    }

    public void setPassword(String password) {
        type(passwordField, password);
        log.info("Entered password success");
    }

    public void clickLoginButton() {
        clickOn(loginButton);
        log.info("Clicked login button success");
    }

public void clickOnshpngCrtLnk(){
        log.info("the shopping cart link is clicked successfully");
        clickOn(shopngCrticon);
}

public void clickOnwshLstIcn(){
        log.info("the wishList icon is clicked successfully");
        clickOn(wishlistIcon);
}




}
