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
	public void openBrowser() {

	    setUp();
	    loginSystem();
	    waitUrlContains("home");
	}
	
	@BeforeMethod(alwaysRun = true)
	public void goToMainPage() {

	    clickOnBubbleOption();
	    clickOnBubbleInTheList();
	    waitVisible(locaterInviteMember.MembersLabel);
	}

	
	@Test
	public void ValidInviteMember() {

	    test = extent.createTest("Invite Member Test");

	    try {
	        test.log(Status.INFO, "Starting Invite Member test");

	        waitVisible(locaterInviteMember.MembersLabel);

	        String before = getText(locaterInviteMember.MembersLabel);
	        int countBefore = Integer.parseInt(before.replaceAll("\\D+", ""));

	        clickButton(locaterInviteMember.AddMemberButton);
	        test.log(Status.INFO, "Clicked 'Add Member' button");

	        waitVisible(locaterInviteMember.SearchMembers);

	        TextElement("salma g", locaterInviteMember.SearchMembers);
	        test.log(Status.INFO, "Entered member name");

	        clickButton(locaterInviteMember.SearchResultMember);
	        test.log(Status.INFO, "Clicked Member selected to invite");

	        clickButton(locaterInviteMember.ApplyButton);
	        test.log(Status.INFO, "Clicked Apply Button");

	        waitTextToChange(locaterInviteMember.MembersLabel, before);

	        String after = getText(locaterInviteMember.MembersLabel);
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
	public void InValidInviteMember() {

	    test = extent.createTest("Cancel Invite Member Test");

	    try {
	        test.log(Status.INFO, "Starting Cancel Invite Member test");

	        waitVisible(locaterInviteMember.MembersLabel);

	        String before = getText(locaterInviteMember.MembersLabel);
	        int countBefore = Integer.parseInt(before.replaceAll("\\D+", ""));

	        clickButton(locaterInviteMember.AddMemberButton);
	        test.log(Status.INFO, "Clicked 'Add Member' button");

	        waitVisible(locaterInviteMember.SearchMembers);

	        TextElement("salma g", locaterInviteMember.SearchMembers);
	        test.log(Status.INFO, "Entered member name");


	        clickButton(locaterInviteMember.SearchResultMember);
	        test.log(Status.INFO, "Clicked Member selected to invite");

	        clickButton(locaterInviteMember.CancelButton);
	        test.log(Status.INFO, "Clicked Cancel Button");

	        waitVisible(locaterInviteMember.MembersLabel);

	        String after = getText(locaterInviteMember.MembersLabel);
	        int countAfter = Integer.parseInt(after.replaceAll("\\D+", ""));

	        test.log(Status.INFO, "Members before cancel invite: " + countBefore);
	        test.log(Status.INFO, "Members after cancel invite: " + countAfter);

	        AssertJUnit.assertEquals(countBefore, countAfter);

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
