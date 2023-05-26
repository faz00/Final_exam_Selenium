package us.piit.pages.tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

import static org.testng.Assert.assertEquals;

public class AddToCartHomePage extends CommonAPI {
    Logger log = LogManager.getLogger(AddToCartHomePage.class.getName());

    public AddToCartHomePage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = " //*[@id=\"content\"]/div[3]/div[1]/div/div[1]/a/img")
    WebElement productimg;
    @FindBy(xpath = "//*[@class='btn btn-primary']")
    WebElement continuBttn;

    @FindBy(xpath = "//*[@id='button-cart']")
    WebElement addTCrtButtn;
    @FindBy(xpath = "//*[@id=\"product-product\"]/div[1]")
    WebElement successMsg;
    @FindBy(linkText = "shopping cart")
    WebElement shopgCrtLink;
    @FindBy(xpath = "//*[@id='cart-total']")
    WebElement shopCrtBr;
    @FindBy(xpath = "//*[@class='btn btn-danger btn-xs']")
    WebElement removeIcn;
    @FindBy(xpath = "//*[@id=\"content\"]/p")
    WebElement emptyShngCrtMsg;
    @FindBy(xpath = "//i[@class='fa fa-refresh']")
    WebElement updateIcn;
    @FindBy(xpath = "//*[@class='alert alert-success alert-dismissible']")
    WebElement updateSucssMsg;
    @FindBy(xpath = "//*[@id=\"content\"]/form/div/table/tbody/tr/td[4]/div/input")
    WebElement qntInputField;
    @FindBy(linkText ="Checkout")
    WebElement checkoutBttn;

    public void clikOnCntnuebttn(){
        log.info("the continue button is clicked");
        clickOn(continuBttn);
    }

    public void clkPrdctImg(){
        log.info("the product is clcked successfully");
        clickOn(productimg);
    }
    public void CkickOnAddTCrtBttn(){
        log.info("the user clickes on add to cart button ");
        clickOn(addTCrtButtn);
    }
    public boolean isSuccessMsgDisplayed(){
        log.info("the success message displayed");
        return successMsg.isDisplayed();
    }
    public void clickShpgCrtLink(){
        log.info("the shopping cart Link is clicked ");
        clickOn(shopgCrtLink);
    }
    public boolean islinkShopCartDisp(){

        return shopgCrtLink.isDisplayed();
    }
    public void clickRmvIcn(){
        log.info("the remove icon is clicked ");
        clickOn(removeIcn);
    }
    public boolean isEmptyShpCrtmsgDisp(){
        log.info("message about empty cart shop displayed");
        return emptyShngCrtMsg.isDisplayed();
    }
    public void clickshopCrtBr(){
        log.info("the shop cart bar clicked successfully");
        clickOn(shopCrtBr);
    }
    public void clickOnUpdateIcn(){
        log.info("the update icon is clicked successfully");
        clickOn(updateIcn);
    }
    public boolean isUpdatSucssMsgDisp(){
        log.info("the update sucess message is displayed");
        return  updateSucssMsg.isDisplayed();
    }
    public String getPgeURL() {
        log.info("the user is able to see the URL of the page");
        return driver.getCurrentUrl();
    }
    public void setprdctQnt(String qnt){
        log.info("the product quantity is updated");
        type(qntInputField,qnt);
    }
    public void clickChektBttn(){
        log.info("the checkout buttn is clicked");
        clickOn(checkoutBttn);
    }
    public void verifyProductQuantityIncreasing() {
        WebElement productQuantityElement = qntInputField;
        String quantityValue = productQuantityElement.getAttribute("value");
        int quantity = Integer.parseInt(quantityValue);
        log.info("the product quantity is "+quantity);
        assertEquals(2, quantity, "The quantity of the product is not increasing when adding it multiple times.");

    }
}

