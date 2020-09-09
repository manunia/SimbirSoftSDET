package UI_testing;

import UI_testing.config.SeleniumHandler;
import UI_testing.pages.AutoQALoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestLoginAndSendLetter {
    private static final String BASE_URL = "https://www.gmail.com/";
    private static final String TEST_GMAIL_COM = "100820.test@gmail.com";
    private static final String TEST_PASSWORD = "#EDC4rfv";
    private static final String ADRESS = "100820.test@gmail.com";
    private static final String THEME = "Simbirsoft theme";

    private SeleniumHandler handler = new SeleniumHandler();
    AutoQALoginPage qaLogin;

    @Description("open browser")
    @BeforeTest
    private void initialization() {
        if (handler.start(false,false)) {
            qaLogin = new AutoQALoginPage(handler);
            handler.openPage(BASE_URL);
            System.out.println("open page: " + BASE_URL);
        }
    }

    @Description("Login")
    @Story("Enter login and password")
    @Test
    private void login() {
        qaLogin.loginToAutoQA(TEST_GMAIL_COM,TEST_PASSWORD);
    }

    @Description("Close browser")
    @AfterTest
    private void stop() {

        //handler.stop();
    }

}
