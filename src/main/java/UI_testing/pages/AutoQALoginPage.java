package UI_testing.pages;

import UI_testing.config.SeleniumHandler;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

public class AutoQALoginPage extends AbstractPage{
    private static final String LOGIN_FIELD = "//*[@id='identifierId']";
    private static final String PASSWORD_FIELD = "//*[@id='password']/div[1]/div/div[1]/input";

    public AutoQALoginPage(SeleniumHandler handler) {
        super(handler);
    }

    @Step("Enter login {0}")
    private void setLogin(String login) {
        handler.setTextToElement(LOGIN_FIELD, login + Keys.ENTER);
    }

    @Step("Enter password {0}")
    private void setPassword(String password) {
        handler.setTextToElement(PASSWORD_FIELD, password + Keys.ENTER);
    }

    @Step("Get start")
    public void loginToAutoQA(String login, String password) {
        this.setLogin(login);
        this.setPassword(password);
    }
}
