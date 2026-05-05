package com.test.login2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginQa {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://qa-practice.razvanvancea.ro/auth_ecommerce.html");
        driver.manage().window().maximize();
    }
    @Test
    public void validLogin() throws InterruptedException {
        driver.findElement(By.id("email")).sendKeys("admin@admin.com");
        driver.findElement(By.id("password")).sendKeys("admin123");

        Thread.sleep(3000);

        driver.findElement(By.id("submitLoginBtn")).click();
        
        Thread.sleep(3000);
        WebElement successMessage = driver.findElement(By.id("home"));
        String successMessageText = successMessage.getText();
        String expectedMessageText = "Home";
        Assert.assertEquals(successMessageText, expectedMessageText);
        
    }
    @Test
    public void invalidLogin() throws InterruptedException {
        driver.findElement(By.id("email")).sendKeys("sameer@admin.com");
        driver.findElement(By.id("password")).sendKeys("sameer123");

        Thread.sleep(3000);

        driver.findElement(By.id("submitLoginBtn")).click();

        Thread.sleep(3000);
        WebElement successMessage = driver.findElement(By.id("message"));
        String successMessageText = successMessage.getText();
        String expectedMessageText = "Bad credentials! Please try again! Make sure that you've registered.";
        Assert.assertEquals(successMessageText, expectedMessageText);
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

