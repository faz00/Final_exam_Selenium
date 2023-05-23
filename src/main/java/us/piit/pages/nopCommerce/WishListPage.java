package us.piit.pages.nopCommerce;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
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
    WebElement WishListDetailsTable;

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

    @FindBy(css = "[class=\"quantity\"] input")
    WebElement QTYfield;

    @FindBy(css = "[class=\"product-unit-price\"]")
    WebElement UnitPrice;

    @FindBy(css = "[class=\"product-subtotal\"]")
    WebElement TotalPrice;

    @FindBy(css = "[id=\"updatecart\"]")
    WebElement updateWishListBTn;

    @FindBy(css = "[name=\"addtocart\"]")
    WebElement productCheckBox;

    @FindBy(css = "[name=\"addtocartbutton\"]")
    WebElement AddToCartBtn;

    @FindBy(css = "[class=\"button-2 email-a-friend-wishlist-button\"]")
    WebElement Email_a_FriendBTn;

    @FindBy(css = "[class=\"page email-a-friend-page\"]")
    WebElement Email_a_Friend_Form;

    @FindBy(id = "FriendEmail")
    WebElement FriendEmailInput;

    @FindBy(id = "YourEmailAddress")
    WebElement YourEmailInput;

    @FindBy(id = "PersonalMessage")
    WebElement PersonalMessage;

    @FindBy(name = "send-email")
    WebElement SendEmailBtn;

    @FindBy(xpath = "//*[contains(text(), 'Your message has been sent')]")
    WebElement emailSentSuccess;


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

    public void exploreWishlist(){
        Assert.assertTrue(wishCartDetails().contains("1"));
        waitFor(1);
        WishListCart.click();
        waitForElementToBeVisible(WishListDetailsTable);
        isInteractable(WishListDetailsTable);
        Assert.assertTrue(WishListDetailsTable.isDisplayed());
        waitFor(1);
    }

    public void updateWishlist(){
        Assert.assertTrue(wishCartDetails().contains("1"));
        waitFor(1);
        WishListCart.click();
        waitForElementToBeVisible(WishListDetailsTable);
        isVisible(QTYfield);

        // fetch unit price
        String unitPriceWithSymbols = UnitPrice.getText();
        Double unitPrice = extractNumericPrice(unitPriceWithSymbols);
        log.info("Unit Price of Product" + unitPrice);

        QTYfield.clear();
        QTYfield.sendKeys("10");
        updateWishListBTn.click();
        waitFor(1);

        // fetch updated total price
        String TotalPriceWithSymbols = TotalPrice.getText();
        Double totalPrice = extractNumericPrice(TotalPriceWithSymbols);
        log.info("total Price of Product" + totalPrice);

        Assert.assertEquals(totalPrice, unitPrice*10);
        waitFor(1);
    }

    public void add_Product_From_wishlist_to_cart(){
        Assert.assertTrue(wishCartDetails().contains("1"));
        WishListCart.click();
        waitForElementToBeVisible(WishListDetailsTable);
        isVisible(WishListDetailsTable);
        productCheckBox.click();
        checkCheckBoxIsCh(productCheckBox);
        AddToCartBtn.click();
        waitFor(1);
        Assert.assertTrue(ShoppingCart.getText().contains("1"));
        Assert.assertTrue(driver.findElement(By.cssSelector("[class=\"page shopping-cart-page\"]")).isDisplayed());
        waitFor(1);
    }

    public void shareYourWishList(){
        WishListCart.click();
        waitForElementToBeVisible(WishListDetailsTable);
        isVisible(WishListDetailsTable);
        Email_a_FriendBTn.click();
        waitForElementToBeVisible(Email_a_Friend_Form);
        waitFor(1);
        FriendEmailInput.sendKeys("friend@gmail.com");
        PersonalMessage.sendKeys("This is test message");
        waitFor(1);
        SendEmailBtn.click();
        waitFor(1);
        Assert.assertTrue(emailSentSuccess.isDisplayed());
        Assert.assertTrue(emailSentSuccess.getText().contains("Your message has been sent"));
    }

    public void removeProdcut(){
        WishListCart.click();
        waitForElementToBeVisible(WishListDetailsTable);
        RemoveProductFromCart.click();
        waitFor(1);
        Assert.assertTrue(WishListCart.getText().contains("0"));
    }
}
