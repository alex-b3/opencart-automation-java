package com.selenium.assignment.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    private static WebDriver driver;

    // Private constructor to prevent instantiation
    private DriverManager() {}

    /**
     * Initializes the WebDriver instance if not already initialized.
     *
     * @return The singleton WebDriver instance.
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    /**
     * Quits the WebDriver and sets it to null.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
