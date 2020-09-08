import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class RestAssuredTestArticle {

    private static final String URL = "https://cat-fact.herokuapp.com/facts";

    @DataProvider(name = "testUsers")
    public static Object[][] checkingNames() {
        return new Object[][] {{"Kasimir", "Schulz"}};
    }

    @Test(description = "Checking the number of repetitions", dataProvider = "testUsers")
    public void getJsonPathTest(String firstName, String lastName) {
        RestAssured.defaultParser = Parser.JSON;
        Response response = given()
                .log().all()
                .headers("Content-Type", ContentType.JSON)
                .when()
                .get(URL)
                .then()
                .log().status()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().response();

        String checkingName = Sorting.getMaxEntry(response);
        //сравниваем того, кто в конце с интересующим значением
        Assert.assertEquals(checkingName.equals("{first=" + firstName + ", last=" + lastName + "}"), true);
    }



}
