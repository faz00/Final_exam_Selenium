package us.piit.pages.nopCommerce;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import us.piit.base.CommonAPI;

public class WishListPage extends CommonAPI {

    Logger log = LogManager.getLogger(LoginPage.class.getName());
    protected WebDriver driver;
    public WishListPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//div[@class=\"bar-notification success\"]")
    WebElement Successbar;

    @FindBy(xpath = "//div[@class=\"bar-notification success\"]//span[@title='Close']")
    WebElement closeSuccessBar;

    @FindBy(id = "Email")
    WebElement EmailInputField;

    @FindBy(id = "Password")
    WebElement PasswordInputField;

    @FindBy(id = "RememberMe")
    WebElement RememberMeCheckBox;

    @FindBy(xpath = "//button[@class=\"button-1 login-button\"]")
    WebElement LoginButton;

    @FindBy(css = "[class=\"ico-cart\"]")
    WebElement ShoppingCart;

    @FindBy(xpath = "//a[@href=\"/login?returnUrl=%2F\"]")
    WebElement LoginLink;

    @FindBy(xpath = "//div[@class=\"header-links\"]//a[@href=\"/wishlist\"]")
    WebElement WishListCart;

    @FindBy(xpath = "//ul[@class=\"top-menu notmobile\"]//a[@href=\"/computers\"]")
    WebElement ProductCategoryComputes;

    @FindBy(xpath = "//ul[@class=\"top-menu notmobile\"]//a[@href=\"/desktops\"]")
    WebElement Desktops;

    @FindBy(xpath = "(//button[@class=\"button-2 add-to-wishlist-button\"])[2]")
    WebElement WishListButton;

    @FindBy(css = "[class=\"wishlist-content\"]")
    WebElement WishListDetails;

    @FindBy(css = "[name=\"updatecart\"][class=\"remove-btn\"]")
    WebElement RemoveProductFromCart;

    @FindBy(xpath = "(//div[@class=\"buttons\"]//button[text()='Add to wishlist'])[2]")
    WebElement WishListIcon;

    @FindBy(xpath = "//strong[text()='Featured products']")
    public WebElement FeaturedProductSection;

    @FindBy(xpath = "//div[@data-productid=\"4\"]//a[text()='Apple MacBook Pro 13-inch']")
    WebElement ProductTitle;

    @FindBy(xpath = "(//button[@class=\"button-2 product-box-add-to-cart-button\"])[2]")
    WebElement AddToCartButton;

    @FindBy(xpath = "//ul[@class=\"top-menu notmobile\"]//a[@href=\"/computers\"]")
    WebElement ProductCategoryComputers;

    public String getProductTitle(){
        Assert.assertTrue(ProductTitle.isDisplayed());
        return ProductTitle.getText();
    }

    public void clickOnWishListIcon(){
        Assert.assertTrue(WishListIcon.isDisplayed());
        WishListIcon.click();
        log.info("Wish list icon clicked success");
    }

    public void scrollToElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})", element);
        waitFor(1);
    }

    public String wishCartDetails(){
        waitFor(1);
        Assert.assertTrue(WishListCart.isDisplayed());
        log.info(WishListCart.getText());
        return WishListCart.getText();
    }

    public void goToProductPage(){
        hoverOver(ProductCategoryComputers);
        Desktops.isDisplayed();
        Desktops.click();
        waitFor(1);
        Assert.assertTrue(WishListButton.isDisplayed());
    }

    public void AddProductToWishList(){
        Assert.assertTrue(WishListButton.isDisplayed());
        WishListButton.click();
        waitFor(1);
        Assert.assertTrue(Successbar.isDisplayed());
        waitFor(1);
        closeSuccessBar.click();
        waitFor(1);
    }

    public void hoverOver(WebElement locator){
        Actions actions=new Actions(driver);
        try {
            actions.moveToElement(locator).build().perform();
        }catch (Exception e){
            actions.moveToElement(locator).build().perform();

        }
    }
}
