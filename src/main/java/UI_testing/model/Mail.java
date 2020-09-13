package UI_testing.model;

import UI_testing.config.SeleniumHandler;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mail {
    private int countLetters;

    @Step("Enter count of letters {0}")
    @Description("Set count of letters")
    public void setCountLetters(int countOfLetters) {
        this.countLetters = countOfLetters;
    }

    public void getElementIncomingLetters(List<WebElement> elements) {
        List<String> sendersAndThemeList = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            String[] temp = elements.get(i).getText().split("\n");
            sendersAndThemeList.add(temp[0] + temp[1]);
        }

        int x = getLettersFromMySelf(sendersAndThemeList);
        System.out.println("++++++++++++++" + x);

        this.setCountLetters(x);
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

    @Override
    public String toString() {
        int x = countLetters;
        StringBuilder sb = new StringBuilder();
        while (x > 0) {
            x = x % 10;
        }
        sb.append("Найдено ");
        if (x == 1) {
            sb.append(countLetters + " письмо");
        } else if (x > 1 && x <= 4) {
            sb.append(countLetters + " письма");
        } else {
            sb.append(countLetters + " писем");
        }
        return sb.toString();
    }
}
