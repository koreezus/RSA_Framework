package kha.pages;

import kha.abstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends AbstractComponents {
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css=".details__item")
    WebElement pageItem;
    @FindBy(css="input[placeholder='Select Country']")
    WebElement countryDropdown;
    @FindBy(css=".ta-results")
    WebElement results;
    @FindBy(css=".ta-item")
    List<WebElement> countryList;
    @FindBy(css=".btnn")
    WebElement placeOrderBtn;
    public void insertInfo(String myCountry){
        waitToAppear(pageItem);
        countryDropdown.sendKeys(myCountry.substring(0,3));
        waitToAppear(results);
        WebElement countryItem = countryList.stream().filter(n->n.getText().equalsIgnoreCase(myCountry)).findFirst().orElse(null);
        countryItem.click();
    }
    public ConfirmationPage clickConfirm(){
        placeOrderBtn.click();
        return new ConfirmationPage(driver);
    }
}
