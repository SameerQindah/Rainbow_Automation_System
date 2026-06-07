package com.test.RainbowProject;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Locaters.locatersCreateBubble;

public class BasePage {

	public WebDriver driver;

	public static ExtentReports extent;
	public ExtentTest test;

	@BeforeSuite
	public void setupReport() {
		ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");

		extent = new ExtentReports();
		extent.attachReporter(spark);

		extent.setSystemInfo("Project Name", "Rainbow Automation");
		extent.setSystemInfo("Tester", "Sameer Qindah");
		extent.setSystemInfo("Browser", "Chrome");
	}

	@AfterSuite
	public void tearDownReport() {
		extent.flush();
	}

	public void setUp() {
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();

		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.password_manager_leak_detection", false);

		prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
		prefs.put("profile.default_content_setting_values.media_stream_mic", 1);
		prefs.put("profile.default_content_setting_values.notifications", 2);

		options.setExperimentalOption("prefs", prefs);

		options.addArguments("--disable-save-password-bubble");
		options.addArguments("--disable-features=PasswordLeakDetection");
		options.addArguments("--use-fake-ui-for-media-stream");
		options.addArguments("--use-fake-device-for-media-stream");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		driver.get("https://web.openrainbow.net/rb/173/login");
	}

	public WebDriverWait waitDriver() {
		return new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public WebElement waitVisible(By locator) {
		return waitDriver().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitClickable(By locator) {
		return waitDriver().until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void TextElement(String value, By locator) {
		WebElement element = waitVisible(locator);
		element.clear();
		element.sendKeys(value);
	}

	public void clickButton(By locator) {
		waitClickable(locator).click();
	}
	public void waitUntilTextNumberChanged(By locator, int expectedNumber) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    wait.until(driver -> {
	        String text = driver.findElement(locator).getText();
	        int actualNumber = Integer.parseInt(text.replaceAll("\\D+", ""));
	        return actualNumber == expectedNumber;
	    });
	}

	public String getText(By locator) {
		return waitVisible(locator).getText();
	}

	public String getAttribute(By locator, String attributeName) {
		return waitVisible(locator).getAttribute(attributeName);
	}

	public void hoverOnElement(By locator) {
		WebElement element = waitVisible(locator);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	public void waitUrlContains(String text) {
		waitDriver().until(ExpectedConditions.urlContains(text));
	}

	public void waitTextToChange(By locator, String oldText) {
		waitDriver().until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, oldText)));
	}
	public void waitButtonEnabled(By locator) {
	    waitDriver().until(driver -> {
	        String value = driver.findElement(locator).getAttribute("aria-disabled");
	        return value == null || value.equals("false");
	    });
	}

	public void loginSystem() {

	    TextElement("sameerfaris2005@gmail.com", locatersCreateBubble.Username);

	    waitButtonEnabled(locatersCreateBubble.ContinueButton);
	    clickButton(locatersCreateBubble.ContinueButton);

	    TextElement("Sameerqindah_123", locatersCreateBubble.Password);

	    waitButtonEnabled(locatersCreateBubble.ConnectButton);
	    clickButton(locatersCreateBubble.ConnectButton);
	}

	public void clickOnBubbleOption() {
		clickButton(locatersCreateBubble.optionBubble);
	}
	public void waitButtonDisabled(By locator) {
	    waitDriver().until(driver -> {
	        String value = driver.findElement(locator).getAttribute("aria-disabled");
	        return value != null && value.equals("true");
	    });
	}

	public void waitPageContains(String text) {
	    waitDriver().until(driver -> driver.getPageSource().contains(text));
	}

	public void waitUrlContainsText(String text) {
	    waitDriver().until(ExpectedConditions.urlContains(text));
	}

	public void quit() {
		if (driver != null) {
			driver.quit();
		}
	}
}