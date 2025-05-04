package first.test;

import baseTest.TestUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class UnSuccessfulLoginTest extends TestUtil {

    @Test(dataProvider = "wrongUsers")
    public void unSuccessfulLoginTest(String username, String password){

        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.click();
        userNameInput.clear();
        userNameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.cssSelector("[data-test=password]"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.cssSelector("[name=login-button]"));
        loginBtn.click();

        WebElement errorMsg = driver.findElement(By.cssSelector(".error-message-container.error"));
        Assert.assertTrue(errorMsg.isDisplayed());
    }

    @DataProvider(name = "wrongUsers")
    public Object[][] readWrongUsers(){
        try {
            CSVReader csvReader =
                    new CSVReader(new FileReader("src/test/resources/wrongUsers.csv"));

            List<String[]> csvData = csvReader.readAll();
            Object[][] csvDataResult = new Object[csvData.size()][2];

            for (int i = 0; i < csvData.size(); i++){
                csvDataResult[i] = csvData.get(i);
            }

            return csvDataResult;
        }catch (IOException e){
            System.out.println(e);
            return null;
        }catch (CsvException e){
            System.out.println(e);
            return null;
        }
    }
}
