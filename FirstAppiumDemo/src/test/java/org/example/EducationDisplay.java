package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
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
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EducationDisplay {
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
    public void goToEducation() {
        //нажать по табу "Обучение"
        WebElement educationTab = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/nav_education")));
        educationTab.click();

        //перейти к табу видео
        WebElement goVideos = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/chipVideos")));
        goVideos.click();
    }

        @Test (priority = 3)
        //описываем скролл
        public void scrollVideoFeed() {
            //скролл списка объектов видео

            /*
            WebElement videoDisplay = driver.findElement(By.id("com.pharmbonus.by.test:id/recyclerView"));

            int centerX = videoDisplay.getRect().x + (videoDisplay.getSize().width / 2);

            double startY = videoDisplay.getRect().y + (videoDisplay.getSize().height * 0.9);

            double endY = videoDisplay.getRect().y + (videoDisplay.getSize().height * 0.2);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

            Sequence swipe = new Sequence(finger, 1);

            swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                    PointerInput.Origin.viewport(), centerX, (int) startY));

            swipe.addAction(finger.createPointerDown(0));

            swipe.addAction(finger.createPointerMove(Duration.ofMillis(300),
                    PointerInput.Origin.viewport(), centerX, (int) endY));

            swipe.addAction(finger.createPointerUp(0));

            driver.perform(Arrays.asList(swipe));

//            List<WebElement> listElements = driver.findElements(By.className("android.widget.TextView"));
//
//            System.out.println(listElements.size());
//
//            for(WebElement el : listElements){
//                if(el.getText().equalsIgnoreCase("[не трогать] видео тест")){
//                    // do some validation //
//                }
//                System.out.println(el.getText());
//            }
//
//            List<WebElement> listElements2 = driver.findElements(By.id("com.pharmbonus.by.test:id/recyclerView"));
//            for(WebElement el : listElements2){
//                if(el.getText().equalsIgnoreCase("[не трогать] видео тест")){
//                    // do some validation //
//                }
//                System.out.println(el.getText());
//            }
//            List<WebElement> elements = driver.findElements(new By.ByClassName("androidx.recyclerview.widget.RecyclerView"));
//            System.out.println(elements.size());


            int i = 1;
            while (true) {
                if (i > 4) {
                    i = 1;
                    driver.perform(Arrays.asList(swipe));
                }
                //поиск видео и переход на экран видео [не трогать] видео тест
                //System.out.println(i);

//                WebElement getVideo = new WebDriverWait(driver, Duration.ofSeconds(150))
//                        .until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[3]/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.TextView")));
//                System.out.println(getVideo.getText());
                i++;

                if (getVideo.getText().equals("[не трогать] видео тест")) {
                    System.out.println(getVideo.getText());
                    getVideo.click();
                    break;
                } else {
                    driver.perform(Arrays.asList(swipe));
                }
            }
             */

            //скролл до элемента видео
//            WebElement videoObject = (WebElement) driver.findElement(MobileBy.AndroidUIAutomator(
//                    "new UiScrollable(new UiSelector().scrollable(true)).setMaxSearchSwipes(15)" +
//                            ".scrollIntoView(new UiSelector().text(\"не трогать - видео автотест\"))"));
//            videoObject.click();

            WebElement elementVideo = driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().text(\"не трогать - видео автотест\"))"));
            elementVideo.click();

            //проверяем что пользователь перешел на экран видео
            WebElement checkVideoDisplay = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/title_video")));
            System.out.println(checkVideoDisplay.getText().equals("не трогать - видео автотест"));

            //проверяем нотификейшен что необходимо просмотреть видео до концп, когда пользователь нажимает кнопку
            //завершить
            //свайпаем экран до кнопки (Завершить)
            WebElement goCompleteBtn = driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().text(\"Complete\"))"));
            System.out.println(goCompleteBtn.getText().equals("Complete"));

            //нажимаем по кнопке (Завершить)
            WebElement completeVideoObject = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/btnSubmit")));
            completeVideoObject.click();

            //проверяем отображение нотификейшена
//            WebElement checkNotification = new WebDriverWait(driver, Duration.ofSeconds(15))
//                    .until(ExpectedConditions.elementToBeClickable(By.xpath("hierarchy/android.widget.Toast[2]")));
//            System.out.println(checkNotification.getText().equals("To complete the lesson, you need to watch the video till the end!"));

            //выходим на экран списка видео объектов
            WebElement backButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(new AppiumBy.ByAccessibilityId("\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200ENavigate up\u200E\u200F\u200E\u200E\u200F\u200E")));
            backButton.click();
        }

        @Test (priority = 4)
        public void goToPresentation() {
            //перейти к табу презентации
            WebElement goPresentations = new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/chipPresentations")));
            goPresentations.click();

            //скролл вверх списка презентаций
            try {
                driver.findElement(MobileBy.AndroidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(10)"));
            } catch (InvalidSelectorException e) {
                // ignore
            }

            //перейти на экран презентации
//            WebElement presentationObject = driver.findElement(MobileBy.AndroidUIAutomator(
//                    "new UiScrollable(new UiSelector().scrollable(true)).setMaxSearchSwipes(15)" +
//                            ".scrollIntoView(new UiSelector().text(\"не трогать - презентация автотест\"))"));
//            presentationObject.click();

            WebElement elementPresentation = driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().text(\"не трогать - презентация автотест\"))"));
            elementPresentation.click();

            //проверяем что пользователь перешел на экран презентации
            WebElement checkpresentationDisplay = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/title_presentation")));
            System.out.println(checkpresentationDisplay.getText().equals("не трогать - презентация автотест"));

            //перейти на экран описания презентации
            WebElement clickInfoPresentation = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/txt_content_description_type")));
            clickInfoPresentation.click();

            //проверяем что пользователь перешел на экран информации о презентации
            WebElement checkInfoPresentation = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/label_descriptions")));
            System.out.println(checkInfoPresentation.getText().equals("Information and Instructions"));

            //выходим с экрана информации о презентации
            WebElement exitInfoPresentation = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.elementToBeClickable(new AppiumBy.ByAccessibilityId("\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200ENavigate up\u200E\u200F\u200E\u200E\u200F\u200E")));
            exitInfoPresentation.click();

            //отвечаем на вопросы теста презентации
            //свайпаем страницу к блоку вопросов
            WebElement goToQuestions = driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().text(\"1. question 1\"))"));
            System.out.println(goToQuestions.getText().equals("1. question 1"));

            //отвечаем на первый вопрос презентации
            WebElement answerFirstQuestion = driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().text(\"Радиокнопка 1\"))"));
            answerFirstQuestion.click();

            //отвечаем на второй вопрос презентации
            WebElement answerSecondQuestionOne = driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().text(\"Чекбокс 1\"))"));
            answerSecondQuestionOne.click();

            WebElement answerSecondQuestionTwo = driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().text(\"Чекбокс 2\"))"));
            answerSecondQuestionTwo.click();

            //завершаем тест в презентации
            WebElement finishTest = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/btnSubmit")));
            finishTest.click();

            //закрыть окно успешного завершения теста презентации
            WebElement closePopUp = new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
            closePopUp.click();

            //проверить что пользователь перешел на экран "обучение"
            WebElement checkDisplay = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/titleTextView")));
            System.out.println(checkDisplay.getText().equals("Education"));

//            //выйти на экран списка презентаций
//            WebElement backButton = new WebDriverWait(driver, Duration.ofSeconds(10))
//                    .until(ExpectedConditions.elementToBeClickable(new AppiumBy.ByAccessibilityId("\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200ENavigate up\u200E\u200F\u200E\u200E\u200F\u200E")));
//            backButton.click();
        }

        @Test (priority = 5)
        public void goToSurvays() {
            //перейти к табу опросы
            WebElement goSurveys = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/chipSurveys")));
            goSurveys.click();

            //скролл и переход на экран опроса
            WebElement elementSurvay = driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().text(\"[не трогать] опрос тест\"))"));
            elementSurvay.click();

            //проверяем что пользователь перешел на экран опроса
            WebElement elementSurvayCheck = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView[2]")));
            System.out.println(elementSurvayCheck.getText().equals("[не трогать] опрос тест"));

            //ответить на вопросы опроса
            WebElement answerQuestion1 = driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().text(\"1) question 1\"))"));
            System.out.println(answerQuestion1.getText().equals("1) question 1"));

            //отвечаем на первый вопрос
            WebElement enterAnswer1 = driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().text(\"Enter the answer\"))"));
            enterAnswer1.click();
            enterAnswer1.sendKeys("answer 1");
            //закрыть клавиатуру андроида
            driver.navigate().back();

            //отвечаем на второй вопрос
            WebElement answerQuestion2 = driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().text(\"Радио кнопка 1\"))"));
            System.out.println(answerQuestion2.getText().equals("Радио кнопка 1"));
            answerQuestion2.click();

            //отвечаем на третий вопрос
            WebElement answerQuestion3One = driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().text(\"Чекбокс 1\"))"));
            System.out.println(answerQuestion3One.getText().equals("Чекбокс 1"));
            answerQuestion3One.click();

            WebElement answerQuestion3Two = driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().text(\"Чекбокс 2\"))"));
            System.out.println(answerQuestion3Two.getText().equals("Чекбокс 2"));
            answerQuestion3Two.click();

            //отправить ответы опроса
            WebElement submitButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/btnSubmit")));
            submitButton.click();

            //закрыть окно успешного завершения опроса
            WebElement closePopUp = new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
            closePopUp.click();

            //проверить что пользователь перешел на экран "обучение"
            WebElement checkDisplay = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/titleTextView")));
            System.out.println(checkDisplay.getText().equals("Education"));

            //выйти на экран списка опросов
//            WebElement backButton = new WebDriverWait(driver, Duration.ofSeconds(10))
//                    .until(ExpectedConditions.elementToBeClickable(new AppiumBy.ByAccessibilityId("\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200ENavigate up\u200E\u200F\u200E\u200E\u200F\u200E")));
//            backButton.click();
        }

        @Test (priority = 6)
        public void goToWebinars() {
        //перейти к табу вебинары
            WebElement goWebinars = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/chipWebinars")));
            goWebinars.click();

            //поиск в ленте и переход на экран вебинара
            WebElement goToWebinar = driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().text(\"[не трогать] вебинар тест\"))"));
            System.out.println(goToWebinar.getText().equals("[не трогать] вебинар тест"));
            goToWebinar.click();

            //проверяем что пользователь перешел на экран вебинара
            WebElement elementWebinarCheck = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.TextView[2]")));
            System.out.println(elementWebinarCheck.getText().equals("[не трогать] вебинар тест"));

            //выйти на экран списка вебинаров
            WebElement backButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(new AppiumBy.ByAccessibilityId("\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200ENavigate up\u200E\u200F\u200E\u200E\u200F\u200E")));
            backButton.click();
        }

        @Test (priority = 7)
        public void goToCompleted() {
            //перейти к табу завершенного обучения
            WebElement goCompleted = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(new AppiumBy.ByAccessibilityId("Completed")));
            goCompleted.click();

            //скролл и переход на экран завершенной презентации
            WebElement goCompleteEducation = driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().text(\"[test] presentation 22 04 01 (не трогать)\"))"));
            System.out.println(goCompleteEducation.getText().equals("[test] presentation 22 04 01 (не трогать)"));
            goCompleteEducation.click();

            //проверяем что пользователь перешел на экран завершенной презентации
            WebElement checkFinishedPresent = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView[2]")));
            System.out.println(checkFinishedPresent.getText().equals("[test] presentation 22 04 01 (не трогать)"));

            //перейти на экран комментариев презентации
            //оставить комментарий к завершенной презентации
            WebElement selectCommentsField = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/edit_comment")));
            selectCommentsField.click();

            selectCommentsField.sendKeys("Finished education object autotest user comment!@#$");
            //Отправляем комментарий пользователя (без псевдонима)
            WebElement sendComment = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(new AppiumBy.ByAccessibilityId("Send message")));
            sendComment.click();

            //закрываем окно алерта перехода к настройке псевдонима
            //оставляем комментарий без псевдонима, когда пользователь перавый раз оставляет комментарий
            WebElement selectCancel = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.id("android:id/button2")));
            if (selectCancel.isDisplayed()) {
                selectCancel.click();
            } else {
                //проверяем отображение оставленного комментария
                WebElement getComment = new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/text_message")));
                System.out.println(getComment.getText());
            }

            //выйти с экрана комментариев завершенной презентации
            WebElement backButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(new AppiumBy.ByAccessibilityId("\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200ENavigate up\u200E\u200F\u200E\u200E\u200F\u200E")));
            backButton.click();

            WebElement checkDisplay = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/titleTextView")));
            System.out.println(checkDisplay.getText().equals("Education"));
        }

    @AfterTest
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
}
