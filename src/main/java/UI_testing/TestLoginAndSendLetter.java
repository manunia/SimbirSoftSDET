package UI_testing;

import UI_testing.config.TestConfig;
import UI_testing.config.SeleniumHandler;
import UI_testing.pages.AutoQALoginPage;
import UI_testing.pages.AutoQAMailPage;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestLoginAndSendLetter {

    private TestConfig configFile;

    private static final String BASE_URL = "https://www.gmail.com/";
    private static final String THEME = "Simbirsoft theme";

    private SeleniumHandler handler;
    private AutoQALoginPage qaLogin;
    private AutoQAMailPage qaMailPage;

    @Description("open browser")
    @BeforeTest
    private void initialization() {
        handler = new SeleniumHandler(false,false);
        configFile = new TestConfig();
        qaLogin = new AutoQALoginPage(handler);
        handler.openPage(BASE_URL);
        System.out.println("open page: " + BASE_URL);
        qaMailPage = new AutoQAMailPage(handler);
        login();
    }
    
    private void login() {
        qaLogin.loginToAutoQA(configFile.getPropertyValue("test_gmail"),configFile.getPropertyValue("test_password"));
    }

    @Description("create letter")
    @Story("Create and send letter")
    @Test
    private void sendLetter() {
        qaMailPage.createLetter(configFile.getPropertyValue("test_gmail"),THEME);
    }

    @Description("Close browser")
    @AfterTest
    private void stop() {
        handler.stop();
    }

}
