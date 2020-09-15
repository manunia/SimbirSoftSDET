package UI_testing.pages;

import UI_testing.config.SeleniumHandler;
import UI_testing.config.Waiters;
import UI_testing.data.DataUseCaseClass;
import UI_testing.model.Mail;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AutoQAMailPage extends AbstractPage{

    private static final String INCOMING_LETTERS = "//table[@class = 'F cf zt']/tbody/tr[@class = 'zA zE']";

    private static final String NEW_LETTER = "//*[@class='aic']/div/div";

    private static final String ADRESS_FIELD = "//*[@name='to']";
    private static final String THEME_FIELD = "//*[@name='subjectbox']";
    private static final String LETTER_BODY = "//*[@class='Ar Au']/div[@aria-label='Тело письма']";
    private static final String SEND_BUTTON = "//*[@class='dC']/div";

    private static final String AFTER_SEND = "//*[@id='link_vsm']";

    public AutoQAMailPage(SeleniumHandler handler) {
        super(handler);
    }

    @Step("Count incoming letters from my self")
    public String getResultFromIncomingLetters() {
        List<WebElement> elements = handler.getElements(INCOMING_LETTERS);
        Mail resultEmail = new Mail();
        resultEmail.setCountLetters(DataUseCaseClass.getElementIncomingLetters(elements));
        return resultEmail.toString();
    }

    @Step("Letter sending")
    private void sendLetter() {
        handler.click(handler.getElement(SEND_BUTTON));
    }

    @Step("Print letter body {0}")
    private void setLetterBody(String s) {
        handler.setTextToElement(LETTER_BODY, s);
    }

    @Step("Enter theme {0}")
    private void setTheme(String theme) {
        handler.setTextToElement(THEME_FIELD, theme);
    }

    @Step("Enter address {0}")
    private void setAddress(String address) {
        handler.setTextToElement(ADRESS_FIELD, address);
    }

    @Step("Press New letter button")
    private void createNewLetter() {
        handler.click(handler.getElement(NEW_LETTER));
    }

    public void createLetter(String address, String theme) {
        String letterBody = getResultFromIncomingLetters();
        createNewLetter();
        setAddress(address);
        setTheme(theme);
        setLetterBody(letterBody);
        sendLetter();
        WebElement dinamicElement = Waiters.waitElement(handler,10,AFTER_SEND);
    }

}
