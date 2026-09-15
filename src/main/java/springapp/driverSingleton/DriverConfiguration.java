package springapp.driverSingleton;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static springapp.driverSingleton.ConfigHelper.getBrowser;
import static springapp.driverSingleton.BrowserFactory.chooseBrowser;
import static springapp.driverSingleton.EnvironmentFactory.chooseEnvironment;

public class DriverConfiguration {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    private DriverConfiguration() {
    }

    public static WebDriver getDriver() {
        if (webDriver.get() == null) {
            WebDriver driver = chooseBrowser(System.getProperty("browser", getBrowser()));
            webDriver.set(driver);
            openBrowser();
        }
        return webDriver.get();
    }

    public static void quitDriver() {
        if (webDriver.get() != null) {
            webDriver.get().close();
            if (!System.getProperty("browser", getBrowser()).equalsIgnoreCase("firefox")) {
                webDriver.get().quit();
            }
            webDriver.remove();
        }
    }

    private static void openBrowser() {
        getDriver().get(chooseEnvironment(System.getProperty("environment", "test")));
        getDriver().manage().window().setSize(new Dimension(1920,1080));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }
}