package com.selenium.assignment.pages;

import com.selenium.assignment.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {
    private final ElementUtils elementUtils;

    private final WebDriver driver;

    private final By searchBar = By.name("search");
    private final By featuredProducts = By.cssSelector(".product-thumb");
    private final By categories = By.cssSelector("nav.navbar");
    private final By priceLocator = By.cssSelector("span.price-new");
    private final By buttonForm = By.cssSelector("form[data-oc-target='#header-cart']");
    private final By addToCartButton = By.cssSelector("button[title='Add to Cart']");
    private final By shoppingCartPageLink = By.cssSelector("a[title='Shopping Cart']");

    public HomePage(WebDriver driver) {
        this.elementUtils = new ElementUtils(driver);
        this.driver = driver;
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

    private WebElement findProductContainerByName(String productName) {
        List<WebElement> productContainers = driver.findElements(featuredProducts);

        return productContainers.stream()
                .filter(container -> container.findElement(By.tagName("h4")).getText().equals(productName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Product with name '" + productName + "' not found"));
    }


    public String getPriceForProduct(String productName) {

        WebElement productContainer = findProductContainerByName(productName);

        WebElement priceElement = productContainer.findElement(priceLocator);

        return priceElement.getText();
    }

    public void clickAddToCartForProduct(String productName) {

        WebElement productContainer = findProductContainerByName(productName);

        WebElement addToCartBtn = productContainer
                .findElement(buttonForm).findElement(addToCartButton);
        addToCartBtn.click();
    }

    public void clickShoppingCartLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            Thread.sleep(5000); // Wait for 2 seconds (2000 milliseconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement shoppingCartLink = wait.until(ExpectedConditions.elementToBeClickable(shoppingCartPageLink));
        shoppingCartLink.click();
    }

}
