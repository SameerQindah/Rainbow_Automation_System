package com.test.RainbowProject;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.Status;

import Locaters.locaersLogin;
public class TestLogin extends BaseTest {

	@BeforeClass
	public void openBrowser() {

		setUp();
	}
	

	@BeforeMethod(alwaysRun = true)
	public void goToLoginPage() {
		driver.get("https://web.openrainbow.net/rb/2.170.17/#/login");
	}
	

	@Test(dataProvider = "create")
	public void validLogin(String username, String password, String expectedResult) throws Exception {

	    test = extent.createTest("Login Test - Username: " + username + " | Expected: " + expectedResult);

	    try {
	        test.log(Status.INFO, "Starting login test");
	        test.log(Status.INFO, "Username: " + username);
	        test.log(Status.INFO, "Expected Result: " + expectedResult);

	        TextElement(username, locaersLogin.Username);
	        test.log(Status.INFO, "Entered username");

	        if (expectedResult.equals("disabledEmail")) {

	            waitButtonDisabled(locaersLogin.ContinueButton);

	            String ariaDisabled = getAttribute(locaersLogin.ContinueButton, "aria-disabled");

	            AssertJUnit.assertTrue(
	                    "Continue button should be disabled when username is less than 5 characters",
	                    ariaDisabled.equals("true")
	            );

	            test.log(Status.PASS, "Continue button is disabled when username is less than 5 characters");
	            return;

	        } else if (expectedResult.equals("selectEnvironment")) {
	        	
	        	 driver.findElement(locaersLogin.Username).sendKeys(Keys.ENTER);
	        	 test.log(Status.INFO, "Pressed Enter after username");
	        	    
	            clickButton(locaersLogin.EnvironmentDropdown);

	            clickButton(locaersLogin.SreEnvironmentOption);

	            waitUrlContainsText("openrainbow.org");

	            String actualurl = driver.getCurrentUrl();
	            String expectedurl = "https://web-sre-edge-lts-sbg-dev1.openrainbow.org/rb/2.161.29/index.html#/login";

	            test.log(Status.INFO, "Actual URL: " + actualurl);
	            test.log(Status.INFO, "Expected URL: " + expectedurl);

	            AssertJUnit.assertTrue(
	                "Environment page did not open correctly. Actual URL: " + actualurl,
	                actualurl.contains("openrainbow.org")
	            );

	            test.log(Status.PASS, "Environment dropdown displayed and selected, openrainbow page opened correct URL");
	            return;
	        }

	        waitButtonEnabled(locaersLogin.ContinueButton);
	        clickButton(locaersLogin.ContinueButton);

	        test.log(Status.INFO, "Clicked Continue button");

	        waitVisible(locaersLogin.Password);
	        TextElement(password, locaersLogin.Password);

	        test.log(Status.INFO, "Entered password");

	        if (expectedResult.equals("disabledPassword")) {

	            waitButtonDisabled(locaersLogin.ConnectButton);

	            String ariaDisabled = getAttribute(locaersLogin.ConnectButton, "aria-disabled");

	            AssertJUnit.assertTrue(
	                    "Connect button should be disabled when password is less than 8 characters",
	                    ariaDisabled.equals("true")
	            );

	            test.log(Status.PASS, "Connect button is disabled when password is less than 8 characters");

	            clickButton(locaersLogin.ModifyEmailButton);
	            return;
	        }

	        waitButtonEnabled(locaersLogin.ConnectButton);
	        clickButton(locaersLogin.ConnectButton);

	        test.log(Status.INFO, "Clicked Connect button");

	        if (expectedResult.equals("success")) {

	            waitUrlContainsText("home");

	            AssertJUnit.assertTrue(
	                    "Login failed, home page not opened",
	                    driver.getCurrentUrl().contains("home")
	            );

	            test.log(Status.PASS, "Login success and dashboard page opened");

	        } else if (expectedResult.equals("incorrect")) {

	            waitPageContains("Incorrect username or password");

	            AssertJUnit.assertTrue(
	                    "incorrect credentials message is not displayed",
	                    driver.getPageSource().contains("Incorrect username or password")
	            );

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
	public Object[][] dataSet1() throws IOException {
	    return ReadTextFile.readLoginData("src/test/java/com/test/RainbowProject/loginData.txt");
	}
	
	
	
	
	@Test
	public void ForgotPassword() { // click on forgot your password because not reminder the password

	    test = extent.createTest("Forgot Password Test");

	    try {
	        test.log(Status.INFO, "Starting Forgot Password test");

	        TextElement("sameer@gmail.com", locaersLogin.Username);
	        test.log(Status.INFO, "Entered email: sameer@gmail.com");

	        waitButtonEnabled(locaersLogin.ContinueButton);
	        clickButton(locaersLogin.ContinueButton);
	        test.log(Status.INFO, "Clicked Continue button");

	        waitClickable(locaersLogin.ForgotPassword);
	        clickButton(locaersLogin.ForgotPassword);
	        test.log(Status.INFO, "Clicked Forgot Password link");

	        waitVisible(locaersLogin.ResetPasswordTitle);

	        AssertJUnit.assertTrue(
	                "Reset your password page is not displayed",
	                driver.findElement(locaersLogin.ResetPasswordTitle).isDisplayed()
	        );

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
	public void LearnMoreLinkAboutRainbow() {  // click on Learn More About Rainbow link

	    test = extent.createTest("Learn More Link About Rainbow Test");

	    try {
	        test.log(Status.INFO, "Starting Learn More Link About Rainbow test");

	        String originalWindow = driver.getWindowHandle();

	        waitClickable(locaersLogin.LearnMoreLink);
	        clickButton(locaersLogin.LearnMoreLink);
	        test.log(Status.INFO, "Clicked Learn More about Rainbow link");

	        waitDriver().until(ExpectedConditions.numberOfWindowsToBe(2));

	        for (String windowHandle : driver.getWindowHandles()) {
	            if (!windowHandle.equals(originalWindow)) {
	                driver.switchTo().window(windowHandle);
	                break;
	            }
	        }

	        test.log(Status.INFO, "Switched to new tab");

	        waitUrlContainsText("openrainbow.com");

	        String actualurl = driver.getCurrentUrl();
	        String expectedurl = "https://www.openrainbow.com/app/fr";

	        test.log(Status.INFO, "Actual URL: " + actualurl);
	        test.log(Status.INFO, "Expected URL: " + expectedurl);

	        AssertJUnit.assertEquals(actualurl, expectedurl);

	        test.log(Status.PASS, "Learn More link opened correct URL");

	        driver.close();
	        test.log(Status.INFO, "Closed new tab");

	        driver.switchTo().window(originalWindow);
	        test.log(Status.INFO, "Returned to original tab");

	    } catch (AssertionError e) {

	        test.log(Status.FAIL, "Test failed: " + e.getMessage());
	        throw e;

	    } catch (Exception e) {

	        test.log(Status.FAIL, "Test failed because of exception: " + e.getMessage());
	        throw e;
	    }
	}
	
	@Test
	public void DontHaveAnAccountSignUp() {// click on Don't Have An Account ? Sign Up! link

	    test = extent.createTest("Don't Have An Account Sign Up Test");

	    try {
	        test.log(Status.INFO, "Starting Don't Have An Account Sign Up test");

	        waitClickable(locaersLogin.DontHaveAnAccountlink);
	        clickButton(locaersLogin.DontHaveAnAccountlink);

	        test.log(Status.INFO, "Clicked Don't have an account Sign up link");

	        WebElement createAccountTitle = waitVisible(locaersLogin.CreateAccountTitle);

	        AssertJUnit.assertTrue(
	                "Create Account page is not displayed",
	                createAccountTitle.isDisplayed()
	        );

	        test.log(Status.PASS, "Create Account page is displayed");

	    } catch (AssertionError e) {

	        test.log(Status.FAIL, "Test failed: " + e.getMessage());
	        throw e;

	    } catch (Exception e) {

	        test.log(Status.FAIL, "Test failed because of exception: " + e.getMessage());
	        throw e;
	    }
	}

	
	@Test
	public void TheTermsOfService() { // click on The Terms Of Service link

	    test = extent.createTest("Terms Of Service Link Test");

	    try {
	        test.log(Status.INFO, "Starting Terms Of Service link test");

	        String originalWindow = driver.getWindowHandle();

	        waitClickable(locaersLogin.TermsOfServiceLink);
	        clickButton(locaersLogin.TermsOfServiceLink);

	        test.log(Status.INFO, "Clicked Terms Of Service link");

	        waitDriver().until(ExpectedConditions.numberOfWindowsToBe(2));

	        for (String windowHandle : driver.getWindowHandles()) {
	            if (!windowHandle.equals(originalWindow)) {
	                driver.switchTo().window(windowHandle);
	                break;
	            }
	        }

	        test.log(Status.INFO, "Switched to new tab");

	        waitUrlContains("terms-of-service");

	        String actualurl = driver.getCurrentUrl();
	        String expectedurl = "https://www.al-enterprise.com/en/rainbow/terms-of-service";

	        test.log(Status.INFO, "Actual URL: " + actualurl);
	        test.log(Status.INFO, "Expected URL: " + expectedurl);

	        AssertJUnit.assertEquals(actualurl, expectedurl);

	        test.log(Status.PASS, "Terms Of Service page opened correct URL");

	        driver.close();
	        test.log(Status.INFO, "Closed new tab");

	        driver.switchTo().window(originalWindow);
	        test.log(Status.INFO, "Returned to original tab");

	    } catch (AssertionError e) {

	        test.log(Status.FAIL, "Test failed: " + e.getMessage());
	        throw e;

	    } catch (Exception e) {

	        test.log(Status.FAIL, "Test failed because of exception: " + e.getMessage());
	        throw e;
	    }
	} 

	
	@Test
	public void ThePrivacyPolicy() {// click on The Privacy Policy link

	    test = extent.createTest("Privacy Policy Link Test");

	    try {
	        test.log(Status.INFO, "Starting Privacy Policy link test");

	        clickButton(locaersLogin.PrivacyPolicyLink);
	        test.log(Status.INFO, "Clicked Privacy Policy link");

	        waitDriver().until(ExpectedConditions.numberOfWindowsToBe(2));

	        Object[] windows = driver.getWindowHandles().toArray();

	        driver.switchTo().window(windows[1].toString());
	        test.log(Status.INFO, "Switched to new tab");

	        waitUrlContains("/rainbow/data-privacy");

	        String actualurl = driver.getCurrentUrl();
	        String expectedurl = "https://www.al-enterprise.com/en/rainbow/data-privacy";

	        test.log(Status.INFO, "Actual URL: " + actualurl);
	        test.log(Status.INFO, "Expected URL: " + expectedurl);

	        AssertJUnit.assertEquals(expectedurl, actualurl);

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
	}

}