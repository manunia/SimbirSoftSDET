package UI_testing.model;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class Mail {
    private int countLetters;

    @Step("Enter count of letters {0}")
    @Description("Set count of letters")
    public void setCountLetters(int countOfLetters) {
        try {
            countLetters = countOfLetters;
        } catch (Exception e) {
            System.out.println("Error parsing count of letters " + e.getMessage());
        }

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
