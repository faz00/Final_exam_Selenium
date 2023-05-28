package tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.tutorialsNinja.AddToCartHomePage;
import us.piit.pages.tutorialsNinja.AddToCartPage;
import us.piit.utility.Utility;

import java.time.Duration;
import java.util.Properties;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddToCartTest extends CommonAPI {

        Logger log = LogManager.getLogger(AddToCartTest.class.getName());
        Properties prop = Utility.loadProperties();
        String search = Utility.decode(prop.getProperty("tutorialsninja.search"));
        String validPassword = Utility.decode(prop.getProperty("tutorialsninja.validPassword"));

        // @Test(priority=1,groups="navigationTests")
        public void verifyUserNavigateTShoppingCartHmPge(){

        AddToCartPage adtcrtpge=new AddToCartPage(getDriver());
        AddToCartHomePage adtcrthmpge=new AddToCartHomePage(getDriver());

//enter the search item into the input search field
        adtcrtpge.setSearchField(search);
        adtcrtpge.clickSearchBtn();
        waitFor(1);
        //scroll to the product image
        JavascriptExecutor jsex= (JavascriptExecutor) driver;
        jsex.executeScript("window.scrollBy(0,400)");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //click on product image
        adtcrthmpge.clkPrdctImg();
        //scroll to the product image
        JavascriptExecutor jx= (JavascriptExecutor) driver;
        jx.executeScript("window.scrollBy(0,400)");
//click on add to cart button
        adtcrthmpge.CkickOnAddTCrtBttn();
        //click on the shoppingCartLink
        waitFor(2);
        adtcrthmpge.clickShpgCrtLink();
        waitFor(1);
        //assert the  shopping cart home page is displayed
        String expURL="https://tutorialsninja.com/demo/index.php?route=checkout/cart";
           String actURL = adtcrthmpge.getPgeURL(driver);
        assertEquals(expURL,actURL,"the URL is not the same");
    }

@Test(priority=3,groups="removeItemsTest")

        public void VerifyRemoveAnItemFromCartWhnOnePrdctAdded() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        AddToCartPage adtcrtpge=new AddToCartPage(getDriver());
        AddToCartHomePage adtcrthmpge=new AddToCartHomePage(getDriver());

//enter the search item into the input search field
        adtcrtpge.setSearchField(search);
        adtcrtpge.clickSearchBtn();
        //scroll to the product image
        JavascriptExecutor jsex= (JavascriptExecutor) driver;
        jsex.executeScript("window.scrollBy(0,400)");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //click on product image
        adtcrthmpge.clkPrdctImg();
        //scroll to the product image
        JavascriptExecutor jx= (JavascriptExecutor) driver;
        jx.executeScript("window.scrollBy(0,400)");
//click on add to cart button
        adtcrthmpge.CkickOnAddTCrtBttn();
        waitFor(2);
        //click on the shoppingCartLink
        adtcrthmpge.clickShpgCrtLink();
        //click on shopCart bar
        adtcrthmpge.clickshopCrtBr();
        //click on remove item
        adtcrthmpge.clickRmvIcn();
        //verify if apage showing that the shoping cart is empty
        assertTrue(adtcrthmpge.isEmptyShpCrtmsgDisp(),"no message displayed");

    }
    @Test(priority = 4,groups = "updateItemsTest")
        public void verifyUpdateAnItem(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        AddToCartPage adtcrtpge=new AddToCartPage(getDriver());
        AddToCartHomePage adtcrthmpge=new AddToCartHomePage(getDriver());

//enter the search item into the input search field
        adtcrtpge.setSearchField(search);
        adtcrtpge.clickSearchBtn();
        //scroll to the product image
        JavascriptExecutor jsex= (JavascriptExecutor) driver;
        jsex.executeScript("window.scrollBy(0,400)");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //click on product image
        adtcrthmpge.clkPrdctImg();
        //scroll to the product image
        JavascriptExecutor jx= (JavascriptExecutor) driver;
        jx.executeScript("window.scrollBy(0,400)");
//click on add to cart button
        adtcrthmpge.CkickOnAddTCrtBttn();
        waitFor(2);
        //click on the shoppingCartLink
        adtcrthmpge.clickShpgCrtLink();
        //click on the update icon
            waitFor(1);
        adtcrthmpge.clickOnUpdateIcn();
        //verify if a message displayed
        assertTrue(adtcrthmpge.isUpdatSucssMsgDisp(),"the update message is not displayed");

    }
        @DataProvider(name = "quantityUpdate")
        public Object[][] updatePrdctQnt() {
        return new Object[][] {
                {"-10"},
                {""}, // Empty coupon code
                {"0"},

        };
    }
    @Test(priority = 5,groups = "updatePrdctQntTest",dataProvider = "quantityUpdate")
        public void verifyPrdctUpdtQunt(String qnt) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        AddToCartPage adtcrtpge = new AddToCartPage(getDriver());
        AddToCartHomePage adtcrthmpge = new AddToCartHomePage(getDriver());

//enter the search item into the input search field
        adtcrtpge.setSearchField(search);
        adtcrtpge.clickSearchBtn();
             driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
             //scroll to the product image
        JavascriptExecutor jsex = (JavascriptExecutor) driver;
        jsex.executeScript("window.scrollBy(0,400)");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //click on product image
        adtcrthmpge.clkPrdctImg();
        //scroll to the product image
        JavascriptExecutor jx = (JavascriptExecutor) driver;
        jx.executeScript("window.scrollBy(0,400)");
//click on add to cart button
        adtcrthmpge.CkickOnAddTCrtBttn();
        waitFor(1);
        //click on the shoppingCartLink
        adtcrthmpge.clickShpgCrtLink();
        //clear the previous qntnum
             waitFor(2);
             adtcrthmpge.clearPrvsQntNum();
        //change the quantity of the product
        adtcrthmpge.setprdctQnt(qnt);
        //click on update
        adtcrthmpge.clickOnUpdateIcn();
        takeScreenshot("tutorialsNinja","updatePrdctQntWthInvldnmbrs");
//assert an error msg displayed
        waitFor(2);
        takeScreenshot("tutorialsNinja","updatePrdctQntWthInvldnmbrserrmsg");
        assertTrue(adtcrthmpge.isEmptyShpCrtmsgDisp(), "no error message displayed");
    }

        //verify adding items to cart from 'product comparison home page'
       @Test(priority=7,groups ="addPrdctsFrmFrmPrdctCmprsnHmPgetest")
        public void addPrdctsFrmPrdctCmprsnHmPge(){

        AddToCartPage adtcrtpge=new AddToCartPage(getDriver());
        AddToCartHomePage adtcrthmpge=new AddToCartHomePage(getDriver());

//enter the search item into the input search field
        adtcrtpge.setSearchField(search);
        adtcrtpge.clickSearchBtn();
        //scroll to the product image
        JavascriptExecutor jsex= (JavascriptExecutor) driver;
        jsex.executeScript("window.scrollBy(0,400)");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //click on product image
        adtcrthmpge.clkPrdctImg();
        //scroll to the product image
        JavascriptExecutor jx= (JavascriptExecutor) driver;
        jx.executeScript("window.scrollBy(0,400)");
//click on add to cart button
        adtcrthmpge.CkickOnAddTCrtBttn();
        //click on the shoppingCartLink
        waitFor(2);
        //click on the 'compare this product' icon
        adtcrthmpge.CmprPrdIcn();
        //verify a messge displayed
        assertTrue(adtcrthmpge.isCmprPrdctSccssMsgDsp(),"the compare product success message is not displayed");
        //click on compare product link
        adtcrthmpge.clickPrdctCmprsnLink();
        //scroll till the add to cart button
        JavascriptExecutor je= (JavascriptExecutor) driver;
        je.executeScript("window.scrollBy(0,400)");
        //click on add to cart button
        adtcrthmpge.clickAddToCart();
        //assert a success message displayed that the product is added successfully
        assertTrue(adtcrthmpge.isSccssAddItmMsgDsp(),"no success message is displayed");

    }




    }

