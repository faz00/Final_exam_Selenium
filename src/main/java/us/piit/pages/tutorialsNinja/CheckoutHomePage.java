package us.piit.pages.tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

public class CheckoutHomePage extends CommonAPI {
    Logger log = LogManager.getLogger(CheckoutHomePage.class.getName());

    public CheckoutHomePage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = " //*[@id=\"content\"]/div[3]/div[1]/div/div[1]/a/img")
    WebElement productimg;
    @FindBy(xpath = "//*[@id='button-cart']")
    WebElement addTCrtButtn;
    @FindBy(xpath = "//*[@id=\"product-product\"]/div[1]")
    WebElement successMsg;
    @FindBy(linkText = "shopping cart")
    WebElement shopgCrtLink;
    @FindBy(linkText = "Use Coupon Code")
    WebElement couponCodeBtn;
    @FindBy(xpath = "//input[@id='input-coupon']")
    WebElement inputCopnCodeField;
    @FindBy(xpath = "//input[@id='button-coupon']")
    WebElement applycopnbtn;
    @FindBy(xpath = "//*[@class='alert alert-danger alert-dismissible']")
    WebElement copnWarningMsg;
    @FindBy(xpath = "//*[@id='content']/P")
    WebElement chcktPgHdr;

    public void clkPrdctImg() {
        log.info("the product is clcked successfully");
        clickOn(productimg);
    }

    public void CkickOnAddTCrtBttn() {
        log.info("the user clickes on add to cart button ");
        clickOn(addTCrtButtn);
    }

    public boolean isSuccessMsgDisplayed() {
        log.info("the success message displayed");
        return successMsg.isDisplayed();
    }

    public void clickShpgCrtLink() {
        log.info("the shopping cart Link is clicked ");
        clickOn(shopgCrtLink);
    }


    public void clickCouponCodeBtn() {
        log.info("the coupon code button is clicked");
        clickOn(couponCodeBtn);
    }

    public void setcoupnCode(String code) {
        log.info("the coupon code entered in success");
        type(inputCopnCodeField, code);
    }

    public String getPgeURL(WebDriver driver) {
        log.info("the URL of the web page is " + driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    public void clickonCopnApply() {
        log.info("the apply coupon button is clicked successfully");
        clickOn(applycopnbtn);
    }

    public boolean isCopnErMsgDisp() {
        log.info("a warning message is displayed when entering invalid or no coupons");
        return copnWarningMsg.isDisplayed();
    }

    public boolean isCouponCodePlaceholderDisp() {
        String placeholder = inputCopnCodeField.getAttribute("placeholder");
        log.info("The coupon code functionality has placeholder: " + placeholder);
        return placeholder != null && !placeholder.isEmpty();
    }

    public boolean ischeckoutHeaderPageDisp() {
        log.info("the checkout home page header is displayed");
  return chcktPgHdr.isDisplayed();
    }
}
