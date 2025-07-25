package Tests;

import Base.BaseTest;
import Base.SortOption;
import Pages.HomePage;
import Pages.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SortProductsTest extends BaseTest {
    @BeforeMethod
    public void pageSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage = new LoginPage();
        homePage = new HomePage();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void userCanSortProducts() {
        login();
        homePage.selectSortOption(SortOption.PRICELOWTOHIGH);

        Assert.assertEquals(homePage.getFirstPrice(), 7.99);
        Assert.assertEquals(homePage.getLastPrice(), 49.99);

        homePage.selectSortOption(SortOption.PRICEHIGHTOLOW);

        Assert.assertEquals(homePage.getFirstPrice(), 49.99);
        Assert.assertEquals(homePage.getLastPrice(), 7.99);

        homePage.selectSortOption(SortOption.NAMEATOZ);

        Assert.assertTrue(homePage.getFirstTitle().contains("Backpack"));
        Assert.assertTrue(homePage.getLastTitle().contains("Red"));

        homePage.selectSortOption(SortOption.NAMEZTOA);

        Assert.assertTrue(homePage.getFirstTitle().contains("Red"));
        Assert.assertTrue(homePage.getLastTitle().contains("Backpack"));
    }
}
