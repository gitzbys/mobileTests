package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class EducationDisplayNew extends PageBase {
    LogRegDisply logRegDisply;
    LoginDisplay loginDisplay;

    public EducationDisplayNew(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/nav_education")
    WebElement educationTab;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/chipVideos")
    WebElement videoEducationTab;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/title_video")
    WebElement videoObjectTitle;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/btnSubmit")
    WebElement educationCompleteBtn;

    @AndroidFindBy (accessibility = "")
    WebElement backFromEducation;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/chipPresentations")
    WebElement presentationEducationTab;

    @AndroidFindBy (id = "")
    WebElement g;

    @AndroidFindBy (id = "")
    WebElement k;

    @AndroidFindBy (id = "")
    WebElement l;

    //нажать по табу "Обучение"
    public void clickEducationTab() {
        click(educationTab);
    }

    //перейти к табу видео
    public void clickVideoTab() {
        click(videoEducationTab);
    }

    //скролл списка объектов видео
    public void searchVideoClick() {
        searchObject("не трогать - видео автотест");
    }

    //проверяем что пользователь перешел на экран видео
    public void checkVideoTitle() {
        checkTxt(videoObjectTitle, "не трогать - видео автотест");
    }

    //проверяем нотификейшен что необходимо просмотреть видео до концп, когда пользователь нажимает кнопку (завершить)
    //свайпаем экран до кнопки (Завершить)
    public void searchCompleteBtn() {
        searchObject("Complete");
    }

    //нажимаем по кнопке (Завершить)
    public void clickEducationComplete() {
        click(educationCompleteBtn);
    }

    //выходим на экран списка видео объектов
    public void goBackEducation () {
        click(backFromEducation);
    }

    //перейти к табу презентации
    public void goPresentationTab() {
        click(presentationEducationTab);
    }

}
