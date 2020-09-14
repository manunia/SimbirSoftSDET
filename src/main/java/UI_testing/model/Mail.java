package UI_testing.model;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class Mail {
    public int countLetters;

    @Step("Enter count of letters {0}")
    @Description("Set count of letters")
    public void setCountLetters(int countOfLetters) {
        this.countLetters = countOfLetters;
    }

    @Override
    public String toString() {
        int x = countLetters;
        String str = "";
        while (x > 0) {
            x = x % 10;
        }
        str+="Найдено ";
        if (x == 1) {
            str+=countLetters + " письмо";
        } else if (x > 1 && x <= 4) {
            str+=countLetters + " письма";
        } else {
            str+=countLetters + " писем";
        }
        return str;
    }
}
