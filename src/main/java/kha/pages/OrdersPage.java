package kha.pages;

import kha.abstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractComponents {
    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath="//tr//td[2]")
    List<WebElement> tableNameCell;
    @FindBy(css=".btn.btn-primary")
    WebElement tableElement;
    public Boolean getProductName(String productName){
        waitToAppear(tableElement);

        return tableNameCell.stream().anyMatch(n->n.getText().contains(productName));
    }
}
