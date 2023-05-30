package luma;

import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.luma.RegisterPage;

public class RegisterTest extends CommonAPI {


    @Test
    public void createNewUser(){
        RegisterPage registerPage = new RegisterPage(getDriver());

        // Verify page title
        Assert.assertEquals(getCurrentTitle(), "Home Page");

        // Go to Create User page
        registerPage.goToCreateUserPage();

        // verify that user is on create account page
        Assert.assertEquals(getCurrentTitle(), "Create New Customer Account");

        // Fill the user detail form
        registerPage.FillUserDetailForm("All details");

        // Click Create account button
        registerPage.clickOnCreateAccountBtn();

        // verify that account is created
        Assert.assertTrue(registerPage.verifyThatAccountIsCreated());
    }

    @Test
    public void Register_WithOut_FirstName(){
        RegisterPage registerPage = new RegisterPage(getDriver());

        // Verify page title
        Assert.assertEquals(getCurrentTitle(), "Home Page");

        // Go to Create User page
        registerPage.goToCreateUserPage();

        // verify that user is on create account page
        Assert.assertEquals(getCurrentTitle(), "Create New Customer Account");

        // Fill the user detail form without First Name
        registerPage.FillUserDetailForm("With Out First Name");

        // Click Create account button
        registerPage.clickOnCreateAccountBtn();

        // verify that all field error
        Assert.assertTrue(registerPage.verifyThatFiedlErrorAppears());
    }

    @Test
    public void Register_WithOut_LastName(){
        RegisterPage registerPage = new RegisterPage(getDriver());

        // Verify page title
        Assert.assertEquals(getCurrentTitle(), "Home Page");

        // Go to Create User page
        registerPage.goToCreateUserPage();

        // verify that user is on create account page
        Assert.assertEquals(getCurrentTitle(), "Create New Customer Account");

        // Fill the user detail form without First Name
        registerPage.FillUserDetailForm("With Out Last Name");

        // Click Create account button
        registerPage.clickOnCreateAccountBtn();

        // verify that all field error
        Assert.assertTrue(registerPage.verifyThatFiedlErrorAppears());
    }

    @Test
    public void Register_WithOut_Email(){
        RegisterPage registerPage = new RegisterPage(getDriver());

        // Verify page title
        Assert.assertEquals(getCurrentTitle(), "Home Page");

        // Go to Create User page
        registerPage.goToCreateUserPage();

        // verify that user is on create account page
        Assert.assertEquals(getCurrentTitle(), "Create New Customer Account");

        // Fill the user detail form without First Name
        registerPage.FillUserDetailForm("With Out Email");

        // Click Create account button
        registerPage.clickOnCreateAccountBtn();

        // verify that all field error
        Assert.assertTrue(registerPage.verifyThatFiedlErrorAppears());
    }

    @Test
    public void Register_WithOut_Password(){
        RegisterPage registerPage = new RegisterPage(getDriver());

        // Verify page title
        Assert.assertEquals(getCurrentTitle(), "Home Page");

        // Go to Create User page
        registerPage.goToCreateUserPage();

        // verify that user is on create account page
        Assert.assertEquals(getCurrentTitle(), "Create New Customer Account");

        // Fill the user detail form without First Name
        registerPage.FillUserDetailForm("With Out Password");

        // Click Create account button
        registerPage.clickOnCreateAccountBtn();

        // verify that all field error
        Assert.assertTrue(registerPage.verifyThatFiedlErrorAppears());
    }
}
