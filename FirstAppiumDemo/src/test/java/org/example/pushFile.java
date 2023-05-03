package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsActions;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class pushFile {

    AppiumDriver driver;

    private static By photo = By.xpath("//android.view.ViewGroup[contains(@content-desc,'Photo taken')]");
    private static By signInBtn = By.id("com.google.android.apps.photos:id/sign_in_button");
//    private static By checkTitle = By.id("com.google.android.apps.photos:id/collection_title");
//    private static By closeMenuBtn = By.id("touch_outside");
    File classPath, imageDir, img;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platformName", "Android");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:platformVersion", "12");
        caps.setCapability("appium:deviceName", "emulator-5554");
        caps.setCapability("appium:appPackage", "com.google.android.apps.photos");
        caps.setCapability("appium:appActivity", ".home.HomeActivity");

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
    }

    @Test(priority = 1)
    public void pushFileDownloads() throws IOException {

        Runtime.getRuntime().exec("adb -s emulator-5554 push testing-trends-world-quality-report.jpeg /sdcard/Pictures/testing-trends-world-quality-report.jpeg");

        classPath = new File(System.getProperty("user.dir"));
        imageDir = new File(classPath, "resources/images");
        img = new File(imageDir.getCanonicalFile(), "testing-trends-world-quality-report.jpeg");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfElementLocated(signInBtn)); //.getText().equals("Sign in to back up");
//        wait.until(ExpectedConditions.presenceOfElementLocated(closeMenuBtn)).click();
//        wait.until(ExpectedConditions.presenceOfElementLocated(checkTitle)).getText();

        AndroidTouchAction touchAction = new AndroidTouchAction((PerformsTouchActions) driver);
        touchAction.press(PointOption.point(550, 210)).release().perform();
        touchAction.waitAction();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfElementLocated(signInBtn));

//        TouchAction action = new TouchAction((PerformsTouchActions) driver);
//        action.press(PointOption.point(550,210)).release().perform();
//        action.waitAction();

        //com.google.android.apps.photos:id/coordinator
//        WebElement touchOutside = driver.findElement(By.id("com.google.android.apps.photos:id/container"));
//        AndroidTouchAction androidTouchAction = new AndroidTouchAction((PerformsTouchActions) driver);
//        androidTouchAction.tap(ElementOption.element(touchOutside, 550, 210)).perform();

        String Android_Photo_Path = "sdcard/Pictures";

        ((AndroidDriver) driver).pushFile(Android_Photo_Path + "/" + img.getName(), img);
        WebElement conditions = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(35))
                .until(ExpectedConditions.numberOfElementsToBe(photo, 1));
        System.out.println(conditions.getText());

    }

    @AfterTest
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
}
