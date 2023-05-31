package us.piit.pages.nopCommerce;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import us.piit.base.CommonAPI;

import java.time.Duration;

public class CheckOutPage extends CommonAPI {
    protected WebDriver driver;
    Logger log = LogManager.getLogger(LoginPage.class.getName());
    @FindBy(css = "input[id='termsofservice']")
    WebElement TermOfServiceCheckBox;
    @FindBy(id = "checkout")
    WebElement CheckOutBtn;
    @FindBy(css = "[class=\"page checkout-page\"]")
    WebElement CheckOutPage;
    @FindBy(css = "[id=\"BillingNewAddress_CountryId\"]")
    WebElement BillingNewAddress_Country_dropdown;
    @FindBy(xpath = "//option[@value='1']")
    WebElement US_Option;
    @FindBy(id = "BillingNewAddress_City")
    WebElement BillingNewAddress_City;
    @FindBy(id = "BillingNewAddress_Address1")
    WebElement BillingNewAddress_Address1;
    @FindBy(id = "BillingNewAddress_ZipPostalCode")
    WebElement BillingNewAddress_ZipPostalCode;
    @FindBy(id = "BillingNewAddress_PhoneNumber")
    WebElement BillingNewAddress_PhoneNumber;
    @FindBy(css = "[class=\"button-1 new-address-next-step-button\"][name='save']")
    WebElement BillingNewAddress_ContinueBtn;
    @FindBy(css = "[class=\"button-1 shipping-method-next-step-button\"]")
    WebElement shipping_method_next_button;
    @FindBy(css = "[class=\"button-1 payment-method-next-step-button\"]")
    WebElement payment_method_next_button;
    @FindBy(css = "[class=\"button-1 payment-info-next-step-button\"]")
    WebElement payment_info_next_button;
    @FindBy(css = "[class=\"button-1 confirm-order-next-step-button\"]")
    WebElement confirm_order_next_button;
    @FindBy(css = "[class=\"page checkout-page order-completed-page\"]")
    WebElement OrderCompletedPage;
    @FindBy(id = "BillingNewAddress_StateProvinceId")
    WebElement BillingNewAddress_StateProvince_dropdown;
    @FindBy(css = "[id=\"BillingNewAddress_StateProvinceId\"] [value='53']")
    WebElement StateOption;
    @FindBy(css = "[class=\"header-links\"] [href=\"/customer/info\"]")
    WebElement MyAccount;
    @FindBy(css = "[class=\"customer-orders inactive\"] [href=\"/order/history\"]")
    WebElement OrdersMenuOption;
    @FindBy(css = "[class=\"page account-page order-list-page\"]")
    WebElement OrderDetailsPage;
    @FindBy(css = "[class=\"section order-item\"]")
    WebElement order_Details;

    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void CheckOutTheProduct() {
        waitFor(1);
        TermOfServiceCheckBox.click();
        checkCheckBoxIsCh(TermOfServiceCheckBox);
        CheckOutBtn.click();
        waitFor(1);
        waitForElementToBeVisible(CheckOutPage);
        CheckOutPage.isDisplayed();
    }

    public void PlaceOrder() {
        if (driver.findElements(By.id("billing-address-select")).size() > 0) {
            waitFor(1);
            BillingNewAddress_ContinueBtn.click();
        } else {
            isVisible(BillingNewAddress_Country_dropdown);
            isInteractable(BillingNewAddress_Country_dropdown);
            BillingNewAddress_Country_dropdown.click();
            isVisible(US_Option);
            US_Option.click();
            waitFor(1);
            BillingNewAddress_StateProvince_dropdown.click();
            isVisible(StateOption);
            StateOption.click();
            waitFor(1);
            type(BillingNewAddress_City, "Auburn");
            type(BillingNewAddress_Address1, "Abby Road 36830 · Aberdeen Lane 36830 ");
            type(BillingNewAddress_ZipPostalCode, "36801");
            type(BillingNewAddress_PhoneNumber, "+1908327626");
            waitFor(1);
            isInteractable(BillingNewAddress_ContinueBtn);
            BillingNewAddress_ContinueBtn.click();

        }

        waitForElementToBeVisible(shipping_method_next_button);
        isInteractable(shipping_method_next_button);
        shipping_method_next_button.click();

        waitForElementToBeVisible(payment_method_next_button);
        isInteractable(payment_method_next_button);
        payment_method_next_button.click();

        waitForElementToBeVisible(payment_info_next_button);
        isInteractable(payment_info_next_button);
        payment_info_next_button.click();

        waitForElementToBeVisible(confirm_order_next_button);
        isInteractable(confirm_order_next_button);
        confirm_order_next_button.click();

        waitForElementToBeVisible(OrderCompletedPage);
        isVisible(OrderCompletedPage);
        Assert.assertTrue(OrderCompletedPage.getText().contains("Your order has been successfully processed!"));

    }

    public void OrderDetails(){
        isInteractable(MyAccount);
        MyAccount.click();
        waitFor(1);
        isVisible(OrdersMenuOption);
        isInteractable(OrdersMenuOption);
        OrdersMenuOption.click();
        waitFor(1);
        Assert.assertTrue(OrderDetailsPage.isDisplayed());
        Assert.assertTrue(order_Details.isDisplayed());
    }

    public void waitForElementToBeVisible(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.visibilityOf(element));
            Assert.assertTrue(element.isDisplayed());
        } catch (Exception e) {
            log.info(e);
        }
    }
}
