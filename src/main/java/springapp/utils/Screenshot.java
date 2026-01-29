package springapp.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

import static springapp.driverSingleton.DriverConfiguration.getDriver;
import static springapp.driverSingleton.ConfigHelper.getScreenshotFilePath;

public class Screenshot {

    private Screenshot() {}

    public static byte[] takeScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static void saveJpg(byte[] screenshotBytes, String fileName) {

        try {
            Path dir = Path.of(getScreenshotFilePath());
            Files.createDirectories(dir);

            Path filePath = dir.resolve(fileName + ".jpg");
            Files.write(filePath, screenshotBytes);

        } catch (IOException e) {
            throw new RuntimeException("Screenshot not saved", e);
        }
    }
}
