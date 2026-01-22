package springapp.driverSingleton;

import java.time.Duration;
import java.io.InputStream;
import java.util.Properties;

public class ConfigHelper {

    private static Integer timeout;

    private ConfigHelper() {}

    public static int getTimeout() {
        return Integer.parseInt(
                getProperty("selenium.wait.timeout")
        );
    }

    public static Duration getTimeoutDuration() {
        return Duration.ofSeconds(getTimeout());
    }

    public static String getBaseUrl() {
        return getProperty("url");
    }

    public static String getUsername() {
        return getProperty("username");
    }

    public static String getPassword() {
        return getProperty("password");
    }

    private static String getProperty(String key) {
        try (InputStream is = ConfigHelper.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (is == null) {
                throw new RuntimeException("application.properties not found on classpath");
            }

            Properties props = new Properties();
            props.load(is);

            String value = props.getProperty(key);
            if (value == null) {
                throw new RuntimeException("Missing property: " + key);
            }

            return value;

        } catch (Exception e) {
            throw new RuntimeException("Cannot read property: " + key, e);
        }
    }
}