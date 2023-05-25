package us.piit.pages.orangeHRM;

import io.opentelemetry.api.baggage.propagation.W3CBaggagePropagator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

public class TimePage extends CommonAPI {
    Logger log = LogManager.getLogger(TimePage.class.getName());
    public TimePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath="//div[contains(text(),'pnt')]")
    WebElement scrollDown;


    public void scroll(){
        scrollToElement(scrollDown);
    }
}
