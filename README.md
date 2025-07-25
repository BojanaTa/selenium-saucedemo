# Saucedemo Automated Test Framework (Java + Selenium)

This project is an automated testing framework for the website [saucedemo.com](https://www.saucedemo.com), developed using **Java**, **Selenium WebDriver**, and **TestNG**. The framework follows the **Page Object Model (POM)** design pattern for better test maintainability and readability.

---

## Project Structure

```
src/
└── test/
    └── java/
        ├── Base/
        │   ├── BaseTest.java
        │   └── SortOption.java
        │
        ├── Pages/
        │   ├── LoginPage.java
        │   ├── HomePage.java
        │   ├── ProductPage.java
        │   ├── CheckoutPage.java
        │   └── NavigationBarPage.java
        │
        └── Tests/
            ├── LoginTest.java
            ├── ProductTest.java
            ├── CartTest.java
            └── SortProductsTest.java
```

---

## Test Scenarios

The test cases are organized into the following classes:

* **LoginTest** – tests successful and unsuccessful user login
* **ProductTest** – tests product page loading, adding/removing items from cart, and navigation
* **CartTest** – tests shopping cart functionality, including item quantity and checkout visibility
* **SortProductsTest** – tests product sorting by name and price (both ascending and descending)

---

## Technologies Used

* Java
* Selenium WebDriver
* TestNG
* ChromeDriver
* Maven (optional for dependency management)

---

## How to Run the Tests

1. Clone the repository:

   ```bash
   git clone https://github.com/BojanaTa/selenium-saucedemo.git
   ```

2. Open the project in your preferred IDE (e.g., IntelliJ IDEA or Eclipse).

3. Make sure ChromeDriver is installed and set up properly.

4. Run the desired test class using TestNG, or run all tests via the TestNG suite file (if configured).

---

## Notes

* All credentials and test data are predefined for the saucedemo.com test environment.
* The framework disables Chrome password manager and notifications before test execution.

---

## License

This project is for educational and testing purposes.
