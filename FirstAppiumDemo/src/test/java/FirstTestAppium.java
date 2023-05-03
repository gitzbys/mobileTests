import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class FirstTestAppium {

    public static AppiumDriver driver;

    public static void AndroidSetUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platformName", "Android");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:platformVersion", "12");
        caps.setCapability("appium:deviceName", "emulator-5554");
        caps.setCapability("appium:app", System.getProperty("user.dir") + "/apps/PhB-develop.apk");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
    }

//    public static void IosSetUp() throws MalformedURLException {
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability("appium:platformName", "");
//        caps.setCapability("appium:automationName", "");
//        caps.setCapability("appium:platformVersion", "");
//        caps.setCapability("appium:deviceName", "-5554");
//        caps.setCapability("appium:app", System.getProperty("user.dir")+"");
//        driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), caps);
//    }

    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
}
