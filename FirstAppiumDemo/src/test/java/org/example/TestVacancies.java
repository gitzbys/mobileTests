package org.example;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Arrays;

public class TestVacancies extends TestBase{

    LogRegDisply logRegDisply;
    LoginDisplay loginDisplay;
    VacancyDisplay vacancyDisplay;



    @Test (priority = 1)
    public void test_user_login() throws MalformedURLException {
        AndroidSetUp();
        logRegDisply = new LogRegDisply(driver);
        loginDisplay = new LoginDisplay(driver);

        logRegDisply.clickLoginBtn();
        loginDisplay.enterPhone("+375447849732");
        loginDisplay.enterPassword("123456");
        loginDisplay.clickSignIn();
    }

    @Test (priority = 2)
    public void submit_vacancy() {
        vacancyDisplay = new VacancyDisplay(driver);

        //переходим в таб вакансии из главного меню
        vacancyDisplay.clickJobsTab();

        //переход на экрн вакансии
        //vacancyDisplay.clickOnVacancy();
        vacancyDisplay.searchVacancyClick();

        //отправляем отклик на вакансию
        vacancyDisplay.vacancySubmit();

        //подтверждаем отправку отклика
        vacancyDisplay.applySubmit();

        //закрываем окно алерта после успешной отправки отклика
        vacancyDisplay.applySubmit();

        //перейти на таб "Мои отклики" на вакансии
        vacancyDisplay.goVacancyResponses();

        //перейти на таб "medical" для вакансий
        vacancyDisplay.goVacancyMedical();
        vacancyDisplay.getMedicalName();

        //перейти на таб "pharma" для вакансий
        vacancyDisplay.goVacancyPharma();
        vacancyDisplay.getPharmlName();

        //перейти на таб "business" для вакансий
        vacancyDisplay.goVacancyBusiness();
        vacancyDisplay.getBusinessName();
    }

    @Test (priority = 3)
    public void goAllVacancy() {
        vacancyDisplay = new VacancyDisplay(driver);

        //перейти в таб "все" на экране "вакансии"
        vacancyDisplay.goAllJobs();

        //скролл дисплея "вакансий" в табе "все"
        vacancyDisplay.scrollVacancies();
    }

    @AfterTest
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }

//    @Test (priority = 4)
//    public void goVacancy() {
//        //ready
//        //переходим в таб вакансии из главного меню
//        WebElement clickJobsTab = new WebDriverWait(driver, Duration.ofSeconds(15))
//                .until(ExpectedConditions.elementToBeClickable(new AppiumBy.ByAccessibilityId("Jobs")));
//        clickJobsTab.click();
//
//        //ready
//        //поиск вакансии и переход на страницу вакансии
//        WebElement searchVacancy = new WebDriverWait(driver, Duration.ofSeconds(15))
//                .until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[3]/android.widget.FrameLayout/android.widget.RelativeLayout")));
//        searchVacancy.click();
//
//        //ready
//        //отправляем отклик на вакансию
//        WebElement submitApplication = new WebDriverWait(driver, Duration.ofSeconds(15))
//                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/submitButton")));
//        submitApplication.click();
//
//        //ready
//        //подтверждаем отправку отклика
//        WebElement selectOk = new WebDriverWait(driver, Duration.ofSeconds(15))
//                .until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
//        selectOk.click();
//
//        //ready
//        //закрываем окно алерта после успешной отправки отклика
//        WebElement acceptSubmit = new WebDriverWait(driver, Duration.ofSeconds(15))
//        .until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
//        acceptSubmit.click();
//
//        //ready
//        //перейти на таб "Мои отклики" на вакансии
//        WebElement myApplacation = new WebDriverWait(driver, Duration.ofSeconds(15))
//                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/chipSigned")));
//        myApplacation.click();
//        System.out.println(myApplacation.getText().equals("My responses"));
//
//        //ready
//        //перейти на таб "medical" для вакансий
//        WebElement goMedicalTab = new WebDriverWait(driver, Duration.ofSeconds(15))
//                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/chipDoctor")));
//        goMedicalTab.click();
//        System.out.println(goMedicalTab.getText().equals("Medical"));
//
//        //ready
//        //перейти на таб "pharma" для вакансий
//        WebElement goPharmaTab = new WebDriverWait(driver, Duration.ofSeconds(15))
//                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/chipPharm")));
//        goPharmaTab.click();
//        System.out.println(goPharmaTab.getText().equals("Pharma"));
//
//        //ready
//        //перейти на таб "business" для вакансий
//        WebElement goBusinessTab = new WebDriverWait(driver, Duration.ofSeconds(15))
//                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/chipBusiness")));
//        goBusinessTab.click();
//        System.out.println(goBusinessTab.getText().equals("Business"));
//
//    }
//    @Test (priority = 3)
//    //обновить ленту вакансий в табе "все"
//    public void scrollJosbsFeed() {
//        //ready
//        //переходим в таб "все"
//        WebElement goAllJobs = new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.elementToBeClickable(By.id("com.pharmbonus.by.test:id/chipAll")));
//        goAllJobs.click();
//
//        //ready
//        //обновляем ленту вакансий
//        WebElement jobsDisplay = driver.findElement(By.id("com.pharmbonus.by.test:id/recyclerView"));
//
//        int centerX = jobsDisplay.getRect().x + (jobsDisplay.getSize().width/2);
//
//        double startY = jobsDisplay.getRect().y + (jobsDisplay.getSize().height * 0.9);
//
//        double endY = jobsDisplay.getRect().y + (jobsDisplay.getSize().height * 0.2);
//
//        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
//
//        Sequence swipe = new Sequence(finger, 1);
//
//        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),
//                PointerInput.Origin.viewport(), centerX, (int)startY));
//
//        swipe.addAction(finger.createPointerDown(0));
//
//        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1700),
//                PointerInput.Origin.viewport(), centerX, (int)endY));
//
//        swipe.addAction(finger.createPointerUp(0));
//
//        driver.perform(Arrays.asList(swipe));
//    }

//    @AfterTest
//    public void tearDown() {
//        if (null != driver) {
//            driver.quit();
//        }
//    }
}
