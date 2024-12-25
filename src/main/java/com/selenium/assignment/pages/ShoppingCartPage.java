package com.selenium.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartPage {
    private final WebDriver driver;

    private final By cartItemsLocator = By.cssSelector("#shopping-cart .table-responsive tbody tr");
    private final By productNameLocator = By.cssSelector("#shopping-cart .table-responsive tbody tr td:nth-child(2) a");

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getCartItemCount() {
        List<WebElement> cartItems = driver.findElements(cartItemsLocator);
        return cartItems.size();
    }

    public List<String> getCartProductNames() {
        List<WebElement> productElements = driver.findElements(productNameLocator);
        return productElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void verifyCartContents(int expectedCount, List<String> expectedProductNames) {

        int cartItemCount = getCartItemCount();
        if (cartItemCount != expectedCount) {
            throw new AssertionError("Cart does not contain the expected number of items! Expected: "
                    + expectedCount + ", but found: " + cartItemCount);
        }

        List<String> actualProductNames = getCartProductNames();
        for (String productName : expectedProductNames) {
            if (!actualProductNames.contains(productName)) {
                throw new AssertionError("Cart does not contain the expected product: " + productName);
            }
        }
    }

}
