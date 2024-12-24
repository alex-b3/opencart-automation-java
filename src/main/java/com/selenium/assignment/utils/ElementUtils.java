package com.selenium.assignment.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementUtils {

    private final WebDriver driver;

    public ElementUtils(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementDisplayed(By locator) {
        return driver.findElements(locator).size() > 0 && driver.findElement(locator).isDisplayed();
    }

    public static WebElement findElementByDataAutomation(WebDriver driver, String dataAutomation) {

        By dataAutomationLocator = By.cssSelector("[data_automation='" + dataAutomation + "']");

        if (driver.findElements(dataAutomationLocator).isEmpty()) {
            System.err.println("ERROR: Element with data_automation='" + dataAutomation + "' not found.");
            throw new NoSuchElementException(
                    "Element with data_automation='" + dataAutomation + "' not found in the DOM."
            );
        }
        System.out.println("Element with data_automation='" + dataAutomation + "' located successfully.");
        return driver.findElement(dataAutomationLocator);
    }

    public WebElement findElementByCssAndText(String cssSelector, String text) {
        List<WebElement> elements = driver.findElements(By.cssSelector(cssSelector));

        return elements.stream()
                .filter(e -> e.getText().equals(text))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        "Element with CSS selector '" + cssSelector + "' and text '" + text + "' not found."
                ));
    }
}
