package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.time.Duration;

public class RegisterTest extends BaseTest {

    @Test
    public void registerNewUser() {
        driver.findElement(By.id("menuUser")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Wait for loader to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loader")));

        // Wait until "CREATE NEW ACCOUNT" link is clickable
        WebElement createNewAccount = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='CREATE NEW ACCOUNT']"))
        );
        createNewAccount.click();

        // Wait for the first field of the form to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("usernameRegisterPage")));

        // Fill the form
        driver.findElement(By.name("usernameRegisterPage")).sendKeys("Nimal");
        driver.findElement(By.name("emailRegisterPage")).sendKeys("nimal@example.com");
        driver.findElement(By.name("passwordRegisterPage")).sendKeys("Test1234");
        driver.findElement(By.name("confirm_passwordRegisterPage")).sendKeys("Test1234");
        driver.findElement(By.name("first_nameRegisterPage")).sendKeys("Nimal");
        driver.findElement(By.name("last_nameRegisterPage")).sendKeys("Peiris");
        driver.findElement(By.name("phone_numberRegisterPage")).sendKeys("0771284507");
        driver.findElement(By.name("cityRegisterPage")).sendKeys("Colombo");
        driver.findElement(By.name("addressRegisterPage")).sendKeys("123 Main St");
        driver.findElement(By.name("state_/_province_/_regionRegisterPage")).sendKeys("Western");
        driver.findElement(By.name("postal_codeRegisterPage")).sendKeys("10100");
        driver.findElement(By.name("i_agree")).click();

        // Register
        driver.findElement(By.xpath("//button[@id='register_btn']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='hi-user containMiniTitle ng-binding']")));

        // Wait for the user icon to become active
        driver.findElement(By.id("menuUser")).click();

        // Wait for "Sign out" label to appear after registration
        WebElement signOut = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//label[@role='link'][normalize-space()='Sign out']")));

        //Assert that "Sign out" is displayed, meaning registration & login was successful
        Assert.assertTrue(signOut.isDisplayed(), "Sign out label should be visible after registration");

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
