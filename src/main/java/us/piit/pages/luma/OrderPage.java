package us.piit.pages.luma;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import us.piit.base.CommonAPI;

public class OrderPage extends CommonAPI {

    public OrderPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[class=\"action showcart\"]")
    WebElement ShowCart;

    @FindBy(css = "[id=\"top-cart-btn-checkout\"]")
    WebElement ProceedToCheckOutBtn;

    @FindBy(id = "#shipping")
    WebElement ShippingAddressPage;

    @FindBy(name = "street[0]")
    WebElement StreetAddress;

    @FindBy(name = "city")
    WebElement City;
    
    @FindBy(css = "[name=\"region_id\"]")
    WebElement State_dropdown;

    @FindBy(css = "[data-title=\"Alabama\"]")
    WebElement State_option;

    @FindBy(name = "postcode")
    WebElement ZipCode;

    @FindBy(name = "country_id")
    WebElement Country_dropdown;

    @FindBy(css = "[data-title=\"United States\"]")
    WebElement Country_Option;

    @FindBy(name = "telephone")
    WebElement PhoneNumber;

    @FindBy(xpath = "(//*[@data-bind=\"click: element.selectShippingMethod\"]//input)[1]")
    WebElement shipping_Method_RadioBtn;

    @FindBy(css = "[class=\"button action continue primary\"]")
    WebElement shipping_NextBtn;

    @FindBy(css = "[class=\"action primary checkout\"]")
    WebElement PlaceOrderBTN;

    @FindBy(xpath = "//span[text()='Thank you for your purchase!']")
    WebElement OrderSuccessMessage;

    @FindBy(xpath = "(//button[@data-action=\"customer-menu-toggle\"])[1]")
    WebElement SignOutDropDown;

    @FindBy(xpath = "(//*[@href=\"https://magento.softwaretestingboard.com/customer/account/\"])[1]")
    WebElement myAccountBtn;

    @FindBy(css = "[class=\"sidebar sidebar-main\"] [href=\"https://magento.softwaretestingboard.com/sales/order/history/\"]")
    WebElement myOrderMenuOption;

    @FindBy(css = "[class=\"table-wrapper orders-history\"]")
    WebElement ordersList;


    public void proceedTOCheckout(){
        hoverOver(ShowCart);
        ShowCart.click();
        isVisible(ProceedToCheckOutBtn);
        ProceedToCheckOutBtn.click();
        waitFor(3);
    }

    public void place_order(){
        shipping_Method_RadioBtn.isEnabled();
        shipping_Method_RadioBtn.click();
        waitFor(1);
        shipping_NextBtn.click();
        waitFor(2);
        isVisible(PlaceOrderBTN);
        waitFor(5);
        PlaceOrderBTN.click();
        waitFor(3);
        Assert.assertTrue(OrderSuccessMessage.isDisplayed());
    }

    public void view_placed_orders(){
        isInteractable(SignOutDropDown);
        SignOutDropDown.click();
        isInteractable(myAccountBtn);
        myAccountBtn.click();
        waitFor(2);
        isInteractable(myOrderMenuOption);
        myOrderMenuOption.click();
        waitFor(2);
        isVisible(ordersList);
        Assert.assertTrue(ordersList.isDisplayed());
    }


    public void hoverOver(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }
}
