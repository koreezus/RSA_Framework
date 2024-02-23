package kha.TestCases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.*;
import java.util.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class standaloneTest {
    public static void main(String[] args) {

        //prerequisites
        String userName = "korey.subscribes10@gmail.com";
        String password = "1LONGpassword";
        String myProduct = "ADIDAS";
        String myCountry = "India";
        String confirmationMessage = "THANKYOU FOR THE ORDER.";
        WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3L));
        driver.get("https://rahulshettyacademy.com/client/");


        //Login Page
        //wait until button element loaded on page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
        //enter username and password
        driver.findElement(By.id("userEmail")).sendKeys(userName);
        driver.findElement(By.id("userPassword")).sendKeys(password);
        //click login
        driver.findElement(By.id("login")).click();

        //Main Page
        //get list of all items on page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement product = products.stream().filter(n->n.findElement(By.cssSelector("b")).getText().contains(myProduct)).findFirst().orElse(null);
        //click add to cart button
        product.findElement(By.cssSelector(".card-body .btn:last-of-type")).click();
        //wait for badge to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-container")));
        //wait for spinner to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
        driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

        //My Cart Page
        //obtain list of cart items
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection")));
        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
        //make sure selected product in cart
        Boolean match = cartItems.stream().anyMatch(n->n.getText().contains(myProduct));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector(".subtotal .btn")).click();

        //Checkout Page
        //wait for page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".details__item")));
        //insert country code into dropdown
        driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys(myCountry.substring(0,3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        List<WebElement> countryList = driver.findElements(By.cssSelector(".ta-item"));
        WebElement countryItem = countryList.stream().filter(n->n.getText().equalsIgnoreCase(myCountry)).findFirst().orElse(null);
        countryItem.click();
        driver.findElement(By.cssSelector(".btnn")).click();

        //Order Confirmation Page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
        Assert.assertEquals(confirmationMessage,driver.findElement(By.cssSelector(".hero-primary")).getText());
        driver.close();
    }
}
