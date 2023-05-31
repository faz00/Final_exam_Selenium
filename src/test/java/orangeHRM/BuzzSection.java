package orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import us.piit.utility.Utility;
import us.piit.base.CommonAPI;
import us.piit.pages.orangeHRM.BuzzPage;
import us.piit.pages.orangeHRM.DashbordPage;
import us.piit.pages.orangeHRM.LoginPage;


import java.io.File;
import java.util.Properties;

import static us.piit.utility.Utility.currentDir;


public class BuzzSection extends CommonAPI {

    Logger log = LogManager.getLogger(BuzzSection.class.getName());

    @DataProvider(name = "loginCredentials")
    public Object[][] getLoginCredentials() {
        Properties prop = Utility.loadProperties();
        String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
        String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));
        String textPost="Hi, this is just a test";

        String pathScreenPackage="orangeHRMScreenshots";


        return new Object[][]{
                {validUsername, validPassword,textPost,pathScreenPackage}

        };
    }



    @DataProvider(name = "EditPost")
    public Object[][] editPost() {
        Properties prop = Utility.loadProperties();
        String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
        String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));
        String textPost="Hi, this is just a test";

        String pathScreenPackage="orangeHRM";
        String imagePath = currentDir+ File.separator+"screenShots\\orangeHRM\\analysis.png";




        return new Object[][]{
                {validUsername, validPassword,textPost,pathScreenPackage,imagePath}

        };
    }





    @Test(dataProvider = "loginCredentials", priority = 1, groups = "Buzz")
    public void sharePost(String validUsername, String validPassword,String textPost,String pathScreenPackage){
        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        BuzzPage buzzPage = new BuzzPage(getDriver());

        String expectedTitle = "OrangeHRM";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);


        //enter username,enter password, and click on login button

        loginPage.enterUsername(validUsername);


        loginPage.enterPassword(validPassword);

        loginPage.clickOnLoginBtn();

        //check user is logged in
        String expectedHomePage = "Dashboard";
        String actualHomePage = dashbordPage.getHraderText();
        Assert.assertEquals(expectedHomePage,actualHomePage);
        log.info("user logged in success");


        waitFor(5);
        //click on buzz
        dashbordPage.searchOptionOnSearchBar("Buzz");

        dashbordPage.clickOnBuzzOption();

        //check the buzz`s page
        String expectedBuzzTitle="https://opensource-demo.orangehrmlive.com/web/index.php/buzz/viewBuzz";
        String actualBuzzTitle = driver.getCurrentUrl();
        Assert.assertEquals(expectedBuzzTitle,actualBuzzTitle);

        //share post
        waitFor(5);
        buzzPage.typeWhatInYourMind(textPost);
        waitFor(5);
        buzzPage.clickOnSubmitBtn();


        waitFor(2);

        //Take a screenshot
        takeScreenshot(pathScreenPackage,"SharePostInBuzzscreenshot");

        waitFor(10);

        //check the post
        String expectedPost="Hi, this is just a test";
        String actualPost = buzzPage.getSharedPostText();
        Assert.assertEquals(expectedPost,actualPost);
    }





    @Test(dataProvider = "EditPost", priority = 2, groups = "Buzz")
    public void editSharedPost(String validUsername, String validPassword,String textPost,String pathScreenPackage,String imagePath){

        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        BuzzPage buzzPage = new BuzzPage(getDriver());

        String expectedTitle = "OrangeHRM";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);


        //enter username,enter password, and click on login button

        loginPage.enterUsername(validUsername);


        loginPage.enterPassword(validPassword);

        loginPage.clickOnLoginBtn();

        //check user is logged in
        String expectedHomePage = "Dashboard";
        String actualHomePage =  dashbordPage.getHraderText();
        Assert.assertEquals(expectedHomePage,actualHomePage);
        log.info("user logged in success");


        waitFor(5);
        //click on buzz
        dashbordPage.searchOptionOnSearchBar("Buzz");

        dashbordPage.clickOnBuzzOption();


        //check the buzz`s page
        String expectedBuzzTitle="https://opensource-demo.orangehrmlive.com/web/index.php/buzz/viewBuzz";
        String actualBuzzTitle = driver.getCurrentUrl();
        Assert.assertEquals(expectedBuzzTitle,actualBuzzTitle);

        //share post

        waitFor(5);
        buzzPage.typeWhatInYourMind(textPost);
        waitFor(5);
        buzzPage.clickOnSubmitBtn();


        waitFor(5);
        //Edit the shared post
        buzzPage.clickOnThreeDotes();
        waitFor(2);
        buzzPage.clickOnEditOption();

        waitFor(2);


        waitFor(5);
        buzzPage.setThePathOfImage(imagePath);
        buzzPage.clickOnSubmitEditPost();

        waitFor(7);

        //Take a screenshot
        takeScreenshot(pathScreenPackage,"EditPostInBuzzscreenshot");



        waitFor(5);
        //check the post
        String expectedPost="Hi, this is just a test";
        String actualPost =buzzPage.getSharedPostText();
        Assert.assertEquals(expectedPost,actualPost);
    }
    @DataProvider(name = "deletePost")
    public Object[][] deletePost() {
        Properties prop = Utility.loadProperties();
        String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
        String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));
        String textPost="Hi, this is just a test";

        String pathScreenPackage="orangeHRMScreenshots";
        return new Object[][]{
                {validUsername, validPassword,textPost,pathScreenPackage}

        };
    }

    @Test(dataProvider = "deletePost", priority = 3, groups = "Buzz")

    public void DeletePost(String validUsername, String validPassword,String textPost,String pathScreenPackage){

        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        BuzzPage buzzPage = new BuzzPage(getDriver());

        String expectedTitle = "OrangeHRM";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);


        //enter username,enter password, and click on login button

        loginPage.enterUsername(validUsername);


        loginPage.enterPassword(validPassword);

        loginPage.clickOnLoginBtn();

        //check user is logged in
        String expectedHomePage = "Dashboard";
        String actualHomePage =  dashbordPage.getHraderText();
        Assert.assertEquals(expectedHomePage,actualHomePage);


        waitFor(5);
        //click on buzz
        dashbordPage.searchOptionOnSearchBar("Buzz");

        dashbordPage.clickOnBuzzOption();

        //check the buzz`s page
        String expectedBuzzTitle="https://opensource-demo.orangehrmlive.com/web/index.php/buzz/viewBuzz";
        String actualBuzzTitle = driver.getCurrentUrl();
        Assert.assertEquals(expectedBuzzTitle,actualBuzzTitle);

        //share post
        waitFor(5);
        buzzPage.typeWhatInYourMind(textPost);
        waitFor(5);
        buzzPage.clickOnSubmitBtn();

        waitFor(2);
        //check the post
        String expectedPost="Hi, this is just a test";
        String actualPost = buzzPage.getSharedPostText();
        Assert.assertEquals(expectedPost,actualPost);

        //Delete a shared post
        buzzPage.clickOnThreeDotes();
        waitFor(2);
        buzzPage.clickOnDeleteBtn();
        waitFor(2);
        buzzPage.clickOnYesDelte();
        waitFor(2);


        //Take a screenshot

        takeScreenshot(pathScreenPackage,"DeletePostInBuzzscreenshot");



    }

    @DataProvider(name = "addComment")
    public Object[][] addComment() {
        Properties prop = Utility.loadProperties();
        String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
        String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));
        String textPost="Hello Word";


        String pathScreenPackage="orangeHRMScreenshots";



        return new Object[][]{
                {validUsername, validPassword,textPost,pathScreenPackage}

        };
    }

    @Test(dataProvider = "addComment", priority = 4, groups = "Buzz")
    public void addCommentAndLike(String validUsername, String validPassword, String textPost, String pathScreenPackage) {

        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        BuzzPage buzzPage = new BuzzPage(getDriver());

        String expectedTitle = "OrangeHRM";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);


        //enter username,enter password, and click on login button

        loginPage.enterUsername(validUsername);


        loginPage.enterPassword(validPassword);

        loginPage.clickOnLoginBtn();

        //check user is logged in
        String expectedHomePage = "Dashboard";
        String actualHomePage =  dashbordPage.getHraderText();
        Assert.assertEquals(expectedHomePage,actualHomePage);
        log.info("user logged in success");

        waitFor(5);
        //click on buzz
        dashbordPage.searchOptionOnSearchBar("Buzz");

        dashbordPage.clickOnBuzzOption();

        waitFor(10);

        //check the buzz`s page
        String expectedBuzzTitle="https://opensource-demo.orangehrmlive.com/web/index.php/buzz/viewBuzz";
        String actualBuzzTitle = driver.getCurrentUrl();
        Assert.assertEquals(expectedBuzzTitle,actualBuzzTitle);

        //go to the most commented post
        buzzPage.clickOnMostCommentedBtn();

        //comment
        buzzPage.clickOnCommentIcon();


        //write the comment
        buzzPage.shareComment(textPost);

        waitFor(2);

        //Take a screenshot
        takeScreenshot(pathScreenPackage,"CommentPostInBuzzScreenshot");


//
//        String expectedBuzzComment="Hello World";
//    String actualBuzzComment = buzzPage.getTheComment();
//    Assert.assertEquals(expectedBuzzComment,actualBuzzComment);



    }
}
