package us.piit.pages.tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

public class CheckoutPage extends CommonAPI {

    Logger log = LogManager.getLogger(CheckoutPage.class.getName());

    public CheckoutPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='fa fa-share']")
    WebElement checkoutLink;
    @FindBy(xpath = "//input[@class='form-control input-lg']")
    WebElement inputSearchField;
    @FindBy(xpath = "//*[@class='input-group-btn']")
    WebElement searchBttn;


    public void clickOnCheckoutLink(){
        log.info("the checkoutLink successfully clicked");
        clickOn(checkoutLink);

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
