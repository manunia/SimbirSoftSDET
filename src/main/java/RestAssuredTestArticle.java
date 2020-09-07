import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;


public class RestAssuredTestArticle {

    private static final String URL = "https://cat-fact.herokuapp.com/facts";

    @DataProvider(name = "testUsers")
    public static Object[][] checkingNames() {
        return new Object[][] {{"Kasimir", "Schulz"}, {"Alex","Wohlbruck"}};
    }

    @BeforeTest
    @Test(description = "Check status code 200")
    public void getRequestTest() {
        Response response =
                given()
                .log().all()
                .when()
                .get(URL)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
        response.getBody().print();

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
                .contentType(ContentType.JSON).extract().response();

        String checkingName = getMaxEntry(response);
        //сравниваем того, кто в конце с интересующим значением
        Assert.assertEquals(checkingName.equals("{first=" + firstName + ", last=" + lastName + "}"), true);
    }

    private String getMaxEntry(Response response) {
        //получаем список всех имен
        List<Map<String, String>> names = response.jsonPath().getList("all.user.name");
        Map<String,Integer> counters = new HashMap<>();
        //подсчитываем уникальные значения
        for (int i = 0; i < names.size(); i++) {
            String temp = String.valueOf(names.get(i));
            if (!counters.containsKey(temp)) {
                counters.put(temp,1);
            } else {
                counters.put(temp, counters.get(temp) + 1);
            }
        }

        //сортируем уникальные значения и записываем результат в новый список
        List<Map.Entry<String,Integer>> valueList = counters.entrySet().stream()
                .sorted(Map.Entry.<String,Integer> comparingByValue())
                .collect(Collectors.toList());

        //тот, кто больше всего фактов написал окажется в конце
        return valueList.get(valueList.size() - 1).getKey();

    }

}
