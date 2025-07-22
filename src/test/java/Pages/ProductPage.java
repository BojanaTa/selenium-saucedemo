package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BaseTest {
    public ProductPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div[data-test='inventory-item-name']")
    public WebElement productTitle;

    @FindBy(css = "div[data-test='inventory-item-price']")
    public WebElement price;

    @FindBy(id = "add-to-cart")
    public WebElement addToCartButton;

    @FindBy(id = "remove")
    public WebElement removeFromCartButton;

    @FindBy(id = "back-to-products")
    public WebElement backToProductsButton;

    public String getProductTitleText() {
        return productTitle.getText();
    }

    public void clickOnAddToCartButton() {
        addToCartButton.click();
    }

    public void clickOnRemoveFromCartButton() {
        removeFromCartButton.click();
    }

    public void clickOnBackToProductsButton() {
        backToProductsButton.click();
    }
}
