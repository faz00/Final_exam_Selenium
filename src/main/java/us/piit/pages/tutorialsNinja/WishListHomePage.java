package us.piit.pages.tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

public class WishListHomePage extends CommonAPI {

    Logger log = LogManager.getLogger(WishListHomePage.class.getName());

    public WishListHomePage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = " //*[@id=\"content\"]/div[3]/div[1]/div/div[1]/a/img")
    WebElement productimg;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div[2]/div[1]/button[1]/i")
    WebElement addTWhshLstBttn;
    @FindBy(xpath = "//*[@class='alert alert-success alert-dismissible']")
    WebElement alertMsg;
    @FindBy(xpath = "//*[@id='wishlist-total']")
    WebElement wshlstOptn;
    @FindBy(xpath = "//*[@id=\"content\"]/p")
    WebElement emptyWshLst;
    @FindBy(xpath = "/html[1]/body[1]/div[2]/div[1]/div[1]/h2[1]")
    WebElement wshLstHdrPgTtl;
    public void clkPrdctImg() {
        log.info("the product is clcked successfully");
        clickOn(productimg);
    }
   public void clkWhshLstBttn(){
        log.info("the wish list button is clicked successfully");
        clickOn(addTWhshLstBttn);
   }
public boolean isAlrtMsgDsp(){
        log.info("an alert message displayed when non registred user add items to wish list");
  return alertMsg.isDisplayed();

    }
public void clkOnWshLstOptn(){
        log.info("the user clickes on the wishList option successfully");
        clickOn(wshlstOptn);
}
public boolean isWshLstEmpty(){
        log.info("the wishList is empty");
        return emptyWshLst.isDisplayed();
}
 public String getURL(WebDriver driver){
        log.info("the url of the page is displayed");
        return driver.getCurrentUrl();
 }
 public boolean isHdrPgeTtlDsp(){
        log.info("the header page title is displayed");
        return wshLstHdrPgTtl.isDisplayed();
 }
}
