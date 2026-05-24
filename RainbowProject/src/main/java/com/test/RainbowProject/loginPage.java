package com.test.RainbowProject;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class loginPage {

	public WebDriver driver;

	public void setUp() {
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.password_manager_leak_detection", false);

		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--disable-save-password-bubble");
		options.addArguments("--disable-features=PasswordLeakDetection");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://web.openrainbow.net/rb/173/login");
	}

	public void TextElement(String value, By locator) {
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(value);
	}

	public void clickButton(By locator) {
		driver.findElement(locator).click();
	}

	public void quit() {
		driver.quit();
	}
}
