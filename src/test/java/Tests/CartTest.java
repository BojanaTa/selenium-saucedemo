package Tests;

import Base.BaseTest;
import Base.ProductType;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class CartTest extends BaseTest {
    @BeforeMethod
    public void pageSetup() {
        Map<String, Object> prefs = new HashMap<>();

        // Isključi password manager
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        // Opcija za sprečavanje upozorenja za slabu lozinku
        // Nema zvaničnog prefsa za "weak password", ali user-data-dir i incognito sprečavaju čuvanje stanja
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        // Dodaj i ove dodatke ako upozorenje i dalje iskače
        options.addArguments("--incognito"); // sprečava sve vrste promptova
        options.addArguments("--disable-save-password-bubble"); // starija opcija, često radi
        options.addArguments("--disable-notifications"); // ako Chrome tretira kao notifikaciju

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage = new LoginPage();
        homePage = new HomePage();
        productPage = new ProductPage();
        navigationBarPage = new NavigationBarPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void userCanRemoveAllProductsFromCart() {
        login();
        homePage.addProductToCart(ProductType.BACKPACK);
        homePage.addProductToCart(ProductType.JACKET);
        homePage.addProductToCart(ProductType.BIKE);
        navigationBarPage.clickOnCart();
        cartPage.removeAllProductsFromCart();

        Assert.assertEquals(navigationBarPage.getNumberOfItemsInCart(), 0);
    }

    @Test
    public void userCanBuyProducts() {
        login();
        homePage.addProductToCart(ProductType.BACKPACK);
        homePage.addProductToCart(ProductType.JACKET);
        homePage.addProductToCart(ProductType.BIKE);
        navigationBarPage.clickOnCart();

        double totalPrice = cartPage.totalCartPrice();

        cartPage.clickOnCheckoutButton();
        checkoutPage.fillFirstNameField("Bojana");
        checkoutPage.fillLastNameField("Tasovac");
        checkoutPage.fillPostaCodeField("021");
        checkoutPage.clickOnContinueButton();

        Assert.assertEquals(checkoutPage.shippingInfoValue.getText(), "Free Pony Express Delivery!");
        Assert.assertTrue(checkoutPage.paymentInfoValue.getText().contains("SauceCard"));
        Assert.assertEquals(checkoutPage.getItemsTotalPrice(), totalPrice);

        checkoutPage.clickOnFinishButton();

        Assert.assertEquals(checkoutPage.completeHeader.getText(), "Thank you for your order!");
        Assert.assertTrue(checkoutPage.backToProductButton.isDisplayed());
    }

    @Test
    public void userCannotCheckoutProductsWithoutFillPersonalData() {
        login();
        homePage.addProductToCart(ProductType.BACKPACK);
        homePage.addProductToCart(ProductType.JACKET);
        homePage.addProductToCart(ProductType.BIKE);
        navigationBarPage.clickOnCart();
        cartPage.clickOnCheckoutButton();

        checkoutPage.clickOnContinueButton();
        Assert.assertTrue(checkoutPage.errorMessage.getText().contains("Error: First Name is required"));

        checkoutPage.fillFirstNameField("Bojana");
        checkoutPage.clickOnContinueButton();
        Assert.assertTrue(checkoutPage.errorMessage.getText().contains("Error: Last Name is required"));

        checkoutPage.clickOnErrorButton();
        checkoutPage.fillLastNameField("Tasovac");
        checkoutPage.clickOnContinueButton();
        Assert.assertTrue(checkoutPage.errorMessage.getText().contains("Error: Postal Code is required"));

        checkoutPage.clickOnErrorButton();
        checkoutPage.fillPostaCodeField("021");
        checkoutPage.clickOnContinueButton();

        Assert.assertTrue((checkoutPage.finishButton.isDisplayed()));
    }

    @Test
    public void userCanContinueShoppingAfterEnterCart() {
        login();
        homePage.addProductToCart(ProductType.BACKPACK);
        homePage.addProductToCart(ProductType.JACKET);
        homePage.addProductToCart(ProductType.BIKE);
        navigationBarPage.clickOnCart();
        cartPage.clickOnContinueShoppingButton();

        Assert.assertTrue(navigationBarPage.title.isDisplayed());
    }

    @Test
    public void userCanCancelCheckoutOnFillingInformation() {
        login();
        homePage.addProductToCart(ProductType.BACKPACK);
        homePage.addProductToCart(ProductType.JACKET);
        homePage.addProductToCart(ProductType.BIKE);
        navigationBarPage.clickOnCart();
        cartPage.clickOnCheckoutButton();
        checkoutPage.clickOnCancelButton();

        Assert.assertEquals(navigationBarPage.title.getText(), "Your Cart");
    }

    @Test
    public void userCanCancelCheckoutOnOverview() {
        login();
        homePage.addProductToCart(ProductType.BACKPACK);
        homePage.addProductToCart(ProductType.JACKET);
        homePage.addProductToCart(ProductType.BIKE);
        navigationBarPage.clickOnCart();
        cartPage.clickOnCheckoutButton();

        checkoutPage.fillFirstNameField("Bojana");
        checkoutPage.fillLastNameField("Tasovac");
        checkoutPage.fillPostaCodeField("021");
        checkoutPage.clickOnContinueButton();

        checkoutPage.clickOnCancelButton();

        Assert.assertTrue(navigationBarPage.title.getText().contains("Products"));
    }
}
