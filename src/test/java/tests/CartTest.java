package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.time.Duration;

public class CartTest extends BaseTest {

    @Test
    public void addProductToCart() {
        // Go to homepage
        driver.get("https://advantageonlineshopping.com/#/");

        // Click user icon to open login modal
        driver.findElement(By.id("menuUser")).click();

        // Wait for login modal
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));

        // Enter login credentials
        driver.findElement(By.name("username")).sendKeys("Sanduni02");
        driver.findElement(By.name("password")).sendKeys("Test1234");

        // Wait for loader to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loader")));

        // Click sign in
        driver.findElement(By.id("sign_in_btn")).click();

        // Wait until user is logged in
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@class='hi-user containMiniTitle ng-binding']")));

        // ✅ Wait for homepage categories (like laptopsImg) to reappear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("laptopsImg")));

        // Navigate to laptops
        driver.findElement(By.id("laptopsImg")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@id='9'])[1]")));

        // Select first laptop
        driver.findElement(By.xpath("(//img[@id='9'])[1]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("save_to_cart")));

        // Add to cart
        driver.findElement(By.name("save_to_cart")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menuCart")));

        // Open cart
        driver.findElement(By.id("menuCart")).click();

        // Validate cart quantity
        String quantity = driver.findElement(By.name("quantity")).getAttribute("value");
        Assert.assertEquals(quantity, "1", "Product not added to cart.");
    }
}
