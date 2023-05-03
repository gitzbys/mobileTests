package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class StocksDisplay {

    AppiumDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platformName", "Android");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:platformVersion", "12");
        caps.setCapability("appium:deviceName", "emulator-5554");
        caps.setCapability("appium:app", System.getProperty("user.dir")+"/apps/PhB-develop.apk");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test(priority = 1)
    public void UserAuth() {
        //авторизация пользователя в мобильное приложение
        //нажимаем по кнопке loginButton на стартовой странице
        WebElement loginButton = new WebDriverWait(driver, Duration.ofSeconds(40))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/button_Login")));
        loginButton.click();

        //вводим номер телефона пользователя в поле "phone / email"
        WebElement enterLogin = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.EditText")));
        enterLogin.sendKeys("+375447849732");

        //вводим номер пароль пользователя в поле "password"
        WebElement enterPassword = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.EditText")));
        enterPassword.sendKeys("123456");

        //нажимаем по кнопке "войти"
        WebElement clickLogin = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/button_login")));
        clickLogin.click();

    }
    @Test (priority = 2)
    public void goStocksDisplay() {
        //поиск и переход к табу "акции"
        WebElement tabPromo = new WebDriverWait(driver, Duration.ofSeconds(25))
                .until(ExpectedConditions.elementToBeClickable(new AppiumBy.ByAccessibilityId("Promo")));
        tabPromo.click();

    }
    @Test (priority = 3)
    public void scrollPromoFeed() {
        //скролл ленты новостей
        WebElement promoDisplay = driver.findElement(By.id("com.pharmbonus.by.test:id/recyclerView"));

        int centerX = promoDisplay.getRect().x + (promoDisplay.getSize().width/2);

        double startY = promoDisplay.getRect().y + (promoDisplay.getSize().height * 0.9);

        double endY = promoDisplay.getRect().y + (promoDisplay.getSize().height * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                PointerInput.Origin.viewport(), centerX, (int)startY));

        swipe.addAction(finger.createPointerDown(0));

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1700),
                PointerInput.Origin.viewport(), centerX, (int)endY));

        swipe.addAction(finger.createPointerUp(0));

//        driver.perform(Arrays.asList(swipe));

        //WebElement recyclerView = new WebDriverWait(driver, Duration.ofSeconds(10))
          //      .until(ExpectedConditions.visibilityOfElementLocated(By.id("com.pharmbonus.by.test:id/recyclerView")));

        //поиск и переход на экран акции
        /*
        WebElement promoCard = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[7]/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView[1]")));
        System.out.println(promoCard.getText());
        */

        int i = 1;

        while (true) {
            if (i > 7) {
                i = 1;
                driver.perform(Arrays.asList(swipe));
            }

            WebElement promoCard = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout["+i+"]/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView[1]")));
            System.out.println(promoCard.getText());
            i++;
            if (promoCard.getText().equals("[не трогать] акция тест")) {
                System.out.println(promoCard.getText());
                promoCard.click();
                break;
            } else {
             }
        }

    }
    @Test (priority = 4)
    public void sendPromo() {
        //отправить форму акции
        WebElement applyPromo = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("com.pharmbonus.by.test:id/button_submit")));
        applyPromo.click();

    }

    @AfterTest
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
}
