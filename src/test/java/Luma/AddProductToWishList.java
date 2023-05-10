package Luma;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import us.piit.SetUp;

public class AddProductToWishList extends SetUp {


    @FindBy(xpath = "(//li[@class='authorization-link']//a)[1]")
    WebElement SignInLink;

    @FindBy(id = "email")
    WebElement EmailInput;

    @FindBy(id = "pass")
    WebElement PasswordInput;

    @FindBy(xpath = "//button[@class=\"action login primary\"]")
    WebElement SignInButton;

    @FindBy(xpath = "(//li[@class=\"greet welcome\"])[1]")
    WebElement WelcomeGreetMessage;

    @FindBy(xpath = "(//a[@title=\"Add to Wish List\"])[1]")
    WebElement WishListIcon;
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

    @FindBy(css = "[id=\"ui-id-8\"]")
    WebElement SaleTab;

    @FindBy(css = "[title=\"Remove This Item\"]")
    WebElement RemoveWhishListProduct;

    @FindBy(xpath = "//span[text()='You have no items in your wish list.']")
    WebElement NoItemInWishList;




    @Parameters({"Email", "Password"})
    @Test
    public void AddProductToWishList(String Email, String Password){

        // Login First
        Login(Email, Password);

        // Add Product to Wish List
        SaleTab.click();
        isVisible(HoodiesAndSweatshirts);
        HoodiesAndSweatshirts.click();

        // Add a product to wishlist
        hoverOver(FirstResult);
        isVisible(WishListIcon);
        WishListIcon.click();
        waitFor(3);

        // Remove product from wish list
        hoverOver(RemoveWhishListProduct);
        isVisible(RemoveWhishListProduct);
        RemoveWhishListProduct.click();
        waitFor(2);
        isVisible(NoItemInWishList);
        Assert.assertTrue(NoItemInWishList.isDisplayed());

    }






    public void Login(String Email, String Password){
        // Click on SignIn button
        SignInLink.click();

        //Enter email and password to login
        EmailInput.sendKeys(Email);
        PasswordInput.sendKeys(Password);
        SignInButton.click();
        waitFor(2);
        Assert.assertTrue(WelcomeGreetMessage.getText().contains("Welcome"));
    }
}
