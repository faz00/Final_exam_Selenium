package us.piit.pages.orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

public class DashbordPage extends CommonAPI {
    Logger log = LogManager.getLogger(DashbordPage.class.getName());
    public DashbordPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
    WebElement userNameOfTheLoggedEmployee;

    @FindBy(xpath = "//h6[normalize-space()='Dashboard']")
    WebElement mainHeader;
    @FindBy(css=".oxd-userdropdown-name")
    WebElement menuBtn;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    WebElement logoutBtn;

    @FindBy(css=".oxd-userdropdown-name")
    WebElement userDropDownMenu;

    @FindBy (xpath ="//a[normalize-space()='Change Password']" )
    WebElement changePasswordOption;


    @FindBy(css = "input[placeholder='Search']")
    WebElement searchBar;

    @FindBy( xpath="(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='Buzz'])[1]")
    WebElement buzzButtonInMenu;


    @FindBy(xpath=" (//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='Time'])[1]")
    WebElement TimeButtonInMenu;

    @FindBy(xpath="(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='Maintenance'])[1]")
    WebElement maintenanceButtonInMenu;

    @FindBy(xpath="(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='Leave'])[1]")
    WebElement leaveButtonInMenu;

    @FindBy(xpath = "(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='My Info'])[1]")
    WebElement myInfoButtonInMenu;
    @FindBy(xpath = "(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='Recruitment'])[1]")
    WebElement recruitmentButtonInMenu;
    @FindBy(xpath = "(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='Admin'])[1]")
    WebElement adminButtonInMenu;

    @FindBy(xpath = "(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'])[1]")
    WebElement pimButton;
    public String getHraderText(){
        String text = getElementText(mainHeader);
        log.info("user logged in success");
        return text;
    }

    public void ClickOnMenuButton(){
        clickOn(menuBtn);
        log.info("click on Menu button Success");

    }

    public void searchOptionOnSearchBar(String option){
        type(searchBar,option);
        log.info(option+" typed in success");

    }

    public void clickOnBuzzOption(){
        clickOn(buzzButtonInMenu);
        log.info("Buzz clicked on success");
    }


    public void clickOnTimeOption(){
        clickOn(TimeButtonInMenu);
        log.info("Time Button clicked on success");
    }
    public void clickOnMaintenanceOption(){
        clickOn(maintenanceButtonInMenu);
        log.info("Maintenance clicked on success");
    }

    public void clickOnLeaveOption(){
        clickOn(leaveButtonInMenu);
        log.info("Leave clicked on success");
    }

    public void clickOnMyInfoOption(){
        clickOn(myInfoButtonInMenu);
        log.info("My Info clicked on success");
    }
    public void clickOnRecruitmentOption(){
        clickOn(recruitmentButtonInMenu);
        log.info("Recruitment clicked on success");
    }

    public void clickOnPimOption(){
        clickOn(pimButton);
        log.info("PIM page displayed");
    }
    public void clickOnLogoutBtn(){
        clickOn(logoutBtn);
        log.info("click on logout button Success");
    }

    public void clickOnUserDropdwon(){
        clickOn(userDropDownMenu);
        log.info("click on the Menu Success");
    }
    public void clickOnAdminOption(){
        clickOn(adminButtonInMenu);
        log.info("Admin clicked on success");
    }
    public void clickOnChangePassword(){
        clickOn(changePasswordOption);
        log.info("click on the Menu Success");
    }

    public String getUsernameName(){
        return getElementText(userNameOfTheLoggedEmployee);

    }
}
