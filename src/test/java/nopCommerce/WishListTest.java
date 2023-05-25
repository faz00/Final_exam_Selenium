package nopCommerce;

import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.nopCommerce.WishListPage;

public class WishListTest extends CommonAPI {


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

}
