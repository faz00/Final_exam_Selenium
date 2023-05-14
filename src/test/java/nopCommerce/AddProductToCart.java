package nopCommerce;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;

public class AddProductToCart extends CommonAPI {


    @FindBy(xpath = "(//button[@class=\"button-2 product-box-add-to-cart-button\"])[2]")
    WebElement AddToCartButton;

    @FindBy(xpath = "//ul[@class=\"top-menu notmobile\"]//a[@href=\"/computers\"]")
    WebElement ProductCategoryComputes;

    @FindBy(xpath = "//ul[@class=\"top-menu notmobile\"]//a[@href=\"/desktops\"]")
    WebElement Desktops;

    @FindBy(xpath = "//div[@class=\"bar-notification success\"]")
    WebElement Successbar;

    @FindBy(xpath = "//div[@class=\"bar-notification success\"]//span[@title='Close']")
    WebElement closeSuccessBar;

    @FindBy(css = "[class=\"ico-cart\"]")
    WebElement ShoppingCart;

    @FindBy(xpath = "//button[@class=\"button-1 cart-button\"]")
    WebElement GoToCartButton;

    @FindBy(css = "[id=\"shopping-cart-form\"]")
    WebElement ShoppingCartDetailedTable;

    public  @FindBy(id = "Email")
    WebElement EmailInputField;

    public  @FindBy(id = "Password")
    WebElement PasswordInputField;

    public static @FindBy(id = "RememberMe")
    WebElement RememberMeCheckBox;

    public static @FindBy(xpath = "//button[@class=\"button-1 login-button\"]")
    WebElement LoginButton;

    public @FindBy(xpath = "//a[@href=\"/login?returnUrl=%2F\"]")
    WebElement LoginLink;

    public @FindBy(css = "[name=\"updatecart\"][class=\"remove-btn\"]")
    WebElement RemoveProductFromCart;


    @Parameters({"Email", "Password"})
    @Test()
    public void AddToCartProduct(String Email, String Password){

        // Login First
        Login(Email, Password);

        isVisible(ShoppingCart);
        // Verify that cart has 0 items before
        Assert.assertTrue(ShoppingCart.getText().contains("0"));

        isVisible(ProductCategoryComputes);
        hoverOver(ProductCategoryComputes);
        isVisible(Desktops);
        Desktops.click();
        hoverOver(AddToCartButton);
        AddToCartButton.click();
        isVisible(Successbar);
        Assert.assertTrue(Successbar.isDisplayed());
        closeSuccessBar.click();
        waitFor(1);

        // verify that cart has 1 item
        Assert.assertTrue(ShoppingCart.getText().contains("1"));
        hoverOver(ShoppingCart);
        waitFor(1);
        isVisible(GoToCartButton);
        GoToCartButton.click();
        waitFor(2);
        isVisible(ShoppingCartDetailedTable);
        Assert.assertTrue(ShoppingCartDetailedTable.isDisplayed());
        RemoveProductFromCart.click();
        waitFor(1);
        Assert.assertTrue(ShoppingCart.getText().contains("0"));
    }

    public void Login(String Email, String Password){
        LoginLink.click();
        waitFor(2);
        EmailInputField.sendKeys(Email);
        PasswordInputField.sendKeys(Password);
        RememberMeCheckBox.click();
        LoginButton.click();
    }
}
