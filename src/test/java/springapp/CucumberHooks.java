package springapp;

import org.slf4j.Logger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.slf4j.LoggerFactory;
import io.qameta.allure.Allure;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.LocalDateTime;
import java.io.ByteArrayInputStream;
import java.time.format.DateTimeFormatter;

import static springapp.driverSingleton.ConfigHelper.getBrowser;
import static springapp.utils.Screenshot.saveJpg;
import static springapp.utils.Screenshot.takeScreenshot;
import static springapp.driverSingleton.DriverConfiguration.getDriver;
import static springapp.driverSingleton.DriverConfiguration.quitDriver;


public class CucumberHooks {

    private final static Logger logger = LoggerFactory.getLogger(CucumberHooks.class);

    @Before
    public void initialization(final Scenario scenario) {
        logger.info("Test scenario is running: " + logger.getName());
        Allure.parameter("Browser", System.getProperty("browser", getBrowser()));
        Allure.label("browser2", System.getProperty("browser", getBrowser()));
    }

    @After
    public void afterScenario(final Scenario scenario) {
        if (scenario.isFailed()) {
            saveJpg(takeScreenshot(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + "_" + scenario.getName().replace(' ', '_'));
            byte[] screenshots = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failure screenshot" + scenario.getName(),  "image/jpg", new ByteArrayInputStream(screenshots), ".jpg");
        }
        quitDriver();
        logger.info("Test scenario is ending: " + logger.getName());
    }
}