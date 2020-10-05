package mobile_test;

import mobile_test.activities.AutoQAMainActivity;
import mobile_test.config.AndroidDriverHandler;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MobileJoomTest {
    private AndroidDriverHandler driverHandler;
    private AutoQAMainActivity mainActivity;

    @BeforeTest
    private void initialization() {
        driverHandler = new AndroidDriverHandler();
        mainActivity = new AutoQAMainActivity(driverHandler);
    }

    @Test
    private void openFilterTest() {
        mainActivity.openingTheFiltersShutterTestCase();
    }

    @Test
    private void currencySelectionTest() {
        mainActivity.currencySelectionTestCase();
    }

}

