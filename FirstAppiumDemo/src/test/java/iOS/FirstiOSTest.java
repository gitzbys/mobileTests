package iOS;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class FirstiOSTest {

    public static AppiumDriver driver;

    @BeforeTest
    public static void iOSSetUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platformName", "iOS");
        caps.setCapability("appium:automationName", "XCUITest");
        caps.setCapability("appium:platformVersion", "16.2");
        caps.setCapability("appium:deviceName", "iPhone 14 pro");
        caps.setCapability(  "appium:udid", "1A427581-962B-4D63-832A-0ABD3F90C722");
        caps.setCapability("appium:app", System.getProperty("user.dir") + "/apps/UIKitCatalog.app");
        driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), caps);
    }

    @Test
    public void clickAppBtn() {
        WebElement activIndBtn = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//XCUIElementTypeStaticText[@name=\"Activity Indicators\"]")));
        activIndBtn.click();
    }

    @AfterTest
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
}
