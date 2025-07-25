package Tests;

import Base.BaseTest;
import Pages.LoginPage;
import Pages.NavigationBarPage;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class LoginTest extends BaseTest {
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        loginPage = new LoginPage();
        navigationBarPage = new NavigationBarPage();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void userCanLogin() {
        login();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(navigationBarPage.appLogo.isDisplayed());
    }

    @Test
    public void userCannotLoginWithWrongUsername() {
        login("standard_user_wrong", "secret_sauce");

        Assert.assertTrue(loginPage.errorMessage.isDisplayed());
        Assert.assertTrue(loginPage.getErrorMessageText()
                .contains("Username and password do not match any user in this service"));
    }

    @Test
    public void userCannotLoginWithWrongPassword() {
        login("standard_user", "secret_sauce_wrong");

        Assert.assertTrue(loginPage.errorMessage.isDisplayed());
        Assert.assertTrue(loginPage.getErrorMessageText()
                .contains("Username and password do not match any user in this service"));
    }

    @Test
    public void userCannotLoginWithWrongUsernameAndPassword() {
        login("standard_user_wrong", "secret_sauce_wrong");

        Assert.assertTrue(loginPage.errorMessage.isDisplayed());
        Assert.assertTrue(loginPage.getErrorMessageText()
                .contains("Username and password do not match any user in this service"));
    }

    @Test
    public void lockedupUserCannotLogin() {
        login("locked_out_user", "secret_sauce");

        Assert.assertTrue(loginPage.errorMessage.isDisplayed());
        Assert.assertTrue(loginPage.getErrorMessageText().contains("Sorry, this user has been locked out."));
    }

    @Test
    public void userCanLogout() {
        login();
        navigationBarPage.clickOnMenuButton();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
        navigationBarPage.clickOnLogoutButton();

        Assert.assertTrue(loginPage.usernameField.isDisplayed());
        Assert.assertTrue(loginPage.passwordField.isDisplayed());
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }
}
