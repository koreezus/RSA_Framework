package kha.TestCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import kha.TestComponents.BaseTest;
import kha.pages.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.testng.TestRunner.PriorityWeight.dependsOnMethods;

public class SubmitOrder_Test extends BaseTest {
    public String testProduct = "ADIDAS ORIGINAL";
    public String testLocation = "India";
    public String confirmationMessage="THANKYOU FOR THE ORDER.";

    @Test(dataProvider="getData",groups={"PurchaseOrder"})
    public void submitOrder(HashMap<String,String>input) throws IOException {

        MainPage main = login.submitCredentials(input.get("username"),input.get("password"));
        main.addItems(input.get("product"));

        CartPage cart = main.clickCart();
        Assert.assertTrue(cart.checkCart(input.get("product")));

        CheckoutPage checkout = cart.toCheckout();
        checkout.insertInfo(input.get("testLocation"));

        ConfirmationPage confirmation = checkout.clickConfirm();
        Assert.assertEquals(confirmation.getOrderConfirmation(),confirmationMessage);
        
    }
    @Test(dataProvider = "getData",dependsOnMethods= {"submitOrder"})
    public void OrderHistoryTest(HashMap<String,String>input){
        MainPage main = login.submitCredentials(input.get("username"),input.get("password"));
        OrdersPage orders = main.clickOrder();
        Assert.assertTrue(orders.getProductName(input.get("product")));
    }
    @DataProvider
    public Object[][] getData() throws IOException {
    List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//kha//TestData//PurchaseOrder.json");
    return new Object[][]{{data.get(0)},{data.get(1)}};
   }
}
