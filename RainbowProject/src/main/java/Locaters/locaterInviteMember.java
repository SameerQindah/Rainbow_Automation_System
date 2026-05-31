package Locaters;

import org.openqa.selenium.By;

public class locaterInviteMember {
	public static final By SelectBubble =By.xpath("(//roomcell[@role='listitem'])[1]");
	public static final By AddMemberButton =By.xpath("//button[./span[@translate='addMembers']]");
	public static final By SearchMembers =By.xpath("//input[@placeholder='Invite people by their name']");
	public static final By SearchResultMember =By.xpath("//button[@role='listitem' and contains(@class,'room-add-contact-cell')]");
	public static final By ApplyButton =By.xpath("//button[normalize-space()='Apply']");
	public static final By CancelButton =By.xpath("//button[normalize-space()='Cancel']");
	
	public static final By MembersLabel =By.xpath("//h3[@id='invitedUsersLabel']/span");
	
	
	

}
