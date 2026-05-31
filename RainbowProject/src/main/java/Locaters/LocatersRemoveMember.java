package Locaters;

import org.openqa.selenium.By;

public class LocatersRemoveMember {
	public static final By SelectBubble =By.xpath("(//roomcell[@role='listitem'])[1]");
	public static final By FirstMember =By.xpath("(//ul[@aria-labelledby='invitedUsersLabel']//button[contains(@class,'user-list-cell_name')])[1]");
	public static final By FirstMemberThreeDots =By.xpath("(//ul[@aria-labelledby='invitedUsersLabel']//div[@role='button' and contains(@class,'dropdown-elem')])[1]");
	public static final By RemoveMemberButton =By.xpath("//div[normalize-space()='Remove member']");
	public static final By MembersLabel =By.xpath("//h3[@id='invitedUsersLabel']/span");

}
