package us.piit.pages.tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

public class LoginHomePage extends CommonAPI {

    Logger log = LogManager.getLogger(LoginHomePage.class.getName());
    public LoginHomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath ="//*[@id=\"content\"]/h2[1]" )
    WebElement accountHeaderPage;
    @FindBy(xpath = "//*[@id='content']/h2[1]")
    WebElement myAccoubtLoginHompage;
    @FindBy(xpath =" //a[normalize-space()='Account']")
    WebElement accountLink;
    public String homePageHeader(){

        log.info("the page header title is:"+accountHeaderPage.getText());

        return accountHeaderPage.getText();
    }

    public String getLoginURL(){
        return driver.getCurrentUrl();
    }
    public boolean isHomePagHeaderDisplayed(){
        log.info("the Home Page Header is displayed "+accountHeaderPage.isDisplayed());
        return accountHeaderPage.isDisplayed();
    }
    public boolean isAccountLinkDisplayed(){

        return accountLink.isDisplayed();
    }


}






