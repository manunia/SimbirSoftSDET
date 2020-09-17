package UI_testing.config;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScriptExecutor {
    public static final String CLICK_SCRIPT = "arguments[0].scrollIntoView(true);arguments[0].click()";

    public static void executeJS(WebDriver driver, String script, WebElement element) {
        ((JavascriptExecutor) driver).executeScript(script, element);
    }
}
