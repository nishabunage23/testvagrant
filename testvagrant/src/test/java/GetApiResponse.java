import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class GetApiResponse {
    JsonPath responseJson;
    public JsonPath getResponse() {

        RestAssured.baseURI = "https://api.openweathermap.org/";
        String response = given().queryParam("q", "pune").queryParam("appid", "7fe67bf08c80ded756e598d6f8fedaea")
                .when().get("data/2.5/weather").then().assertThat().log().all().statusCode(200).extract().response().asString();

        responseJson = new JsonPath(response);
        System.out.println("response");
        return responseJson;

    }
}