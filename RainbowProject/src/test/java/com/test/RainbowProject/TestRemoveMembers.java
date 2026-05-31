package com.test.RainbowProject;

import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Locaters.LocatersRemoveMember;
public class TestRemoveMembers extends RemoveMemberPage{

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
		clickOnBubbleInTheList();
	}
	
	
	@Test
	public void ValidRemoveMember() throws InterruptedException { 

		test = extent.createTest("Remove Member Test");

		try {
			test.log(Status.INFO, "Starting Remove Member test");

			Thread.sleep(3000);
			String before =driver.findElement(LocatersRemoveMember.MembersLabel).getText();
			int countBefore =Integer.parseInt(before.replaceAll("\\D+", ""));
			
			Actions actions = new Actions(driver);
			Thread.sleep(2000);
			actions.moveToElement(driver.findElement(LocatersRemoveMember.FirstMember)).perform();
			Thread.sleep(2000);

			clickButton(LocatersRemoveMember.FirstMemberThreeDots);
			test.log(Status.INFO, "Clicked 'Member Three Dots' button");
			Thread.sleep(2000);


			clickButton(LocatersRemoveMember.RemoveMemberButton);
			test.log(Status.INFO, "clicked 'Remove Member' Button");
			Thread.sleep(2000);

			Thread.sleep(3000);

			String after = driver.findElement(LocatersRemoveMember.MembersLabel).getText();
			int countAfter = Integer.parseInt(after.replaceAll("\\D+", ""));

			test.log(Status.INFO, "Members before Remove: " + countBefore);
			test.log(Status.INFO, "Members after Remove: " + countAfter);
			
			AssertJUnit.assertEquals(countBefore - 1, countAfter);
			test.log(Status.PASS, "Remove member is done");

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
