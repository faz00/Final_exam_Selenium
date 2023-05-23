
package us.piit.base;


import com.relevantcodes.extentreports.LogStatus;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import us.piit.reporting.ExtentManager;
import us.piit.reporting.ExtentTestManager;
import us.piit.utility.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonAPI {
    Logger log = LogManager.getLogger(CommonAPI.class.getName());

    Properties prop = Utility.loadProperties();
    String browserstackUsername = prop.getProperty("browserstack.username");
    String browserstackPassword = prop.getProperty("browserstack.password");

    String implicitWait = prop.getProperty("implicit.Wait","10");
    String windowMaximize = prop.getProperty("browser.maximize","true");
    String takeScreenshots = prop.getProperty("take.screenshosts","false");


    protected WebDriver driver;
    public static com.relevantcodes.extentreports.ExtentReports extent;

    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName().toLowerCase();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }
    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    @AfterMethod
    public void afterEachTestMethod(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        }
        ExtentTestManager.endTest();
        extent.flush();
        if (takeScreenshots.equalsIgnoreCase("true")){
            if (result.getStatus() == ITestResult.FAILURE) {
                takeScreenshot(result.getName());
            }
        }
        driver.quit();
    }
    @AfterSuite
    public void generateReport() {
        extent.close();
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

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




    public boolean isInteractable(String locator) {
        try {
            return  driver.findElement(By.cssSelector(locator)).isEnabled();
        } catch (Exception e) {
            return driver.findElement(By.xpath(locator)).isEnabled();

        }

    }

    public boolean checkCheckBoxIsCh(String locator) {
        try {
            return  driver.findElement(By.cssSelector(locator)).isSelected();
        } catch (Exception e) {
            return driver.findElement(By.xpath(locator)).isSelected();

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

    public void sendKey(WebElement locator,String text){

        locator.sendKeys(text);
    }

    public boolean isInteractable(WebElement element) {

        return element.isEnabled();
    }


    public boolean checkCheckBoxIsCh(WebElement element) {


        return element.isSelected();
    }

    public static void captureScreenshot(WebDriver driver, String screenshotPath) {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File(screenshotPath));
            System.out.println("Screenshot captured successfully");
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    public void waitForElementToBeVisible(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.visibilityOfAllElements(element));
            Assert.assertTrue(element.isDisplayed());
        }
        catch (Exception e){
            log.info(e);
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

    public void clickWithJavascript(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", element);
    }
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();",element);
    }
    public void captureScreenshot() {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file,new File("screenshots"+File.separator+"screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void takeScreenshot(String screenshotName){
        DateFormat df = new SimpleDateFormat("MMddyyyyHHmma");
        Date date = new Date();
        df.format(date);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(Utility.currentDir+ File.separator +"screenshots"+ File.separator + screenshotName+" "+df.format(date)+".jpeg"));
            System.out.println("Screenshot captured");
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot "+e.getMessage());
        }
    }

    public void takeScreenshot(String packageName,String screenshotName){
        DateFormat df = new SimpleDateFormat("MMddyyyyHHmma");
        Date date = new Date();
        df.format(date);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(Utility.currentDir+ File.separator +"screenshots"+File.separator+packageName+ File.separator + screenshotName+" "+df.format(date)+".jpeg"));
            System.out.println("Screenshot captured");
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot "+e.getMessage());
        }
    }

    public Double extractNumericPrice(String priceWithSymbol) {
        Pattern p = Pattern.compile("[^0-9]*([0-9]*,?([0-9]+(\\.[0-9]*))?)");
        Matcher m = p.matcher(priceWithSymbol);
        m.matches();
        String s_num = m.group(1).replace(",", "");
        Double d_num = Double.valueOf(s_num);
        return d_num;
    }


}

