import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    public Properties properties;
    public WebDriver initialiseDriver() throws IOException {
        properties = new Properties();
        FileInputStream filePath = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/config/config.properties");
        properties.load(filePath);
        String browserName = properties.getProperty("browser");

        if(browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver", properties.getProperty("chromedriverpath"));
            driver = new ChromeDriver();
        }else if(browserName.equals("firefox")){
            //driver path could be provided from here
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

}