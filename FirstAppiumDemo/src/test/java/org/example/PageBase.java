package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.xml.soap.Text;
import java.io.Serializable;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class PageBase {

    AppiumDriver driver;
    WebDriverWait wait;
    AndroidDriver androidEvents;

    public PageBase(AppiumDriver appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
        driver = appiumDriver;
    }

    public void waitForVisibility(WebElement element) {
        this.wait = wait;
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }

    public void clear(WebElement element) {
        waitForVisibility(element);
        element.clear();
    }

    public void click(WebElement element) {
        waitForVisibility(element);
        element.click();
    }

    public void sendText(WebElement element, String text) {
        waitForVisibility(element);
        element.sendKeys(text);
    }

    public void searchText (WebElement element, String text) {
        waitForVisibility(element);
        element.click();
        element.sendKeys(text);
    }

    public void androidSentEnter() {
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public String getAttribute(WebElement element, String attribute) {
        waitForVisibility(element);
        return element.getAttribute(attribute);
    }

    public static class ByAccessibilityId extends AppiumBy implements Serializable {
        public ByAccessibilityId(String accessibilityId) {
            super("accessibility id", accessibilityId, "accessibilityId");
        }
    }

    public void scrollDisplay (String elementName) {
         driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"" + elementName + "\"))"));
    }

    public void searchObject (String elementName) {
        driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"" + elementName + "\"))")).click();
    }

    public void searchText (String elementName) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"" + elementName + "\"))")).getText();
        //System.out.println(elementName);
    }

    public void checkTxt(WebElement element, String text) {
        waitForVisibility(element);
        element.getText().equals(text);
    }

    public void updateDisplay(WebElement element) {
        waitForVisibility(element);

        int centerX = element.getRect().x + (element.getSize().width/2);

        double startY = element.getRect().y + (element.getSize().height * 0.9);

        double endY = element.getRect().y + (element.getSize().height / 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                PointerInput.Origin.viewport(), centerX, (int)startY));

        swipe.addAction(finger.createPointerDown(0));

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(180),
                PointerInput.Origin.viewport(), centerX, (int)endY));

        swipe.addAction(finger.createPointerUp(0));

        driver.perform(Arrays.asList(swipe));
    }

    public void checkFilter(WebElement element, String text, String xpath) {
        List<WebElement> filterList = driver.findElements(By.xpath(xpath));
            for(WebElement el : filterList){
                if (el.getText().equalsIgnoreCase(text)) {
                    Assert.assertEquals(element, text);
                    click(element);
                }
            }
    }

}