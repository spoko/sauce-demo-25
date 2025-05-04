package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestUtil {
    public WebDriver driver;
    private String browser, targetUrl;
    private int implicitWait;

    @BeforeMethod
    public void setDriverAndNavigateToTargetUrl(){
        readConfig("src/test/resources/config.properties");
        setupDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofMillis(implicitWait));
        driver.get(targetUrl);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    private void readConfig(String pathToFile){
        try {
            FileInputStream fileInputStream = new FileInputStream(pathToFile);
            Properties properties = new Properties();
            properties.load(fileInputStream);

            targetUrl = properties.getProperty("url");
            browser = properties.getProperty("browser");
            implicitWait = Integer.parseInt(properties.getProperty("implicitWait"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setupDriver(){
        switch (browser){
            case "firefox":
                driver = setupFirefox();
                break;
            case "mozilla":
                driver = setupFirefox();
                break;
            case "edge":
                driver = setupEdge();
                break;
            default:
                driver = setupChromeDriver();
        }
    }

    private WebDriver setupFirefox(){
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private WebDriver setupEdge(){
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }

    private WebDriver setupChromeDriver(){
        WebDriverManager.chromedriver().setup();
        return  new ChromeDriver();
    }
}
