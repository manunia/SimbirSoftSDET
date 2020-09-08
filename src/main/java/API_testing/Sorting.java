package API_testing;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Sorting {

    public static String getMaxEntry(Response response) {
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
