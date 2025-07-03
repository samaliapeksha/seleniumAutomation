package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    public void loginWithValidCredentials() {
        driver.get("https://advantageonlineshopping.com/#/");

        driver.findElement(By.id("menuUser")).click();

        // Wait for login modal
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));

        // Fill credentials
        driver.findElement(By.name("username")).sendKeys("sandy10");
        driver.findElement(By.name("password")).sendKeys("xp@Qg7uCT48j");

        // Wait until loader is hidden
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loader")));

        // Click Sign In
        driver.findElement(By.id("sign_in_btn")).click();


        // Wait for login to complete
        WebElement userIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#menuUserLink > span")
        ));

        System.out.println("Login successful, username shown: " + userIcon.getText());
    }

}
