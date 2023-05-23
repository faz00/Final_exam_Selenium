package us.piit.pages.luma;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import us.piit.base.CommonAPI;

public class ProductPage extends CommonAPI {
    public ProductPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//*[@class='modes']//*[@title=\"Grid\"])[1]")
    WebElement gridViewIcon;

    @FindBy(xpath = "(//*[@class='modes']//*[@title=\"List\"])[1]")
    WebElement ListViewIcon;

    @FindBy(xpath = "(//li[@class=\"product-item\"])[1]")
    WebElement first_products_ON_HomePage;

    @FindBy(xpath = "(//li[@class=\"product-item\"])[2]")
    WebElement second_products_ON_HomePage;

    @FindBy(xpath = "(//*[@class='product-item']//*[@class=\"action tocompare\"])[1]")
    WebElement first_comp_icon;

    @FindBy(xpath = "(//*[@class='product-item']//*[@class=\"action tocompare\"])[2]")
    WebElement second_comp_icon;

    @FindBy(css = "[class=\"item link compare\"] a")
    WebElement compareProductLink;

    @FindBy(id = "product-comparison")
    WebElement product_comparison_table;

    @FindBy(css = "a[title=\"Print This Page\"]")
    WebElement print_this_page_link;

    @FindBy(css = "[class=\"action-primary action-accept\"]")
    WebElement OKBtn;

    @FindBy(id = "compare-clear-all")
    WebElement ClearAllCompareListBtn;

    @FindBy(css = "[data-ui-id=\"message-success\"]")
    WebElement emptyListMessage;

    @FindBy(xpath = "(//button[@data-action=\"customer-menu-toggle\"])[1]")
    WebElement SignOutDropDown;

    @FindBy(xpath = "(//*[@href=\"https://magento.softwaretestingboard.com/customer/account/\"])[1]")
    WebElement myAccountBtn;


    public void changeToListView(){
        isInteractable(ListViewIcon);
        waitFor(2);
    }

    public void changeToGridView(){
        isInteractable(gridViewIcon);
        waitFor(2);
    }

    public void Add_product_to_compare_list(){
        waitFor(1);
        scrollToElement(first_products_ON_HomePage);
        hoverOver(first_products_ON_HomePage);
        isInteractable(first_comp_icon);
        first_comp_icon.click();
        waitFor(1);
        hoverOver(second_products_ON_HomePage);
        isVisible(second_comp_icon);
        second_comp_icon.click();
        waitFor(1);
    }

    public void compare_products(){
        isVisible(compareProductLink);
        compareProductLink.click();
        waitFor(1);
        isVisible(product_comparison_table);
        Assert.assertTrue(product_comparison_table.isDisplayed());
    }

    public void hoverOver(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public void scrollToElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})", element);
        waitFor(1);
    }

    public void removeProductsFromComparisonList() {
        waitFor(1);
        SignOutDropDown.click();
        waitFor(1);
        myAccountBtn.click();
        waitFor(1);
        hoverOver(ClearAllCompareListBtn);
        isVisible(ClearAllCompareListBtn);
        ClearAllCompareListBtn.click();
        waitFor(1);
        OKBtn.click();
        waitFor(1);
        isVisible(emptyListMessage);
        emptyListMessage.isDisplayed();
        Assert.assertTrue(emptyListMessage.getText().contains("You cleared the comparison list."));
    }

}
