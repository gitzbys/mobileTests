package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumBy.ByAccessibilityId;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;

public class VacancyDisplay extends PageBase{
    public VacancyDisplay(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy (accessibility = ("Jobs"))
    WebElement jobsTab;

    @AndroidFindBy (xpath = ("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[3]/android.widget.FrameLayout/android.widget.RelativeLayout"))
    WebElement searchVacancy;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/submitButton")
    WebElement submitVacancy;

    @AndroidFindBy (id = "android:id/button1")
    WebElement verifySubmit;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/chipSigned")
    WebElement vacancyResponses;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/chipDoctor")
    WebElement goMedicalTab;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/chipPharm")
    WebElement goPharmaTab;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/chipBusiness")
    WebElement goBusinessTab;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/chipAll")
    WebElement allJobs;


    public void clickJobsTab() {
        click(jobsTab);
    }

    public void clickOnVacancy() {
        click(searchVacancy);
    }

    public void vacancySubmit() {
        click(submitVacancy);
    }

    public void applySubmit() {
        click(verifySubmit);
    }

    public void goVacancyResponses() {
        click(vacancyResponses);
    }

    public void goVacancyMedical() {
        click(goMedicalTab);
    }

    public void goVacancyPharma() {
        click(goPharmaTab);
    }

    public void goVacancyBusiness() {
        click(goBusinessTab);
    }

    public void goAllJobs() {
        click(allJobs);
    }

    public void getMedicalName() {
        getAttribute(goMedicalTab, "text").equals("Med");;
    }

    public void getPharmlName() {
        getAttribute(goPharmaTab, "text").equals("Pharma");;
    }

    public void getBusinessName() {
        getAttribute(goBusinessTab, "text").equals("Business");
    }

    public void scrollVacancies() {
        scrollDisplay("test: vacancy display");
    }

    public void searchVacancyClick() {
        searchObject("[не трогать] вакансия с откликом");
    }

}
