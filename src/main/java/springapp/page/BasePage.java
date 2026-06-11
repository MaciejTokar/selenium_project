package springapp.page;

import lombok.Getter;
import org.openqa.selenium.*;
import springapp.utils.SoftAssert;
import springapp.helpers.WaitHelper;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;

import static org.openqa.selenium.support.PageFactory.initElements;
import static springapp.driverSingleton.DriverConfiguration.getDriver;

public class BasePage {

    @Getter
    protected final Logger logger = LogManager.getLogger(getClass());
    protected final WaitHelper waitHelper;
    protected final SoftAssert softAssert;
    private final Actions actions;
    private final JavascriptExecutor js;

    public BasePage() {
        initElements(getDriver(), this);
        actions = new Actions(getDriver());
        waitHelper = new WaitHelper();
        js = (JavascriptExecutor) getDriver();
        softAssert = new SoftAssert();
    }

    public static String requireNotBlank(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new NullPointerException("String can't be null or empty");
        }
        return value;
    }

    protected void clickButton(WebElement button) {
        WebElement webElement = waitHelper.waitForClickable(button);
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
        WebElement webElement = waitHelper.waitForClickable(button);
        logger.info("Button is clickable");
        actions
                .moveToElement(webElement)
                .doubleClick()
                .build()
                .perform();
    }

    protected void typeText(WebElement input, String text) {
        WebElement webElement = waitHelper.waitForVisibility(input);
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