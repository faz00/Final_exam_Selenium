package luma;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.luma.CartPage;
import us.piit.pages.luma.LoginPage;
import us.piit.pages.luma.OrderPage;
import us.piit.utility.Utility;

import java.util.Properties;

public class OrderTest extends CommonAPI {

    Properties prop = Utility.loadProperties();
    String ValidEmail = prop.getProperty("luma.username");
    String validPassword = prop.getProperty("luma.password");


    @Test
    public void PlaceAnOrder(){
        LoginPage loginPage = new LoginPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        OrderPage orderPage = new OrderPage(getDriver());


        // go to login page and enter  username, password, and click on login button
        loginPage.goToLoginPage();
        loginPage.enterUsername(ValidEmail);
        loginPage.enterPassword(validPassword);
        loginPage.clickOnSignInBtn();

        // Go to product page and add product to cart
        cartPage.clickOnSaleTab();
        waitFor(2);
        cartPage.clickOnHoodiesAndShirts();
        waitFor(2);
        cartPage.AddProductToCart();

        //Proceed to checkout
        orderPage.proceedTOCheckout();

        // place order
        orderPage.place_order();
    }

    @Test
    public void ViewPlacedOrders(){
        LoginPage loginPage = new LoginPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        OrderPage orderPage = new OrderPage(getDriver());


        // go to login page and enter  username, password, and click on login button
        loginPage.goToLoginPage();
        loginPage.enterUsername(ValidEmail);
        loginPage.enterPassword(validPassword);
        loginPage.clickOnSignInBtn();

        // View placed orders
        orderPage.view_placed_orders();
    }

}
