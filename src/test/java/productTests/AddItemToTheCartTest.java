package productTests;

import baseTest.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddItemToTheCartTest extends TestUtil {
    private final static String BASE_PRODUCT_ID = "add-to-cart-sauce-labs-";

    @Test(dataProvider = "shoppingItems")
    public void addProductToTheCart(String itemToAdd){
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.click();
        userNameInput.clear();
        userNameInput.sendKeys("performance_glitch_user");

        WebElement passwordInput = driver.findElement(By.cssSelector("[data-test=password]"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.cssSelector("[name=login-button]"));
        loginBtn.click();

        WebElement itemToBeAdded = driver.findElement(By.id(BASE_PRODUCT_ID + itemToAdd));
        itemToBeAdded.click();

        WebElement shoppingCartBadge = driver.findElement(By.className("shopping_cart_badge"));

        Assert.assertEquals(shoppingCartBadge.getText(), "1");
    }

    @DataProvider(name = "shoppingItems")
    public Object[] getShoppingItems(){
        return new Object[]{
                "bike-light",
                "fleece-jacket",
                "backpack"
        };
    }
}
