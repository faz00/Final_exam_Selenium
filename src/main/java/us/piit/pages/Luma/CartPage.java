package us.piit.pages.Luma;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import us.piit.base.CommonAPI;
import us.piit.pages.Luma.LoginPage;

public class CartPage extends CommonAPI {

    Logger log = LogManager.getLogger(LoginPage.class.getName());
    protected WebDriver driver;
    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "[id=\"ui-id-8\"]")
    WebElement SaleTab;

    @FindBy(xpath = "(//a[text()='Hoodies and Sweatshirts'])[1]")
    WebElement HoodiesAndSweatshirts;

    @FindBy(xpath = "(//li[@class=\"item product product-item\"])[1]")
    WebElement FirstResult;

    @FindBy(xpath = "(//button[@class=\"action tocart primary\"])[1]")
    WebElement FirstResultAddToCartButton;

    @FindBy(css= "[option-id=\"166\"]")
    WebElement ShirtSizeXS;

    @FindBy(css= "[option-id=\"168\"]")
    WebElement ShirtSizeM;

    @FindBy(css ="[id=\"option-label-color-93-item-52\"]")
    WebElement ShirtColorGray;

    @FindBy(css ="[option-label=\"Purple\"]")
    WebElement ShirtColorPurple;

    @FindBy(css = "[id=\"product-addtocart-button\"]")
    WebElement AddToCartButton;

    @FindBy(css = "[data-ui-id=\"message-success\"]")
    WebElement CartSuccessMessage;

    @FindBy(css = "[class=\"action showcart\"]")
    WebElement ShowCart;

    @FindBy(css = "[id=\"top-cart-btn-checkout\"]")
    WebElement ProceedToCheckOut;

    @FindBy(css = "[class=\"action delete\"]")
    WebElement DeleteProductFromCart;

    @FindBy(xpath = "//button[@class=\"action-primary action-accept\"]")
    WebElement okButton;

    @FindBy(xpath = "//strong[text()='You have no items in your shopping cart.']")
    WebElement NoItemInCartText;

    @FindBy(css = "[class=\"action edit\"]")
    WebElement PencinleIcon;

    @FindBy(css = "[id=\"product-updatecart-button\"]")
    WebElement UpdateCartButton;

    @FindBy(css = "[id=\"qty\"]")
    WebElement QTYinput;

    public void clickOnSaleTab(){
        Assert.assertTrue(SaleTab.isEnabled());
        SaleTab.click();
        log.info("Sale tab clicked success");
    }


    public void clickOnHoodiesAndShirts(){
        Assert.assertTrue(HoodiesAndSweatshirts.isEnabled());
        HoodiesAndSweatshirts.click();
        log.info("Hoodies & T-shirts clicked success");
    }

    public void AddProductToCart(){
        hoverOver(FirstResult);
        Assert.assertTrue(FirstResultAddToCartButton.isDisplayed());
        FirstResultAddToCartButton.click();
        waitFor(3);
        hoverOver(ShirtSizeXS);
        ShirtSizeXS.click();
        hoverOver(ShirtColorGray);
        ShirtColorGray.click();
        waitFor(1);
        AddToCartButton.click();
        waitFor(1);
        Assert.assertTrue(CartSuccessMessage.isDisplayed());
    }

    public void UpdateCart(){
        hoverOver(ShowCart);
        Assert.assertTrue(ShowCart.getText().contains("1"));
        ShowCart.click();
        Assert.assertTrue(PencinleIcon.isDisplayed());
        PencinleIcon.click();
        Assert.assertTrue(UpdateCartButton.isDisplayed());
        waitFor(1);
        hoverOver(ShirtSizeM);
        ShirtSizeM.click();
        hoverOver(ShirtColorPurple);
        ShirtColorPurple.click();
        QTYinput.clear();
        QTYinput.sendKeys("2");
        Assert.assertTrue(UpdateCartButton.isEnabled());
        UpdateCartButton.click();
        waitFor(1);
        Assert.assertTrue(CartSuccessMessage.isDisplayed());
    }

    public void deleteProductFromCart(){
        hoverOver(ShowCart);
        ShowCart.click();
        isVisible(DeleteProductFromCart);
        waitFor(1);
        DeleteProductFromCart.click();
        waitFor(1);
        okButton.click();
        waitFor(2);
        isVisible(NoItemInCartText);
        Assert.assertTrue(NoItemInCartText.isDisplayed());
    }
    public void hoverOver(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }
}
