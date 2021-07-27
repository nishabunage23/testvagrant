import io.restassured.path.json.JsonPath;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.Pages;
import utils.Utils;

import java.io.IOException;


public class CompareWeather extends BaseTest {
    Pages pages;
    Utils utils;
    WebDriver driver;
    GetApiResponse apiResponse;

    int varianceFactorTemp;

    //Component for UI
    int temperatureFromUI;
    int humidityFromUI;
    int windSpeedFromUI;
    int windGustFromUI;

    //Component from API
    int tempFromAPI;
    int humidityFromAPI;
    int windSpeedFromAPI;
    int windGustFromAPI;

    @Test(priority = 1)
    public void getWeatherFromUI() throws IOException, InterruptedException {
        driver = initialiseDriver();
        driver.get(properties.getProperty("url"));
        pages = new Pages(driver);

        //Navigate to details page
        pages.getSearchBoxSelector().sendKeys("Pune");
        pages.getPuneSelector().click();
        Thread.sleep(3000);
        driver.navigate().refresh();
        pages.getMoreDetailsSelector().click();
        driver.navigate().refresh();


        //Get temperature
        String temp = pages.getTempSelector().getText();
        System.out.println(temp);
      //  String temperatureFromUIString = temp.replace("°C", "");
        String temperatureFromUIString = temp.substring(0,temp.indexOf("°"));
        temperatureFromUI = Integer.parseInt(temperatureFromUIString);

        //get Humidity
        //String humidityFromUI = pages.getAllWetherComp().get("Humidity");
        humidityFromUI = Integer.parseInt(pages.getAllWetherComp().get("Humidity").replace("%", ""));

        ////get Wind Speed
        String windSpeedFromUIWithUnit = pages.getAllWetherComp().get("Wind");
        String windSpeedString = windSpeedFromUIWithUnit.substring((windSpeedFromUIWithUnit.indexOf(" ") + 1), windSpeedFromUIWithUnit.lastIndexOf(" "));
        windSpeedFromUI = Integer.parseInt(windSpeedString);

        //get Wind Gust
        String windGustFromUIWithUnit = pages.getAllWetherComp().get("Wind Gusts");
        String windGustFromUIString = windGustFromUIWithUnit.substring(0, windGustFromUIWithUnit.indexOf(" "));
        windGustFromUI = Integer.parseInt(windGustFromUIString);

        //Close browser
        driver.close();
    }

    @Test(priority = 2)
    public void getWeatherFromAPI() {
        apiResponse = new GetApiResponse();
        JsonPath response = apiResponse.getResponse();
        utils = new Utils();

        //getTemp
        String rawTempAPI = response.getString("main.temp");
        int tempFromAPIInKel = utils.getRoundOffValue(rawTempAPI);
        tempFromAPI = utils.convertKelvinToCelcius(tempFromAPIInKel);

        //get Humidity
        humidityFromAPI = Integer.parseInt(response.getString("main.humidity"));

        //get wind Speed
        String windSpeedFromAPIRaw = response.getString("wind.speed");
        windSpeedFromAPI = utils.getRoundOffValue(windSpeedFromAPIRaw);

        //get wind gust
        String windGustFromAPIRaw = response.getString("wind.gust");
        windGustFromAPI = utils.getRoundOffValue(windGustFromAPIRaw);

    }

    @Test(priority = 3)
    public void compareTemperature() {
        utils = new Utils();
        System.out.println("Comparing Temp: " + temperatureFromUI + " " + tempFromAPI);
        utils.compare(temperatureFromUI, tempFromAPI, 10);
    }

    @Test(priority = 3)
    public void compareWindGust() {
        System.out.println("Comparing WG: " + windGustFromUI + " " + windGustFromAPI);
        utils.compare(windGustFromUI, windGustFromAPI, 1);
    }

    @Test(priority = 3)
    public void compareWindSpeed() {
        System.out.println("Comparing WS: " + windSpeedFromUI + " " + windSpeedFromAPI);
        utils.compare(windSpeedFromUI, windSpeedFromAPI, 1);
    }

    @Test(priority = 3)
    public void compareHumidity() {
        System.out.println("Comparing Humidity: " + humidityFromUI + " " + humidityFromAPI);
        utils.compare(humidityFromUI, humidityFromAPI, 1);
    }

}


