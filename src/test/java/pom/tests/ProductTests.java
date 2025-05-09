package pom.tests;

import baseTest.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pom.pages.LoginPage;
import pom.pages.ProductPage;

public class ProductTests extends TestUtil {

    @Test
    public void addItemsToTheCartTest(){
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage =
                loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(productPage.isAt(),
                "Because the username and password shall be accepted");

        SoftAssert softAssert = new SoftAssert();

        productPage.addItemToTheCart("bike-light");
        softAssert.assertEquals(productPage.getShoppingCartCount(), "2");

        productPage.addItemToTheCart("fleece-jacket");
        softAssert.assertEquals(productPage.getShoppingCartCount(), "2");

        productPage.addItemToTheCart("backpack");
        softAssert.assertEquals(productPage.getShoppingCartCount(), "4");

        softAssert.assertAll();
    }
}
