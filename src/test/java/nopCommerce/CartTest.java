package nopCommerce;

import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.nopCommerce.CartPage;

public class CartTest extends CommonAPI {

    @Test
    public void SeeProductDetailsByClickingAddToCartButton(){
        CartPage cartPage = new CartPage(getDriver());

        // Verify Page title
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "nopCommerce demo store");

        //Scroll to featured product section
        cartPage.scrollToElement(cartPage.FeaturedProductSection);
        waitFor(1);

        // click Add to cart button
        String productTitle = cartPage.getProductTitle();
        cartPage.clickOnAddToCartBTN();
        waitFor(3);

        // verify that product details are displayed
        Assert.assertEquals(getCurrentTitle(), actualTitle + ". " + productTitle);
        waitFor(1);
    }

    @Test
    public void AddProductToCart(){
        CartPage cartPage = new CartPage(getDriver());

        // Verify Page title
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "nopCommerce demo store");

        // Verify that cart has 0 items before
        Assert.assertTrue(cartPage.shoppingCartDetails().contains("0"));

        // Go to product page
        cartPage.goToProductPage();

        // Add product to cart
        cartPage.AddProductToCart();

        // verify that cart has 1 item
        Assert.assertTrue(cartPage.VerifyThatCartHasProduct("1"));
    }

    @Test
    public void ViewCart(){
        CartPage cartPage = new CartPage(getDriver());
        // Verify Page title
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "nopCommerce demo store");

        // Go to product page
        cartPage.goToProductPage();

        // Add product to cart
        cartPage.AddProductToCart();

        // view cart
        cartPage.viewCart();
    }

    @Test
    public void SeeEstimatedShippingCost(){
        CartPage cartPage = new CartPage(getDriver());
        // Verify Page title
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "nopCommerce demo store");

        // Go to product page
        cartPage.goToProductPage();

        // Add product to cart
        cartPage.AddProductToCart();

        // view cart
        cartPage.viewCart();

        // see estimated shipping cost
        cartPage.seeEstimatedShippingCost();
    }

    @Test
    public void RemoveProductsFromCart(){
        CartPage cartPage = new CartPage(getDriver());
        // Verify Page title
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "nopCommerce demo store");

        // Go to product page
        cartPage.goToProductPage();

        // Add product to cart
        cartPage.AddProductToCart();

        // view cart
        cartPage.viewCart();

        // Remove product from cart
        cartPage.removeProductFromCart();
    }
}
