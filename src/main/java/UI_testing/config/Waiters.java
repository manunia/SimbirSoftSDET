package UI_testing.config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiters {

    public static WebElement waitElement(SeleniumHandler handler, int seconds, String xpath) {
        return (new WebDriverWait(handler.getDriver(),seconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }
}
