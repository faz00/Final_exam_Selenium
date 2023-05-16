package tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import us.piit.utility.Utility;
import us.piit.base.CommonAPI;
import us.piit.pages.tutorialsNinja.SearchProductsHomePage;
import us.piit.pages.tutorialsNinja.SearchProductsPage;

import java.util.List;
import java.util.Properties;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchProductsTest extends CommonAPI {

    Logger log = LogManager.getLogger( SearchProductsTest.class.getName());
    Properties prop = Utility.loadProperties();
    String search = Utility.decode(prop.getProperty("tutorialsninja.search"));//"Search - MacBook"
    String nonExissearch = Utility.decode(prop.getProperty("tutorialsninja.nonExisSearch"));//"Headphones"
    @Test
    public void verifyUserNavigatesToTHeSearchHomePage() {
        waitFor(30);
        SearchProductsPage searchProductsPage = new SearchProductsPage(getDriver());
        SearchProductsHomePage searchprhomePage = new SearchProductsHomePage(getDriver());

        //enter the search name and click on the search button
        searchProductsPage.SearchField(search);
        searchProductsPage.SearchButton();
        // assert that the user navigates to the ResearchHomePage
        assertTrue(searchProductsPage.isSearchResultsPageDisplayed());

    }

    @Test

    public void verifySearchWithAnExistingProduct() {
        waitFor(30);
        SearchProductsPage searchProductsPage = new SearchProductsPage(getDriver());
        SearchProductsHomePage searchprhomePage = new SearchProductsHomePage(getDriver());

        //enter the search name and click on the search button
        searchProductsPage.SearchField(search);
        searchProductsPage.SearchButton();

        // Verify that the search results page is displayed
        assertTrue(searchprhomePage.isSearchResultsPageDisplayed(), "Search results page is not displayed");

        // Verify that the search results contain at least one product
        assertTrue(searchprhomePage.areSearchResultsDisplayed(), "Search results do not contain any products");
    }
    @Test

    public void verifySearchWithANonExistingProduct() {
        SearchProductsPage searchProductsPage = new SearchProductsPage(getDriver());
        SearchProductsHomePage searchprhomePage = new SearchProductsHomePage(getDriver());

        waitFor(30);

// Search for a non existing product
        searchProductsPage.SearchField(nonExissearch);
        searchProductsPage.SearchButton();

        // Assert a non-existing product message is displayed
        assertTrue(searchProductsPage.isErrorMessageDisplayed(), "Error message is not displayed");
    }

    //verify if the user is able to sort the products
    @Test
    public void verifySortingProducts() {
        SearchProductsPage searchProductsPage = new SearchProductsPage(getDriver());
        SearchProductsHomePage searchprhomePage = new SearchProductsHomePage(getDriver());

        searchProductsPage.SearchField(search);

        assertTrue(searchprhomePage.isSearchResultsPageDisplayed(), "Search results page is not displayed");

        searchprhomePage.sortProductsByPriceDescending();

        assertTrue(searchprhomePage.verifyProductSortingByPriceDescending(), "Products are not sorted by price in descending order");
    }

    //verify the user able to select how many products to be displayed
    @Test

    public void verifyProductDisplayCount() {
        waitFor(30);
        SearchProductsPage searchProductsPage = new SearchProductsPage(getDriver());
        SearchProductsHomePage searchprhomePage = new SearchProductsHomePage(getDriver());

        // Search for products
        searchProductsPage.SearchField(search);

        // click on the search  button
        searchProductsPage.SearchButton();

        // Click on the display count dropdown
        searchprhomePage .clickOnDisplayCountDropdown();

        // Verify that the display count options are displayed
        Select select = new Select(searchprhomePage.displayCountDropdown);
        List<WebElement> options = select.getOptions();
        assertTrue(options.size() > 1, "Display count options are not displayed");

        // Select an option by visible text
        select.selectByVisibleText("100");

        // Verify that the number of displayed products matches the selected count
        assertEquals(searchprhomePage.getDisplayedProductCount(), 100, "Displayed product count doesn't match the selected count");
    }
}










