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

public class CartPage extends CommonAPI {
    Logger log = LogManager.getLogger(LoginPage.class.getName());
    protected WebDriver driver;
    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//strong[text()='Featured products']")
    public WebElement FeaturedProductSection;

    @FindBy(xpath = "(//div[@class=\"buttons\"]//button[text()='Add to cart'])[2]")
    WebElement AddToCartBtnOfProduct;

    @FindBy(xpath = "//div[@data-productid=\"4\"]//a[text()='Apple MacBook Pro 13-inch']")
    WebElement ProductTitle;

    @FindBy(css = "[class=\"ico-cart\"]")
    WebElement ShoppingCart;

    @FindBy(xpath = "(//button[@class=\"button-2 product-box-add-to-cart-button\"])[2]")
    WebElement AddToCartButton;

    @FindBy(xpath = "//ul[@class=\"top-menu notmobile\"]//a[@href=\"/computers\"]")
    WebElement ProductCategoryComputers;

    @FindBy(xpath = "//ul[@class=\"top-menu notmobile\"]//a[@href=\"/desktops\"]")
    WebElement Desktops;

    @FindBy(xpath = "//div[@class=\"bar-notification success\"]")
    WebElement Successbar;

    @FindBy(xpath = "//div[@class=\"bar-notification success\"]//span[@title='Close']")
    WebElement closeSuccessBar;

    @FindBy(xpath = "//button[@class=\"button-1 cart-button\"]")
    WebElement GoToCartButton;

    @FindBy(css = "[id=\"shopping-cart-form\"]")
    WebElement ShoppingCartDetailedTable;

    @FindBy(css = "[name=\"updatecart\"][class=\"remove-btn\"]")
    WebElement RemoveProductFromCart;



    public void clickOnAddToCartBTN(){
        Assert.assertTrue(AddToCartBtnOfProduct.isDisplayed());
        AddToCartBtnOfProduct.click();
        log.info("Add to cart button clicked success");
    }

    public String getProductTitle(){
        Assert.assertTrue(ProductTitle.isDisplayed());
        return ProductTitle.getText();
    }
    public void scrollToElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})", element);
        waitFor(1);
    }

    public String shoppingCartDetails(){
        waitFor(1);
        Assert.assertTrue(ShoppingCart.isDisplayed());
        return ShoppingCart.getText();
    }

    public void goToProductPage(){
        hoverOver(ProductCategoryComputers);
        Desktops.isDisplayed();
        Desktops.click();
        waitFor(1);
        Assert.assertTrue(AddToCartButton.isDisplayed());
    }

    public void AddProductToCart(){
        Assert.assertTrue(AddToCartButton.isDisplayed());
        AddToCartButton.click();
        waitFor(1);
        Assert.assertTrue(Successbar.isDisplayed());
        waitFor(1);
        closeSuccessBar.click();
        waitFor(1);
    }

    public boolean VerifyThatCartHasProduct(String items){
        return ShoppingCart.getText().contains(items);
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

