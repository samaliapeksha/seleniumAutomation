package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class LogoutTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://www.advantageonlineshopping.com/#/");
    }

    @Test
    public void loginAndLogout() {
        // Click user icon
        driver.findElement(By.id("menuUser")).click();

        // Wait for login modal
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));

        // Enter credentials
        driver.findElement(By.name("username")).sendKeys("sandy10");
        driver.findElement(By.name("password")).sendKeys("xp@Qg7uCT48j");

        // Wait for loader to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loader")));

        // Click Sign In
        driver.findElement(By.id("sign_in_btn")).click();

        // Wait for login to complete (user name visible)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#menuUserLink > span")));

        // Click user icon to open dropdown
        driver.findElement(By.id("menuUserLink")).click();

        // Wait for the menu container
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginMiniTitle")));

        // Wait for the Sign Out label using stable XPath
        WebElement signOutLabel = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id='loginMiniTitle']//label[@translate='Sign_out']")
                )
        );

        // Click using JavaScript to avoid issues with non-clickable labels
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signOutLabel);

        // Verify logout - wait until user icon (Sign In) is visible again
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menuUser")));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
