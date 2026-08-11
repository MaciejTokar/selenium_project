package springapp.driverSingleton;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static springapp.driverSingleton.ConfigHelper.getBrowser;
import static springapp.driverSingleton.BrowserFactory.chooseBrowser;
import static springapp.driverSingleton.EnvironmentFactory.chooseEnvironment;

public class DriverConfiguration {

    private static WebDriver webDriver;

    private DriverConfiguration() {
    }

    public static WebDriver getDriver() {
        if (webDriver == null) {
            webDriver = chooseBrowser(System.getProperty("browser", getBrowser()));
            openBrowser();
        }
        return webDriver;
    }

    public static void quitDriver() {
        if (webDriver != null) {
            webDriver.close();
            if (!System.getProperty("browser", getBrowser()).equalsIgnoreCase("firefox")) {
                webDriver.quit();
            }
            webDriver = null;
        }
    }

    private static void openBrowser() {
        getDriver().get(chooseEnvironment(System.getProperty("environment", "test")));
//        getDriver().manage().window().maximize();
        getDriver().manage().window().setSize(new Dimension(1920,1080));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }
}