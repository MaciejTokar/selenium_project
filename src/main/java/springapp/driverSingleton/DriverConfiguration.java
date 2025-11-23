package springapp.driverSingleton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

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

    private static void openBrowser() {
        getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }
}