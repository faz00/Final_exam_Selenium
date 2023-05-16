package us.piit.pages.orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import us.piit.base.CommonAPI;

public class BuzzPage extends CommonAPI {
    Logger log = LogManager.getLogger(DashbordPage.class.getName());
    public BuzzPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }


    @FindBy(css="textarea[placeholder=\"What's on your mind?\"]")
    WebElement postFieldToTypeText;

    @FindBy(css = "button[type='submit']")
    WebElement submitThePostBtn;

    @FindBy(css = "body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > p:nth-child(1)")
    WebElement textShared;

    @FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > li:nth-child(1) > button:nth-child(1) > i:nth-child(1)")
    WebElement threeDotesButtonOfSharedPost;

    @FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > li:nth-child(1) > ul:nth-child(2) > li:nth-child(2) > p:nth-child(2)")
    WebElement editOption;

    @FindBy(xpath = "//input[@type='file']")
    WebElement inputFieldOfTheDialog;

    @FindBy(css = "div[class='oxd-form-actions orangehrm-buzz-post-modal-actions'] button[type='submit']")
    WebElement submitEditPostBtn;


    @FindBy(xpath = "(//p[normalize-space()='Delete Post'])[1]")
    WebElement deletePostOption;

    @FindBy(css = "button[class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']")
    WebElement yesDeletePostButton;

    @FindBy(xpath = "//button[normalize-space()='Most Commented Posts']")
    WebElement mostCommentedPost;

    @FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > button:nth-child(2) > i:nth-child(1)")
    WebElement commentIcon;

    @FindBy(css="input[placeholder='Write your comment...']")
    WebElement commentTextField;

    @FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(5) > div:nth-child(6) > div:nth-child(2) > div:nth-child(1) > span:nth-child(2)")
    WebElement comment;
    public void clickOnSubmitBtn(){
        clickOn(submitThePostBtn);
        log.info("button clicked on success");
    }

    public  void typeWhatInYourMind(String text){
        type(postFieldToTypeText,text);
        log.info("Text typed in success");
    }

    public String getSharedPostText(){
        String text = getElementText(textShared);
        log.info("get shared message text success");
        return text;
    }



    public void clickOnThreeDotes(){
        clickOn(threeDotesButtonOfSharedPost);
        log.info("three dotes button clicked on success");
    }
    public void clickOnEditOption(){
        clickOn(editOption);
        log.info("Click on Edit button success");
    }
    public void setThePathOfImage(String path){
        inputFieldOfTheDialog.sendKeys(path);
        log.info("path sent with success");
    }

    public void clickOnSubmitEditPost(){
        clickOn(submitEditPostBtn);
        log.info("button clicked on success");
    }
    public void clickOnDeleteBtn(){
        clickOn(deletePostOption);
        log.info("Click on delete button success");
    }
    public void clickOnYesDelte(){
        clickOn(yesDeletePostButton);
        log.info("Click on yes success");
    }

    public void clickOnMostCommentedBtn(){
        clickOn(mostCommentedPost);
        log.info("Button clicked on success");

    }
    public void clickOnCommentIcon(){
        clickOn(commentIcon);
        log.info("click on comment success");
    }
    public void shareComment(String text){
        sendKey(commentTextField ,text);
        commentTextField.sendKeys(Keys.ENTER);
        log.info("comment Shared succeess");
    }

    public String getTheComment(){
        String text = getElementText(comment);
        log.info("get comment success");
        return text;
    }


}
