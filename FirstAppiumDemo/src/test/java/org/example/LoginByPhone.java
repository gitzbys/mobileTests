package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
import java.util.concurrent.TimeUnit;

public class LoginByPhone {

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

        @Test
        public void loginInTheApplication() {
        //нажимаем по кнопке login на стартовой странице
        WebElement loginButton = driver.findElement(By.id("com.pharmbonus.by.test:id/button_Login"));
        loginButton.click();

        //поиск поля ввода номера телефона пользователя и ввод данных
        WebElement loginField = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.EditText"));
        loginField.sendKeys("+375447849732");

        //поиск поля пароля пользователя и ввод данных
        WebElement passwordField = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.EditText"));
        passwordField.sendKeys("123456");

        //нажимаем по кнопке login/ авторизация в приложении
        WebElement loginInTheApp = driver.findElement(By.id("com.pharmbonus.by.test:id/button_login"));
        loginInTheApp.click();

        //проверяем что пользователь авторизовался и находим элемент на главном экране приложения, когда авторизован
            WebElement cabinetTab = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/nav_cabinet")));
//        WebElement cabinetTab = driver.findElement(By.id("com.pharmbonus.by.test:id/nav_cabinet"));
//        cabinetTab.click();
//        homeElement.getText();
        cabinetTab.click();
        System.out.println(cabinetTab.getText());

    }

        @AfterTest
        public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
    }
