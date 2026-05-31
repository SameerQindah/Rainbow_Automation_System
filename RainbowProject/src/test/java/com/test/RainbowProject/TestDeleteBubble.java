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
	public void openBrowser() throws InterruptedException {

		setUp();
		Thread.sleep(5000);
		loginSystem();
	}
	
	
	@BeforeMethod
	public void goToMainPage() throws InterruptedException {
		Thread.sleep(10000);
		clickOnBubbleOption();
		
	}
	
	
	@Test
	public void ValidDeleteBubble() throws InterruptedException { 

		test = extent.createTest("Delete Bubble Test");

		try {
			test.log(Status.INFO, "Starting Delete Bubble test");

			Thread.sleep(3000);
			String before =driver.findElement(LocatersDeleteBubble.AllBubblesLabel).getText();
			int countBefore =Integer.parseInt(before.replaceAll("\\D+", ""));
			
			clickButton(LocatersDeleteBubble.FirstBubbleThreeDots);
			test.log(Status.INFO, "Clicked 'First Bubble Three Dots' option");
			Thread.sleep(2000);


			clickButton(LocatersDeleteBubble.DeleteBubbleButton);
			test.log(Status.INFO, "Clicked 'Delete Bubble' button");
			Thread.sleep(2000);
			
			clickButton(LocatersDeleteBubble.ConfirmDeleteButton);
			test.log(Status.INFO, "Clicked 'Confirm' button to delete a bubble");
			Thread.sleep(4000);

			String after = driver.findElement(LocatersDeleteBubble.AllBubblesLabel).getText();
			int countAfter = Integer.parseInt(after.replaceAll("\\D+", ""));

			test.log(Status.INFO, "Members before Delete Bubble: " + countBefore);
			test.log(Status.INFO, "Members after Delete Bubble: " + countAfter);
			
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
	public void InValidDeleteBubble() throws InterruptedException { 

		test = extent.createTest("Cancel the Delete Bubble Test");

		try {
			test.log(Status.INFO, "Starting Cancel the Delete Bubble test");

			Thread.sleep(3000);
			String before =driver.findElement(LocatersDeleteBubble.AllBubblesLabel).getText();
			int countBefore =Integer.parseInt(before.replaceAll("\\D+", ""));
			
			clickButton(LocatersDeleteBubble.FirstBubbleThreeDots);
			test.log(Status.INFO, "Clicked 'First Bubble Three Dots' option");
			Thread.sleep(2000);


			clickButton(LocatersDeleteBubble.DeleteBubbleButton);
			test.log(Status.INFO, "Clicked 'Delete Bubble' button");
			Thread.sleep(2000);
			
			clickButton(LocatersDeleteBubble.CancelDeleteButton);
			test.log(Status.INFO, "Clicked 'Cancel' button to Cancel the delete a bubble");
			Thread.sleep(4000);

			String after = driver.findElement(LocatersDeleteBubble.AllBubblesLabel).getText();
			int countAfter = Integer.parseInt(after.replaceAll("\\D+", ""));

			test.log(Status.INFO, "Members before Cancel the Delete Bubble: " + countBefore);
			test.log(Status.INFO, "Members after Cancel the Delete Bubble: " + countAfter);
			
			AssertJUnit.assertEquals(countBefore , countAfter);
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
