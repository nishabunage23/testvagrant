package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;

public class Pages {
    public WebDriver driver;
    private By searchBox = By.name("query");
    private By moreDetails = By.className("cur-con-weather-card__cta");
    private By pune = By.xpath("//*[text()='Pune, Maharashtra, IN']");
    private By temp = By.xpath("//*[@class='temp']");

    HashMap<String, String> weatherDetails = new HashMap<String, String>();

    public Pages(WebDriver driver1) {
        this.driver = driver1;
    }
    public WebElement getSearchBoxSelector() {
        return  driver.findElement(searchBox);
    }
    public WebElement getMoreDetailsSelector() {
        return  driver.findElement(moreDetails);
    }
    public WebElement getPuneSelector() {
        return  driver.findElement(pune);
    }
    public WebElement getTempSelector() {
        return  driver.findElement(temp);
    }

    public HashMap<String, String> getAllWetherComp(){
        String key3=null;
        String value3=null;
        for(int i =1; i<6;i++) {

            for(int j=1;j<2;j++) {
                key3 = driver.findElement(By.xpath("//div[(@class='detail-item spaced-content')]["+i+"]//div["+j+"]")).getText();
                value3 = driver.findElement(By.xpath("//div[(@class='detail-item spaced-content')]["+i+"]//div["+(j+1)+"]")).getText();
              //  System.out.println("key:" + key3 + " and value is " + value3);
            }
            weatherDetails.put(key3, value3);

        }
        return weatherDetails;
    }

    public HashMap<String, String> getAll(){
        ////div[starts-with(@class,'detail-item spaced-content')]//div
        String key2 = null;
        String value2 = null;
        for(int i=1;i<23;i++){
            if(i%2!=0){
              String  keyValue = driver.findElement(By.xpath("//div[starts-with(@class,'detail-item spaced-content')]["+i+"]")).getText();
            }
            System.out.println("key:" + key2 + " and value is " + value2);
            weatherDetails.put(key2, value2);
        }
        return weatherDetails;
    }
}
