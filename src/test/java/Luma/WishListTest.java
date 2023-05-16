package luma;

import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.Luma.LoginPage;
import us.piit.pages.Luma.WishListPage;
import us.piit.utility.Utility;

import java.util.Properties;

public class WishListTest extends CommonAPI {

    Properties prop = Utility.loadProperties();
    String ValidEmail = prop.getProperty("luma.username");
    String validPassword = prop.getProperty("luma.password");


    @Test
    public void AddProductToWishList(){
        WishListPage wishListPage = new WishListPage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());

        // Login First
        loginPage.Login(ValidEmail, validPassword);


        // Go to product page
        wishListPage.clickOnSaleTab();
        waitFor(2);
        wishListPage.clickOnHoodiesAndShirts();
        waitFor(2);

        // Add product to wish list
        wishListPage.AddProductToWishList();
        waitFor(1);
    }
}
