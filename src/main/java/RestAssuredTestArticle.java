import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;



public class RestAssuredTestArticle {

    @Test
    public static void getRequestTest() {

        Response response =
                given()
                    .log().all()
                .when()
                .get("https://cat-fact.herokuapp.com/facts")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
        response.getBody().print();





    }
}
