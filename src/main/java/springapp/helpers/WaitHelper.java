package springapp.helpers;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import static springapp.driverSingleton.ConfigHelper.getTimeoutDuration;
import static springapp.driverSingleton.DriverConfiguration.getDriver;

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

    public String waitForUrl(String dashboardUrl) {
        try {
            fluentWait.until(ExpectedConditions.urlContains(dashboardUrl));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element is not clickable " + dashboardUrl, e);
        }
        return dashboardUrl;
    }

    public void runAfterTimeout(Runnable action) {
        try {
            fluentWait.until(d -> false);
        } catch (TimeoutException ignored) {
            action.run();
        }
    }
}