package mobile_test.activities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import mobile_test.config.AndroidDriverHandler;
import org.openqa.selenium.By;

public class AutoQAMainActivity {

    private TouchAction touchAction;
    private AndroidDriverHandler driverHandler;

    public AutoQAMainActivity(AndroidDriverHandler driverHandler) {
        this.driverHandler = driverHandler;
        touchAction = new TouchAction(driverHandler.getDriver());
    }

    @Step("тапнули за пределами попапа")
    private void tapOutPopup() {
        touchAction.tap(TapOptions.tapOptions().withPosition(PointOption.point(0, 0))).perform();
    }

    @Step("нажали на поисковое поле и установили курсор в поисковом поле")
    private void tapSearchField() {
        for (int i = 0; i < 2; i++) {
            touchAction.tap(TapOptions.tapOptions()
                    .withElement(ElementOption.element(
                            driverHandler.getElementById("com.joom:id/search")))
            ).perform();
        }
    }

    @Step("ввели поисковый запрос")
    private void enterQuery() {
        driverHandler.getElementById("com.joom:id/search_field").findElement(By.id("com.joom:id/input")).sendKeys("платье");
    }

    @Step("выбрали результат запроса")
    private void selectQueryResult() {
        touchAction.tap(TapOptions.tapOptions()
                .withElement(ElementOption.element(driverHandler.getElementByXpath("//android.widget.TextView[@text='платье']")))).perform();
    }

    @Step("прокрутка")
    private void scrolling() {
        MobileElement buttons = (MobileElement) driverHandler.getDriver().findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView("
                        + "new UiSelector().resourceId(\"com.joom:id/search_attributes_buttons\"))"
        ));
    }

    @Step("нажали на фильтр")
    private void tapOnFilter() {
        touchAction.tap(TapOptions.tapOptions()
                .withElement(
                        ElementOption.element(driverHandler.getElementByXpath("//android.widget.LinearLayout[@index='1']/android.widget.TextView[@text='Filter']")))
        ).perform();
    }

    @Step("перешли в профиль")
    private void goToProfile() {
        touchAction.tap(TapOptions.tapOptions()
                .withElement(
                        ElementOption.element(driverHandler.getElementById("com.joom:id/main_bottom_bar").findElement(By.xpath("//android.view.ViewGroup[@index='4']")))
                )).perform();
    }


    public void openingTheFiltersShutterTestCase() {
        tapOutPopup();
        tapSearchField();
        enterQuery();
        selectQueryResult();
        scrolling();
        tapOnFilter();
    }

    public void currencySelectionTestCase() {
        goToProfile();
    }
}
