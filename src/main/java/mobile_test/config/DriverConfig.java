package mobile_test.config;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverConfig {

    public static DesiredCapabilities getDesiredCapabilities() {
        //device 192.168.42.101:5555
        //Android 9.0
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "192.168.42.101:5555");
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android"); //platformName
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9"); //platformVersion
        cap.setCapability("appPackage","com.joom");
        cap.setCapability("appActivity","com.joom.ui.main.MainActivity");
        return cap;
    }
}
