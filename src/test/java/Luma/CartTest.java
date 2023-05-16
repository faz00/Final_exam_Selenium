package Luma;

import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.Luma.CartPage;

public class CartTest extends CommonAPI {


    @Test
    public void AddProductsToCart(){
        CartPage cartPage = new CartPage(getDriver());

        // Verify that user is on Home page
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "Home Page");
        waitFor(3);


        // Go to product page
        cartPage.clickOnSaleTab();
        waitFor(2);
        cartPage.clickOnHoodiesAndShirts();
        waitFor(2);

        // Add product to Cart
        cartPage.AddProductToCart();
    }

    @Test
    public void UpdatCart(){
        CartPage cartPage = new CartPage(getDriver());

        // Verify that user is on Home page
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "Home Page");
        waitFor(3);


        // Go to product page
        cartPage.clickOnSaleTab();
        waitFor(2);
        cartPage.clickOnHoodiesAndShirts();
        waitFor(2);

        // Add product to Cart
        cartPage.AddProductToCart();

        // Update cart
        cartPage.UpdateCart();
    }

    @Test
    public void DeleteProductFromCart(){
        CartPage cartPage = new CartPage(getDriver());

        // Verify that user is on Home page
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "Home Page");
        waitFor(3);


        // Go to product page
        cartPage.clickOnSaleTab();
        waitFor(2);
        cartPage.clickOnHoodiesAndShirts();
        waitFor(2);

        // Add product to Cart
        cartPage.AddProductToCart();

        // Delete Product from cart
        cartPage.deleteProductFromCart();
    }

}
