package orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;

public class LogOutTest extends CommonAPI {


        Logger log = LogManager.getLogger(LogOutTest.class.getName());

        @Test
        public  void logout() throws InterruptedException {


            String expectedTitle = "orangeHRM";
            String actualTitle = driver.getTitle();
            Assert.assertEquals(expectedTitle,actualTitle);


            //enter username,enter password, and click on login button

            type("input[placeholder='Username']","Admin");
            log.info("Enter username , success");

            type("input[placeholder='Password']","admin123");
            log.info("Enter password , success");

            clickOn("button[type='submit']");
            log.info("click on button Login Success");


            //check user is logged in
            String expectedHomePage = "Dashboard";
            String actualHomePage =  getElementText("//h6[normalize-space()='Dashboard']");
            Assert.assertEquals(expectedHomePage,actualHomePage);
            log.info("user logged in success");


            //Log Out
            clickOn(".oxd-userdropdown-name");
            log.info("click on the Menu Success");

            clickOn("//a[normalize-space()='Logout']");
            log.info("click on the logOut button Success");

            waitFor(5);


            String expectedLoginPage = "orangeHRM";
            String actualLoginPage = driver.getTitle();
            Assert.assertEquals(expectedLoginPage,actualLoginPage);

            log.info("user logged out success");


        }
    }

