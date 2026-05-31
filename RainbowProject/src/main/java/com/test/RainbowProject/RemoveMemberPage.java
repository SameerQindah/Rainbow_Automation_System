package com.test.RainbowProject;

import Locaters.LocatersRemoveMember;

public class RemoveMemberPage extends BasePage{
	public void clickOnBubbleInTheList () {  //Pre-condition for go next to first "Bubble" page
		clickButton(LocatersRemoveMember.SelectBubble);
    }

}
