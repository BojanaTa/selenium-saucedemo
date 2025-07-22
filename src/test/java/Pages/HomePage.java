package Pages;

import Base.BaseTest;
import Base.ProductType;
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

    public String getFirstName() {
        return productsNames.getFirst().getText();
    }

    public String getLastName() {
        return productsNames.getLast().getText();
    }

    public double getFirstPrice() {
        return Double.parseDouble(prices.getFirst().getText().replace("$", ""));
    }

    public double getLastPrice() {
        return Double.parseDouble(prices.getLast().getText().replace("$", ""));
    }

    public WebElement getItem(ProductType productType) {
        List<WebElement> items = productsNames;
        for (WebElement item : items) {
            if (item.getText().toLowerCase().contains(productType.toString().toLowerCase())) {
                System.out.println(item.getText() + " is opened.");
                return item;
            }
        }
        System.out.println(items.getFirst().getText() + " is opened.");
        return items.getFirst();
    }

    public void clickOnProduct(ProductType productType) {
        getItem(productType).click();
    }

    public void addProductToCart(ProductType productType) {
        switch (productType) {
            case BACKPACK -> driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            case BIKE -> driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
            case JACKET -> driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
            case RED -> driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
            case BOLT -> driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
            case ONESIE -> driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
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
