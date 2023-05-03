package org.example;

import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class TestLoginAndroid extends TestBase {

    LogRegDisply logRegDisply;
    LoginDisplay loginDisplay;

    @Test
    public void test_user_login() throws MalformedURLException {
        AndroidSetUp();
        logRegDisply = new LogRegDisply(driver);
        loginDisplay = new LoginDisplay(driver);

        logRegDisply.clickLoginBtn();
        loginDisplay.enterPhone("+375447849732");
        loginDisplay.enterPassword("123456");
        loginDisplay.clickSignIn();
        tearDown();
    }
}
