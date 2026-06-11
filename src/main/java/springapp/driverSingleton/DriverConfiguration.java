package springapp.driverSingleton;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static springapp.driverSingleton.ConfigHelper.getBaseUrl;
import static springapp.driverSingleton.ConfigHelper.getHeadless;

public class DriverConfiguration {

    private static WebDriver webDriver;

    private DriverConfiguration() {
    }

    public static WebDriver getDriver() {
        if (webDriver == null) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--window-size=1920,1080");
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--remote-allow-origins=*");
            boolean isHeadlessMode = Boolean.parseBoolean(getHeadless());
            if (isHeadlessMode) {
                chromeOptions.addArguments("--headless=new");
            }
            webDriver = new ChromeDriver(chromeOptions);
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
//        getDriver().manage().window().maximize();
        getDriver().manage().window().setSize(new Dimension(1920,1080));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }
}