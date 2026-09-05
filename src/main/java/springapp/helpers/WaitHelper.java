package springapp.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static springapp.driverSingleton.DriverConfiguration.getDriver;
import static springapp.driverSingleton.ConfigHelper.getTimeoutDuration;

public class WaitHelper {

    private final FluentWait<WebDriver> fluentWait;

    public WaitHelper() {
        fluentWait = new FluentWait<>(getDriver())
                .withTimeout(getTimeoutDuration())
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);
    }

    public WebElement waitForVisibility(WebElement element) {
        try {
        fluentWait.until(ExpectedConditions.visibilityOf(element));
            } catch (TimeoutException e) {
            throw new TimeoutException("Element is not visible: " + element, e);
        }
        return  element;
    }

    public WebElement waitForClickable(WebElement element) {
        try {
            fluentWait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element is not clickable " + element, e);
        }
        return element;
    }

    public String waitForUrl(String url) {
        try {
            fluentWait.until(ExpectedConditions.urlContains(url));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element is not clickable " + url, e);
        }
        return url;
    }

    public void runAfterTimeout(Runnable action) {
        try {
            fluentWait.until(d -> false);
        } catch (TimeoutException ignored) {
            action.run();
        }
    }
}