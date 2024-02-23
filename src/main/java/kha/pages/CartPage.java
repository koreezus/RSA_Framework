package kha.pages;

import kha.abstractcomponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponents {
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css=".cartSection h3")
    List<WebElement> cartItems;
    @FindBy(css=".subtotal .btn")
    WebElement checkoutBtn;
    By pageWait = By.cssSelector(".cartSection");
    public Boolean checkCart(String myProduct){
        waitByToAppear(pageWait);
        return cartItems.stream().anyMatch(n->n.getText().contains(myProduct));
    }
    public CheckoutPage toCheckout(){
        checkoutBtn.click();
        return new CheckoutPage(driver);
    }

}
