package us.piit.pages.luma;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import us.piit.base.CommonAPI;

public class WishListPage extends CommonAPI {
    private WebDriver driver;
    Logger log = LogManager.getLogger(LoginPage.class.getName());
    public WishListPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//li[@class='authorization-link']//a)[1]")
    WebElement SignInLink;

    @FindBy(id = "email")
    WebElement EmailInput;

    @FindBy(id = "pass")
    WebElement PasswordInput;

    @FindBy(xpath = "//button[@class=\"action login primary\"]")
    WebElement SignInButton;

    @FindBy(xpath = "(//li[@class=\"greet welcome\"])[1]")
    WebElement WelcomeGreetMessage;

    @FindBy(xpath = "(//a[@title=\"Add to Wish List\"])[1]")
    WebElement WishListIcon;
    @FindBy(xpath = "(//a[text()='Hoodies and Sweatshirts'])[1]")
    WebElement HoodiesAndSweatshirts;

    @FindBy(xpath = "(//li[@class=\"item product product-item\"])[1]")
    WebElement FirstResult;

    @FindBy(xpath = "(//button[@class=\"action tocart primary\"])[1]")
    WebElement FirstResultAddToCartButton;

    @FindBy(css= "[option-id=\"166\"]")
    WebElement ShirtSize;

    @FindBy(css ="[id=\"option-label-color-93-item-52\"]")
    WebElement ShirtColor;

    @FindBy(css = "[id=\"product-addtocart-button\"]")
    WebElement AddToCartButton;

    @FindBy(css = "[data-ui-id=\"message-success\"]")
    WebElement CartSuccessMessage;

    @FindBy(css = "[id=\"ui-id-8\"]")
    WebElement SaleTab;

    @FindBy(css = "[title=\"Remove This Item\"]")
    WebElement RemoveWhishListProduct;

    @FindBy(xpath = "//span[text()='You have no items in your wish list.']")
    WebElement NoItemInWishList;



    public void clickOnSaleTab(){
        Assert.assertTrue(SaleTab.isEnabled());
        SaleTab.click();
        log.info("Sale tab clicked success");
    }


    public void clickOnHoodiesAndShirts(){
        Assert.assertTrue(HoodiesAndSweatshirts.isEnabled());
        HoodiesAndSweatshirts.click();
        log.info("Hoodies & T-shirts clicked success");
    }

    public void AddProductToWishList(){
        hoverOver(FirstResult);
        WishListIcon.isDisplayed();
        WishListIcon.click();
        waitFor(2);
    }

    public void hoverOver(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }
}
