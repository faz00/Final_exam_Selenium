
package us.piit.base;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import us.piit.Utility.Utility;

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

    String implicitWait = prop.getProperty("implicit.Wait","10");
    String windowMaximize = prop.getProperty("browser.maximize","true");
    String takeScreenshots = prop.getProperty("take.screenshosts","false");


    protected WebDriver driver;

    // This method is getting Cloud Browser
    public void getCloudDriver(String envName , String os , String osVersion , String browserName , String browserVersion,String userName,String password) throws MalformedURLException {

        //cardentials to access to the website
        DesiredCapabilities cap = new DesiredCapabilities();

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

        }

    }

    // This method is getting Browsers from Bonigracia repo and manage Browsers at run time
    public void getLocalDriver(String browserName){
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
        }else if(browserName.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            log.info("Edge browser open success");
        }
    }


    // This method invoke and Open up the browser
    @Parameters({"useCloudEnv","envName","os","osVersion","browserName","browserVersion","url"})
    @BeforeMethod
    public void setUp(@Optional("false") String useCloudEnv, @Optional("browserstack") String envName, @Optional("windows") String os,
                      @Optional("10") String osVersion, @Optional("chrome") String browserName, @Optional("110") String browserVersion,
                      @Optional("https://www.google.com") String url) throws MalformedURLException {
        if (useCloudEnv.equalsIgnoreCase("true")){
            getCloudDriver(envName,os,osVersion,browserName,browserVersion,browserstackUsername,browserstackPassword);

        } else if(useCloudEnv.equalsIgnoreCase("false")){
            getLocalDriver(browserName);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(implicitWait)));

        if(windowMaximize.equalsIgnoreCase(("true"))){
            driver.manage().window().maximize();
        }
        driver.get(url);

       // PageFactory.initElements(driver, this);
    }

    // This method quit the browser after each test case


    @AfterMethod
    public void tearDownR(){
        //close browser
        driver.quit();
        log.info("browser close success");
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------
    //                                                          selenium methods
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------


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
            driver.findElement(By.xpath(locator)).click();

        }
    }
    public void type(String locator,String text){
        try {
            driver.findElement(By.cssSelector(locator)).sendKeys(text);
        }catch (Exception e){
            driver.findElement(By.xpath(locator)).sendKeys(text);

        }
    }
    public void hoverOver(WebElement locator){
        Actions actions=new Actions(driver);
        try {
            actions.moveToElement(locator).build().perform();
        }catch (Exception e){
            actions.moveToElement(locator).build().perform();

        }
    }
    public void waitFor(int seconds)  {
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    public boolean isInteractable(String locator) {
        try {
            return  driver.findElement(By.cssSelector(locator)).isEnabled();
        } catch (Exception e) {
            return driver.findElement(By.xpath(locator)).isEnabled();

        }

    }

    public boolean checkCheckBoxIsCh(String locator) {
        try {
            return  driver.findElement(By.cssSelector(locator)).isDisplayed();
        } catch (Exception e) {
            return driver.findElement(By.xpath(locator)).isDisplayed();

        }

    }

    public String generateTestEmail(){
        Random rn = new Random();
        int ranNumber = rn.nextInt(100) + 2;
        String TestEmail = "test"+ranNumber+"@gmail.com";
        return TestEmail;
    }

    public void clickOnRatingStar(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('#Rating_5').click()");
    }

    public String generateTimeStamp() {

        Date date=new Date();
        return date.toString().replace(" ", " _").replace(":", " ");

    }
    public void linktext(String locator) {

        driver.findElement(By.linkText(locator));
    }

    public void type(String locator) {
        try {
            driver.findElement(By.xpath(locator));
        }catch(Exception e) {
            driver.findElement(By.cssSelector(locator));

        }
    }
    public void linkclickOn(String locator) {

        driver.findElement(By.linkText(locator)).click();
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------
    //                                                          selenium methods with locators
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------


    public WebDriver getDriver() {
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
    public boolean isVisible(WebElement element) {


        return element.isDisplayed();
    }


    public boolean isInteractable(WebElement element) {

        return element.isEnabled();
    }

    public boolean checkCheckBoxIsCh(WebElement element) {


        return element.isSelected();
    }
}




    //------------------------------------------------------------------------------------------------------------------
    //                                              selenium methods
    //------------------------------------------------------------------------------------------------------------------

    public WebDriver getDriver() {
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
    public void type(WebElement element, String text){
        element.sendKeys(text);
    }
    public void hoverOver(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }
    public void hoverOverAndClickOn(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }
    public void waitFor(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isVisible(WebElement element){
        return element.isDisplayed();
    }
    public boolean isInteractible(WebElement element){
        return element.isEnabled();
    }
    public boolean isChecked(WebElement element){
        return element.isSelected();
    }
    public String generateTestEmail(){
        Random rn = new Random(System.nanoTime());
        int ranNumber = rn.nextInt(1000) + 101;
        String TestEmail = "test"+ranNumber+"@gmail.com";
        return TestEmail;
    }
}

