package UI_testing.pages;

import UI_testing.config.SeleniumHandler;
import UI_testing.model.Mail;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.stream.Collectors;

public class AutoQAMailPage {

    private static final String INCOMING_LETTERS = "//table[@class = 'F cf zt']/tbody/tr[@class = 'zA zE']";//"//table[@class = 'F cf zt']/tbody/tr/td/div[@class = 'afn']";
    private static final String LETTERS = ".//td/div[@class = 'afn']";

    private static final String NEW_LETTER = "//*[@class='aic']/div/div";

    private static final String ADRESS_FIELD = "//*[@name='to']";
    private static final String THEME_FIELD = "//*[@name='subjectbox']";
    private static final String LETTER_BODY = "//*[@class='Ar Au']/div[@aria-label='Тело письма']";
    private static final String SEND_BUTTON = "//*[@class='dC']/div";

    private SeleniumHandler handler;

    public AutoQAMailPage(SeleniumHandler handler) {
        this.handler = handler;
    }

    @Step("Count incoming letters from my self")
    public String getResultFromIncomingLetters() {
        Mail result = getElementIncomingLetters(INCOMING_LETTERS);
        System.out.println(result.toString());
        return result.toString();
    }

    private Mail getElementIncomingLetters(String mail) {
        Mail mailObj = new Mail();
        List<WebElement> elements = handler.getElems(mail);
        List<String> sendersAndThemeList = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            String[] temp = elements.get(i).getText().split("\n");
            sendersAndThemeList.add(temp[0] + temp[1]);
        }

        int x = getLettersFromMySelf(sendersAndThemeList);
        System.out.println("++++++++++++++" + x);

        mailObj.setCountLetters(x);
        return mailObj;
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

    @Step("Letter sending")
    private void sendLetter() {
        handler.click(handler.getElem(SEND_BUTTON));
    }

    @Step("Print letter body {0}")
    private void setLetterBody(String s) {
        handler.setTextToElement(LETTER_BODY, s);
    }

    @Step("Enter theme {0}")
    private void setTheme(String theme) {
        handler.setTextToElement(THEME_FIELD, theme);
    }

    @Step("Enter addres {0}")
    private void setAddres(String addres) {
        handler.setTextToElement(ADRESS_FIELD, addres);
    }

    @Step("Press New letter button")
    private void createNewLetter() {
        handler.click(handler.getElem(NEW_LETTER));
    }

    public void createALetter(String adress, String theme) {
        String letterBody = getResultFromIncomingLetters();
        createNewLetter();
        setAddres(adress);
        setTheme(theme);
        setLetterBody(letterBody);
//        sendLetter();
    }
}
