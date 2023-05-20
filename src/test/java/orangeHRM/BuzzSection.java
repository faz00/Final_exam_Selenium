package orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.utility.Utility;
import us.piit.base.CommonAPI;
import us.piit.pages.orangeHRM.BuzzPage;
import us.piit.pages.orangeHRM.DashbordPage;
import us.piit.pages.orangeHRM.LoginPage;


import java.util.Properties;


public class BuzzSection extends CommonAPI {

    Logger log = LogManager.getLogger(BuzzSection.class.getName());

    Properties prop = Utility.loadProperties();
    String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
    String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));

    String textPost="Hi, this is just a test";


    String pathScreenPackage="orangeHRMScreenshots";
   String imagePath = "C:\\Users\\DELL G5\\IdeaProjects\\Final_exam_Selenium\\screenShots\\orangeHRMScreenshots\\DeletePostInBuzzscreenshot.png";
//
//    String screenShotPath="jetbrains://idea/navigate/reference?project=Final_exam_Selenium&fqn=screenShots.orangeHRM.orangeHRMScreenshots";





    @Test
    public void sharePost(){
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
//        captureScreenshot(driver, screenShotPath+File.separator+"SharePostInBuzzscreenshot.png");

        takeScreenshot(pathScreenPackage,"SharePostInBuzzscreenshot");

        waitFor(10);

        //check the post
        String expectedPost="Hi, this is just a test";
        String actualPost = buzzPage.getSharedPostText();
        Assert.assertEquals(expectedPost,actualPost);
    }





    @Test

    public void editSharedPost(){

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



        // Use the sendKeys() method to set the path of the image file in the input field of the dialog
//        WebElement fileInputField = driver.findElement(By.xpath("//input[@type='file']"));
//        fileInputField.sendKeys("C:\\Users\\DELL G5\\IdeaProjects\\Final_exam_Selenium\\src\\test\\java\\orangeHRM\\orangeHRMScreenshots"+File.separator+"DeletePostInBuzzscreenshot.png");


        waitFor(5);
        buzzPage.setThePathOfImage(imagePath);
        buzzPage.clickOnSubmitEditPost();

        waitFor(7);

        //Take a screenshot
        //CaptureScreenshot(driver, screenShotPath+File.separator+"EditPostInBuzzscreenshot.png");
        takeScreenshot(pathScreenPackage,"EditPostInBuzzscreenshot");



        waitFor(5);
        //check the post
        String expectedPost="Hi, this is just a test";
        String actualPost =buzzPage.getSharedPostText();
        Assert.assertEquals(expectedPost,actualPost);
    }

@Test

    public void DeletePost(){

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
//        captureScreenshot(driver, screenShotPath+File.separator+"DeletePostInBuzzscreenshot.png");
        takeScreenshot(pathScreenPackage,"DeletePostInBuzzscreenshot");



    }


    @Test

 public void addCommentAndLike(){

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
       buzzPage.shareComment("Hello Word");

        waitFor(2);

        //Take a screenshot
     takeScreenshot(pathScreenPackage,"CommentPostInBuzzscreenshot");
      //  captureScreenshot(driver, currentDir+File.separator+"CommentPostInBuzzscreenshot.png");

//
//        String expectedBuzzComment="Hello World";
//    String actualBuzzComment = buzzPage.getTheComment();
//    Assert.assertEquals(expectedBuzzComment,actualBuzzComment);



    }
}
