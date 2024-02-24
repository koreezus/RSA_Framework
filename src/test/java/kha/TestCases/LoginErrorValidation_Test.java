package kha.TestCases;

import kha.TestComponents.BaseTest;
import kha.pages.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class LoginErrorValidation_Test extends BaseTest {
    //wrong credentials test
    @Test(dataProvider = "getBadData")
    public void loginError(HashMap<String,String>badInput) throws IOException {
        login = new LoginPage(driver);
        Assert.assertEquals(login.getErrorMsg(badInput.get("username"),badInput.get("password")),badInput.get("expectedError"));
    }
    //invalid credentials test
    @Test(dataProvider="getInvalidData")
    public void loginErrorValidation(HashMap<String,String>invalidInput){
        login = new LoginPage(driver);
        Assert.assertEquals(login.getInvalidEmail(invalidInput.get("username")),invalidInput.get("expectedUserError"));
        Assert.assertEquals(login.getInvalidPass(invalidInput.get("password")),invalidInput.get("expectedPassError"));
    }
    @DataProvider
    public Object[][] getBadData() throws IOException {
        List<HashMap<String,String>> badData =getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//kha//TestData//InvalidCredentials.json");
        return new Object[][]{{badData.get(0)}};
    }
    @DataProvider
    public Object[][]getInvalidData() throws IOException{
        List<HashMap<String,String>>invalidData=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//kha//TestData//InvalidCredentials.json");
        return new Object[][]{{invalidData.get(1)}};
    }

}
