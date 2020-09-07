import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.http.ContentEncoding;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;



public class RestAssuredTestArticle {

    private static final String URL = "https://cat-fact.herokuapp.com/facts";

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

    @Test(description = "Checking the number of repetitions")
    public void getJsonPathTest() {
        RestAssured.defaultParser = Parser.JSON;
        Response response = given()
                .log().all()
                .headers("Content-Type", ContentType.JSON)
                .when()
                .get(URL)
                .then()
                .contentType(ContentType.JSON).extract().response();
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
        //сортируем уникальные значения по ключу
        List<Map.Entry<String,Integer>> valueList = new ArrayList<>(counters.entrySet());
        Collections.sort(valueList, new Comparator<Map.Entry<String,Integer>>() {
            @Override
            public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        //тот, кто больше всего фактов написал окажется в конце
        System.out.println(valueList.get(valueList.size() - 1).getKey());
        //сравниваем того, кто в конце с интересующим значением
        Assert.assertEquals(valueList.get(valueList.size() - 1).getKey().equals("{first=Kasimir, last=Schulz}"), true);


    }


}
