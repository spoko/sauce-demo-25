package pom.tests;

import baseTest.TestUtil;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pom.pages.LoginPage;
import pom.pages.ProductPage;

public class SuccessfulLogoutTest extends TestUtil {
    @Test
    public void successfulLogoutTest() {
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login("standard_user1", "secret_sauce");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(productPage.isAt());

        productPage.logout();

        softAssert.assertTrue(loginPage.isAt());
    }
}
