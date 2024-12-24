package com.selenium.assignment.tests.home_page_tests;

import com.selenium.assignment.pages.HomePage;
import com.selenium.assignment.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void verifyHomepageElements() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(homePage.isSearchBarDisplayed(), "Search bar is not displayed!");
        Assert.assertTrue(homePage.areFeaturedProductsDisplayed(), "Featured products are not displayed!");
        Assert.assertTrue(homePage.areCategoriesDisplayed(), "Categories are not displayed!");
    }

    @Test
    public void verifyFeaturedProductByText() {
        HomePage homePage = new HomePage(driver);

        String productName = "iPhone";
        Assert.assertNotNull(
                homePage.getFeaturedProductByText(productName),
                "Featured product '" + productName + "' is not displayed!"
        );
    }
}
