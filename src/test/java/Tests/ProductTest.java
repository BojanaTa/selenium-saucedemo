package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.NavigationBarPage;
import Pages.ProductPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ProductTest extends BaseTest {
    @BeforeMethod
    public void pageSetup() {
        Map<String, Object> prefs = new HashMap<>();

        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage = new LoginPage();
        homePage = new HomePage();
        productPage = new ProductPage();
        navigationBarPage = new NavigationBarPage();

        login();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @DataProvider(name = "productTestData")
    public Object[] productDataProvider() {
        return new Object[] {
                "Backpack",
                "Bike",
                "Jacket",
                "Onesie",
                "Red",
                "Bolt"
        };
    }

    @Test(dataProvider = "productTestData")
    public void productPageIsLoaded(String productName) {
        homePage.clickOnProduct(productName);

        Assert.assertTrue(productPage.getProductTitleText().toLowerCase().contains(productName.toLowerCase()));
        Assert.assertTrue(productPage.price.isDisplayed());
        Assert.assertTrue(productPage.addToCartButton.isDisplayed());
    }

    @Test(dataProvider = "productTestData")
    public void userCanAddItemToCart(String productName) {
        homePage.clickOnProduct(productName);
        int numberOfItemsInCart = navigationBarPage.getNumberOfItemsInCart();
        productPage.clickOnAddToCartButton();

        Assert.assertTrue(productPage.removeFromCartButton.isDisplayed());
        Assert.assertEquals(navigationBarPage.getNumberOfItemsInCart(), numberOfItemsInCart + 1);
    }

    @Test(dataProvider = "productTestData")
    public void userCanRemoveItemFromCart(String productName) {
        homePage.clickOnProduct(productName);
        productPage.clickOnAddToCartButton();

        int numberOfItemsInCart = navigationBarPage.getNumberOfItemsInCart();
        productPage.clickOnRemoveFromCartButton();

        Assert.assertEquals(navigationBarPage.getNumberOfItemsInCart(), numberOfItemsInCart - 1);
    }

    @Test(dataProvider = "productTestData")
    public void userCanNavigateBackToHomePage(String productName) {
        homePage.clickOnProduct(productName);
        productPage.clickOnBackToProductsButton();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertEquals(navigationBarPage.title.getText(), "Products");
    }
}
