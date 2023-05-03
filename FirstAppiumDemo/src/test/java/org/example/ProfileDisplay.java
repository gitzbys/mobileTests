package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
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

public class ProfileDisplay {
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

        driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), caps);
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
        enterLogin.sendKeys("+375457849732");

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
    public void userVerificationByDocument() throws IOException {
        //верификация пользователя по документу
        //переходим в таб "кабинет"
        WebElement goCabinet = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(new AppiumBy.ByAccessibilityId("Cabinet")));
        goCabinet.click();

        //переходим на экран верификации
        WebElement goVerification = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/card_verification")));
        goVerification.click();

        //переходим на экран выбора метода верификации
        WebElement chooseVerificationMethod = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/button_verification_list")));
        chooseVerificationMethod.click();

        //выбираем метод верификации "загрузить документ"
        WebElement selectVerificationMethod = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/card_documents")));
        selectVerificationMethod.click();

        //нажать по кнопке (загрузить фото)
        WebElement uploadVerificationDocument = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/ll_placeholder")));
        uploadVerificationDocument.click();

        //перейти на экран выбора фото из галереи
        WebElement chooseFromGallery = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[1]")));
        chooseFromGallery.click();

//        File file = new File(MainChatPage.class.getClassLoader().getResource("files/test.jpg").toURI());
//        if(file.exists()) {
//            if (isAndroid()) {
//                ((AndroidDriver) driver).pushFile("/storage/emulated/0/DCIM/test.jpg", file);
//            } else if (isIOS()) {
//                ((IOSDriver) driver).pushFile("/private/var/mobile/Media/DCIM/IMG_0001.JPG", file);
//            }
//
        //загрузить файл на iOS девайс
//        ((IOSDriver) driver).pushFile();
//        //загрузить файл на андроид девайс
        //разрешить загрузку фото из галереи
        WebElement clickAllow = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.android.permissioncontroller:id/permission_allow_button")));
        clickAllow.click();

        //перейти в папку downloads на sdk_android
        WebElement openLeftMenu = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(new AppiumBy.ByAccessibilityId("Show roots")));
        openLeftMenu.click();

        WebElement selectSdkFolder = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.linkText("sdk_gphone64_x86_64")));
        selectSdkFolder.click();

        WebElement selectDownloadsFolder = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.linkText("Download")));
        selectDownloadsFolder.click();

        //выбрать изображение для загрузки документа
        WebElement selectImage = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(new AppiumBy.ByAccessibilityId("IMG_20221212_013018.jpg")));
        selectImage.click();

        //поиск загруженного документа по статусу 'On review'
        WebElement checkUploadedImage = driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"On review\"))"));
        System.out.println(checkUploadedImage.getText().equals("On review"));

        //выйти с экрана "документы"
        WebElement goBack = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(new AppiumBy.ByAccessibilityId("\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200ENavigate up\u200E\u200F\u200E\u200E\u200F\u200E")));
        goBack.click();

    }
    @Test (priority = 3)
    public void userVerificationByCode() {
        //верификация пользователя по коду доступа

        //переходим на экран выбора метода верификации
        WebElement chooseVerificationMethod = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/button_verification_list")));
        chooseVerificationMethod.click();

        //выбираем метод верификации "код доступа"
        WebElement selectVerificationMethod = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/card_access_code")));
        selectVerificationMethod.click();

        //ввести код доступа
        WebElement enterCode = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/edit_code")));
        enterCode.click();
        enterCode.sendKeys("41nzc5");
        //закрываем клавиатуру девайса
        driver.navigate().back();

        //откправляем запрос на проверку кода доступа
        WebElement sendCode = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/button_save")));
        sendCode.click();

        //закрываем поп ап окно с информацией об успешной отправке кода доступа
        WebElement checkPopUp = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("android:id/message")));
        System.out.println(checkPopUp.getText().equals("Invitation code applied"));

        WebElement closePopUp = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
        closePopUp.click();

        //выйти с экрана "код доступа"
        WebElement goBack = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(new AppiumBy.ByAccessibilityId("\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200ENavigate up\u200E\u200F\u200E\u200E\u200F\u200E")));
        goBack.click();

    }
    @Test (priority = 4)
    public void VerificationByLicenseCode() {
        //переходим на экран выбора метода верификации
        WebElement chooseVerificationMethod = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/button_verification_list")));
        chooseVerificationMethod.click();

        //выбираем метод верификации "license code"
        WebElement chooseLicenseCode = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/card_license_code")));
        chooseLicenseCode.click();

        //выбрать поле ввода кода лицензии и ввести код лицензии
        WebElement enterLicenseCode = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/edit_code")));
        enterLicenseCode.click();
        enterLicenseCode.sendKeys("qwe1234");
        driver.navigate().back();

        //откправляем запрос на проверку кода лицензии
        WebElement sendCode = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/button_save")));
        sendCode.click();

//        //закрываем поп ап окно с информацией об успешной отправке кода лицензии
//        WebElement checkPopUp = new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.elementToBeClickable(By.id("android:id/message")));
//        System.out.println(checkPopUp.getText().equals("Invitation code applied"));

        //закрываем поп ап окно с информацией об успешной отправке кода лицензии
        WebElement closePopUp = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
        closePopUp.click();

        //выйти с экрана "код доступа"
        WebElement goBack = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(new AppiumBy.ByAccessibilityId("\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200ENavigate up\u200E\u200F\u200E\u200E\u200F\u200E")));
        goBack.click();

        //выйти с экране верификации
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(new AppiumBy.ByAccessibilityId("\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200ENavigate up\u200E\u200F\u200E\u200E\u200F\u200E")));
        goBack.click();

    }

    @AfterTest
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
}
