package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class NewsPage {

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

    @Test (priority = 1)
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
    public void searchNewsByName() {
        //ready
        //проверка поиска новости и переход к внутренней новости
        //выбираем поле поиска и вводим название новости в это поле
        WebElement selectSearch = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/edit_search")));
        selectSearch.click();
        //ready
        selectSearch.sendKeys("[не трогать] новость для deeplinks");
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

        //ready
        //переходим к экрану новости нажатием по ней в ленте новостей
        WebElement clickOnNews = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/card_view")));
        clickOnNews.click();

        /*
        //просмотр видео на экране новости
        //воспроизведение видео
        WebElement clickStartVideo = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/play_pause_button")));
        clickStartVideo.click();


        //открытие видео во весь экран
        WebElement fullScreenMode = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/fullscreen_button")));
        fullScreenMode.click();

        //перемотка видео
        WebElement rewindVideo = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("")));
        rewindVideo.click();
        //x 65
        //y 499
        //выход из полноэкранного режима
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/fullscreen_button")));
        fullScreenMode.click();

        //ставим видео на паузу
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/play_pause_button")));
        clickStartVideo.click();
        */
    }
    @Test (priority = 3)
            public void setNewsComment() {
        //переходим на экран комментарии новости
        WebElement goComments = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/fabComments")));
        goComments.click();

        //проверяем отображение
        // уже оставленных комментариев
        WebElement checkOlderComments = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.TextView[1]")));
        System.out.println(checkOlderComments.getText());

        //ready
        //фокусируемся на поле для ввода комментария и оставляем комментарий к новости
        WebElement selectCommentsField = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/addCommentEditText")));
        selectCommentsField.click();
        selectCommentsField.sendKeys("autotest user comment!");

        //ready
        //Отправляем комментарий пользователя (без псевдонима)
        WebElement sendComment = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/btnSendComment")));
        sendComment.click();

        //ready
        //закрываем окно алерта перехода к настройке псевдонима
        //оставляем комментарий без псевдонима, когда пользователь перавый раз оставляет комментарий к новости
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
    }
    @Test (priority = 4)
            public void deleteUserComment() {
        //ready
        //удаляем комментарий новости
        //нажимаем по кнопке (more)
        WebElement clickMore = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/button_more")));
        clickMore.click();

        //ready
        //нажимаем по кнопке (delete comment)
        WebElement deleteComment = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("android:id/title")));
        deleteComment.click();

        //ready
        //подтверждаем уданение комментария
        WebElement confirmDelete = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("android:id/button2")));
        confirmDelete.click();

        //ready
        //выходим с экрана комментариев по кнопке (назад) к новости
        WebElement clickBack = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageButton[@content-desc=\"\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200ENavigate up\u200E\u200F\u200E\u200E\u200F\u200E\"]")));
        clickBack.click();

        //ready
        //добавляем новость в избранное с экрана новости
        WebElement addToFavorites = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/image_favorite")));
        addToFavorites.click();

        //ready
        //удаляем новость из избранного с экрана новости
        WebElement deleteFromFavorites = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/image_favorite")));
        deleteFromFavorites.click();

        //ready
        //выходим с экрана новости в ленту новостей
        WebElement clickBackTwo = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageButton[@content-desc=\"\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200ENavigate up\u200E\u200F\u200E\u200E\u200F\u200E\"]")));
        clickBackTwo.click();

    }

    @Test (priority = 5)
    public void getNewsToFavorites() {
        //ready
        //добавляем новость в избранное из ленты новостей, поиск кнопки (добавить в избранное)
        //нажатие по кнопке (добавить в избранное)
        WebElement clickFavorites = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/image_favorite")));
        clickFavorites.click();

        //ready
        //провряем что новость добавлена в избранное
        WebElement searchSuccessMsg = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/image_favorite")));
        searchSuccessMsg.isEnabled();
        System.out.println(searchSuccessMsg.getText());

        //ready
        //удаляем новость из избранного из ленты новостей
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/image_favorite")));
        clickFavorites.click();

        //ready
        //очищаем поле поиска новости
        WebElement clearSearch = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/searchClearButton")));
        clearSearch.click();
    }

    @Test (priority = 6)
    public void getExternalNews() {
        //ready
        //проверка поиска новости и переход к внешней новости
        WebElement selectSearch = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/edit_search")));
        selectSearch.click();
        selectSearch.sendKeys("[не трогать] внешняя новость тест");
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

        //ready
        //переходим к экрану внешней новости нажатием по ней в ленте новостей
        WebElement clickOnNews = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/card_view")));
        clickOnNews.click();

        //проверяем корректность перехода на экран внешней новости на наличие текста новости
        WebElement goComments = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/fabComments")));
        System.out.println(goComments.getText());

        //выходим с экрана новости по кнопке (назад) в ленту новостей
        WebElement clickBack = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageButton[@content-desc=\"\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200ENavigate up\u200E\u200F\u200E\u200E\u200F\u200E\"]")));
        clickBack.click();

        //очищаем поле поиска новости
        WebElement clearSearch = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/searchClearButton")));
        clearSearch.click();
    }
    @Test (priority = 7)
    public void updateNewsFeed() {
        //обновление ленты новостей
        //ready
        WebElement newsPage = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[3]"));

        int centerX = newsPage.getRect().x + (newsPage.getSize().width/2);

        double startY = newsPage.getRect().y + (newsPage.getSize().height * 0.9);

        double endY = newsPage.getRect().y + (newsPage.getSize().height / 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                PointerInput.Origin.viewport(), centerX, (int)startY));

        swipe.addAction(finger.createPointerDown(0));

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(), centerX, (int)endY));

        swipe.addAction(finger.createPointerUp(0));

        driver.perform(Arrays.asList(swipe));

    }
    @Test (priority = 8)
    public void observeStories() {
        //ready
        //поиск сторис
        //переход к экрану сторис нажатием по ней
        WebElement searchStories = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.TextView")));
        searchStories.click();

        //ready
        //[не трогать] Group of stories
        //листаем сторис нажатием по ней
        WebElement tapNextStories = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/btnNext")));
        for (int i = 0; i < 2; i++) {
            tapNextStories.click();
        }

        //ready
        //переходим по deeplink с экрана сторис
        WebElement goByDeeplink = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/btnAction")));
        goByDeeplink.click();

        //ready
        //возвращаемся к экрану сторис
        WebElement clickBack = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageButton[@content-desc=\"\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200ENavigate up\u200E\u200F\u200E\u200E\u200F\u200E\"]")));
        clickBack.click();

        //ready
        //закрываем экран просмотра сторис
        WebElement closeStoriesDisplay = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/btnClose")));
        closeStoriesDisplay.click();

    }
    @Test (priority = 9)
    public void scrollNewsFeed() {
        //ready
        //скролл ленты новостей
        WebElement newsDisplay = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[3]"));

        int centerX = newsDisplay.getRect().x + (newsDisplay.getSize().width/2);

        double startY = newsDisplay.getRect().y + (newsDisplay.getSize().height * 0.9);

        double endY = newsDisplay.getRect().y + (newsDisplay.getSize().height * 0.5);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                PointerInput.Origin.viewport(), centerX, (int)startY));

        swipe.addAction(finger.createPointerDown(0));

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(), centerX, (int)endY));

        swipe.addAction(finger.createPointerUp(0));

        driver.perform(Arrays.asList(swipe));
    }
    @Test (priority = 10)
    public void filterNews() {
        //Тест фильтр по автору
        //нажимаем по кнопке (фильтр) справа от поле поиска новости по названию
        WebElement clickFilter = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/filterButton")));
        clickFilter.click();

        //в окне выбора фильтра нажимаем по кнопке (фильтр по автору)
        WebElement authorFilter = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/layoutAuthor")));
        authorFilter.click();

        //выбираем автора по которому будут фильтроваться новости [AlpenPharma AG]
        WebElement selectAuthor = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[3]")));
        selectAuthor.click();

        //проверяем что автор выбран
        WebElement authorIsSelected = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("com.pharmbonus.by.test:id/author_checked_image")));
        System.out.println(authorIsSelected.getText());

        //выходим назад на страницу фильтрации
        WebElement clickBackBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageButton[@content-desc=\"\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200ENavigate up\u200E\u200F\u200E\u200E\u200F\u200E\"]")));
        clickBackBtn.click();

        //нажимаем по кнопке (применить фильтр)
        WebElement applyFilter = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/btnApply")));
        applyFilter.click();

        //проверяем что фильтр применился
        WebElement checkAuthor = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/text_author_name")));
        System.out.println(checkAuthor.getText().equals("Alpen Pharma AG"));

        //Нажимаем по кнопке (сбросить фильтр)
        WebElement deleteFilter = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/snackbar_action")));
        deleteFilter.click();

        //Тест фильтр по категории
        //нажимаем по кнопке (фильтр) справа от поле поиска новости по названию
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/filterButton")));
        clickFilter.click();

        //в окне выбора фильтра нажимаем по кнопке (фильтр по категории)
        WebElement categoryFilter = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/layoutCategory")));
        categoryFilter.click();

        //выбираем категорию по которой будут фильтроваться по категории [FAQ отвечает фарм специалист часть 57]
        WebElement selectCategory = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[3]")));
        selectCategory.click();

        //проверяем что категория выбрана
        WebElement categoryIsSelected = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("com.pharmbonus.by.test:id/author_checked_image")));
        System.out.println(categoryIsSelected.getText());

        //выходим назад на страницу фильтрации
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageButton[@content-desc=\"\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200ENavigate up\u200E\u200F\u200E\u200E\u200F\u200E\"]")));
        clickBackBtn.click();

        //нажимаем по кнопке (применить фильтр)
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/btnApply")));
        applyFilter.click();

        //проверяем что фильтр применился
        WebElement checkCategory = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/text_title")));
        System.out.println(checkCategory.getText().equals("FAQ отвечает фарм специалист часть 57"));

        //Нажимаем по кнопке (сбросить фильтр)
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/snackbar_action")));
        deleteFilter.click();

        //Тест фильтр новостей по популярности
        //нажимаем по кнопке (фильтр) справа от поле поиска новости по названию
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/filterButton")));
        clickFilter.click();

        //выбираем фильтр по просмотрам
        WebElement sortByViews = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/sort_by_views")));
        sortByViews.click();

        //нажимаем по кнопке (применить фильтр)
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/btnApply")));
        applyFilter.click();

        //проверяем корректность работы фильтра берем кол-во просмотров из первой новости
        //скролим ленту новостей к след. новости
        WebElement newsDisplay = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[2]")));

        int centerX = newsDisplay.getRect().x + (newsDisplay.getSize().width/2);

        double startY = newsDisplay.getRect().y + (newsDisplay.getSize().height * 0.9);

        double endY = newsDisplay.getRect().y + (newsDisplay.getSize().height * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                PointerInput.Origin.viewport(), centerX, (int)startY));

        swipe.addAction(finger.createPointerDown(0));

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1700),
                PointerInput.Origin.viewport(), centerX, (int)endY));

        swipe.addAction(finger.createPointerUp(0));

        driver.perform(Arrays.asList(swipe));

        //проверяем корректность работы фильтра берем кол-во просмотров из первой новости
        WebElement getViews1 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("com.pharmbonus.by.test:id/text_views_count")));
        System.out.println(getViews1.getText());

        //скролим ленту новостей к след. новости
        driver.perform(Arrays.asList(swipe));

        //проверяем корректность работы фильтра берем кол-во просмотров из второй новости
        WebElement getViews2 = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("com.pharmbonus.by.test:id/text_views_count")));
        System.out.println(getViews2.getText());

        //проверяем кол-во просмотров между новостью 1 и новостью 2

//        if (getViews1 > getViews2) {
//            System.out.println(true);
//        } else {
//            System.out.println();
//        }

    }

    @AfterTest
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
}
