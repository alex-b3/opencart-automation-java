package com.selenium.assignment.pages;

import com.selenium.assignment.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private final ElementUtils elementUtils;

    private final By searchBar = By.name("search");
    private final By featuredProducts = By.cssSelector(".product-thumb");
    private final By categories = By.cssSelector("nav.navbar");

    public HomePage(WebDriver driver) {
        this.elementUtils = new ElementUtils(driver);
    }

    public boolean isSearchBarDisplayed() {
        return elementUtils.isElementDisplayed(searchBar);
    }

    public boolean areFeaturedProductsDisplayed() {
        return elementUtils.isElementDisplayed(featuredProducts);
    }

    public boolean areCategoriesDisplayed() {
        return elementUtils.isElementDisplayed(categories);
    }

    public WebElement getFeaturedProductByText(String productName) {
        return elementUtils.findElementByCssAndText("div.description h4", productName);
    }
}
