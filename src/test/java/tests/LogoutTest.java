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
        driver.findElement(By.id("menuUser")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        driver.findElement(By.name("username")).sendKeys("sandy10");
        driver.findElement(By.name("password")).sendKeys("xp@Qg7uCT48j");

        driver.findElement(By.id("sign_in_btn")).click();

        // Wait for loader to disappear after clicking login
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loader")));

        // Wait for user to be logged in (username displayed)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#menuUserLink > span")));

        // Wait again for loader to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loader")));

        // Wait until menuUserLink is clickable
        WebElement menuUserLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("menuUserLink")));
        menuUserLink.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginMiniTitle")));

        WebElement signOutLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='loginMiniTitle']//label[@translate='Sign_out']")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signOutLabel);

        // Verify logout
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menuUser")));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
