<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
package us.piit.base;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
<<<<<<< Updated upstream
import org.testng.annotations.*;
import us.piit.utility.*;
=======
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import us.piit.Utility.Utility;
>>>>>>> Stashed changes

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class CommonAPI {
    Logger log = LogManager.getLogger(CommonAPI.class.getName());

    Properties prop = Utility.loadProperties();
    String browserstackUsername = prop.getProperty("browserstack.username");
    String browserstackPassword = prop.getProperty("browserstack.password");

<<<<<<< Updated upstream
    String implicitWait = prop.getProperty("implicit.Wait","10");
    String windowMaximize = prop.getProperty("browser.maximize","true");
    String takeScreenshots = prop.getProperty("take.screenshosts","false");


    protected WebDriver driver;

    // This method is getting Cloud Browser
    public void getCloudDriver(String envName , String os , String osVersion , String browserName , String browserVersion,String userName,String password) throws MalformedURLException {
=======
    String implicitWait = prop.getProperty("implicit.Wait", "10");
    String windowMaximize = prop.getProperty("browser.maximize", "true");
    String takeScreenshots = prop.getProperty("take.screenshosts", "false");


    public WebDriver driver;

    // This method is getting Cloud Browser
    public void getCloudDriver(String envName, String os, String osVersion, String browserName, String browserVersion, String userName, String password) throws MalformedURLException {
>>>>>>> Stashed changes

        //cardentials to access to the website
        DesiredCapabilities cap = new DesiredCapabilities();

<<<<<<< Updated upstream
        cap.setCapability("os",os);
        //os
        cap.setCapability("os_version",osVersion);
        //os version
        cap.setCapability("browser",browserName);
        //browser
        cap.setCapability("browser_version",browserVersion);
        //browser version
        //url + username + password

        if(envName.equalsIgnoreCase("browserstack")) {
            cap.setCapability("resolution","1024x768");
            driver = new RemoteWebDriver(new URL("http://"+userName+":"+password+"@hub-cloud.browserstack.com:80/wd/hub"), cap);
=======
        cap.setCapability("os", os);
        //os
        cap.setCapability("os_version", osVersion);
        //os version
        cap.setCapability("browser", browserName);
        //browser
        cap.setCapability("browser_version", browserVersion);
        //browser version
        //url + username + password

        if (envName.equalsIgnoreCase("browserstack")) {
            cap.setCapability("resolution", "1024x768");
            driver = new RemoteWebDriver(new URL("http://" + userName + ":" + password + "@hub-cloud.browserstack.com:80/wd/hub"), cap);
>>>>>>> Stashed changes

        }

    }

    // This method is getting Browsers from Bonigracia repo and manage Browsers at run time
<<<<<<< Updated upstream
    public void getLocalDriver(String browserName){
=======
    public void getLocalDriver(String browserName) {
>>>>>>> Stashed changes
        // Setting Chrome browser
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            log.info("chrome browser open success");

            // Setting Firefox browser
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            log.info("Firefox browser open success");

            // Setting Edge browser
<<<<<<< Updated upstream
        }else if(browserName.equalsIgnoreCase("edge")){
=======
        } else if (browserName.equalsIgnoreCase("edge")) {
>>>>>>> Stashed changes
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            log.info("Edge browser open success");
        }
    }


    // This method invoke and Open up the browser
<<<<<<< Updated upstream
    @Parameters({"useCloudEnv","envName","os","osVersion","browserName","browserVersion","url"})
=======
    @Parameters({"useCloudEnv", "envName", "os", "osVersion", "browserName", "browserVersion", "url"})
>>>>>>> Stashed changes
    @BeforeMethod
    public void setUp(@Optional("false") String useCloudEnv, @Optional("browserstack") String envName, @Optional("windows") String os,
                      @Optional("10") String osVersion, @Optional("chrome") String browserName, @Optional("110") String browserVersion,
                      @Optional("https://www.google.com") String url) throws MalformedURLException {
<<<<<<< Updated upstream
        if (useCloudEnv.equalsIgnoreCase("true")){
            getCloudDriver(envName,os,osVersion,browserName,browserVersion,browserstackUsername,browserstackPassword);

        } else if(useCloudEnv.equalsIgnoreCase("false")){
=======
        if (useCloudEnv.equalsIgnoreCase("true")) {
            getCloudDriver(envName, os, osVersion, browserName, browserVersion, browserstackUsername, browserstackPassword);

        } else if (useCloudEnv.equalsIgnoreCase("false")) {
>>>>>>> Stashed changes
            getLocalDriver(browserName);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(implicitWait)));

<<<<<<< Updated upstream
        if(windowMaximize.equalsIgnoreCase(("true"))){
=======
        if (windowMaximize.equalsIgnoreCase(("true"))) {
>>>>>>> Stashed changes
            driver.manage().window().maximize();
        }
        driver.get(url);

        // PageFactory.initElements(driver, this);
    }

    // This method quit the browser after each test case


<<<<<<< Updated upstream
    @AfterMethod
    public void tearDownR(){
        //close browser
        driver.quit();
        log.info("browser close success");
    }
=======
   /* @AfterMethod
    public void tearDownR() {
        //close browser
        driver.quit();
        log.info("browser close success");
    }*/
>>>>>>> Stashed changes

    //---------------------------------------------------------------------------------------------------------------------------------------------------
    //                                                          selenium methods
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------


<<<<<<< Updated upstream
    public String getElementText(String locator){
        try {
            return driver.findElement(By.cssSelector(locator)).getText();
        }catch (Exception e){
            return driver.findElement(By.xpath(locator)).getText();
        }
    }
    public void clickOn(String locator){
        try {
            driver.findElement(By.cssSelector(locator)).click();
        }catch (Exception e){
=======
    public String getElementText(String locator) {
        try {
            return driver.findElement(By.cssSelector(locator)).getText();
        } catch (Exception e) {
            return driver.findElement(By.xpath(locator)).getText();
        }
    }

    public void clickOn(String locator) {
        try {
            driver.findElement(By.cssSelector(locator)).click();
        } catch (Exception e) {
>>>>>>> Stashed changes
            driver.findElement(By.xpath(locator)).click();

        }
    }
<<<<<<< Updated upstream
    public void type(String locator,String text){
        try {
            driver.findElement(By.cssSelector(locator)).sendKeys(text);
        }catch (Exception e){
=======

    public void type(String locator, String text) {
        try {
            driver.findElement(By.cssSelector(locator)).sendKeys(text);
        } catch (Exception e) {
>>>>>>> Stashed changes
            driver.findElement(By.xpath(locator)).sendKeys(text);

        }
    }

<<<<<<< Updated upstream

=======
    public void hoverOver(WebElement locator) {
        Actions actions = new Actions(driver);
        try {
            actions.moveToElement(locator).build().perform();
        } catch (Exception e) {
            actions.moveToElement(locator).build().perform();

        }
    }

    public void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
>>>>>>> Stashed changes


    public boolean isInteractable(String locator) {
        try {
<<<<<<< Updated upstream
            return  driver.findElement(By.cssSelector(locator)).isEnabled();
=======
            return driver.findElement(By.cssSelector(locator)).isEnabled();
>>>>>>> Stashed changes
        } catch (Exception e) {
            return driver.findElement(By.xpath(locator)).isEnabled();

        }

    }

    public boolean checkCheckBoxIsCh(String locator) {
        try {
<<<<<<< Updated upstream
            return  driver.findElement(By.cssSelector(locator)).isDisplayed();
=======
            return driver.findElement(By.cssSelector(locator)).isDisplayed();
>>>>>>> Stashed changes
        } catch (Exception e) {
            return driver.findElement(By.xpath(locator)).isDisplayed();

        }

    }

<<<<<<< Updated upstream
    public String generateTestEmail(){
        Random rn = new Random();
        int ranNumber = rn.nextInt(100) + 2;
        String TestEmail = "test"+ranNumber+"@gmail.com";
        return TestEmail;
    }

    public void clickOnRatingStar(){
=======
    public String generateTestEmail() {
        Random rn = new Random();
        int ranNumber = rn.nextInt(100) + 2;
        String TestEmail = "test" + ranNumber + "@gmail.com";
        return TestEmail;
    }

    public void clickOnRatingStar() {
>>>>>>> Stashed changes
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('#Rating_5').click()");
    }

    public String generateTimeStamp() {

<<<<<<< Updated upstream
        Date date=new Date();
        return date.toString().replace(" ", " _").replace(":", " ");

    }
=======
        Date date = new Date();
        return date.toString().replace(" ", " _").replace(":", " ");

    }

>>>>>>> Stashed changes
    public void linktext(String locator) {

        driver.findElement(By.linkText(locator));
    }

    public void type(String locator) {
        try {
            driver.findElement(By.xpath(locator));
<<<<<<< Updated upstream
        }catch(Exception e) {
=======
        } catch (Exception e) {
>>>>>>> Stashed changes
            driver.findElement(By.cssSelector(locator));

        }
    }
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
    public void linkclickOn(String locator) {

        driver.findElement(By.linkText(locator)).click();
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------
    //                                                          selenium methods with locators
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------


    public WebDriver getDriver() {
<<<<<<< Updated upstream
        return driver;
    }

    public String getCurrentTitle(){

        return driver.getTitle();
    }
    public String getElementText(WebElement element){

        return element.getText();
    }
    public void clickOn(WebElement element){

        element.click();
    }
    public void type(WebElement element,String text){

        element.sendKeys(text);
    }
    public void hoverOverAndClickOn(WebElement element){
        Actions actions=new Actions(driver);
        actions.moveToElement(element).click().build().perform();

    }
=======

        return driver;
    }

    public void linkClickOn(WebElement element) {
        element.click();
    }

    public String getCurrentTitle() {

        return driver.getTitle();
    }

    public String getElementText(WebElement element) {

        return element.getText();
    }

    public void clickOn(WebElement element) {

        element.click();
    }

    public void type(WebElement element, String text) {

        element.sendKeys(text);
    }

    public void hoverOverAndClickOn(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();

    }

>>>>>>> Stashed changes
    public boolean isVisible(WebElement element) {


        return element.isDisplayed();
    }

<<<<<<< Updated upstream
    public void sendKey(WebElement locator,String text){

        locator.sendKeys(text);
    }
=======
>>>>>>> Stashed changes

    public boolean isInteractable(WebElement element) {

        return element.isEnabled();
    }

<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
    public boolean checkCheckBoxIsCh(WebElement element) {


        return element.isSelected();
    }

<<<<<<< Updated upstream
    public static void captureScreenshot(WebDriver driver, String screenshotPath) {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File(screenshotPath));
            System.out.println("Screenshot captured successfully");
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }






    //------------------------------------------------------------------------------------------------------------------
    //                                              selenium methods
    //------------------------------------------------------------------------------------------------------------------





    public void hoverOver(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }


    public void waitFor(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isInteractible(WebElement element){
        return element.isEnabled();
    }
    public boolean isChecked(WebElement element){
        return element.isSelected();
    }

}
=======
    @AfterMethod
    public void tearDownR() {
        //close browser
        driver.quit();
        log.info("browser close success");
    }
 @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Get the driver from your test class

            // Take the screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Set the name and destination folder for the screenshot
            String screenshotName = result.getMethod().getMethodName() + "_" + System.currentTimeMillis() + ".png";
            String screenshotDirectory = "./src/screenshots/\"";

            // Save the screenshot to the destination folder
            try {
                FileUtils.copyFile(screenshot, new File(screenshotDirectory + screenshotName));
                System.out.println("Screenshot saved: " + screenshotDirectory + screenshotName);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    }

>>>>>>> Stashed changes
