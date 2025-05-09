package pom.tests;

import baseTest.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.pages.LoginPage;
import pom.pages.ProductPage;

public class SuccessfulLoginTest extends TestUtil {
    @Test
    public void successfulLoginTest(){
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage =
                loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(productPage.isAt(), "Because the username and password shall be accepted");
    }
}
