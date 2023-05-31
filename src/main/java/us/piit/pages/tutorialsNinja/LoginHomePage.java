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
    @FindBy(xpath = "//h2[normalize-space()='My Account']")
    WebElement myAccountLoginHompage;
    @FindBy(xpath =" //a[normalize-space()='Account']")
    WebElement accountLink;
    @FindBy(linkText = "Edit your account information")
    WebElement editAccountLink;


    public String homePageHeader(){

        log.info("the page header title is:"+accountHeaderPage.getText());

        return accountHeaderPage.getText();
    }

    public String getLoginURL(WebDriver driver){
           log.info("the url of the page is displayed");
        return driver.getCurrentUrl();
    }
    public boolean isHomePagHeaderDisplayed(){
        log.info("the Home Page Header is displayed "+accountHeaderPage.isDisplayed());
        return accountHeaderPage.isDisplayed();
    }
    public boolean isAccountLinkDisplayed(){
       log.info("the account link is displayed");
        return accountLink.isDisplayed();
    }

public String getLgnHmPgHdr(){
        log.info("the login home page header is displayed");
        return myAccountLoginHompage.getText();
}
public boolean IsEditAccountLinkDsp(){
        log.info("Edit your account information link is displayed");
        return editAccountLink.isDisplayed();
}
}



