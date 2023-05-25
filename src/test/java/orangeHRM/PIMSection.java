package orangeHRM;

import com.beust.ah.A;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.orangeHRM.DashbordPage;
import us.piit.pages.orangeHRM.LoginPage;
import us.piit.pages.orangeHRM.PIMPage;
import us.piit.pages.orangeHRM.TimePage;
import us.piit.utility.Utility;

import java.util.Properties;

public class PIMSection extends CommonAPI {

    public class LoginToMaintenance extends CommonAPI {
        // Existing code

        @DataProvider(name = "loginCredentials")
        public Object[][] getLoginCredentials() {
            Properties prop = Utility.loadProperties();
            String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
            String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));

            return new Object[][]{
                    {validUsername, validPassword}
//                    {validUsername, "InvalidAdmin123"}
            };
        }


        @DataProvider(name = "testData")
        public Object[][] getTestData() {
            Properties prop = Utility.loadProperties();
            String validUsername = Utility.decode(prop.getProperty("orangeHRM.username"));
            String validPassword = Utility.decode(prop.getProperty("orangeHRM.password"));

            String imagePath = "C:\\Users\\DELL G5\\IdeaProjects\\Final_exam_Selenium\\screenShots\\orangeHRMScreenshots\\DeletePostInBuzzscreenshot.png";
            String firstName = "Fazia";
            String middleName = "Sid";
            String lastName = "Ali";
            String userName = "FaziaSid";
            String password = "Fazia2000";
            String companyName = "Amazon";
            String titleJob = "QA Tester";
            String beginningDate = "2022-02-03";
            String endDate = "2023-04-04";

            return new Object[][]{
                    {validUsername, validPassword,imagePath, firstName, middleName, lastName, userName, password, companyName, titleJob, beginningDate, endDate}


            };
        }

        @Test(dataProvider = "loginCredentials", priority = 1, groups = "PIM")
        public void searchEmployee(String validUsername, String validPassword) {
            LoginPage loginPage = new LoginPage(getDriver());
            DashbordPage dashbordPage = new DashbordPage(getDriver());
            PIMPage pimPage = new PIMPage(getDriver());

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
            //click on PIM
            dashbordPage.searchOptionOnSearchBar("PIM");

            dashbordPage.clickOnPimOption();
            pimPage.searchEmployeeName(dashbordPage.getUsernameName());

            pimPage.submitSearchEmployeeBtn();


            String expectedResult = "(1) Record Found";
            String actualResult = getElementText("div[class='orangehrm-horizontal-padding orangehrm-vertical-padding'] span[class='oxd-text oxd-text--span']");
            Assert.assertEquals(expectedResult, actualResult);


        }


      @Test(dataProvider = "testData", priority = 2, groups = "PIM")
        public void addEmployee(String validUsername, String validPassword, String imagePath, String firstName, String middleName, String lastName, String userName, String password, String companyName, String titleJob, String beginningDate, String endDate) {
            LoginPage loginPage = new LoginPage(getDriver());
            DashbordPage dashbordPage = new DashbordPage(getDriver());
            PIMPage pimPage = new PIMPage(getDriver());

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
            //click on PIM
            dashbordPage.searchOptionOnSearchBar("PIM");

            dashbordPage.clickOnPimOption();

            pimPage.clickOnAddEmployeeBtn();

            pimPage.addFirstName(firstName);
            pimPage.addMidleName(middleName);
            pimPage.addLastName(lastName);

            //add profile picture

            waitFor(5);
            pimPage.setThePathOfImage(imagePath);

            waitFor(10);

            pimPage.clickCheckBox();

            pimPage.addUsername(userName);

            pimPage.addPasswordForNewUser(password);

            pimPage.confirmPasswordForNewUser(password);

            pimPage.clickOnSubmitNewUser();

            waitFor(5);
            String expectedUsername = userName;
            String actualUsername = pimPage.getActualUsername();
            Assert.assertEquals(expectedUsername, actualUsername);

        }


       @Test(dataProvider = "testData", priority = 3, groups = "PIM")
        public void editEmployeesQualification(String validUsername, String validPassword, String imagePath, String firstName, String middleName, String lastName, String userName, String password, String companyName, String titleJob, String beginningDate, String endDate) {
            LoginPage loginPage = new LoginPage(getDriver());
            DashbordPage dashbordPage = new DashbordPage(getDriver());
            PIMPage pimPage = new PIMPage(getDriver());

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
            //click on PIM
            dashbordPage.searchOptionOnSearchBar("PIM");

            dashbordPage.clickOnPimOption();

            pimPage.clickOnEditBtn();

            waitFor(2);
            // Qualification
            pimPage.clickOnQualificationOption();

            waitFor(5);

            pimPage.clickOnAddExperienceBtn();

            waitFor(3);


            pimPage.typeCompanyName(companyName);
            pimPage.typeTitleJobField(titleJob);
            pimPage.typeBeginningDate(beginningDate);
            pimPage.typeEndDate(endDate);


            pimPage.clickSubmitBtn();

            String expectedResultAddedExperience = companyName;
            String actualResultAddedExperience = pimPage.getActualResultAddedExperience();
            Assert.assertEquals(expectedResultAddedExperience, actualResultAddedExperience);


        }


        @Test(dataProvider = "loginCredentials", priority = 4, groups = "PIM")
        public void editTaxExemptionOFAnEmployee(String validUsername, String validPassword) {
            LoginPage loginPage = new LoginPage(getDriver());
            DashbordPage dashbordPage = new DashbordPage(getDriver());
            PIMPage pimPage = new PIMPage(getDriver());

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
            //click on PIM
            dashbordPage.searchOptionOnSearchBar("PIM");

            dashbordPage.clickOnPimOption();

            pimPage.clickOnEditBtn();

            pimPage.clickOnTaxExemptionBtn();

            pimPage.dropDownList1();

            pimPage.typeTaxExemption("23");

            waitFor(5);

            pimPage.dropDownList2();

            pimPage.typeTaxExemptions2("23");

            waitFor(5);

            pimPage.dropDownListOption3();
            waitFor(5);
            pimPage.dropDownList4();
            waitFor(5);
            pimPage.clickOnSubmitButtonTaxExemption();

            takeScreenshot("orangeHRM", "EditTaxExemptions");

        }


        @Test(dataProvider = "loginCredentials", priority = 5, groups = "PIM")
        public void deleteEmployee(String validUsername, String validPassword) {
            LoginPage loginPage = new LoginPage(getDriver());
            DashbordPage dashbordPage = new DashbordPage(getDriver());
            PIMPage pimPage = new PIMPage(getDriver());

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
            //click on PIM
            dashbordPage.searchOptionOnSearchBar("PIM");

            dashbordPage.clickOnPimOption();

            pimPage.clickOnDeleteIcon();
            pimPage.clickOnYesDeleteButton();
//
            waitFor(2);


            takeScreenshot("orangeHRM", "deleteEmployeePIM");


        }

    }

}
