package com.test.RainbowProject;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest extends BasePage {

    @BeforeMethod
    public void openLoginPage() {
        driver.get("https://web.openrainbow.net/rb/173/login");
    }

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

		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--disable-save-password-bubble");
		options.addArguments("--disable-features=PasswordLeakDetection");

		prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
		prefs.put("profile.default_content_setting_values.media_stream_mic", 1);
		prefs.put("profile.default_content_setting_values.notifications", 2);

		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--use-fake-ui-for-media-stream");
		options.addArguments("--use-fake-device-for-media-stream");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://web.openrainbow.net/rb/173/login");
	}
}