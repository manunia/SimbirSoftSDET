package UI_testing.config;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumHandler {
    private static final String SELENIUM_SERVER_URL = "http://localhost:4444/wd/hub";

    private WebDriver driver;

    public SeleniumHandler(boolean headlessMode, boolean needRemote) {
        try {
            driver = needRemote ? getRemoteDriver(headlessMode) : getChromeDriver(headlessMode);
            //настройка неявных ожиданий
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("Error: driver was not initialized " + e.getMessage());
        }
    }

    public boolean openPage(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            System.out.println("Error opening page: " + url);
            return false;
        }
        return true;
    }

    private WebDriver getChromeDriver(boolean headlessMode) {
        ChromeConfig.setDriverPath();
        return new ChromeDriver(ChromeConfig.getChromeOptions(headlessMode));
    }

    private WebDriver getRemoteDriver(boolean handlessMode) throws MalformedURLException {
        return new RemoteWebDriver(
                new URL(SELENIUM_SERVER_URL),
                ChromeConfig.getDesiredCapabilities(handlessMode));
    }

    public WebElement getElement(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public List<WebElement> getElements(String xpath) {
        return driver.findElements(By.xpath(xpath));
    }

    public WebElement getChildElement(String xpath, String parentElement) {
        return driver.findElement(By.xpath(parentElement)).findElement(By.xpath(xpath));
    }

    public String getChildElementText(String xpath, String parentElement) {
        try {
            return getChildElement(xpath, parentElement).getText();
        } catch (Exception e) {
            System.out.println("Child element not located : " + e.getMessage());
            return "";
        }
    }

    public void click(WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);arguments[0].click()", webElement);
    }

    public void setTextToElement(String xpath, String text) {
        getElement(xpath).sendKeys(text);
    }

    public void stop() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
