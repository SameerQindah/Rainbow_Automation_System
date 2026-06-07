package com.test.RainbowProject;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import Locaters.locatersCreateBubble;
public class TestCreateBubble extends CreateBubblePage {

	@BeforeClass
	public void openBrowser() {

	    setUp();
	    loginSystem();
	    waitUrlContains("home");
	}

	
	@BeforeMethod(alwaysRun = true)
	public void goToMainPage() throws InterruptedException {
		
		clickOnBubbleOption();
		
		clickOnCreateBubbleButton();
		
	}

	@Test
	public void ValidCreateBubble() throws Exception {

	    test = extent.createTest("Valid Create Bubble Test");

	    try {
	        test.info("Starting ValidCreateBubble test");

	        waitVisible(locatersCreateBubble.AllBubblesLabel);

	        String before = driver.findElement(locatersCreateBubble.AllBubblesLabel).getText();
	        int countBefore = Integer.parseInt(before.replaceAll("\\D+", ""));
	        test.info("Bubble count before create: " + countBefore);

	        waitVisible(locatersCreateBubble.BubbleName);
	        TextElement("Testing", locatersCreateBubble.BubbleName);
	        test.info("Entered Bubble Name: Testing");

	        waitVisible(locatersCreateBubble.BubbleSubject);
	        TextElement("Training", locatersCreateBubble.BubbleSubject);
	        test.info("Entered Bubble Subject: Training");

	        waitClickable(locatersCreateBubble.FirstNextButton);
	        clickButton(locatersCreateBubble.FirstNextButton);
	        test.info("Clicked First Next Button");

	        waitVisible(locatersCreateBubble.SearchForInvite);
	        TextElement("sameer", locatersCreateBubble.SearchForInvite);
	        Thread.sleep(2000);
	        driver.findElement(locatersCreateBubble.SearchForInvite).sendKeys(Keys.SPACE);
	        test.info("Searched for user: sameer");

	        waitClickable(locatersCreateBubble.SearchResultUser);
	        clickButton(locatersCreateBubble.SearchResultUser);
	        test.info("Selected first invited user");

	        waitVisible(locatersCreateBubble.SearchForInvite);
	        TextElement("sameer", locatersCreateBubble.SearchForInvite);
	        Thread.sleep(2000);
	        driver.findElement(locatersCreateBubble.SearchForInvite).sendKeys(Keys.SPACE);
	        test.info("Searched again for user: sameer");

	        waitClickable(locatersCreateBubble.SearchResultUser);
	        clickButton(locatersCreateBubble.SearchResultUser);
	        test.info("Selected second invited user");

	        waitClickable(locatersCreateBubble.SecondNextButton);
	        clickButton(locatersCreateBubble.SecondNextButton);
	        test.info("Clicked Second Next Button");

	        waitVisible(locatersCreateBubble.SearchImages);
	        TextElement("school", locatersCreateBubble.SearchImages);
	        test.info("Searched image: school");

	        waitClickable(locatersCreateBubble.FirstImage);
	        clickButton(locatersCreateBubble.FirstImage);
	        test.info("Selected first image");

	        waitClickable(locatersCreateBubble.CreateButton);
	        clickButton(locatersCreateBubble.CreateButton);
	        test.info("Clicked Create Button");

	        Thread.sleep(5000);
	        clickOnBubbleOption();
	        test.info("Opened Bubble Options");

	        waitUntilTextNumberChanged(
	                locatersCreateBubble.AllBubblesLabel,
	                countBefore + 1
	        );

	        String after = driver.findElement(locatersCreateBubble.AllBubblesLabel).getText();
	        int countAfter = Integer.parseInt(after.replaceAll("\\D+", ""));
	        test.info("Bubble count after create: " + countAfter);

	        AssertJUnit.assertEquals(countBefore + 1, countAfter);
	        test.pass("Bubble created successfully. Count increased by 1");

	    } catch (AssertionError e) {

	        test.fail("Bubble was not created. " + e.getMessage());
	        throw e;

	    } catch (Exception e) {

	        test.fail("Test failed because of exception: " + e.getMessage());
	        throw e;
	    }
	}

	@Test
	public void CancelCreateBubble() {

	    test = extent.createTest("Cancel Create Bubble Test");

	    try {
	        test.info("Starting CancelCreateBubble test");

	        waitVisible(locatersCreateBubble.AllBubblesLabel);

	        String before = driver.findElement(locatersCreateBubble.AllBubblesLabel).getText();
	        int countBefore = Integer.parseInt(before.replaceAll("\\D+", ""));
	        test.info("Bubble count before cancel: " + countBefore);

	        waitVisible(locatersCreateBubble.BubbleName);
	        TextElement("Testing", locatersCreateBubble.BubbleName);
	        test.info("Entered Bubble Name: Testing");

	        waitVisible(locatersCreateBubble.BubbleSubject);
	        TextElement("Training", locatersCreateBubble.BubbleSubject);
	        test.info("Entered Bubble Subject: Training");

	        waitClickable(locatersCreateBubble.CancelButton);
	        clickButton(locatersCreateBubble.CancelButton);
	        test.info("Clicked Cancel Button");

	        clickOnBubbleOption();
	        test.info("Opened Bubble Options");

	        waitVisible(locatersCreateBubble.AllBubblesLabel);

	        String after = driver.findElement(locatersCreateBubble.AllBubblesLabel).getText();
	        int countAfter = Integer.parseInt(after.replaceAll("\\D+", ""));
	        test.info("Bubble count after cancel: " + countAfter);

	        AssertJUnit.assertEquals(countBefore, countAfter);
	        test.pass("Bubble creation was cancelled successfully. Count remained unchanged.");

	    } catch (AssertionError e) {

	        test.fail("Bubble count changed after cancel. " + e.getMessage());
	        throw e;

	    } catch (Exception e) {

	        test.fail("Test failed because of exception: " + e.getMessage());
	        throw e;
	    }
	}

	@Test
	public void InValidCreateBubble() throws Exception {

	    test = extent.createTest("Invalid Create Bubble Test");

	    try {
	        test.info("Starting InValidCreateBubble test");

	        waitVisible(locatersCreateBubble.AllBubblesLabel);

	        String before = driver.findElement(locatersCreateBubble.AllBubblesLabel).getText();
	        int countBefore = Integer.parseInt(before.replaceAll("\\D+", ""));
	        test.info("Bubble count before invalid create: " + countBefore);

	        waitVisible(locatersCreateBubble.BubbleName);
	        TextElement("Testing", locatersCreateBubble.BubbleName);
	        test.info("Entered Bubble Name: Testing");

	        waitVisible(locatersCreateBubble.BubbleSubject);
	        TextElement("Training", locatersCreateBubble.BubbleSubject);
	        test.info("Entered Bubble Subject: Training");

	        waitClickable(locatersCreateBubble.FirstNextButton);
	        clickButton(locatersCreateBubble.FirstNextButton);
	        test.info("Clicked First Next Button");

	        waitVisible(locatersCreateBubble.SearchForInvite);
	        TextElement("sameer", locatersCreateBubble.SearchForInvite);
	        Thread.sleep(2000);
	        driver.findElement(locatersCreateBubble.SearchForInvite).sendKeys(Keys.SPACE);
	        test.info("Searched for user: sameer");

	        waitClickable(locatersCreateBubble.SearchResultUser);
	        clickButton(locatersCreateBubble.SearchResultUser);
	        test.info("Selected first invited user");

	        waitVisible(locatersCreateBubble.SearchForInvite);
	        TextElement("sameer", locatersCreateBubble.SearchForInvite);
	        Thread.sleep(2000);
	        driver.findElement(locatersCreateBubble.SearchForInvite).sendKeys(Keys.SPACE);
	        test.info("Searched again for user: sameer");

	        waitClickable(locatersCreateBubble.SearchResultUser);
	        clickButton(locatersCreateBubble.SearchResultUser);
	        test.info("Selected second invited user");

	        waitClickable(locatersCreateBubble.SecondNextButton);
	        clickButton(locatersCreateBubble.SecondNextButton);
	        test.info("Clicked Second Next Button");

	        waitVisible(locatersCreateBubble.SearchImages);
	        TextElement("school", locatersCreateBubble.SearchImages);
	        test.info("Searched image: school");

	        waitClickable(locatersCreateBubble.FirstImage);
	        clickButton(locatersCreateBubble.FirstImage);
	        test.info("Selected first image");

	        waitClickable(locatersCreateBubble.closeButton);
	        clickButton(locatersCreateBubble.closeButton);
	        test.info("Clicked 'X' Button before creating bubble");

	        waitVisible(locatersCreateBubble.AllBubblesLabel);

	        String after = driver.findElement(locatersCreateBubble.AllBubblesLabel).getText();
	        int countAfter = Integer.parseInt(after.replaceAll("\\D+", ""));
	        test.info("Bubble count after closing: " + countAfter);

	        AssertJUnit.assertEquals(countBefore, countAfter);
	        test.pass("Bubble was not created after clicking 'X', Count remained unchanged.");

	    } catch (AssertionError e) {

	        test.fail("Bubble count changed after clicking 'X'. " + e.getMessage());
	        throw e;

	    } catch (Exception e) {

	        test.fail("Test failed because of exception: " + e.getMessage());
	        throw e;
	    }
	}
	@Test
	public void displayErrorBubbleName() {

	    test = extent.createTest("Display Error Message For Bubble Name");

	    try {
	        test.info("Starting displayErrorBubbleName test");

	        waitVisible(locatersCreateBubble.BubbleName);
	        TextElement("Te", locatersCreateBubble.BubbleName);
	        test.info("Entered invalid Bubble Name: Te");

	        waitVisible(locatersCreateBubble.BubbleSubject);
	        TextElement("Training", locatersCreateBubble.BubbleSubject);
	        test.info("Entered Bubble Subject: Training");

	        waitVisible(locatersCreateBubble.ErrorMessageName);

	        String actualError = driver.findElement(locatersCreateBubble.ErrorMessageName).getText();
	        String expectedError = "Bubble name must be at least 3 characters long";

	        test.info("Actual Error Message: " + actualError);
	        test.info("Expected Error Message: " + expectedError);

	        Assert.assertEquals(actualError, expectedError);
	        test.pass("Correct validation message is displayed.");

	    } catch (AssertionError e) {

	        test.fail("Validation message mismatch. " + e.getMessage());
	        throw e;

	    } catch (Exception e) {

	        test.fail("Test failed because of exception: " + e.getMessage());
	        throw e;
	    }
	}

	@AfterClass
	public void tearDown() {

		quit();
	}

}
