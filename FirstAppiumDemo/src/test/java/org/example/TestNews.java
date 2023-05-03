package org.example;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import java.time.Duration;

import org.testng.annotations.Test;
import java.net.MalformedURLException;


public class TestNews extends TestBase {
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
    public void searchNews() {
        newsDisplayNew = new NewsDisplayNew(driver);
        //поиск новости и переход на экран новости
        newsDisplayNew.checkInternalNews("[не трогать] новость для deeplinks");
        //переход на экран новости
        newsDisplayNew.goNewsDisplay();
    }

    @Test (priority = 3)
    public void newsComment() {
        newsDisplayNew = new NewsDisplayNew(driver);
        //pageBase = new PageBase(driver);

        //переход на экран комментариев новости
        newsDisplayNew.goCommentsDisplay();
        //кликнуть по полю "введите комментарий"
        newsDisplayNew.selectCommentField();
        //ввести комментарий
        newsDisplayNew.enterNewsComment("autotest user comment!123Qa");
        //отправить комментарий к новости
        newsDisplayNew.sentNewsComment();

        //закрываем окно алерта перехода к настройке псевдонима
        //оставляем комментарий без псевдонима, когда пользователь перавый раз оставляет комментарий к новости
        newsDisplayNew.closeAlert();
        //проверяем отображение оставленного комментария
        newsDisplayNew.checkAbandonedComment();
    }


    @Test (priority = 4)
    public void delNewsComment() {
        newsDisplayNew = new NewsDisplayNew(driver);
        //нажать по кнопке (more) в поле комментария
        newsDisplayNew.clickCommentMoreBtn();
        //нажать по кнопке (удалить)
        newsDisplayNew.deleteNewsComment();
        //подтвердить удаление
        newsDisplayNew.confirmDeleteComment();
        //выйти с экрана комментариев новости к экрану новости
        newsDisplayNew.backFromDisplay();
    }

    @Test (priority = 5)
    public void newsAddFavorite() {
        newsDisplayNew = new NewsDisplayNew(driver);
        //добавить новость в избранное с экрана новости
        newsDisplayNew.addFavoriteNewsDisplay();
        //удалить новость из избранного с экрана новости
        newsDisplayNew.addFavoriteNewsDisplay();
        //выйти с экрана новости в ленту новостей
        newsDisplayNew.backFromDisplay();
    }

    @Test (priority = 6)
    public void addFavoriteFromFeed() {
        newsDisplayNew = new NewsDisplayNew(driver);
        //добавляем новость в избранное из ленты новостей, поиск кнопки (добавить в избранное)
        newsDisplayNew.addFavoriteNewsDisplay();
        //провряем что новость добавлена в избранное
//        newsDisplayNew.checkFavoriteNews();
        //удаляем новость из избранного из ленты новостей
        newsDisplayNew.addFavoriteNewsDisplay();
        //очищаем поле поиска новости
        newsDisplayNew.clearSearchField();
    }

    @Test (priority = 7)
    public void checkExternalNews() {
        newsDisplayNew = new NewsDisplayNew(driver);
        //проверка поиска новости и переход к внешней новости
        newsDisplayNew.checkExternalNews("[не трогать] внешняя новость тест");
        //((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
        //переход на экран новости
        newsDisplayNew.goNewsDisplay();
        //проверяем корректность перехода на экран внешней новости на наличие текста новости
        newsDisplayNew.checkNewsText();
        //выходим с экрана новости по кнопке (назад) в ленту новостей
        newsDisplayNew.backFromDisplay();
        //очищаем поле поиска новости
        newsDisplayNew.clearSearchField();
    }

    @Test (priority = 8)
    public void updateNewsFeed() {
        newsDisplayNew = new NewsDisplayNew(driver);
        //обновление ленты новостей
        newsDisplayNew.updateNewsFeed();

    }

    @Test (priority = 9)
    //поиск сторис
    //переход к экрану сторис нажатием по ней
    public void observeStories() {
        newsDisplayNew = new NewsDisplayNew(driver);
        //перейти на экран сторис
        newsDisplayNew.goToStory();
        //листаем сторис нажатием по ней
        newsDisplayNew.checkNextStory();
        //переходим по deeplink с экрана сторис
        newsDisplayNew.clickStoryActionBtn();
        //возвращаемся к экрану сторис
        newsDisplayNew.backFromDisplay();
        //закрываем экран просмотра сторис
        newsDisplayNew.closeStoryDisplay();
    }

    @Test (priority = 10)
    public void scrollNewsFeed() {
        newsDisplayNew = new NewsDisplayNew(driver);
        //скролл ленты новостей
        newsDisplayNew.scrollDisplay("[не трогать] новость для deeplinks");
    }

    @Test (priority = 11)
    public void filterNews() {
        newsDisplayNew = new NewsDisplayNew(driver);
        //Тест фильтр по автору
        //нажимаем по кнопке (фильтр) справа от поле поиска новости по названию
        newsDisplayNew.clickFilterBtn();
        newsDisplayNew.selectAuthorFilter();
        newsDisplayNew.selectAuthor();
        newsDisplayNew.checkAuthor();
        newsDisplayNew.backFromDisplay();
        newsDisplayNew.applyFilter();
        newsDisplayNew.checkSelectedAuthor();
        //Assert.assertEquals(text, )
        newsDisplayNew.resetFilter();

        newsDisplayNew.clickFilterBtn();
        newsDisplayNew.filterByCategory();

        //newsDisplayNew.selectCategory();

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
