package com.test.RainbowProject;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import Locaters.locaterInviteMember;

public class TestInviteMember extends InviteMemberPage {

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
	public void ValidInviteMember() throws InterruptedException { 

		test = extent.createTest("Invite Member Test");

		try {
			test.log(Status.INFO, "Starting Invite Member test");

			Thread.sleep(3000);
			String before =driver.findElement(locaterInviteMember.MembersLabel).getText();
			int countBefore =Integer.parseInt(before.replaceAll("\\D+", ""));
			
			clickButton(locaterInviteMember.AddMemberButton);
			test.log(Status.INFO, "Clicked 'Add Member' button");
			Thread.sleep(2000);


			TextElement("salma g", locaterInviteMember.SearchMembers);
			test.log(Status.INFO, "Entered member name");
			Thread.sleep(2000);
			
			clickButton(locaterInviteMember.SearchResultMember);
			test.log(Status.INFO, "Clicked Member selected to invite");
			Thread.sleep(2000);

			clickButton(locaterInviteMember.ApplyButton);
			test.log(Status.INFO, "Clicked Apply Button");

			Thread.sleep(3000);

			String after = driver.findElement(locaterInviteMember.MembersLabel).getText();
			int countAfter = Integer.parseInt(after.replaceAll("\\D+", ""));

			test.log(Status.INFO, "Members before invite: " + countBefore);
			test.log(Status.INFO, "Members after invite: " + countAfter);
			
			AssertJUnit.assertEquals(countBefore + 1, countAfter);
			test.log(Status.PASS, "Invite member is done");

		} catch (AssertionError e) {

			test.log(Status.FAIL, "Test failed: " + e.getMessage());
			throw e;

		} catch (Exception e) {

			test.log(Status.FAIL, "Test failed because of exception: " + e.getMessage());
			throw e;
		}
	}
	
	@Test
	public void InValidInviteMember() throws InterruptedException { 

		test = extent.createTest("Cancel Invite Member Test");

		try {
			test.log(Status.INFO, "Starting Cancel Invite Member test");

			Thread.sleep(3000);
			String before =driver.findElement(locaterInviteMember.MembersLabel).getText();
			int countBefore =Integer.parseInt(before.replaceAll("\\D+", ""));
			
			clickButton(locaterInviteMember.AddMemberButton);
			test.log(Status.INFO, "Clicked 'Add Member' button");
			Thread.sleep(2000);


			TextElement("salma g", locaterInviteMember.SearchMembers);
			test.log(Status.INFO, "Entered member name");
			Thread.sleep(2000);
			
			clickButton(locaterInviteMember.SearchResultMember);
			test.log(Status.INFO, "Clicked Member selected to invite");
			Thread.sleep(2000);

			clickButton(locaterInviteMember.CancelButton);
			test.log(Status.INFO, "Clicked Cancel Button");

			String after = driver.findElement(locaterInviteMember.MembersLabel).getText();
			int countAfter = Integer.parseInt(after.replaceAll("\\D+", ""));
			Thread.sleep(3000);

			AssertJUnit.assertEquals(countBefore , countAfter);
			test.log(Status.PASS, "Cancel Invite member is done");

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
