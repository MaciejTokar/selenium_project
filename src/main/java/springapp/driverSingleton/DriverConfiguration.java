package springapp.driverSingleton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static springapp.driverSingleton.ConfigHelper.getBaseUrl;

public class DriverConfiguration {

    private static WebDriver webDriver;

    private DriverConfiguration() {
    }

    public static WebDriver getDriver() {
        if (webDriver == null) {
            webDriver = new ChromeDriver();
            openBrowser();
        }
        return webDriver;
    }

    public static void quitDriver() {
        if (webDriver != null) {
            webDriver.close();
            webDriver.quit();
            webDriver = null;
        }
    }

    private static void openBrowser() {
        getDriver().get(getBaseUrl());
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }
}