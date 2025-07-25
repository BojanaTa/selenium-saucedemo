package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BaseTest {
    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "checkout")
    public WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    public WebElement continueShoppingButton;

    public void clickOnCheckoutButton() {
        checkoutButton.click();
    }

    public void clickOnContinueShoppingButton() {
        continueShoppingButton.click();
    }

    public double totalCartPrice() {
        double totalPrice = 0;
        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
        for(WebElement item : prices) {
            totalPrice += Double.parseDouble(item.getText().replace("$", ""));
        }

        return totalPrice;
    }

    public void removeAllProductsFromCart() {
        List<WebElement> removeButtons = driver.findElements(By.cssSelector("[id*='remove-sauce-labs']"));
        for (WebElement removeButton : removeButtons) {
            removeButton.click();
        }
    }
}
