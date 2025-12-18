package springapp.utils;

import com.github.javafaker.Faker;

public class StringGeneratorUtils {

    private final static Faker faker = new Faker();

    private StringGeneratorUtils() {}

    public static String getGeneratedPassword() {
        return faker.internet().password(7, 8, true, true, true);
    }

    public static String getUsername() {
        String username;
        do {
            username = faker.name().username();
        } while (username.length() <= 5);
            return username;
    }
}