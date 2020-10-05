package mobile_test.config;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidDriverHandler {
    private static final String APPIUM_SERVER_PATH = "http://127.0.0.1:4723/wd/hub";
    private AndroidDriver driver;

    public AndroidDriverHandler() {
        try {
            driver = new AndroidDriver(new URL(APPIUM_SERVER_PATH), DriverConfig.getDesiredCapabilities());
            driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public WebElement getElementById(String id) {
        return driver.findElement(By.id(id));
    }

    public WebElement getElementByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public AndroidDriver getDriver() {
        return driver;
    }
}
