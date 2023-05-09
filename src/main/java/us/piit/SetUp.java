package us.piit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Beta;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class SetUp {
    Logger log = LogManager.getLogger(SetUp.class.getName());

    // String browserName= "firefox";

    protected WebDriver driver;

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

    public void getLocalDriver(String browserName){
        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
            log.info("chrome browser open success");
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            log.info("Firefox browser open success");
        }else if(browserName.equalsIgnoreCase("edge")){
            driver = new EdgeDriver();
            log.info("Edge browser open success");
        }
    }
    @Parameters({"useCloudEnv","envName","os","osVersion","browserName","browserVersion","url"})
    @BeforeMethod
    public void setUp(@Optional("false") String useCloudEnv, @Optional("browserstack") String envName, @Optional("windows") String os,
                      @Optional("10") String osVersion, @Optional("chrome") String browserName, @Optional("110") String browserVersion,
                      @Optional("https://www.google.com") String url) throws MalformedURLException {
        if (useCloudEnv.equalsIgnoreCase("true")){
            getCloudDriver(envName,os,osVersion,browserName,browserVersion,"faziasidali_tXAPFy","KqxAkVzqjoKmm43Xsn4p");

        } else if(useCloudEnv.equalsIgnoreCase("false")){
            getLocalDriver(browserName);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get(url);
    }
    @AfterMethod
    public void tearDown(){

        //close browser
        driver.quit();
        log.info("browser close success");
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------
    //                                                          selenium methods
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------

    public String getCurrentTitle(){
        return driver.getTitle();
    }
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
    public void hoverOver(String locator){
        Actions actions=new Actions(driver);
        try {
            actions.moveToElement(driver.findElement(By.cssSelector(locator))).build().perform();
        }catch (Exception e){
            actions.moveToElement(driver.findElement(By.xpath(locator))).build().perform();

        }
    }
    public void waitFor(int seconds)  {
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isVisible(String locator) {
        try {
            return  driver.findElement(By.cssSelector(locator)).isDisplayed();
        } catch (Exception e) {
            return driver.findElement(By.xpath(locator)).isDisplayed();

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
}
