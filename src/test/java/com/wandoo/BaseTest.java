package com.wandoo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public abstract class BaseTest {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected static Properties testProps;

    public BaseTest() {
        testProps = new Properties();
        try (InputStream input = BaseTest.class
                .getClassLoader()
                .getResourceAsStream("test.properties")) {

            if (input == null) {
                throw new RuntimeException("test.properties not found in classpath");
            }
            testProps.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load test.properties", e);
        }
    }

    protected static String getProp(String key) {
        return System.getProperty(key, testProps.getProperty(key));
    }

}