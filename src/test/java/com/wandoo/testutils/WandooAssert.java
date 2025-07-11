package com.wandoo.testutils;

import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;

public class WandooAssert {

    public static void assertEquals(Logger log, Object expected, Object actual, String message) {
        Assertions.assertEquals(expected, actual, message);
        log.info("{}. Actual: {} expected: {}", message, actual, expected);
    }

    public static void assertTrue(Logger log, boolean condition, String message) {
        Assertions.assertTrue(condition, message);
        log.info("{}. Condition is true", message);
    }

    // TODO: Add more methods as needed...
}
