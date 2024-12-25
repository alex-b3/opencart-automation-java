package com.selenium.assignment.tests.home_page_tests;

import com.selenium.assignment.pages.HomePage;
import com.selenium.assignment.pages.ShoppingCartPage;
import com.selenium.assignment.tests.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class HomePageTest extends BaseTest {

    private HomePage homePage;
    ShoppingCartPage shoppingCartPage;


    @BeforeMethod
    public void setupHomePage() {
        homePage = new HomePage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
    }

    @Test
    public void verifyHomepageElements() {
        Assert.assertTrue(homePage.isSearchBarDisplayed(), "Search bar is not displayed!");
        Assert.assertTrue(homePage.areFeaturedProductsDisplayed(), "Featured products are not displayed!");
        Assert.assertTrue(homePage.areCategoriesDisplayed(), "Categories are not displayed!");
    }

    @Test
    public void verifyFeaturedProductByText() {
        String productName = "iPhone";
        Assert.assertNotNull(
                homePage.getFeaturedProductByText(productName),
                "Featured product '" + productName + "' is not displayed!"
        );
    }

    @Test
    public void verifyIphoneProductsPrice() {
        HashMap<String, String> productPrices = new HashMap<>();
        productPrices.put("iPhone", "$123.20");
        productPrices.put("MacBook", "$602.00");
        productPrices.put("Apple Cinema 30\"", "$110.00");
        productPrices.put("Canon EOS 5D", "$98.00");

        productPrices.forEach(this::verifyProductPrice);

    }

    private void verifyProductPrice(String productName, String expectedPrice) {
        String actualPrice = homePage.getPriceForProduct(productName);
        Assert.assertEquals(actualPrice, expectedPrice, "The price for '" + productName + "' is incorrect!");
    }

    @Test
    public void verifyAddingProductToCart() {
        String productName = "MacBook";

        homePage.clickAddToCartForProduct(productName);
        homePage.clickShoppingCartLink();
        List<String> expectedProducts = List.of("MacBook");

        shoppingCartPage.verifyCartContents(1, expectedProducts);
    }

}
