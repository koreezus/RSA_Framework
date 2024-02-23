package kha.abstractcomponents;

import kha.pages.OrdersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import kha.pages.CartPage;

import java.time.Duration;

public class AbstractComponents {
    public WebDriver driver;
    WebDriverWait wait;
    @FindBy(xpath="//button[@routerlink='/dashboard/cart']")
    WebElement myCartBtn;

    @FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
    WebElement myOrdersBtn;

    public AbstractComponents(WebDriver driver){
        this.driver=driver;
        this.wait= new WebDriverWait(driver,Duration.ofSeconds(3L));
        PageFactory.initElements(driver,this);
    }

    public void waitToAppear(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitToDisappear(WebElement element){
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    public void waitByToAppear(By element){
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    public void waitByToDisappear(By element){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }
    public CartPage clickCart(){
        myCartBtn.click();
        return new CartPage(driver);
    }
    public OrdersPage clickOrder(){
        waitToAppear(myOrdersBtn);
        myOrdersBtn.click();
        return new OrdersPage(driver);

    }
}
