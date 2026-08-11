package springapp.driverSingleton;

import static springapp.driverSingleton.ConfigHelper.*;

public class EnvironmentFactory {

    public static String chooseEnvironment(String environment) {
        return switch (environment) {
            case "test" -> getTestEnvironment();
            case "uat" -> getUatEnvironment();
            case "prod" -> getProdEnvironment();
            default -> throw new IllegalArgumentException("Illegal argument: " + environment);
        };
    }
}