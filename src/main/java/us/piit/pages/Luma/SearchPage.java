package us.piit.pages.Luma;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import us.piit.base.CommonAPI;
import us.piit.utility.Utility;

import java.util.Properties;

public class SearchPage extends CommonAPI {

    Logger log = LogManager.getLogger(LoginPage.class.getName());
    protected WebDriver driver;
    public SearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    Properties prop = Utility.loadProperties();
    String ValidEmail = generateTestEmail();
    String validPassword = prop.getProperty("test.password");
    String firstName = prop.getProperty("first.name");
    String lastName = prop.getProperty("last.name");

    @FindBy(id = "search")
    WebElement searchInput;

    @FindBy(css = "[data-ui-id=\"page-title-wrapper\"]")
    WebElement SearchResultHeader;

    @FindBy(css = "[class=\"search results\"] dl")
    WebElement searchResultsection;

    @FindBy(xpath = "(//div[@class=\"toolbar toolbar-products\"])[2]")
    WebElement pageFooter;

    @FindBy(css = "button[class=\"action search\"]")
    WebElement searchIcon;

    @FindBy(xpath = "(//strong[@class=\"product name product-item-name\"]//a[@class=\"product-item-link\"])[1]")
    WebElement FirstProductText;

    @FindBy(css = "[data-role=\"direction-switcher\"]")
    WebElement SortIcon;

    @FindBy(css = "[id=\"ui-id-3\"]")
    WebElement WhatsNewTab;

    @FindBy(xpath = "//div[@class=\"content-heading\"]//h2[text()=\"Luma's Latest\"]")
    WebElement LatestProductSection;

    @FindBy(css = "[id=\"ui-id-4\"]")
    WebElement WomenTab;

    @FindBy(css = "[id=\"ui-id-9\"]")
    WebElement OptionTops;

    @FindBy(css = "[id=\"ui-id-11\"]")
    WebElement OptionJackets;

    public void enterProduct(String product){
        searchInput.click();
        searchInput.sendKeys(product);
        waitFor(1);
        log.info("product name typed success");
    }

    public void clickOnSearchIcon(){
        searchIcon.click();
        waitFor(1);
        log.info("search Icon clicked success");
    }

    public void verifyThatSearchResults(String product){
        waitFor(1);
        Assert.assertTrue(SearchResultHeader.isDisplayed());
        Assert.assertTrue(SearchResultHeader.getText().contains(product));
        waitFor(1);
    }

    public void scrollFullPageToSeeAllProducts(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})", pageFooter);
        waitFor(1);
        Assert.assertTrue(pageFooter.isDisplayed());
        Assert.assertTrue(pageFooter.getText().contains("page"));
    }

    public String FirtProductNameText(){
        hoverOver(FirstProductText);
        Assert.assertTrue(FirstProductText.isDisplayed());
        return FirstProductText.getText();
    }

    public void SelectJackets(){
        waitFor(1);
        Assert.assertTrue(WomenTab.isDisplayed());
        hoverOver(WomenTab);
        OptionTops.isDisplayed();
        hoverOver(OptionTops);
        OptionJackets.isDisplayed();
        OptionJackets.click();
        waitFor(1);
    }

    public boolean relevantProductsDisplayed(){
        SearchResultHeader.isDisplayed();
        return SearchResultHeader.getText().contains("Jackets");
    }

    public void SortProducts(){
        Assert.assertTrue(SortIcon.getAttribute("title").equalsIgnoreCase("Set Ascending Direction"));
        Assert.assertTrue(SortIcon.isEnabled());
        SortIcon.click();
        waitFor(1);
    }

    public boolean VerifyProductsSorted (){
        waitFor(2);
        return SortIcon.getAttribute("title").equalsIgnoreCase("Set Descending Direction");
    }

    public void hoverOver(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public void clickOnWhatsNewTab(){
        Assert.assertTrue(WhatsNewTab.isEnabled());
        WhatsNewTab.click();
        waitFor(1);
        Assert.assertTrue(SearchResultHeader.isDisplayed());
    }

    public void SeeLatestProduct(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})", LatestProductSection);
        waitFor(1);
        Assert.assertTrue(LatestProductSection.isDisplayed());
        Assert.assertTrue(LatestProductSection.getText().contains("Luma's Latest"));
    }
}
