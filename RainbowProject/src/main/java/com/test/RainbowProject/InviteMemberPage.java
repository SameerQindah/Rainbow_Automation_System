package com.test.RainbowProject;

import Locaters.locaterInviteMember;

public class InviteMemberPage extends BasePage{
	public void clickOnBubbleInTheList () { //Pre-condition for go next to "Bubble" page
		clickButton(locaterInviteMember.SelectBubble);
    }

}
