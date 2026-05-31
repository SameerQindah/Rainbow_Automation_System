package com.test.RainbowProject;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import Locaters.locatersCreateBubble;

public class TestCreateBubble extends CreateBubblePage {

	@BeforeClass
	public void openBrowser() throws InterruptedException {
		setUp();
		Thread.sleep(5000);
		loginSystem();
	}

	@BeforeMethod
	public void goToMainPage() throws InterruptedException {
		Thread.sleep(10000);
		clickOnBubbleOption();
		Thread.sleep(5000);
		clickOnCreateBubbleButton();
		Thread.sleep(2000);
	}

	@Test
	public void ValidCreateBubble() throws InterruptedException {

		test = extent.createTest("Valid Create Bubble Test");

		test.info("Starting ValidCreateBubble test");

		Thread.sleep(2000);

		String before = driver.findElement(locatersCreateBubble.AllBubblesLabel).getText();
		int countBefore = Integer.parseInt(before.replaceAll("\\D+", ""));
		test.info("Bubble count before create: " + countBefore);

		TextElement("Testing", locatersCreateBubble.BubbleName);
		test.info("Entered Bubble Name: Testing");

		TextElement("Training", locatersCreateBubble.BubbleSubject);
		test.info("Entered Bubble Subject: Training");

		Thread.sleep(2000);

		clickButton(locatersCreateBubble.FirstNextButton);
		test.info("Clicked First Next Button");

		Thread.sleep(2000);

		TextElement("sameer", locatersCreateBubble.SearchForInvite);
		test.info("Searched for user: sameer");

		Thread.sleep(2000);

		clickButton(locatersCreateBubble.SearchResultUser);
		test.info("Selected first invited user");

		Thread.sleep(2000);

		TextElement("sameer", locatersCreateBubble.SearchForInvite);
		test.info("Searched again for user: sameer");

		Thread.sleep(2000);

		clickButton(locatersCreateBubble.SearchResultUser);
		test.info("Selected second invited user");

		clickButton(locatersCreateBubble.SecondNextButton);
		test.info("Clicked Second Next Button");

		Thread.sleep(2000);

		TextElement("school", locatersCreateBubble.SearchImages);
		test.info("Searched image: school");

		Thread.sleep(2000);

		clickButton(locatersCreateBubble.FirstImage);
		test.info("Selected first image");

		clickButton(locatersCreateBubble.CreateButton);
		test.info("Clicked Create Button");

		Thread.sleep(7000);

		clickOnBubbleOption();
		test.info("Opened Bubble Options");

		Thread.sleep(3000);

		String after = driver.findElement(locatersCreateBubble.AllBubblesLabel).getText();
		int countAfter = Integer.parseInt(after.replaceAll("\\D+", ""));
		test.info("Bubble count after create: " + countAfter);

		try {
			AssertJUnit.assertEquals(countBefore + 1, countAfter);
			test.pass("Bubble created successfully. Count increased by 1");
		} catch (AssertionError e) {
			test.fail("Bubble was not created. Expected: " + (countBefore + 1) + " but found: " + countAfter);
			throw e;
		}
	}

	@Test
	public void CancelCreateBubble() throws InterruptedException {

		test = extent.createTest("Cancel Create Bubble Test");

		test.info("Starting CancelCreateBubble test");

		Thread.sleep(2000);

		String before = driver.findElement(locatersCreateBubble.AllBubblesLabel).getText();
		int countBefore = Integer.parseInt(before.replaceAll("\\D+", ""));
		test.info("Bubble count before cancel: " + countBefore);

		TextElement("Testing", locatersCreateBubble.BubbleName);
		test.info("Entered Bubble Name: Testing");

		TextElement("Training", locatersCreateBubble.BubbleSubject);
		test.info("Entered Bubble Subject: Training");

		Thread.sleep(2000);

		clickButton(locatersCreateBubble.CancelButton);
		test.info("Clicked Cancel Button");

		Thread.sleep(7000);

		clickOnBubbleOption();
		test.info("Opened Bubble Options");

		Thread.sleep(3000);

		String after = driver.findElement(locatersCreateBubble.AllBubblesLabel).getText();
		int countAfter = Integer.parseInt(after.replaceAll("\\D+", ""));
		test.info("Bubble count after cancel: " + countAfter);

		try {
			AssertJUnit.assertEquals(countBefore, countAfter);
			test.pass("Bubble creation was cancelled successfully. Count remained unchanged.");
		} catch (AssertionError e) {
			test.fail("Bubble count changed after cancel. Expected: " + countBefore + " but found: " + countAfter);
			throw e;
		}
	}

	@Test
	public void InValidCreateBubble() throws InterruptedException {

		test = extent.createTest("Invalid Create Bubble Test");

		test.info("Starting InValidCreateBubble test");

		Thread.sleep(2000);

		String before = driver.findElement(locatersCreateBubble.AllBubblesLabel).getText();
		int countBefore = Integer.parseInt(before.replaceAll("\\D+", ""));
		test.info("Bubble count before invalid create: " + countBefore);

		TextElement("Testing", locatersCreateBubble.BubbleName);
		test.info("Entered Bubble Name: Testing");

		TextElement("Training", locatersCreateBubble.BubbleSubject);
		test.info("Entered Bubble Subject: Training");

		Thread.sleep(2000);

		clickButton(locatersCreateBubble.FirstNextButton);
		test.info("Clicked First Next Button");

		Thread.sleep(2000);

		TextElement("sameer", locatersCreateBubble.SearchForInvite);
		test.info("Searched for user: sameer");

		Thread.sleep(2000);

		clickButton(locatersCreateBubble.SearchResultUser);
		test.info("Selected first invited user");

		Thread.sleep(2000);

		TextElement("sameer", locatersCreateBubble.SearchForInvite);
		test.info("Searched again for user: sameer");

		Thread.sleep(2000);

		clickButton(locatersCreateBubble.SearchResultUser);
		test.info("Selected second invited user");

		clickButton(locatersCreateBubble.SecondNextButton);
		test.info("Clicked Second Next Button");

		Thread.sleep(2000);

		TextElement("school", locatersCreateBubble.SearchImages);
		test.info("Searched image: school");

		Thread.sleep(2000);

		clickButton(locatersCreateBubble.FirstImage);
		test.info("Selected first image");

		Thread.sleep(2000);

		clickButton(locatersCreateBubble.closeButton);
		test.info("Clicked 'X' Button before creating bubble");

		Thread.sleep(3000);

		String after = driver.findElement(locatersCreateBubble.AllBubblesLabel).getText();
		int countAfter = Integer.parseInt(after.replaceAll("\\D+", ""));
		test.info("Bubble count after closing: " + countAfter);

		try {
			AssertJUnit.assertEquals(countBefore, countAfter);
			test.pass("Bubble was not created after clicking 'X', Count remained unchanged.");
		} catch (AssertionError e) {
			test.fail(
					"Bubble count changed after clicking 'X'. Expected: " + countBefore + " but found: " + countAfter);
			throw e;
		}
	}

	@Test
	public void displayErrorBubbleName() throws InterruptedException {

	    ExtentTest test = extent.createTest("Display Error Message For Bubble Name");

	    test.info("Starting displayErrorBubbleName test");

	    Thread.sleep(2000);

	    TextElement("Te", locatersCreateBubble.BubbleName);
	    test.info("Entered invalid Bubble Name: Te");

	    TextElement("Training", locatersCreateBubble.BubbleSubject);
	    test.info("Entered Bubble Subject: Training");

	    Thread.sleep(3000);

	    String actualError = driver.findElement(locatersCreateBubble.ErrorMessageName).getText();
	    String expectedError = "Bubble name must be at least 3 characters long";

	    test.info("Actual Error Message: " + actualError);
	    test.info("Expected Error Message: " + expectedError);

	    try {
	        Assert.assertEquals(actualError, expectedError);
	        test.pass("Correct validation message is displayed.");
	    } catch (AssertionError e) {
	        test.fail("Validation message mismatch. Expected: '" 
	                + expectedError + "' but found: '" 
	                + actualError + "'");
	        throw e;
	    }
	}

	@AfterClass
	public void tearDown() {

		quit();
	}

}
