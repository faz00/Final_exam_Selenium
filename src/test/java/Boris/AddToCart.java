package Boris;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.SetUp;

public class AddToCart extends SetUp {

    @FindBy(css = "[id=\"ui-id-8\"]")
    WebElement SaleTab;

    @FindBy(xpath = "(//a[text()='Hoodies and Sweatshirts'])[1]")
    WebElement HoodiesAndSweatshirts;

    @FindBy(xpath = "(//li[@class=\"item product product-item\"])[1]")
    WebElement FirstResult;

    @FindBy(xpath = "(//button[@class=\"action tocart primary\"])[1]")
    WebElement FirstResultAddToCartButton;

    @FindBy(css= "[option-id=\"166\"]")
    WebElement ShirtSize;

    @FindBy(css ="[id=\"option-label-color-93-item-52\"]")
    WebElement ShirtColor;

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


    @Test
    public void AddProductsToCart(){
        // Verify that user is on Home page
        Assert.assertEquals(getCurrentTitle(), "Home Page");

        // click on Sale tab and select Hoodies and SweatShirt
        SaleTab.click();
        isVisible(HoodiesAndSweatshirts);
        HoodiesAndSweatshirts.click();

        // Add a product to cart
        hoverOver(FirstResult);
        isVisible(FirstResultAddToCartButton);
        FirstResultAddToCartButton.click();
        waitFor(3);
        ShirtSize.click();
        ShirtColor.click();
        waitFor(1);
        AddToCartButton.click();
        waitFor(1);
        Assert.assertTrue(CartSuccessMessage.isDisplayed());

        // click on cart and delete added prodcut
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
}
