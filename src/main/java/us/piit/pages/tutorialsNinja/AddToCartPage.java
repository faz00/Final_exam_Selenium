package us.piit.pages.tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

public class AddToCartPage extends CommonAPI {

        Logger log = LogManager.getLogger(AddToCartPage.class.getName());

    public AddToCartPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }

        @FindBy(xpath = "//*[@id=\"top-links\"]/ul/li[4]/a/i")
        WebElement addToCartLk;

        @FindBy(xpath = "//input[@id='input-email']")
        WebElement inputEmail;
        @FindBy(xpath ="//input[@id='input-password']")
        WebElement inputPassword;
        @FindBy(xpath = "//input[@class='form-control input-lg']")
        WebElement inputSearchField;
        @FindBy(xpath = "//*[@class='input-group-btn']")
        WebElement searchBttn;
        public void clkshopCrtTLk() {
        log.info("the shopping cart link is clicked successfully");
        clickOn(addToCartLk);
    }

        public void setEmail(String email) {
        log.info("the email address entered in success");
        type(inputEmail, email);
    }

        public void setPassword(String password) {
        log.info("the password entered in success");
        type(inputPassword, password);
    }
        public void setSearchField(String search){
        log.info("the search item entered in success");
        type(inputSearchField,search);
    }
        public void clickSearchBtn(){
        log.info("the search button clicked");
        clickOn(searchBttn);
    }

}

