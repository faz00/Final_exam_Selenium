package Fazia;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.SetUp;




import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LogOutTest extends SetUp {

        //LofOutTest the name of the class
        Logger log = LogManager.getLogger(LogOutTest.class.getName());

        @Test
        public  void logout() throws InterruptedException {
            //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

            String expectedTitle = "Login | My Gumtree - Gumtree";
            String actualTitle = driver.getTitle();
            Assert.assertEquals(expectedTitle,actualTitle);


            //enter username,enter password, and click on login button

            type("#email","fazia.si00@gmail.com");
            log.info("Enter username , success");

            type("#fld-password","Qwerty789*");
            log.info("Enter password , success");

            driver.findElement(By.cssSelector("#onetrust-accept-btn-handler")).click();



            driver.findElement(By.cssSelector("button[data-analytics='gaEvent:LoginAttempt,userData:{lip:Email}']")).click();
            //  clickOn(".btn-primary btn-full-width");
            //driver.findElement(By.id("login-button")).click();
            log.info("click on button Login Success");



            /*WebElement humbergr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-q='user-menu-button']")));
            humbergr.click();*/
            //driver.findElement(By.cssSelector("button[data-q='user-menu-button']")).click();


            //check user is logged in
/*            String expectedHomePage = "Products";
            String actualHomePage = getElementText("//span[contains(text(),'Products')]");

            Assert.assertEquals(expectedHomePage,actualHomePage);
            log.info("user logged in success");

            //click on humberger menu

            clickOn("#react-burger-menu-btn");
            log.info("click on hamburger menu success");
            Thread.sleep(1000);

            //hover hover lougout link
            hoverOver("#logout_sidebar_link");
            log.info("click on logout link success");


            //check user is landed to the login page
            //WebElement loginPageHeader = driver.findElement(By.id(""));
            //check user is landed to the login page
            boolean loginPageHeaderIsDisplayed = isVisible("//div[contains(text(),'Swag Labs')]");
            Assert.assertTrue(loginPageHeaderIsDisplayed);
            log.info("login page header is displayed");
            String expectedLoginPageHeaderText = "Swag Labs";
            String actualLoginPageHeaderText = getElementText("//div[contains(text(),'Swag Labs')]");
            Assert.assertEquals(expectedLoginPageHeaderText, actualLoginPageHeaderText);
            log.info("login page header text validation success");

*/        }
    }

