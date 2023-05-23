package luma;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.luma.LoginPage;
import us.piit.pages.luma.ProductPage;
import us.piit.pages.luma.WishListPage;
import us.piit.utility.Utility;

import java.util.Properties;

public class ProductsTest extends CommonAPI {

    Properties prop = Utility.loadProperties();
    String ValidEmail = prop.getProperty("luma.username");
    String validPassword = prop.getProperty("luma.password");

    @Test
    public void ChangeProductView(){
        WishListPage wishListPage = new WishListPage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());


        // Go to product page
        wishListPage.clickOnSaleTab();
        waitFor(2);
        wishListPage.clickOnHoodiesAndShirts();
        waitFor(2);

        // change product view
        productPage.changeToListView();
        productPage.changeToGridView();
    }
    
}
