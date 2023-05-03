package org.example;

import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class TestNewsClass extends TestBase {
    LogRegDisply logRegDisply;
    LoginDisplay loginDisplay;
    NewsDisplayNew newsDisplayNew;

    //PageBase pageBase;

    //авторизация в приложении
    @Test(priority = 1)
    public void userLogin() throws MalformedURLException {
        AndroidSetUp();
        logRegDisply = new LogRegDisply(driver);
        loginDisplay = new LoginDisplay(driver);


        logRegDisply.clickLoginBtn();
        loginDisplay.enterPhone("+375447849732");
        loginDisplay.enterPassword("123456");
        loginDisplay.clickSignIn();
    }

    @Test (priority = 2)
    public void filterNews() {
        newsDisplayNew = new NewsDisplayNew(driver);

        newsDisplayNew.clickFilterBtn();
        newsDisplayNew.filterByCategory();
        newsDisplayNew.selectCategory();
        newsDisplayNew.backFromDisplay();
        newsDisplayNew.applyFilter();
        //newsDisplayNew.checkCategory();
        newsDisplayNew.resetFilter();

        newsDisplayNew.clickFilterBtn();
        newsDisplayNew.sortByViews();
        newsDisplayNew.applyFilter();
        newsDisplayNew.checkElements();

    }
}
