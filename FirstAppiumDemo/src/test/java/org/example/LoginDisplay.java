package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginDisplay extends PageBase{
    public LoginDisplay(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
    @AndroidFindBy (xpath = ("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.EditText"))
    WebElement phoneField;

    @AndroidFindBy (xpath = ("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.EditText"))
    WebElement passwordField;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/button_login")
    WebElement loginBtn;

    public void enterPhone(String userPhone) {
        clear(phoneField);
        sendText(phoneField, userPhone);
    }

    public void enterPassword(String userPassword) {
        clear(passwordField);
        sendText(passwordField, userPassword);
    }

    public void clickSignIn() {
        click(loginBtn);
    }
}
