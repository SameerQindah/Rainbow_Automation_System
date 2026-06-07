package com.test.RainbowProject;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Locaters.LocatersDeleteBubble;

public class TestDeleteBubble extends DeleteBubblePage{
	@BeforeClass
	public void openBrowser() {

	    setUp();
	    loginSystem();
	    waitUrlContains("home");
	}
	
	
	@BeforeMethod(alwaysRun = true)
	public void goToMainPage() throws InterruptedException {
		
		clickOnBubbleOption();
		
	}
	
	
	@Test
	public void ValidDeleteBubble() throws Exception { 

	    test = extent.createTest("Delete Bubble Test");

	    try {
	        test.log(Status.INFO, "Starting Delete Bubble test");

	        waitVisible(LocatersDeleteBubble.AllBubblesLabel);

	        String before = driver.findElement(LocatersDeleteBubble.AllBubblesLabel).getText();
	        int countBefore = Integer.parseInt(before.replaceAll("\\D+", ""));
	        
	        waitVisible(LocatersDeleteBubble.FirstBubbleThreeDots);
	        clickButton(LocatersDeleteBubble.FirstBubbleThreeDots);
	        test.log(Status.INFO, "Clicked 'First Bubble Three Dots' option");

	        waitVisible(LocatersDeleteBubble.DeleteBubbleButton);
	        clickButton(LocatersDeleteBubble.DeleteBubbleButton);
	        test.log(Status.INFO, "Clicked 'Delete Bubble' button");
	        
	        waitVisible(LocatersDeleteBubble.ConfirmDeleteButton);
	        clickButton(LocatersDeleteBubble.ConfirmDeleteButton);
	        test.log(Status.INFO, "Clicked 'Confirm' button to delete a bubble");

	        Thread.sleep(3000);

	        String after = driver.findElement(LocatersDeleteBubble.AllBubblesLabel).getText();
	        int countAfter = Integer.parseInt(after.replaceAll("\\D+", ""));

	        test.log(Status.INFO, "Bubbles before Delete Bubble: " + countBefore);
	        test.log(Status.INFO, "Bubbles after Delete Bubble: " + countAfter);
	        
	        AssertJUnit.assertEquals(countBefore - 1, countAfter);
	        test.log(Status.PASS, "Delete Bubble is done");

	    } catch (AssertionError e) {

	        test.log(Status.FAIL, "Test failed: " + e.getMessage());
	        throw e;

	    } catch (Exception e) {

	        test.log(Status.FAIL, "Test failed because of exception: " + e.getMessage());
	        throw e;
	    }
	}
	
	@Test
	public void InValidDeleteBubble() { 

	    test = extent.createTest("Cancel the Delete Bubble Test");

	    try {
	        test.log(Status.INFO, "Starting Cancel the Delete Bubble test");

	        waitVisible(LocatersDeleteBubble.AllBubblesLabel);

	        String before = driver.findElement(LocatersDeleteBubble.AllBubblesLabel).getText();
	        int countBefore = Integer.parseInt(before.replaceAll("\\D+", ""));
	        
	        waitVisible(LocatersDeleteBubble.FirstBubbleThreeDots);
	        clickButton(LocatersDeleteBubble.FirstBubbleThreeDots);
	        test.log(Status.INFO, "Clicked 'First Bubble Three Dots' option");

	        waitVisible(LocatersDeleteBubble.DeleteBubbleButton);
	        clickButton(LocatersDeleteBubble.DeleteBubbleButton);
	        test.log(Status.INFO, "Clicked 'Delete Bubble' button");
	        
	        waitVisible(LocatersDeleteBubble.CancelDeleteButton);
	        clickButton(LocatersDeleteBubble.CancelDeleteButton);
	        test.log(Status.INFO, "Clicked 'Cancel' button to cancel deleting a bubble");

	        waitVisible(LocatersDeleteBubble.AllBubblesLabel);

	        String after = driver.findElement(LocatersDeleteBubble.AllBubblesLabel).getText();
	        int countAfter = Integer.parseInt(after.replaceAll("\\D+", ""));

	        test.log(Status.INFO, "Bubbles before Cancel Delete Bubble: " + countBefore);
	        test.log(Status.INFO, "Bubbles after Cancel Delete Bubble: " + countAfter);
	        
	        AssertJUnit.assertEquals(countBefore, countAfter);
	        test.log(Status.PASS, "Cancel the Delete Bubble is done");

	    } catch (AssertionError e) {

	        test.log(Status.FAIL, "Test failed: " + e.getMessage());
	        throw e;

	    } catch (Exception e) {

	        test.log(Status.FAIL, "Test failed because of exception: " + e.getMessage());
	        throw e;
	    }
	}
	
	

	@AfterClass
	public void tearDown() {

		quit();
	}
}
