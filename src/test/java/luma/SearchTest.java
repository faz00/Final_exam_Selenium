package luma;

import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.luma.SearchPage;

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

    @Test
    public void sort_Products_by_ProductName(){
        SearchPage searchPage = new SearchPage(getDriver());

        // Verify that user is on Home page
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "Home Page");
        waitFor(1);

        // Search Products
        searchPage.enterProduct("t shirt");
        searchPage.clickOnSearchIcon();
        searchPage.verifyThatSearchResults("t shirt");

        // Sort products
        searchPage.SortProductsBy("Product Name");

        // verify that products are sorted
        Assert.assertTrue(searchPage.VerifyProductsSortedBy("Product Name"));
        searchPage.scrollFullPageToSeeAllProducts();
    }

    @Test
    public void sort_Products_by_Price(){
        SearchPage searchPage = new SearchPage(getDriver());

        // Verify that user is on Home page
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "Home Page");
        waitFor(1);

        // Search Products
        searchPage.enterProduct("t shirt");
        searchPage.clickOnSearchIcon();
        searchPage.verifyThatSearchResults("t shirt");

        // Sort products
        searchPage.SortProductsBy("Price");

        // verify that products are sorted
        Assert.assertTrue(searchPage.VerifyProductsSortedBy("Price"));
        searchPage.scrollFullPageToSeeAllProducts();
    }

    @Test
    public void sort_Products_by_Relevance(){
        SearchPage searchPage = new SearchPage(getDriver());

        // Verify that user is on Home page
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "Home Page");
        waitFor(1);

        // Search Products
        searchPage.enterProduct("t shirt");
        searchPage.clickOnSearchIcon();
        searchPage.verifyThatSearchResults("t shirt");

        // Sort products
        searchPage.SortProductsBy("Relevance");

        // verify that products are sorted
        Assert.assertTrue(searchPage.VerifyProductsSortedBy("Relevance"));
        searchPage.scrollFullPageToSeeAllProducts();
    }
}
