package luma;

import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.Luma.SearchPage;

public class SearchTest extends CommonAPI {


    @Test
    public void SearchProduct(){
        SearchPage searchPage = new SearchPage(getDriver());

        // Verify that user is on Home page
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "Home Page");
        waitFor(3);

        // Search Products
        searchPage.enterProduct("t shirt");
        searchPage.clickOnSearchIcon();
        searchPage.verifyThatSearchResults("t shirt");
        searchPage.scrollFullPageToSeeAllProducts();
    }
    
    @Test
    public void SortSearchedProduct(){
        SearchPage searchPage = new SearchPage(getDriver());

        // Verify that user is on Home page
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "Home Page");
        waitFor(1);

        // Search Products
        searchPage.enterProduct("t shirt");
        searchPage.clickOnSearchIcon();
        searchPage.verifyThatSearchResults("t shirt");

        // store first product text for later comparison
        String UnsortedProductName = searchPage.FirtProductNameText();

        // Sort products
        searchPage.SortProducts();

        // verify that products are sorted
        Assert.assertTrue(searchPage.VerifyProductsSorted());
        searchPage.scrollFullPageToSeeAllProducts();
    }


    @Test
    public void SeeProductsByCategory(){
        SearchPage searchPage = new SearchPage(getDriver());

        // Verify that user is on Home page
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "Home Page");
        waitFor(1);

        // Select Jackets from Women Tab
        searchPage.SelectJackets();

        // verify that relavent products are displayed
        Assert.assertTrue(searchPage.relevantProductsDisplayed());
    }


    @Test
    public void SeeLatestProductAndNews(){
        SearchPage searchPage = new SearchPage(getDriver());

        // Verify that user is on Home page
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "Home Page");
        waitFor(1);

        // click on Whats new from nav bar
        searchPage.clickOnWhatsNewTab();

        // See Latest products
        searchPage.SeeLatestProduct();
    }
}
