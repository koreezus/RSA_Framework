package kha.pages;

import kha.abstractcomponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage extends AbstractComponents {
    public MainPage(WebDriver driver){
    super(driver);
    this.driver = driver;
    PageFactory.initElements(driver,this);
    }
    @FindBy(css=".mb-3")
    WebElement locator;

    @FindBy(css=".mb-3")
    List<WebElement>products;
    @FindBy(xpath="//button[@routerlink='/dashboard/cart']")
            WebElement myCartBtn;
    By itemName = By.cssSelector("b");
    By cartBtn = By.cssSelector(".card-body .btn:last-of-type");
    By badge = By.cssSelector(".toast-container");
    By spinner = By.cssSelector(".ngx-spinner-overlay");
    public void addItems(String cartItem){
        waitToAppear(locator);
        WebElement product = products.stream().filter(n->n.findElement(itemName).getText().contains(cartItem)).findFirst().orElse(null);
        product.findElement(cartBtn).click();
        waitByToAppear(badge);
        waitByToDisappear(spinner);
    }
}
