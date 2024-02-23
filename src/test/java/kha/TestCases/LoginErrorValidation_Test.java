package kha.TestCases;

import kha.TestComponents.BaseTest;
import kha.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginErrorValidation_Test extends BaseTest {
    @Test
    public void loginError() throws IOException {
        //wrong credentials
        login = new LoginPage(driver);
        Assert.assertEquals(login.getErrorMsg("kore@gmail.com","password"),"Incorrect email or password.");
    }
    @Test
    public void loginErrorValidation(){
        login = new LoginPage(driver);
        Assert.assertEquals(login.getInvalidEmail("korey@.com"),"*Enter Valid Email");
        Assert.assertEquals(login.getInvalidPass(""),"*Password is required");
    }
}
