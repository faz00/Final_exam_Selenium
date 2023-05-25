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


    @Test
    public void CompareProducts(){
        ProductPage productPage = new ProductPage(getDriver());

        // add products to compare list
        productPage.Add_product_to_compare_list();

        // go to compare products page compare products
        productPage.compare_products();
    }

    @Test
    public void ClearProductComparisonList(){
        ProductPage productPage = new ProductPage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());

        // Verify that user is on Home page
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(actualTitle, "Home Page");
        waitFor(3);

        // go to login page
        loginPage.goToLoginPage();

        //enter  username, password, and click on login button
        loginPage.enterUsername(ValidEmail);
        loginPage.enterPassword(validPassword);
        loginPage.clickOnSignInBtn();

        // add products to compare list
        productPage.Add_product_to_compare_list();

        // print the comparison
        productPage.removeProductsFromComparisonList();
    }
    
}
