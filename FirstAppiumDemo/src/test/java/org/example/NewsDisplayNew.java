package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.w3c.dom.Text;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewsDisplayNew extends PageBase {
    public NewsDisplayNew(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/edit_search")
    WebElement searchNewsField;
    //@AndroidFindBy (xpath = ("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.EditText"))

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/card_view")
    WebElement newsCard;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/fabComments")
    WebElement commentsDisplay;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/addCommentEditText")
    WebElement enterCommentField;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/btnSendComment")
    WebElement sentComment;

    @AndroidFindBy (id = "android:id/button2")
    WebElement alertBtnClose;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/text_message")
    WebElement abandonedComment;

    //@AndroidFindBy (id = "com.pharmbonus.by.test:id/button_more")
    //WebElement commentMoreBtn;

    @AndroidFindBy (id = "android:id/title")
    WebElement deleteCommentBtn;

    @AndroidFindBy (id = "android:id/button2")
    WebElement confirmDeleteBtn;

    @AndroidFindBy (xpath = ("//android.widget.ImageButton[@content-desc=\\\"\\u200E\\u200F\\u200E\\u200E\\u200E\\u200E\\u200E\\u200F\\u200E\\u200F\\u200F\\u200F\\u200E\\u200E\\u200E\\u200E\\u200E\\u200E\\u200F\\u200E\\u200E\\u200F\\u200E\\u200E\\u200E\\u200E\\u200F\\u200F\\u200F\\u200F\\u200F\\u200F\\u200F\\u200F\\u200F\\u200F\\u200E\\u200F\\u200E\\u200E\\u200E\\u200F\\u200F\\u200E\\u200F\\u200E\\u200E\\u200E\\u200F\\u200F\\u200E\\u200E\\u200E\\u200F\\u200F\\u200F\\u200F\\u200E\\u200F\\u200E\\u200E\\u200E\\u200E\\u200F\\u200F\\u200E\\u200F\\u200F\\u200E\\u200F\\u200E\\u200E\\u200F\\u200E\\u200E\\u200F\\u200E\\u200E\\u200E\\u200E\\u200E\\u200E\\u200F\\u200E\\u200F\\u200E\\u200E\\u200E\\u200E\\u200F\\u200F\\u200F\\u200E\\u200E\\u200E\\u200E\\u200ENavigate up\\u200E\\u200F\\u200E\\u200E\\u200F\\u200E\\\"]\""))
    WebElement backBtn;

    //добавляем новость в избранное с экрана новости
    @AndroidFindBy (id = "com.pharmbonus.by.test:id/image_favorite")
    WebElement newsDisplayFavoriteBtn;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/searchClearButton")
    WebElement clearSearch;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/fabComments")
    WebElement checkNewsTxt;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/textTitle")
    WebElement storyObject;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/btnNext")
    WebElement nextStoryBtn;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/btnAction")
    WebElement storyActionBtn;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/btnClose")
    WebElement closeStoryBtn;

    @AndroidFindBy (xpath = ("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[3]"))
    WebElement newsFeed;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/filterButton")
    WebElement filterBtn;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/layoutAuthor")
    WebElement filterByAuthor;

    @AndroidFindBy (xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[3]")
    WebElement selectAlpenPharma;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/author_checked_image")
    WebElement authorIsSelected;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/btnApply")
    WebElement applyFilterBtn;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/text_author_name")
    WebElement checkAuthorName;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/snackbar_action")
    WebElement resetFilterBtn;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/layoutCategory")
    WebElement filterByCategoryBtn;

    //@AndroidFindBy (id = "com.pharmbonus.by.test:id/layoutCategory")
    //WebElement selectCategoryBtn;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/text_title")
    WebElement checkCategoryTitle;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/sort_by_views")
    WebElement sortByViewsBtn;

    @AndroidFindBy (accessibility = ("\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200ENavigate up\u200E\u200F\u200E\u200E\u200F\u200E"))
    WebElement navigateUp;

    @AndroidFindBy (id = "com.pharmbonus.by.test:id/text_views_count")
    WebElement getNewsViews;

    @AndroidFindBy (accessibility = ("Home"))
    WebElement homeButton;

    public void checkInternalNews(String newsName) {
        waitForVisibility(searchNewsField);
        clear(searchNewsField);
        click(searchNewsField);
        searchText(searchNewsField, newsName);
        androidSentEnter();
    }


    public void checkExternalNews(String externalNews) {
        waitForVisibility(searchNewsField);
        clear(searchNewsField);
        click(searchNewsField);
        sendText(searchNewsField, externalNews);
        androidSentEnter();
    }

    public void goNewsDisplay() {
        waitForVisibility(newsCard);
        click(newsCard);
    }

    public void goCommentsDisplay() {
        waitForVisibility(commentsDisplay);
        click(commentsDisplay);
    }

    public void selectCommentField() {
        waitForVisibility(enterCommentField);
        click(enterCommentField);
    }

    public void enterNewsComment(String newsComment) {
        clear(enterCommentField);
        sendText(enterCommentField, newsComment);
    }

    public void sentNewsComment() {
        waitForVisibility(sentComment);
        click(sentComment);
    }

    public void closeAlert() {
        waitForVisibility(alertBtnClose);
        click(alertBtnClose);
    }

    public String checkAbandonedComment() {
        String userComment = "autotest user comment!123Qa";
        waitForVisibility(abandonedComment);
        //searchText("autotest user comment!123Qa");
        List<WebElement> elements = driver.findElements(By.id("com.pharmbonus.by.test:id/text_message"));
        for (WebElement element : elements)
        {
            if(element.getText().equals(userComment))
            {
                Assert.assertEquals(element.getText(), userComment);
                System.out.println(element.getText());
                break;
            }
        }
        return null;
    }

    public void clickCommentMoreBtn() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        //int numElements = 20;

        ArrayList<WebElement> elements = new ArrayList<>(); // create an empty list to hold the elements

        //for (int i = 1; i < numElements; i++)
        int firstElement = 1;
        try {
            while (firstElement < 20) {
                // find the element using the current index i
                WebElement element = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[" + firstElement + "]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ImageView"));

                firstElement++;
                // add the element to the list
                if (element.isDisplayed()) {
                    elements.add(element);
                    //System.out.println(element);
                    //System.out.println("Element found!");
                }
            }

        } catch(NoSuchElementException e){
            System.out.println("Element not found: " + e.getMessage());
        }

        int lastIndex = elements.size() - 1;
        WebElement lastElement = elements.get(lastIndex);
        System.out.println(lastIndex);
        System.out.println(lastElement);

        WebElement moreBtn = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[" + (lastIndex + 1) + "]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ImageView"));
        click(moreBtn);
    }

    public void deleteNewsComment() {
        waitForVisibility(deleteCommentBtn);
        click(deleteCommentBtn);
    }

    public void confirmDeleteComment() {
        waitForVisibility(confirmDeleteBtn);
        click(confirmDeleteBtn);
    }

    public void backFromDisplay() {
        waitForVisibility(navigateUp);
        click(navigateUp);
    }

    //добавляем новость в избранное с экрана новости
    public void addFavoriteNewsDisplay() {
        waitForVisibility(newsDisplayFavoriteBtn);
        click(newsDisplayFavoriteBtn);
    }

    //провряем что новость добавлена в избранное
//    public String checkFavoriteNews() {
//        waitForVisibility(newsDisplayFavoriteBtn);
//        return newsDisplayFavoriteBtn.getAttribute(newsDisplayFavoriteBtn.getText());
//    }

    public void clearSearchField() {
        waitForVisibility(clearSearch);
        click(clearSearch);
    }

    //проверяем корректность перехода на экран внешней новости на наличие текста новости
    public void checkNewsText() {
        waitForVisibility(checkNewsTxt);
        checkTxt(checkNewsTxt, "[не трогать] внешняя новость тест");
    }

    public void goToStory() {
        waitForVisibility(storyObject);
        String storyName = "[не трогать] Group of stories";
        checkTxt(storyObject, "[не трогать] Group of stories");
        Assert.assertEquals(storyObject.getText(), storyName);
        click(storyObject);
    }

    public void checkNextStory() {
        waitForVisibility(nextStoryBtn);
        for (int i = 0; i < 2; i++) {
            click(nextStoryBtn);
        }
    }

    public void clickStoryActionBtn() {
        waitForVisibility(storyActionBtn);
        click(storyActionBtn);
    }

    public void closeStoryDisplay() {
        waitForVisibility(closeStoryBtn);
        click(closeStoryBtn);
    }

    public void updateNewsFeed() {
        //обновление ленты новостей
        updateDisplay(newsFeed);
    }

    //нажимаем по кнопке (фильтр) справа от поле поиска новости по названию
    public void clickFilterBtn() {
        waitForVisibility(homeButton);
        click(homeButton);
        updateDisplay(newsFeed);
        waitForVisibility(filterBtn);
        click(filterBtn);
    }

    //в окне выбора фильтра нажимаем по кнопке (фильтр по автору)
    public void selectAuthorFilter() {
        waitForVisibility(filterByAuthor);
        click(filterByAuthor);
    }

    //выбираем автора по которому будут фильтроваться новости [AlpenPharma AG]
    public void selectAuthor() {
        waitForVisibility(selectAlpenPharma);
        click(selectAlpenPharma);
    }

    //проверяем фильтр выбран
    public void checkAuthor() {
        waitForVisibility(authorIsSelected);
        authorIsSelected.getAttribute("Enabled").equals(true);
        //checkTxt(authorIsSelected, "");
    }

    public void applyFilter() {
        waitForVisibility(applyFilterBtn);
        click(applyFilterBtn);
    }

//    public String checkSelectedAuthor() {
//        waitForVisibility(checkAuthorName);
//        String authorName = "Alpen Pharma AG";
//        Assert.assertEquals(authorName, checkAuthorName.getText());
//        return checkAuthorName.getAttribute(checkAuthorName.getText());
//    }

    //тест сравнения выбранного параметра по тексту элемента
    public void checkSelectedAuthor() {
        waitForVisibility(checkAuthorName);
        String authorName = "Alpen Pharma AG";
        Assert.assertEquals(authorName, checkAuthorName.getText());
    }

    public void resetFilter() {
        click(resetFilterBtn);
    }

    public void filterByCategory() {
        click(filterByCategoryBtn);
    }

//    public void selectCategory() {
//        click(selectCategoryBtn);
//    }

//    public String checkCategory() {
//        waitForVisibility(checkCategoryTitle);
//        return checkCategoryTitle.getAttribute(checkCategoryTitle.getText());
//    }

//    public void checkCategory() {
//        waitForVisibility(checkCategoryTitle);
//        String checkCategoryName = "Alpen Pharma AG";
//        Assert.assertEquals(checkCategoryName, checkCategoryTitle.getText());
//    }

//    public void checkCategory() {
//        int i = 1;
//        String categotyTxt = "FAQ отвечает фарм специалист";
//        String categotyXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout["+ i +"]/android.widget.TextView[1]";
//        WebElement categoryText = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout["+ i +"]/android.widget.TextView[1]"));
//        checkFilter(categoryText, categotyTxt, categotyXpath);
//    }

    public void selectCategory() {
        String categoryName = "FAQ отвечает фарм специалист";
        searchObject(categoryName);
    }

    public void sortByViews() {
        click(sortByViewsBtn);
    }

    public void checkElements() {
        List<WebElement> elements = driver.findElements(By.id("com.pharmbonus.by.test:id/text_views_count"));

        for (int i = 0; i < elements.size() - 1; i++) {
            if (elements.get(i).getLocation().getY() > elements.get(i+1).getLocation().getY()) {
                System.out.println("Element at index " + i + " is below element at index " + (i+1));
            }
        }
    }


}
