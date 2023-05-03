package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LogRegDisply extends PageBase {

    public LogRegDisply(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
    @AndroidFindBy(id = "com.pharmbonus.by.test:id/button_Login")
    WebElement loginBtn;

    @AndroidFindBy(id = "com.pharmbonus.by.test:id/button_registration")
    WebElement registrationBtn;

    public void clickLoginBtn() {
        click(loginBtn);
    }

}
