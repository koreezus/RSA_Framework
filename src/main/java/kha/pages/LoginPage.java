package kha.pages;

import kha.abstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractComponents {
    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id="userEmail")
    WebElement user;
    @FindBy(id="userPassword")
    WebElement pass;
    @FindBy(id="login")
    WebElement submitBtn;
    @FindBy(css=".toast-error")
    WebElement errorMsg;
    @FindBy(xpath = "//div[contains(text(),'*Enter')]")
    WebElement invalidEmailMsg;
    @FindBy(xpath="//div[contains(text(),'*Password')]")
    WebElement invalidPassMsg;
    @FindBy(xpath="//button[@routerlink='/dashboard/']")
    WebElement navbar;
    public void getURL() {
        driver.get("https://rahulshettyacademy.com/client/");
        waitToAppear(submitBtn);
    }
    public MainPage submitCredentials(String username, String password){
        user.sendKeys(username);
        pass.sendKeys(password);
        submitBtn.click();
        waitToAppear(navbar);
        return new MainPage(driver);
    }
    public String getErrorMsg(String badUser, String badPass){
        user.sendKeys(badUser);
        pass.sendKeys(badPass);
        submitBtn.click();
        waitToAppear(errorMsg);
        return errorMsg.getText();
    }
    public String getInvalidEmail(String invalidUser){
        user.sendKeys(invalidUser);
        submitBtn.click();
        waitToAppear(invalidEmailMsg);
        return invalidEmailMsg.getText();
    }
    public String getInvalidPass(String emptyPass){
        pass.sendKeys(emptyPass);
        submitBtn.click();
        waitToAppear(invalidPassMsg);
        return invalidPassMsg.getText();
    }
}
