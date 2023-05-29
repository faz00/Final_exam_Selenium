package tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.tutorialsNinja.WishListHomePage;
import us.piit.pages.tutorialsNinja.WishListPage;
import us.piit.utility.Utility;

import java.time.Duration;
import java.util.Properties;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WishListTest extends CommonAPI {
    Logger log = LogManager.getLogger(WishListTest.class.getName());
    Properties prop = Utility.loadProperties();
    String search = Utility.decode(prop.getProperty("tutorialsninja.search"));//"Search - MacBook"
    String validEmail = Utility.decode(prop.getProperty("tutorialsninja.validEmail"));
    String validPassword = Utility.decode(prop.getProperty("tutorialsninja.validPassword"));


    @Test(priority=2,groups="addItmsToWishListPageTest")
    public void addItmsTWhshLstWthtLogin(){

    WishListPage  WhshLstPge=new WishListPage(getDriver());

    WishListHomePage WhshLstHmPge=new WishListHomePage(getDriver());

    // enter any existing product into the search field area and click on the search button
    WhshLstPge.SearchField(search);

    WhshLstPge.SearchButton();

    //Scroll down into the product image and click on it
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,400)");

    //click on the product image
        WhshLstHmPge.clkPrdctImg();

        //click on the wishlistButton
        WhshLstHmPge.clkWhshLstBttn();

        waitFor(1);
        //verify a message displayed state that the user has to login to save the item
        assertTrue(WhshLstHmPge.isAlrtMsgDsp(),"no alert message is displayed");

    }










}
