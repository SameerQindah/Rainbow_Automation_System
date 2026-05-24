package com.test.RainbowProject;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Locaters.locaersLogin;

public class TestLogin extends loginPage {
	ExtentReports extent;
	ExtentTest test;

	@BeforeClass
	public void openBrowser() {

		ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);

		extent.setSystemInfo("Project Name", "Rainbow Automation");
		extent.setSystemInfo("Tester", "Sameer Qindah");
		extent.setSystemInfo("Browser", "Chrome");

		setUp();
	}

	@BeforeMethod
	public void goToLoginPage() {
		driver.get("https://web.openrainbow.net/rb/2.170.17/#/login");
	}

	@Test(dataProvider = "create")
	public void validLogin(String username, String password, String expectedResult) throws InterruptedException {

		test = extent.createTest("Login Test - Username: " + username + " | Expected: " + expectedResult);

		try {
			test.log(Status.INFO, "Starting login test");
			test.log(Status.INFO, "Username: " + username);
			test.log(Status.INFO, "Expected Result: " + expectedResult);

			Thread.sleep(5000);

			TextElement(username, locaersLogin.Username);
			test.log(Status.INFO, "Entered username");
			Thread.sleep(5000);
			
			if (expectedResult.equals("disabledEmail")) {

			    String ariaDisabled = driver.findElement(locaersLogin.ContinueButton).getAttribute("aria-disabled");

			    AssertJUnit.assertTrue(
			            "Continue button should be disabled when username is less than 5 characters",
			            ariaDisabled.equals("true")
			    );

			    test.log(Status.PASS, "Continue button is disabled when username is less than 5 characters");
			    return;

			}else if (expectedResult.equals("selectEnvironment")) { 
				clickButton(locaersLogin.EnvironmentDropdown); 
				clickButton(locaersLogin.SreEnvironmentOption); Thread.sleep(5000);

				String actualurl = driver.getCurrentUrl(); 
				String expectedurl = "https://web-sre-edge-lts-sbg-dev1.openrainbow.org/rb/2.161.29/index.html#/login"; 

				test.log(Status.INFO, "Actual URL: " + actualurl); test.log(Status.INFO, "Expected URL: " + expectedurl); 
				
				AssertJUnit.assertTrue(
			            "Environment page did not open correctly. Actual URL: " + actualurl,
			            actualurl.contains("web-sre-edge-lts-sbg-dev-1.openrainbow.org")
			    );
				
				test.log(Status.PASS, "Environment dropdown displayed and selected and openrainbow page opened correct URL");
				 return;
				 }

			clickButton(locaersLogin.ContinueButton);

			test.log(Status.INFO, "Clicked Continue button");

			Thread.sleep(3000);
			TextElement(password, locaersLogin.Password);
			test.log(Status.INFO, "Entered password");
			
			if (expectedResult.equals("disabledPassword")) {

			    String ariaDisabled = driver.findElement(locaersLogin.ConnectButton).getAttribute("aria-disabled");

			    AssertJUnit.assertTrue(
			            "Connect button should be disabled when username is less than 8 characters",
			            ariaDisabled.equals("true")
			    );

			    test.log(Status.PASS, "Connect button is disabled when password is less than 8 characters");
			    clickButton(locaersLogin.ModifyEmailButton);
			    return;

			}

			Thread.sleep(5000);

			clickButton(locaersLogin.ConnectButton);
			test.log(Status.INFO, "Clicked Connect button");

			Thread.sleep(3000);

			if (expectedResult.equals("success")) {
				Thread.sleep(25000);

				AssertJUnit.assertTrue("Login failed, home page not opened", driver.getCurrentUrl().contains("home"));

				test.log(Status.PASS, "Login success and dashboard page opened");

			} else if (expectedResult.equals("incorrect")) {

				AssertJUnit.assertTrue("incorrect credentials message is not displayed",
						driver.getPageSource().contains("Incorrect username or password"));

				test.log(Status.PASS, "Invalid credentials message is displayed");
				clickButton(locaersLogin.ModifyEmailButton);

			}

		} catch (AssertionError e) {

			test.log(Status.FAIL, "Test failed: " + e.getMessage());
			throw e;

		} catch (Exception e) {

			test.log(Status.FAIL, "Test failed because of exception: " + e.getMessage());
			throw e;
		}
	}

	@DataProvider(name = "create")
	public Object[][] dataSet1() {
		return new Object[][] { { "mmahmoud@asaltech.com", "", "selectEnvironment" }, { "same", "", "disabledEmail" }, { "sameer", "123456", "disabledPassword" }, { "Admin", "12345678", "incorrect" },
				{ "sameerfaris2005@gmail.com", "Sameerqindah_123", "success" } };
	}
	
	@Test
	public void ForgotPassword() throws InterruptedException { // click on forgot your password because not reminder the
																// password

		test = extent.createTest("Forgot Password Test");

		try {
			test.log(Status.INFO, "Starting Forgot Password test");

			Thread.sleep(5000);

			TextElement("sameer@gmail.com", locaersLogin.Username);
			test.log(Status.INFO, "Entered email: sameer@gmail.com");

			Thread.sleep(5000);

			clickButton(locaersLogin.ContinueButton);
			test.log(Status.INFO, "Clicked Continue button");

			Thread.sleep(3000);

			clickButton(locaersLogin.ForgotPassword);
			test.log(Status.INFO, "Clicked Forgot Password link");

			Thread.sleep(3000);

			AssertJUnit.assertTrue("Reset your password page is not displayed",
					driver.findElement(locaersLogin.ResetPasswordTitle).isDisplayed());

			test.log(Status.PASS, "Reset your password page is displayed");

		} catch (AssertionError e) {

			test.log(Status.FAIL, "Test failed: " + e.getMessage());
			throw e;

		} catch (Exception e) {

			test.log(Status.FAIL, "Test failed because of exception: " + e.getMessage());
			throw e;
		}
	}

	@Test
	public void LearnMoreLinkAboutRainbow() throws InterruptedException { // click on Learn More About Rainbow link

		test = extent.createTest("Learn More Link About Rainbow Test");

		try {
			test.log(Status.INFO, "Starting Learn More Link About Rainbow test");

			Thread.sleep(3000);

			clickButton(locaersLogin.LearnMoreLink);
			test.log(Status.INFO, "Clicked Learn More about Rainbow link");

			Thread.sleep(5000);

			Object[] windows = driver.getWindowHandles().toArray();

			driver.switchTo().window(windows[1].toString());
			test.log(Status.INFO, "Switched to new tab");

			String actualurl = driver.getCurrentUrl();
			String expectedurl = "https://www.openrainbow.com/app/fr";

			test.log(Status.INFO, "Actual URL: " + actualurl);
			test.log(Status.INFO, "Expected URL: " + expectedurl);

			AssertJUnit.assertEquals(actualurl, expectedurl);

			test.log(Status.PASS, "Learn More link opened correct URL");

			driver.close();
			test.log(Status.INFO, "Closed new tab");

			driver.switchTo().window(windows[0].toString());
			test.log(Status.INFO, "Returned to original tab");

		} catch (AssertionError e) {

			test.log(Status.FAIL, "Test failed: " + e.getMessage());
			throw e;
		}
	}

	@Test
	public void DontHaveAnAccountSignUp() throws InterruptedException { // click on Don't Have An Account ? Sign Up!
																		// link

		test = extent.createTest("Don't Have An Account Sign Up Test");

		try {
			test.log(Status.INFO, "Starting Don't Have An Account Sign Up test");

			Thread.sleep(3000);

			clickButton(locaersLogin.DontHaveAnAccountlink);
			test.log(Status.INFO, "Clicked Don't have an account Sign up link");

			Thread.sleep(3000);

			AssertJUnit.assertTrue("Create Account page is not displayed",
					driver.findElement(locaersLogin.CreateAccountTitle).isDisplayed());

			test.log(Status.PASS, "Create Account page is displayed");

		} catch (AssertionError e) {

			test.log(Status.FAIL, "Test failed: " + e.getMessage());
			throw e;
		}
	}

	@Test
	public void TheTermsOfService() throws InterruptedException { // click on The Terms Of Service link

		test = extent.createTest("Terms Of Service Link Test");

		try {
			test.log(Status.INFO, "Starting Terms Of Service link test");

			Thread.sleep(5000);

			clickButton(locaersLogin.TermsOfServiceLink);
			test.log(Status.INFO, "Clicked Terms Of Service link");

			Thread.sleep(3000);

			Object[] windows = driver.getWindowHandles().toArray();

			driver.switchTo().window(windows[1].toString());
			test.log(Status.INFO, "Switched to new tab");

			String actualurl = driver.getCurrentUrl();
			String expectedurl = "https://www.al-enterprise.com/en/rainbow/terms-of-service";

			test.log(Status.INFO, "Actual URL: " + actualurl);
			test.log(Status.INFO, "Expected URL: " + expectedurl);

			AssertJUnit.assertEquals(actualurl, expectedurl);

			test.log(Status.PASS, "Terms Of Service page opened correct URL");

			driver.close();
			test.log(Status.INFO, "Closed new tab");

			driver.switchTo().window(windows[0].toString());
			test.log(Status.INFO, "Returned to original tab");

		} catch (AssertionError e) {

			test.log(Status.FAIL, "Test failed: " + e.getMessage());
			throw e;
		}
	}

	@Test
	public void ThePrivacyPolicy() throws InterruptedException { // click on The Privacy Policy link

		test = extent.createTest("Privacy Policy Link Test");

		try {
			test.log(Status.INFO, "Starting Privacy Policy link test");

			Thread.sleep(5000);

			clickButton(locaersLogin.PrivacyPolicyLink);
			test.log(Status.INFO, "Clicked Privacy Policy link");

			Thread.sleep(3000);

			Object[] windows = driver.getWindowHandles().toArray();

			driver.switchTo().window(windows[1].toString());
			test.log(Status.INFO, "Switched to new tab");

			String actualurl = driver.getCurrentUrl();
			String expectedurl = "https://www.al-enterprise.com/en/rainbow/data-privacy";

			test.log(Status.INFO, "Actual URL: " + actualurl);
			test.log(Status.INFO, "Expected URL: " + expectedurl);

			AssertJUnit.assertEquals(actualurl, expectedurl);

			test.log(Status.PASS, "Privacy Policy page opened correct URL");

			driver.close();
			test.log(Status.INFO, "Closed new tab");

			driver.switchTo().window(windows[0].toString());
			test.log(Status.INFO, "Returned to original tab");

		} catch (AssertionError e) {

			test.log(Status.FAIL, "Test failed: " + e.getMessage());
			throw e;
		}
	}
	
	@AfterClass
	public void tearDown() {

		quit();
		extent.flush();
	}

}