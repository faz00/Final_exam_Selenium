package orangeHRM;

import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalse;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;
import us.piit.pages.orangeHRM.DashbordPage;
import us.piit.pages.orangeHRM.LeavePage;
import us.piit.pages.orangeHRM.LoginPage;
import us.piit.pages.orangeHRM.RecruitmentPage;
import us.piit.utility.ExcelReader;
import us.piit.utility.Utility;

import java.io.File;
import java.util.Properties;

import static us.piit.utility.Utility.currentDir;

public class Recruitment extends CommonAPI {

    String path=currentDir+ File.separator+"manualTestCases\\OrangeHRMTest.xlsx";
    ExcelReader excelReader =new ExcelReader(path);

    @DataProvider(name = "loginCredentials")
    public Object[][] getLoginCredentials() {
        String validUsername = Utility.decode(excelReader.getDataFromCell("DataProvider",1,0));
        String validPassword = Utility.decode(excelReader.getDataFromCell("DataProvider",1,1));


        return new Object[][]{
                {validUsername, validPassword}

        };
    }

    @Test(dataProvider = "loginCredentials", priority = 1, groups = "Recruitment")

    public void viewInterviewerCandidates(String validUsername,String validPassword){

        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        RecruitmentPage recruitmentPage =new RecruitmentPage(getDriver());


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

        //Maintenance
        dashbordPage.searchOptionOnSearchBar("Recruitment");

        waitFor(5);

        dashbordPage.clickOnRecruitmentOption();

        recruitmentPage.clickOnViewIcon();

        recruitmentPage.clickOnEditIcon();

        recruitmentPage.typeNoteInCandidatesInterview("checked");

        recruitmentPage.clickOnSubmitBtn();

        String expectedPage ="View Action History";
        String actualPAge=recruitmentPage.getActualPage();
        Assert.assertEquals(expectedPage,actualPAge);
    }

    @Test(dataProvider = "loginCredentials", priority = 3, groups = "Recruitment")

     public void addVacancies(String validUsername,String validPassword){
        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        RecruitmentPage recruitmentPage =new RecruitmentPage(getDriver());


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

        //Maintenance
        dashbordPage.searchOptionOnSearchBar("Recruitment");

        waitFor(5);

        dashbordPage.clickOnRecruitmentOption();

        waitFor(2);
        recruitmentPage.clickOnVacancies();
        recruitmentPage.clickOnAddVacanciesBtn();

        recruitmentPage.typeVacancyName("Senior Developer");

        recruitmentPage.clickOnjobTitleDropDownList();


        recruitmentPage.typeHiringManagerField();
        recruitmentPage.clickOnSubmitVacancy();

        waitFor(2);
        String expectedResult ="Edit Vacancy";
        String actualResult=recruitmentPage.getActualPage();
        Assert.assertEquals(expectedResult,actualResult);


    }

    @Test(dataProvider = "loginCredentials", priority = 2, groups = "Recruitment")
    public void deleteVacancies( String validUsername,String validPassword){
        LoginPage loginPage = new LoginPage(getDriver());
        DashbordPage dashbordPage = new DashbordPage(getDriver());
        RecruitmentPage recruitmentPage =new RecruitmentPage(getDriver());


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

        //Maintenance
        dashbordPage.searchOptionOnSearchBar("Recruitment");

        waitFor(5);

        dashbordPage.clickOnRecruitmentOption();

        waitFor(2);
        recruitmentPage.clickOnVacancies();
        recruitmentPage.clickOnAddVacanciesBtn();

        recruitmentPage.typeVacancyName("Senior Developer");

        recruitmentPage.clickOnjobTitleDropDownList();


        recruitmentPage.typeHiringManagerField();
        recruitmentPage.clickOnSubmitVacancy();

        waitFor(2);
        String expectedResult ="Edit Vacancy";
        String actualResult=recruitmentPage.getActualResultAddVacancy();
        Assert.assertEquals(expectedResult,actualResult);

        recruitmentPage.clickOnVacancies();
        recruitmentPage.clickOnCheckBox();

        recruitmentPage.clickOnDeleteVacancy();
        recruitmentPage.clickOnYesDeleteBtn();

        waitFor(1);
        takeScreenshot("orangeHRM","DeleteVacancy");

    }
}
