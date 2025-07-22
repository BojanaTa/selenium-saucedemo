package Tests;

import Base.BaseTest;
import Base.ProductType;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.NavigationBarPage;
import Pages.ProductPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {
    @BeforeMethod
    public void pageSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage = new LoginPage();
        homePage = new HomePage();
        productPage = new ProductPage();
        navigationBarPage = new NavigationBarPage();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @DataProvider(name = "productTestData")
    public Object[] loginDataProvider() {
        return new Object[] {
                ProductType.BACKPACK,
                ProductType.BIKE,
                ProductType.JACKET,
                ProductType.ONESIE,
                ProductType.RED,
                ProductType.BOLT
        };
    }

    @Test(dataProvider = "productTestData")
    public void productPageIsLoaded(ProductType productType) {
        login();
        homePage.clickOnProduct(productType);

        Assert.assertTrue(productPage.getProductTitleText().toLowerCase().contains(productType.toString().toLowerCase()));
        Assert.assertTrue(productPage.price.isDisplayed());
        Assert.assertTrue(productPage.addToCartButton.isDisplayed());
    }

    @Test(dataProvider = "productTestData")
    public void userCanAddItemToCart(ProductType productType) {
        login();
        homePage.clickOnProduct(productType);
        int numberOfItemsInCart = navigationBarPage.getNumberOfItemsInCart();
        productPage.clickOnAddToCartButton();

        Assert.assertTrue(productPage.removeFromCartButton.isDisplayed());
        Assert.assertEquals(navigationBarPage.getNumberOfItemsInCart(), numberOfItemsInCart + 1);
    }

    @Test(dataProvider = "productTestData")
    public void userCanRemoveItemFromCart(ProductType productType) {
        login();
        homePage.clickOnProduct(productType);
        productPage.clickOnAddToCartButton();

        int numberOfItemsInCart = navigationBarPage.getNumberOfItemsInCart();
        productPage.clickOnRemoveFromCartButton();

        Assert.assertEquals(navigationBarPage.getNumberOfItemsInCart(), numberOfItemsInCart - 1);
    }

    @Test
    public void userCanNavigateBackToHomePage() {
        login();
        homePage.clickOnProduct(ProductType.JACKET);
        productPage.clickOnBackToProductsButton();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(navigationBarPage.title.isDisplayed());
    }
}
