package UI_testing.pages;

import UI_testing.config.SeleniumHandler;
import UI_testing.model.Mail;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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
        List<WebElement> elements = handler.getElements(INCOMING_LETTERS);
        Mail result = new Mail();
        result.getElementIncomingLetters(elements);
        System.out.println(result.toString());
        return result.toString();
    }

    @Step("Letter sending")
    private void sendLetter() {
        handler.click(handler.getElemtnt(SEND_BUTTON));
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
        handler.click(handler.getElemtnt(NEW_LETTER));
    }

    public void createALetter(String adress, String theme) {
        String letterBody = getResultFromIncomingLetters();
        createNewLetter();
        setAddres(adress);
        setTheme(theme);
        setLetterBody(letterBody);
        sendLetter();
    }
}
