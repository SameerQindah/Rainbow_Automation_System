package com.test.RainbowProject;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Locaters.LocatersRemoveMember;
public class TestRemoveMembers extends RemoveMemberPage{
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
	    waitVisible(LocatersRemoveMember.MembersLabel);
	}
	
	@Test
	public void ValidRemoveMember() {
	    test = extent.createTest("Remove Member Test");

	    try {
	        test.log(Status.INFO, "Starting Remove Member test");

	        String before = getText(LocatersRemoveMember.MembersLabel);
	        int countBefore = Integer.parseInt(before.replaceAll("\\D+", ""));

	        hoverOnElement(LocatersRemoveMember.FirstMember);

	        clickButton(LocatersRemoveMember.FirstMemberThreeDots);
	        test.log(Status.INFO, "Clicked 'Member Three Dots' button");

	        clickButton(LocatersRemoveMember.RemoveMemberButton);
	        test.log(Status.INFO, "Clicked 'Remove Member' Button");

	        waitTextToChange(LocatersRemoveMember.MembersLabel, before);

	        String after = getText(LocatersRemoveMember.MembersLabel);
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
