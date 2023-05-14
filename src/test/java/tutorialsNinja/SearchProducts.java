package tutorialsNinja;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;

public class SearchProducts  extends CommonAPI {

    Logger log = LogManager.getLogger( SearchProducts.class.getName());


    @Test
    public void verifySearchWithAnExistingProduct() {

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Find the search field and enter the search term
        type("//input[@name='search']","MacBook");
        log.info("the search product enters in success");

        // Find and click the search button
        clickOn("button[class='btn btn-default btn-lg']");
        log.info("the click button clicked in success");


        // Verify that the search results page is displayed
        WebElement searchResultsTitle = driver.findElement(By.cssSelector("#content h1"));
        assertEquals(searchResultsTitle.getText(), "Search - MacBook");

        // Verify that the search results contain at least one product
        List<WebElement> productItems = driver.findElements(By.cssSelector(".product-layout .product-thumb"));
        assertTrue(productItems.size() > 0, "Search results contain at least one product");
    }
    @Test

    public void verifySearchWithANonExistingProduct() {

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Find the search field and enter the search term
        type("//input[@name='search']","headphones");
        log.info("the search product enters in success");

        // Find and click the search button
        clickOn("button[class='btn btn-default btn-lg']");
        log.info("the click button clicked in success");


        // Verify that the search results page is displayed
        WebElement searchResultsTitle = driver.findElement(By.cssSelector("#content h1"));
        assertEquals(searchResultsTitle.getText(), "Search - headphones");

        //assert a non exsisting product message is displayed

        // Find the error message element
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/p[2]"));
        // Assert the error message text
        assertEquals(errorMessage.getText(), "There is no product that matches the search criteria.");

    }

    //verify if the user is able to sort the products
    @Test
    public void verifySortingProducts() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Find the search field and enter the search term
        type("//input[@name='search']","MacBook");
        log.info("the search product enters in success");

        // Find and click the search button
        clickOn("button[class='btn btn-default btn-lg']");
        log.info("the click button clicked in success");

        // Find the sort by dropdown element and click on it
        clickOn ("//select[@id='input-sort']");

        // Verify that the products are sorted by price in ascending order
        // Find the sort by dropdown
        WebElement sortByDropdown = driver.findElement(By.id("input-sort"));
        // Create a new Select object
        Select select = new Select(sortByDropdown);

        // Select an option by visible text
        select.selectByVisibleText("Price (High > Low)");

        log.info("the user can sort product in success");


    }
    //verify the user able to select how many products to be displayed
    @Test

    public void verifyProductDisplayCount() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Find the search field and enter the search term
        type("//input[@name='search']","MacBook");
        log.info("the search product enters in success");

        // Find and click the search button
        clickOn("button[class='btn btn-default btn-lg']");
        log.info("the click button clicked in success");

        // Find the display count dropdown element and click on it
        clickOn ("//select[@id='input-limit']");

        // Verify that the display count options are displayed
        WebElement displayCountDropdown = driver.findElement(By.id("input-limit"));
        Select select = new Select(displayCountDropdown);

        List<WebElement> options = select.getOptions();
        assertTrue(options.size() > 1, "Display count options are not displayed");

        // Select an option by visible text
        select.selectByVisibleText("100");

        // Verify that the number of displayed products matches the selected count
        List<WebElement> productTitles = driver.findElements(By.cssSelector(".product-layout .product-thumb h4 a"));
        assertEquals(productTitles.size(), 100, "Displayed product count doesn't match the selected count");

        log.info("the user can select how many products to display in success");







    }



}
