package UI_testing.data;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataUseCaseClass {

    public int getElementIncomingLetters(List<WebElement> elements) {
        List<String> sendersAndThemeList = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            String[] temp = elements.get(i).getText().split("\n");
            sendersAndThemeList.add(temp[0] + temp[1]);
        }
        int x = getLettersFromMySelf(sendersAndThemeList);
        return x;
    }

    public int getLettersFromMySelf(List<String> list) {
        Map<String,Integer> counters = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            String temp = String.valueOf(list.get(i));
            if (!counters.containsKey(temp)) {
                counters.put(temp,1);
            } else {
                counters.put(temp, counters.get(temp) + 1);
            }
        }

        return counters.get("яSimbirsoft theme");

    }


}
