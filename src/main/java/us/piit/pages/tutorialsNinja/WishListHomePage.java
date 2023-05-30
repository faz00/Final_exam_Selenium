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
    @FindBy(linkText = "Continue")
    WebElement continueBtn;
   @FindBy(xpath = "//*[@class='alert alert-success alert-dismissible']")
    WebElement WshLstScssMsg;
@FindBy(xpath = "//img[@title='MacBook']")
WebElement imag;

@FindBy(xpath = "//*[@class='col-sm-9']/h2")
WebElement myWshListHeadr;
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
 public void clickOnCntnBtn(){
        log.info("the continue button is clicked sucessfully");
        clickOn(continueBtn);
 }
    public boolean isWshLstScssMsgDsp(){
        log.info("the wishList success message is displayed");
        return WshLstScssMsg.isDisplayed();
    }
    public void clickOntheImg(){
       log.info("the image clicked in success");
       clickOn(imag);
    }
    public boolean isWshLstHdrPgTtlDsp( WebDriver driver){
       log.info("the header page title is displayed"+driver.getTitle());
       return myWshListHeadr.isDisplayed();
    }
}
