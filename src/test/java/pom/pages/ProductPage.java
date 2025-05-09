package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage {
    private final static String BASE_PRODUCT_ID = "add-to-cart-sauce-labs-";

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenu;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutBtn;

    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartBadge;

    public ProductPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isAt() {
        return pageTitle.isDisplayed();
    }

    public LoginPage logout(){
        burgerMenu.click();
        logoutBtn.click();

        return new LoginPage(driver);
    }

    public void addItemToTheCart(String itemName){
        WebElement itemToBeAdded = driver.findElement(By.id(BASE_PRODUCT_ID + itemName));
        itemToBeAdded.click();
    }

    public String getShoppingCartCount(){
        return shoppingCartBadge.getText();
    }
}
