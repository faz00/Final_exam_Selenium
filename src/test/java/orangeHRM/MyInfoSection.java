package orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.orangeHRM.DashbordPage;
import us.piit.pages.orangeHRM.LoginPage;
import us.piit.pages.orangeHRM.MyInfoPage;
import us.piit.pages.orangeHRM.TimePage;
import us.piit.utility.Utility;

import java.beans.Transient;
import java.util.Properties;

public class MyInfoSection extends CommonAPI {

    @DataProvider(name = "ContactDetails")
    public Object[][] getcontactDetails() {
        Properties prop = Utility.loadProperties();
        String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
        String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));
        String street = "1234 E Jasmine Ave";
        String city = "Denver";
        String state = "CO" ;
        String zipCode="98765";
        return new Object[][]{
                {validUsername, validPassword,street,city,state,zipCode}

        };
    }

    @Test(dataProvider ="ContactDetails", priority = 1, groups = "MyInfo")
    public void addContactDetails(String validUsername, String validPassword,String street,String city,String state,String zipCode){
        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        MyInfoPage myInfoPage = new MyInfoPage(getDriver());

        String expectedTitle = "OrangeHRM";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);


        //enter username,enter password, and click on login button

        loginPage.enterUsername(validUsername);


        loginPage.enterPassword(validPassword);

        loginPage.clickOnLoginBtn();

        //check user is logged in
        String expectedHomePage = "Dashboard";
        String actualHomePage = dashbordPage.getHraderText();
        Assert.assertEquals(expectedHomePage, actualHomePage);



        waitFor(5);
        //click on buzz
        dashbordPage.searchOptionOnSearchBar("My Info");

        dashbordPage.clickOnMyInfoOption();


        myInfoPage.clickOnDetailsOption();


        myInfoPage.typeStreet1Name(street);
        myInfoPage.typeCityName(city);
        myInfoPage.typeStateName(state);
        myInfoPage.typeZipCodeName(zipCode);
        myInfoPage.clickOnCountry();


        myInfoPage.clickOnSubmitContactInfo();

        takeScreenshot("orangeHRM","ContactInfoSaved");
    }

    @DataProvider(name = "dependentsDetails")
    public Object[][] getDependentsDetails() {
        Properties prop = Utility.loadProperties();
        String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
        String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));

        String nameField ="John Doe";
        String dateField ="2022-02-23";
        return new Object[][]{
                {validUsername, validPassword,nameField,dateField}

        };
    }

    @Test(dataProvider ="dependentsDetails", priority = 2, groups = "MyInfo")
    public void addDependentsDetails(String validUsername,String validPassword,String nameField,String dateField){
        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        MyInfoPage myInfoPage = new MyInfoPage(getDriver());

        String expectedTitle = "OrangeHRM";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);


        //enter username,enter password, and click on login button

        loginPage.enterUsername(validUsername);


        loginPage.enterPassword(validPassword);

        loginPage.clickOnLoginBtn();

        //check user is logged in
        String expectedHomePage = "Dashboard";
        String actualHomePage = dashbordPage.getHraderText();
        Assert.assertEquals(expectedHomePage, actualHomePage);



        waitFor(5);
        //click on buzz
        dashbordPage.searchOptionOnSearchBar("My Info");

        dashbordPage.clickOnMyInfoOption();

        myInfoPage.clickOnDependentsInfo();
        myInfoPage.clickOnAddDependentsBtn();


        myInfoPage.typeNameField(nameField);
        myInfoPage.clickOnRelationship();

        myInfoPage.typeDateField(dateField);

        myInfoPage.clickOnSaveDependents();

        String expectedName="Name";
        String actualName= myInfoPage.getActualName();
        Assert.assertEquals(expectedName,actualName);


    }
}
