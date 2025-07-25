package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationBarPage extends BaseTest {
    public NavigationBarPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "app_logo")
    public WebElement appLogo;

    @FindBy(css = "span[data-test='shopping-cart-badge']")
    public WebElement shoppingCartBadge;

    @FindBy(className = "shopping_cart_link")
    public WebElement cartButton;

    @FindBy(id = "react-burger-menu-btn")
    public WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    public WebElement logoutButton;

    @FindBy(className = "title")
    public WebElement title;

    public void clickOnCart() {
        cartButton.click();
    }

    public void clickOnMenuButton() {
        menuButton.click();
    }

    public void clickOnLogoutButton() {
        logoutButton.click();
    }

    public int getNumberOfItemsInCart() {
        try {
            return Integer.parseInt(shoppingCartBadge.getText());
        }
        catch (Exception e) {
            return 0;
        }
    }
}
