package springapp.page;

import org.openqa.selenium.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;

import static org.openqa.selenium.support.PageFactory.initElements;
import static springapp.driverSingleton.DriverConfiguration.getDriver;
import static springapp.driverSingleton.ConfigHelper.getTimeoutDuration;

public class BasePage {

    private final Logger logger = LogManager.getLogger(this);
    private final Actions actions;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    public BasePage() {
        initElements(getDriver(), this);
        actions = new Actions(getDriver());
        wait = new WebDriverWait(getDriver(), getTimeoutDuration());
        js = (JavascriptExecutor) getDriver();
    }

    public static String requireNotBlank(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new NullPointerException("String can't be null or empty");
        }
        return value;
    }

    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void runAfterTimeout(Runnable action) {
        try {
            wait.until(d -> false);
        } catch (TimeoutException ignored) {
            action.run();
        }
    }

    protected void clickButton(WebElement button) {
        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(button));
        logger.info("Button is clickable");

        try {
            webElement.click();
            logger.info("Button: \"" + button + "\" clicked using standard click()");
        } catch (WebDriverException e) {
            logger.warn("Standard click() failed, trying Actions click()", e);
            try {
                actions
                        .moveToElement(webElement)
                        .click()
                        .build()
                        .perform();

                logger.info("Button \"" + button + "\" clicked using Actions");
            } catch (MoveTargetOutOfBoundsException | ElementNotInteractableException | NoSuchElementException e2) {
                logger.warn("Actions click failed, trying JavaScript click()", e2);
                js.executeScript("arguments[0].click();", webElement);
                logger.info("Button \"" + button + "\" clicked using JavaScript");
            }
        }
    }

    protected void doubleClickButton(WebElement button) {
        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(button));
        logger.info("Button is clickable");
        actions
                .moveToElement(webElement)
                .doubleClick()
                .build()
                .perform();
    }

    protected void typeText(WebElement input, String text) {
        WebElement webElement = wait.until(ExpectedConditions.visibilityOf(input));
        logger.info("Input is visible");

        clearObject(input);

        try {
            webElement.sendKeys(text);
            logger.info("Text: \"" + text + "\" typed into the input: \"" + input + "\" by standard sendKeys()");
        } catch (WebDriverException e) {
            logger.warn("Standard sendKeys() failed, trying Actions sendKeys()", e);
            try {
                actions
                        .moveToElement(webElement)
                        .click()
                        .build()
                        .perform();

                logger.info("Text: \"" + text + "\" typed into the input: \"" + input + "\" using Actions");
            } catch (MoveTargetOutOfBoundsException | NoSuchElementException e2) {
                logger.warn("Actions sendKeys() failed, trying JavaScript sendKeys()", e2);
                js.executeScript("arguments[0].value = arguments[1];", webElement, text);
                logger.info("Text \"" + text + "\" typed using JavaScript into the input: \"" + input + "\"");
            }
        }
    }

    private void clearObject(WebElement element) {
        try {
            element.clear();
        } catch (NoSuchElementException | InvalidElementStateException e) {
            logger.warn("Unsuccessful clearing");
        }

        try {
            element.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "A"));
            element.sendKeys(Keys.DELETE);
        } catch (NoSuchElementException | InvalidElementStateException e2) {
            logger.warn("Unsuccessful clearing");
        }

        try {
            js.executeScript("arguments[0].value = '';", element);
        } catch (NoSuchElementException | InvalidElementStateException e3) {
            logger.warn("Unsuccessful clearing");
        }
    }
}