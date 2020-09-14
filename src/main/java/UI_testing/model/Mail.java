package UI_testing.model;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mail {
    private int countLetters;

    @Step("Enter count of letters {0}")
    @Description("Set count of letters")
    public void setCountLetters(int countOfLetters) {
        this.countLetters = countOfLetters;
    }

    @Override
    public String toString() {
        int x = countLetters;
        StringBuilder sb = new StringBuilder();
        while (x > 10) {
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
