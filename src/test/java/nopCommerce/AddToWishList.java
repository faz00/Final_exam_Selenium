package nopCommerce;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import us.piit.SetUp;

public class AddToWishList extends SetUp {

    @FindBy(xpath = "//div[@class=\"bar-notification success\"]")
    WebElement Successbar;

    @FindBy(xpath = "//div[@class=\"bar-notification success\"]//span[@title='Close']")
    WebElement closeSuccessBar;

    public  @FindBy(id = "Email")
    WebElement EmailInputField;

    public  @FindBy(id = "Password")
    WebElement PasswordInputField;

    public static @FindBy(id = "RememberMe")
    WebElement RememberMeCheckBox;

    public static @FindBy(xpath = "//button[@class=\"button-1 login-button\"]")
    WebElement LoginButton;

    @FindBy(css = "[class=\"ico-cart\"]")
    WebElement ShoppingCart;

    public @FindBy(xpath = "//a[@href=\"/login?returnUrl=%2F\"]")
    WebElement LoginLink;

    public @FindBy(xpath = "//div[@class=\"header-links\"]//a[@href=\"/wishlist\"]")
    WebElement WishListCart;

    @FindBy(xpath = "//ul[@class=\"top-menu notmobile\"]//a[@href=\"/computers\"]")
    WebElement ProductCategoryComputes;

    @FindBy(xpath = "//ul[@class=\"top-menu notmobile\"]//a[@href=\"/desktops\"]")
    WebElement Desktops;

    @FindBy(xpath = "(//button[@class=\"button-2 add-to-wishlist-button\"])[2]")
    WebElement WishListButton;

    @FindBy(css = "[class=\"wishlist-content\"]")
    WebElement WishListDetails;

    public @FindBy(css = "[name=\"updatecart\"][class=\"remove-btn\"]")
    WebElement RemoveProductFromCart;


    @Parameters()
    @Test
    public void AddProductToWishList(){

        // Verify that cart has 0 items before
        isVisible(ShoppingCart);
        Assert.assertTrue(ShoppingCart.getText().contains("0"));

        // Verify that wishList has 0 item before
        isVisible(WishListCart);
        Assert.assertTrue(WishListCart.getText().contains("0"));

        // Add product to WishList
        isVisible(ProductCategoryComputes);
        hoverOver(ProductCategoryComputes);
        isVisible(Desktops);
        Desktops.click();
        hoverOver(WishListButton);
        WishListButton.click();
        isVisible(Successbar);
        Assert.assertTrue(Successbar.isDisplayed());
        closeSuccessBar.click();
        waitFor(1);


        // verify that Wish List has 1 item
        Assert.assertTrue(WishListCart.getText().contains("1"));
        hoverOver(WishListCart);
        waitFor(1);
        WishListCart.click();
        waitFor(2);
        isVisible(WishListDetails);
        Assert.assertTrue(WishListDetails.isDisplayed());
        RemoveProductFromCart.click();
        waitFor(1);
        Assert.assertTrue(WishListCart.getText().contains("0"));

    }
}

