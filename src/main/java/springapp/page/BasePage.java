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

    private final static Logger LOGGER = LogManager.getLogger(BasePage.class);
    private final Actions actions;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    public BasePage() {
        initElements(getDriver(), this);
        actions = new Actions(getDriver());
        wait = new WebDriverWait(getDriver(), getTimeoutDuration());
        js = (JavascriptExecutor) getDriver();
    }

    protected void clickButton(WebElement button) {
        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(button));
        LOGGER.info("Button is clickable");

        try {
            webElement.click();
            LOGGER.info("Button: \"" + button + "\" clicked using standard click()");
        } catch (WebDriverException e) {
            LOGGER.warn("Standard click() failed, trying Actions click()", e);
            try {
                actions
                        .moveToElement(webElement)
                        .click()
                        .build()
                        .perform();

                LOGGER.info("Button \"" + button + "\" clicked using Actions");
            } catch (MoveTargetOutOfBoundsException | ElementNotInteractableException | NoSuchElementException e2) {
                LOGGER.warn("Actions click failed, trying JavaScript click()", e2);
                js.executeScript("arguments[0].click();", webElement);
                LOGGER.info("Button \"" + button + "\" clicked using JavaScript");
            }
        }
    }

    protected void typeText(WebElement input, String text) {
        WebElement webElement = wait.until(ExpectedConditions.visibilityOf(input));
        LOGGER.info("Input is visible");

        clearObject(input);

        try {
            webElement.sendKeys(text);
            LOGGER.info("Text: \"" + text + "\" typed into the input: \"" + input + "\" by standard sendKeys()");
        } catch (WebDriverException e) {
            LOGGER.warn("Standard sendKeys() failed, trying Actions sendKeys()", e);
            try {
                actions
                        .moveToElement(webElement)
                        .click()
                        .build()
                        .perform();

                LOGGER.info("Text: \"" + text + "\" typed into the input: \"" + input + "\" using Actions");
            } catch (MoveTargetOutOfBoundsException | NoSuchElementException e2) {
                LOGGER.warn("Actions sendKeys() failed, trying JavaScript sendKeys()", e2);
                js.executeScript("arguments[0].value = arguments[1];", webElement, text);
                LOGGER.info("Text \"" + text + "\" typed using JavaScript into the input: \"" + input + "\"");
            }
        }
    }

    private void clearObject(WebElement element) {
        try {
            element.clear();
        } catch (NoSuchElementException | InvalidElementStateException e) {
            LOGGER.warn("Unsuccessful clearing");
        }

        try {
            element.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "A"));
            element.sendKeys(Keys.DELETE);
        } catch (NoSuchElementException | InvalidElementStateException e2) {
            LOGGER.warn("Unsuccessful clearing");
        }

        try {
            js.executeScript("arguments[0].value = '';", element);
        } catch (NoSuchElementException | InvalidElementStateException e3) {
            LOGGER.warn("Unsuccessful clearing");
        }
    }
}