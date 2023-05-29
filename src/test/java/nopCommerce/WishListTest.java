package nopCommerce;

import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.nopCommerce.LoginPage;
import us.piit.pages.nopCommerce.WishListPage;
import us.piit.utility.Utility;

import java.util.Properties;

public class WishListTest extends CommonAPI {
    Properties prop = Utility.loadProperties();
    String ValidEmail = Utility.decode(prop.getProperty("nopCommerce.username"));
    String validPassword = Utility.decode(prop.getProperty("nopCommerce.password"));

    @Test
    public void SeeProductDetailsByClickingWishListIcon(){
        WishListPage wishListPage = new WishListPage(getDriver());

        // Verify Page title
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "nopCommerce demo store");

        //Scroll to featured product section
        wishListPage.scrollToElement(wishListPage.FeaturedProductSection);
        waitFor(1);

        // Click wish list icon
        String productTitle = wishListPage.getProductTitle();
        wishListPage.clickOnWishListIcon();
        waitFor(2);

        // Verify that product details are displayed
        Assert.assertEquals(getCurrentTitle(), actualTitle + ". " + productTitle);
        waitFor(1);
    }

    @Test
    public void AddProductToWishList(){
        WishListPage wishListPage = new WishListPage(getDriver());

        // Verify Page title
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "nopCommerce demo store");

        // Verify that wish list has 0 items before
        Assert.assertTrue(wishListPage.wishCartDetails().contains("0"));

        // Go to product page
        wishListPage.goToProductPage();

        // Add product to wish list
        wishListPage.AddProductToWishList();

        // Verify that wish list has 1 items
        Assert.assertTrue(wishListPage.wishCartDetails().contains("1"));
    }

    @Test
    public void ExploreWishList(){
        WishListPage wishListPage = new WishListPage(getDriver());

        // Verify Page title
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "nopCommerce demo store");

        // Verify that wish list has 0 items before
        Assert.assertTrue(wishListPage.wishCartDetails().contains("0"));

        // Go to product page
        wishListPage.goToProductPage();

        // Add product to wish list
        wishListPage.AddProductToWishList();

        // explore and see wishlist
        wishListPage.exploreWishlist();
    }

    @Test
    public void UpdaateWishList(){
        WishListPage wishListPage = new WishListPage(getDriver());

        // Verify Page title
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "nopCommerce demo store");

        // Verify that wish list has 0 items before
        Assert.assertTrue(wishListPage.wishCartDetails().contains("0"));

        // Go to product page
        wishListPage.goToProductPage();

        // Add product to wish list
        wishListPage.AddProductToWishList();

        // update wish list
        wishListPage.updateWishlist();
    }

    @Test
    public void AddProductToCartFromWishList(){
        WishListPage wishListPage = new WishListPage(getDriver());

        // Verify Page title
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "nopCommerce demo store");

        // Verify that wish list has 0 items before
        Assert.assertTrue(wishListPage.wishCartDetails().contains("0"));

        // Go to product page
        wishListPage.goToProductPage();

        // Add product to wish list
        wishListPage.AddProductToWishList();

        // Add product to cart from wish list
        wishListPage.add_Product_From_wishlist_to_cart();
    }

    @Test
    public void ShareWishList(){
        WishListPage wishListPage = new WishListPage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());

        // Verify Page title
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "nopCommerce demo store");

        // first login to share your wish list
        //enter  username, password, and click on login button
        loginPage.goToLoginPage();
        loginPage.enterUsername(ValidEmail);
        loginPage.enterPassword(validPassword);
        loginPage.checkRememberMebox();
        loginPage.clickOnLoginBtn();


        // Go to product page
        wishListPage.goToProductPage();

        // Add product to wish list
        wishListPage.AddProductToWishList();

        // share your wish list via email
        wishListPage.shareYourWishList();
    }

    @Test
    public void RemoveProductFromWishList(){
        WishListPage wishListPage = new WishListPage(getDriver());

        // Verify Page title
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "nopCommerce demo store");

        // Go to product page
        wishListPage.goToProductPage();

        // Add product to wish list
        wishListPage.AddProductToWishList();

        // remove product from wishlist
        wishListPage.removeProdcut();
    }
}
