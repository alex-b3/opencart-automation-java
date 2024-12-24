package com.selenium.assignment.tests;

import com.selenium.assignment.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getDriver();
        driver.get("https://demo.opencart.com/");
    }

    @AfterMethod
    public void teardown() {
        DriverManager.quitDriver();
    }
}
