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

    @FindBy(xpath="(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='Maintenance'])[1]")
    WebElement maintenanceButtonInMenu;
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
        log.info("Buzz page displayed");
         }


    public void clickOnMaintenanceOption(){
        clickOn(maintenanceButtonInMenu);
        log.info("Maintenance page displayed");
    }
    public void clickOnLogoutBtn(){
        clickOn(logoutBtn);
        log.info("click on logout button Success");
    }

    public void clickOnUserDropdwon(){
       clickOn(userDropDownMenu);
        log.info("click on the Menu Success");
    }
    public void clickOnChangePassword(){
        clickOn(changePasswordOption);
        log.info("click on the Menu Success");
    }



}
