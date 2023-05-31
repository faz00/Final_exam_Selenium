package tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.tutorialsNinja.CheckoutHomePage;
import us.piit.pages.tutorialsNinja.CheckoutPage;
import us.piit.utility.Utility;

import java.time.Duration;
import java.util.Properties;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckoutTest extends CommonAPI {


    Logger log = LogManager.getLogger(CheckoutTest.class.getName());
    Properties prop = Utility.loadProperties();
    String CouponCode = Utility.decode(prop.getProperty("tutorialsninja.CouponCode"));
    String search = Utility.decode(prop.getProperty("tutorialsninja.search"));

    @Test(priority=1,groups={"navigationTest"})
    public void verfyUsrNvgtChcktHmPge(){

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));

        CheckoutPage checkPage = new CheckoutPage(getDriver());
        CheckoutHomePage checkHmPage=new CheckoutHomePage(getDriver());
// Click on the checkout link
        checkPage.clickOnCheckoutLink();

        //verify the checkout home page url is displayed
        String expURL="https://tutorialsninja.com/demo/index.php?route=checkout/cart";
      String actURL=checkHmPage.getPgeURL(driver);
      assertEquals(actURL,expURL,"they are not the same");

    }

    @Test(priority = 2, groups = {"navigationTest"})
    public void verifyUserNavigateToCheckoutHomePgeWthNoPrdcts() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));

        CheckoutPage checkPage = new CheckoutPage(getDriver());
        CheckoutHomePage checkHmPage=new CheckoutHomePage(getDriver());
// Click on the checkout link
        checkPage.clickOnCheckoutLink();
        //assert that the user navigates to an empty shopping cart page instead of the actual checkout page
        assertTrue(checkHmPage.ischeckoutHeaderPageDisp(), "the checkoutHeaderPage is not displayed");
       // checkHmPage.getCheckoutPgemessage();
    }

    @DataProvider(name = "couponCodes")
    public Object[][] provideCouponCodes() {
        return new Object[][]{
                {"xyz123"},
                {""} // Empty coupon code
        };
    }

    //Verify without entering any fields in the Billing Section of the Checkout Page
    @Test(priority = 3, groups = "couponFonctionalityTest", dataProvider = "couponCodes")
    public void verifyCouponfonctionality(String CouponCode) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));

        CheckoutPage checkPage = new CheckoutPage(getDriver());
        CheckoutHomePage checkHmPage = new CheckoutHomePage(getDriver());
////enter the search item into the input search field
        checkPage.setSearchField(search);
        checkPage.clickSearchBtn();
//        //scroll to the product image
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        //click on product image
        checkHmPage.clkPrdctImg();
//        //scroll to the product image
        JavascriptExecutor jx = (JavascriptExecutor) driver;
        jx.executeScript("window.scrollBy(0,400)");
//click on add to cart button
        checkHmPage.CkickOnAddTCrtBttn();
//        //click on the shoppingCartLink
        waitFor(2);
        checkHmPage.clickShpgCrtLink();
        //   //scroll to the product image
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("window.scrollBy(0,400)");
        waitFor(1);
// click on the 'use coupon code button'
        checkHmPage.clickCouponCodeBtn();
        //enter the coupon code into the input field
        checkHmPage.setcoupnCode(CouponCode);
        //click on 'apply coupon' button
        checkHmPage.clickonCopnApply();
//verify if an error message get displayed
        assertTrue(checkHmPage.isCopnErMsgDisp(), "no warning message got displayed");

    }

    //verify the coupon fonctionality havng the placeholder
    @Test(priority = 4, groups = "PlaceHolderTest")
    public void verifycouponFieldPlaceHolder() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));

        CheckoutPage checkPage = new CheckoutPage(getDriver());
        CheckoutHomePage checkHmPage = new CheckoutHomePage(getDriver());
////enter the search item into the input search field
        checkPage.setSearchField(search);
        checkPage.clickSearchBtn();
//        //scroll to the product image
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        //click on product image
        checkHmPage.clkPrdctImg();
//        //scroll to the product image
        JavascriptExecutor jx = (JavascriptExecutor) driver;
        jx.executeScript("window.scrollBy(0,400)");
//click on add to cart button
        checkHmPage.CkickOnAddTCrtBttn();
//        //click on the shoppingCartLink
        waitFor(2);
        checkHmPage.clickShpgCrtLink();
        //   //scroll to the product image
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("window.scrollBy(0,400)");
        waitFor(1);
// click on the 'use coupon code button'
        checkHmPage.clickCouponCodeBtn();
//verify if the coupon code text field has a placeholder
        assertTrue(checkHmPage.isCouponCodePlaceholderDisp(), "the coupon code text field doesn't have placeholder");
    }


    }

