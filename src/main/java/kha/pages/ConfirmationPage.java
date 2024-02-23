package kha.pages;

import kha.abstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmationPage extends AbstractComponents {
    @FindBy(css=".hero-primary")
    WebElement confirmationText;
    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public String getOrderConfirmation(){
        waitToAppear(confirmationText);
        return confirmationText.getText();
    }
}
