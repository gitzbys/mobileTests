package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
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

public class HelpRequest {

    AppiumDriver driver;

    private static By photo = By.xpath("//android.widget.LinearLayout[@content-desc=\"testing-trends-world-quality-report.jpeg\"]");
    //    private static By photo = new AppiumBy.ByAccessibilityId("");
    File classPath, imageDir, img;

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

    @Test (priority = 1)
    public void sendHelpNoAuth() throws IOException, InterruptedException {
        classPath = new File(System.getProperty("user.dir"));
        imageDir = new File(classPath, "resources/images");
        img = new File(imageDir.getCanonicalFile(), "testing-trends-world-quality-report.jpeg");

        //перейти на экран запроса о помощи
        WebElement goHelpDisplay = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/button_help")));
        goHelpDisplay.click();


        //выбрать страну Беларусь
        WebElement selectCountry = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.TextView[2]")));
        System.out.println(selectCountry.getText().equals("Belarus"));
        selectCountry.click();

        //заполнить поле Full name
        WebElement fieldFullname = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/edtFIOField")));
        fieldFullname.click();
        fieldFullname.sendKeys("Auto Test User");

        //заполнить поле phone number
        WebElement fieldPhone = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/edtPhoneField")));
        fieldPhone.click();
        fieldPhone.sendKeys("447849732");

        //заполнить поле email - address
        WebElement fieldEmail = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/edtEmailField")));
        fieldEmail.click();
        fieldEmail.sendKeys("testuser@test.com");

        //заполняем поле комментарий
        WebElement selectComment = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/txtHelpSubjectTextField")));
        selectComment.click();
        selectComment.sendKeys("test help request autotest");

        //закрыть клавиатуру
        WebElement closeKeyboard = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/layout")));
        closeKeyboard.click();

        //добавляем фото
        WebElement selectAttach = new WebDriverWait(driver, Duration.ofSeconds(25))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/imgPhoto")));
        selectAttach.click();

        //выбираем библиотеку на мобильном девайсе
        WebElement selectLibrary = new WebDriverWait(driver, Duration.ofSeconds(35))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[1]")));
        selectLibrary.click();

        //открываем доступ к файлам
        WebElement getAccess = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.android.permissioncontroller:id/permission_allow_button")));
        getAccess.click();

        //ждем загрузку библиотеки девайса
//        WebElement waitLoadinLibrary = new WebDriverWait(driver, Duration.ofSeconds(20))
//                .until(ExpectedConditions.elementToBeClickable(By.id("com.google.android.documentsui:id/message")));
//        System.out.println(waitLoadinLibrary.getText().equals("No items"));

        WebElement waitLoadinLibrary = driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"No items\"))"));
        System.out.println(waitLoadinLibrary.getText().equals("No items"));

        //перейти в папку downloads на sdk_android
        /*
        WebElement openLeftMenu = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(new AppiumBy.ByAccessibilityId("Show roots")));
        openLeftMenu.click();

        WebElement selectSdkFolder = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.linkText("sdk_gphone64_x86_64")));
        selectSdkFolder.click();

        WebElement selectDownloadsFolder = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.linkText("Download")));
        selectDownloadsFolder.click();

         */

        String Android_Photo_Path = "sdk_gphone64_x86_64/Downloads";

        ((AndroidDriver) driver).pushFile(Android_Photo_Path + "/" + img.getName(), new File(img.toURI()));
        WebElement conditions = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.numberOfElementsToBe(photo, 1));
        System.out.println(conditions.getText());




        //выбираем фото из библиотеки
        WebElement takePhoto = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(new AppiumBy.ByAccessibilityId("IMG_20221212_013018.jpg, 139 kB, Dec 12")));
        takePhoto.click();

        //отправляем help_request
        WebElement sentHelp = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/btnSubmit")));
        sentHelp.click();

        //закрываем алерт об успешной отправке
        WebElement closeAlert = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
        closeAlert.click();
    }

    @Test(priority = 2)
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

    @Test (priority = 3)
    public void sendHelpAuth() {
        //переходим на экран helpRequest
        WebElement goHelp = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/imgHelp")));
        goHelp.click();

        //открываем список тем обращения
        WebElement openThemes = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/txtHelpSubjectField")));
        openThemes.click();

        //выбираем тему обращения
        WebElement selectTheme = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[3]")));
        selectTheme.click();

        //заполняем поле комментарий
        WebElement selectComment = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/txtHelpSubjectTextField")));
        selectComment.click();
        selectComment.sendKeys("test help request autotest");

        //добавляем фото
        WebElement selectPhoto = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/imgPhoto")));
        selectPhoto.click();

        //выбираем библиотеку на мобильном девайсе
        WebElement selectLibrary = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[1]")));
        selectLibrary.click();


        //открываем доступ к файлам
//        WebElement getAccess = new WebDriverWait(driver, Duration.ofSeconds(15))
//                .until(ExpectedConditions.elementToBeClickable(By.id("com.android.permissioncontroller:id/permission_allow_button")));
//        getAccess.click();

        //выбираем фото из библиотеки
        WebElement takePhoto = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(new AppiumBy.ByAccessibilityId("IMG_20221212_013018.jpg, 139 kB, Dec 12")));
        takePhoto.click();

        //отправляем help_request
        WebElement sentHelp = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/btnSubmit")));
        sentHelp.click();

        //закрываем алерт об успешной отправке
        WebElement closeAlert = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
        closeAlert.click();

        //пользователь перенаправлен на экран My requests
        WebElement checkRequest = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.LinearLayout[@content-desc=\"My requests\"]/android.widget.TextView")));
        System.out.println(checkRequest.getText().equals("My requests"));

    }

    @AfterTest
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
}
