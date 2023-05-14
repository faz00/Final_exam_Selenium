package luma;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.piit.base.CommonAPI;

public class AddProductReview extends CommonAPI {

    @FindBy(id = "search")
    WebElement PrductSearchInput;

    @FindBy(xpath = "(//li[@class=\"item product product-item\"])[1]")
    WebElement FirstResult;

    @FindBy(id = "tab-label-reviews-title")
    WebElement ReviewTab;

    @FindBy(id = "Rating_5")
    WebElement RatingStar;

    @FindBy(id = "nickname_field")
    WebElement NickName;

    @FindBy(id = "summary_field")
    WebElement Summary;

    @FindBy(id = "review_field")
    WebElement Review;

    @FindBy(css = "[class=\"action submit primary\"]")
    WebElement ReviewSubmitButton;

    @FindBy(xpath = "(//div[@class=\"messages\"])[1]")
    WebElement ReviewSuccess;




    @Test
    public void AddReview(){
        // Verify that user is on Home page
        Assert.assertEquals(getCurrentTitle(), "Home Page");

        // Search a product
        isVisible(PrductSearchInput);
        PrductSearchInput.sendKeys("T Shirt");

        // Press Enter after typing search text
        PrductSearchInput.sendKeys(Keys.ENTER);

        // open first search result
        isVisible(FirstResult);
        FirstResult.click();
        waitFor(5);

        // Open Review Tab
        isVisible(ReviewTab);
        ReviewTab.click();
        waitFor(5);
        isVisible(RatingStar);
        hoverOver(NickName);
        waitFor(5);
        clickOnRatingStar();

        // Type Review
        NickName.sendKeys("luma");
        Summary.sendKeys("Testing Review");
        Review.sendKeys("Testing the review ");
        waitFor(1);
        ReviewSubmitButton.click();
        waitFor(2);
        isVisible(ReviewSuccess);
        Assert.assertTrue(ReviewSuccess.isDisplayed());
    }
}
