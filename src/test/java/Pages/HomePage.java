package Pages;

import Base.BaseTest;
import Base.SortOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HomePage extends BaseTest {
    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "product_sort_container")
    public WebElement sortDropdown;

    @FindBy(className = "inventory_item_name")
    public List<WebElement> productsNames;

    @FindBy(className = "inventory_item_price")
    public List<WebElement> prices;

    public String getFirstTitle() {
        return productsNames.getFirst().getText();
    }

    public String getLastTitle() {
        return productsNames.getLast().getText();
    }

    public double getFirstPrice() {
        return Double.parseDouble(prices.getFirst().getText().replace("$", ""));
    }

    public double getLastPrice() {
        return Double.parseDouble(prices.getLast().getText().replace("$", ""));
    }

    public WebElement getProductName(String productName) {
        for (WebElement item : productsNames) {
            if (item.getText().toLowerCase().contains(productName.toLowerCase())) {
                System.out.println(item.getText() + " is opened.");
                return item;
            }
        }
        System.out.println(productsNames.getFirst().getText() + " is opened.");
        return productsNames.getFirst();
    }

    public void clickOnProduct(String productName) {
        getProductName(productName).click();
    }

    public void addProductToCart(String productName) {
        switch (productName.toLowerCase()) {
            case "backpack" -> driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            case "bike" -> driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
            case "jacket" -> driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
            case "red" -> driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
            case "bolt" -> driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
            case "onesie" -> driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        }
    }

    public void selectSortOption(SortOption sortOption) {
        Select select = new Select(sortDropdown);

        switch (sortOption) {
            case PRICELOWTOHIGH -> select.selectByVisibleText("Price (low to high)");
            case PRICEHIGHTOLOW -> select.selectByVisibleText("Price (high to low)");
            case NAMEATOZ -> select.selectByVisibleText("Name (A to Z)");
            case NAMEZTOA -> select.selectByVisibleText("Name (Z to A)");
        }
    }
}
