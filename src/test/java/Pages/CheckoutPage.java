package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BaseTest {
    public CheckoutPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    public WebElement firstNameField;

    @FindBy(id = "last-name")
    public WebElement lastNameField;

    @FindBy(id = "postal-code")
    public WebElement postalCodeField;

    @FindBy(id = "continue")
    public WebElement continueButton;

    @FindBy(id = "finish")
    public WebElement finishButton;

    @FindBy(id = "cancel")
    public WebElement cancelButton;

    @FindBy(css = "[data-test='payment-info-value']")
    public WebElement paymentInfoValue;

    @FindBy(css = "[data-test='shipping-info-value']")
    public WebElement shippingInfoValue;

    @FindBy(className = "summary_subtotal_label")
    public WebElement itemsTotalPrice;

    @FindBy(className = "complete-header")
    public WebElement completeHeader;

    @FindBy(id = "back-to-products")
    public WebElement backToProductButton;

    @FindBy(css = "h3[data-test='error']")
    public WebElement errorMessage;

    @FindBy(className = "error-button")
    public WebElement errorButton;

    public void fillFirstNameField(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void fillLastNameField(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void fillPostaCodeField(String postalCode) {
        postalCodeField.sendKeys((postalCode));
    }

    public void clickOnContinueButton() {
        continueButton.click();
    }

    public void clickOnCancelButton() {
        cancelButton.click();
    }

    public void clickOnFinishButton() {
        finishButton.click();
    }

    public void clickOnErrorButton() {
        errorButton.click();
    }

    public double getItemsTotalPrice() {
        return Double.parseDouble(itemsTotalPrice.getText().replace("Item total: $", ""));
    }
}
