package nopCommerce;

import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.nopCommerce.*;
import us.piit.utility.Utility;

import java.util.Properties;

public class CheckOutTest extends CommonAPI {
    Properties prop = Utility.loadProperties();
    String ValidEmail = Utility.decode(prop.getProperty("nopCommerce.username"));
    String validPassword = Utility.decode(prop.getProperty("nopCommerce.password"));

    @Test
    public void CheckOut_And_Place_Order(){
        CheckOutPage checkOutPage = new CheckOutPage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());


        //enter  username, password, and click on login button
        loginPage.goToLoginPage();
        loginPage.enterUsername(ValidEmail);
        loginPage.enterPassword(validPassword);
        loginPage.checkRememberMebox();
        loginPage.clickOnLoginBtn();

        // add product to cart and view cart
        cartPage.goToProductPage();
        cartPage.AddProductToCart();
        cartPage.viewCart();

        // checkout and place order
        checkOutPage.CheckOutTheProduct();

        // fill billing and shipping address and place order
        checkOutPage.PlaceOrder();
    }

    @Test
    public void SeeOrderDetails(){
        CheckOutPage checkOutPage = new CheckOutPage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());


        //enter  username, password, and click on login button
        loginPage.goToLoginPage();
        loginPage.enterUsername(ValidEmail);
        loginPage.enterPassword(validPassword);
        loginPage.checkRememberMebox();
        loginPage.clickOnLoginBtn();

        // add product to cart and view cart
        cartPage.goToProductPage();
        cartPage.AddProductToCart();
        cartPage.viewCart();

        // checkout and place order
        checkOutPage.CheckOutTheProduct();

        // fill billing and shipping address and place order
        checkOutPage.PlaceOrder();

        // see all order details
        checkOutPage.OrderDetails();
    }
}
