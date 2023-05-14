package nopCommerce;


import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.nopCommerce.RegisterPage;


public class RegisterNewUser extends CommonAPI {


    @Test
    public void registerUserWithAllDetails(){
        RegisterPage registerPage = new RegisterPage(getDriver());
        // Verify page title
        Assert.assertEquals(getCurrentTitle(), "nopCommerce demo store");
        // Fill the user form and register
        registerPage.goToRegisterPage();
        registerPage.registerNewUser("All details");
    }

    @Test
    public void registerUserWithoutCompanyDetails(){
        RegisterPage registerPage = new RegisterPage(getDriver());
        // Verify page title
        Assert.assertEquals(getCurrentTitle(), "nopCommerce demo store");
        // Fill the user form and register
        registerPage.goToRegisterPage();
        registerPage.registerNewUser("No company details");
    }

    @Test
    public void registerUserWithEmptyDetails(){
        RegisterPage registerPage = new RegisterPage(getDriver());
        // Verify page title
        Assert.assertEquals(getCurrentTitle(), "nopCommerce demo store");
        // Fill the user form and register
        registerPage.goToRegisterPage();
        registerPage.registerNewUser("Empty details");
    }
}
