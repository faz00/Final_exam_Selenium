package nopCommerce;

import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.nopCommerce.ForgotPasswordPage;
import us.piit.pages.nopCommerce.LoginPage;
import us.piit.utility.Utility;

import java.util.Properties;

public class ForgotPasswordTest extends CommonAPI {
    Properties prop = Utility.loadProperties();
    String ValidEmail = Utility.decode(prop.getProperty("nopCommerce.username"));

    @Test
    public void forgotPassword(){
        LoginPage loginPage = new LoginPage(getDriver());
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(getDriver());

        //enter  Invalid username, Invalid password, and click on login button
        loginPage.goToLoginPage();
        loginPage.enterUsername(ValidEmail);
        loginPage.enterPassword("InvalidPassword");
        loginPage.checkRememberMebox();
        loginPage.clickOnLoginBtn();

        //validate the error message
        String expectedError = "Login was unsuccessful. Please correct the errors and try again";
        String actualError = loginPage.getErrorMessage();
        Assert.assertTrue(actualError.contains(expectedError));

        // click on forgot password and send reset password email
        forgotPasswordPage.resetPassword();
    }
}
