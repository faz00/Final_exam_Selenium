package us.piit.pages.tutorialsNinja;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

public class SearchProductsPage extends CommonAPI {


    Logger log = LogManager.getLogger(SearchProductsPage.class.getName());

    public SearchProductsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='search']")
    WebElement searchField;
    @FindBy(xpath = "//*[@id=\"search\"]/span/button")
    WebElement searchButton;

    @FindBy(xpath = "//*[@id=\"content\"]/p[2]")
    WebElement nonExistingSearchErrMsg;

    public void SearchField(String search) {
        type(searchField, search);
        log.info("the search product enters in success");
    }
    public void SearchButton() {
        clickOn(searchButton);
        log.info("the search button clicked");
    }

    public boolean isErrorMessageDisplayed() {
        // Assert a non-existing product error message is displayed
        log.info("searching for a non existing product creates an error msg");
        return getElementText(nonExistingSearchErrMsg).equals("There is no product that matches the search criteria.");

    }




}





