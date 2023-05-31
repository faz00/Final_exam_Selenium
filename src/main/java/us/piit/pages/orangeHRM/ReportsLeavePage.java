package us.piit.pages.orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

public class ReportsLeavePage extends CommonAPI {

    Logger log = LogManager.getLogger(LeavePage.class.getName());
    public ReportsLeavePage(WebDriver driver){

        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "(//i[@class='oxd-icon bi-chevron-down'])[2]")
    WebElement reportBtn;

    @FindBy(css = "li[class='--active oxd-topbar-body-nav-tab --parent'] li:nth-child(1) a:nth-child(1)")
    WebElement leaveEntitlementsBtn;
    @FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > span:nth-child(2)")
    WebElement employeeCheckBox;

    @FindBy(css="input[placeholder='Type for hints...']")
    WebElement employeeName;

    @FindBy(css="button[type='submit']")
    WebElement generateReportBtn;

    @FindBy(xpath = "//div[contains(text(),'Leave Type')]")
    WebElement actualReport;

    public void clickOnReportBtn(){
        clickOn(reportBtn);
        log.info("click on report on success");
    }

    public void clickOnLeaveEntitlementBtn(){
        clickOn(leaveEntitlementsBtn);
        log.info("Leave Entitlements and Usage Report clicked on success");
    }
    public void checkEmployeeCheckbox(){
        clickOn(employeeCheckBox);
        log.info("Employee checkbox Checked");
    }
    public void chooseEmployeeName(String name){
        type(employeeName,name);
        waitFor(5);
        employeeName.sendKeys(Keys.ARROW_DOWN);
        waitFor(5);
        employeeName.sendKeys(Keys.ENTER);
    }

    public void clickOnGenerateBtn(){
        clickOn(generateReportBtn);
        log.info("clicked on submit ");
    }

    public String getActualReport(){
        return getElementText(actualReport);
    }
}
