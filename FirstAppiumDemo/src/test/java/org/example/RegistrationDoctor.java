package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
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
import java.util.concurrent.TimeUnit;

public class RegistrationDoctor {
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
    public void registrationNewDoctor() {
        //нажимаем по кнопке registration на стартовой странице
        WebElement registrationButton = driver.findElement(By.id("com.pharmbonus.by.test:id/button_registration"));
        registrationButton.click();

        //выбираем страну пользователя
        WebElement countryButton = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]"));
        countryButton.click();

        //выбираем тип регистрируемого юзера "врач"
        WebElement selectDoctor = driver.findElement(By.id("com.pharmbonus.by.test:id/btnAsDoctor"));
        selectDoctor.click();

        //заполняем поле second_name
        WebElement secondName = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.EditText"));
        secondName.sendKeys("AndroidAppium");

        //заполняем поле first_name
        WebElement firstName = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.EditText"));
        firstName.sendKeys("DoctorUser");

        //заполняем поле middle_name
        WebElement middleName = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.FrameLayout/android.widget.EditText"));
        middleName.sendKeys("Autotest");

        //заполняем поле mobilePhone
        WebElement mobilePhone = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[4]/android.widget.FrameLayout/android.widget.EditText"));
        mobilePhone.sendKeys("345345345");

        //заполняем поле userEmail
        WebElement userEmail = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[5]/android.widget.FrameLayout/android.widget.EditText"));
        userEmail.sendKeys("autotest@user.test");

        //заполняем поле userPassword
        WebElement userPassword = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[6]/android.widget.FrameLayout/android.widget.EditText"));
        userPassword.sendKeys("123456");

        //нажимаем по кнопке continueFirst на первом шаге регистрации
        WebElement continueFirst = driver.findElement(By.id("com.pharmbonus.by.test:id/btnNext"));
        continueFirst.click();

        //нажимаем по полю selectCity на втором шаге регистрации
        WebElement selectCity = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/edittext_city")));
        selectCity.click();

        //выбираем workCity пользователя "Минск"
        WebElement workCity = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[6]")));
        workCity.click();

        //нажимаем по полю selectOrganisation на втором шаге регистрации
        WebElement selectOrganisation = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/edittext_organization")));
        selectOrganisation.click();

        //выбираем workOrganisation пользователя SANTE
        WebElement workOrganisation = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[6]")));
        workOrganisation.click();

        //нажимаем по полю jobPosition на втором шаге регистрации
        WebElement jobPosition = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/txtPosition")));
        jobPosition.click();

        //выбираем userJobPosition пользователя SANTE
        WebElement selectPosition = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[4]")));
        selectPosition.click();

        //нажимаем по полю userSpecialty на втором шаге регистрации
        WebElement userSpecialty = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[4]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.EditText")));
        userSpecialty.click();

        //нажимаем специальность пользователя selectSpecialty на втором шаге регистрации
        WebElement selectSpecialty = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[10]")));
        selectSpecialty.click();

        //нажимаем по кнопке continueTwo на втором шаге регистрации
        WebElement continueTwo = driver.findElement(By.id("com.pharmbonus.by.test:id/btnNext"));
        continueTwo.click();

        //нажимаем по кнопке completeRegister на втором шаге регистрации
        WebElement completeRegister = driver.findElement(By.id("com.pharmbonus.by.test:id/btnComplete"));
        completeRegister.click();

        //нажимаем по кнопке laterRegistration на втором шаге регистрации
        WebElement skipRegister = driver.findElement(By.id("android:id/button3"));
        skipRegister.click();

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
