package springapp.utils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.ArrayList;

public class SoftAssert {

    private final Logger logger = LogManager.getLogger(this);

    private final List<String> passed = new ArrayList<>();
    private final List<AssertionError> listOfErrors = new ArrayList<>();


    public void assertTrue(boolean condition, String message) {
        try {
            if (condition) {
                passed.add("Passed: " + message);
            } else {
                throw new AssertionError(message);
            }
        } catch (AssertionError e) {
            listOfErrors.add(e);
        }
    }

    public void assertFalse(boolean condition, String message) {
        try {
            if (!condition) {
                passed.add("Not passed: " + message);
            } else {
                throw new AssertionError(message);
            }
        } catch (AssertionError e) {
            listOfErrors.add(e);
        }
    }

    public void assertEquals(Object actual, Object expected, String message) {
        try {
            if (actual.equals(expected)) {
                passed.add(message);
            } else {
                throw new AssertionError(message);
            }
        } catch (AssertionError e) {
            listOfErrors.add(e);
        }
    }

    public void assertAll() {
        StringBuilder stringBuilder = new StringBuilder("Assertion Result:\n");

        for (String value : passed) {
            stringBuilder
                    .append("Passed: ")
                    .append(value)
                    .append("\n");
        }

        if (!listOfErrors.isEmpty()) {
            for (AssertionError error : listOfErrors) {
                stringBuilder
                        .append("Failed: ")
                        .append(error)
                        .append("\n");
            }
            AssertionError assertionError = new AssertionError(stringBuilder.toString());
            listOfErrors.forEach(assertionError::addSuppressed);

            throw assertionError;
        }
    }

    public void compareField(String columnName, String actual, String expected, int row) {
        assertEquals(
                actual,
                expected,
                "\nRow: " + row +
                        "\nColumn: " + columnName +
                        "\nExpected CSV: " + expected +
                        "\nActual UI: " + actual + "\n"
        );
    }
}