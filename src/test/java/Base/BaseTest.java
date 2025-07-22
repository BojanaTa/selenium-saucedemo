package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

public class BaseTest {
   public static WebDriver driver;
   protected WebDriverWait wait;
   protected LoginPage loginPage;
   protected HomePage homePage;
   protected ProductPage productPage;
   protected NavigationBarPage navigationBarPage;
   protected CartPage cartPage;
   protected CheckoutPage checkoutPage;

   @BeforeClass
    public void setUp() {
       WebDriverManager.chromedriver().setup();
   }

   protected void login() {
      login("standard_user", "secret_sauce");
   }

   protected void login(String username, String password) {
      loginPage.insertUsername(username);
      loginPage.insertPassword(password);
      loginPage.clickOnLoginButton();
   }
}
