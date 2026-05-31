package Locaters;

import org.openqa.selenium.By;

public class LocatersDeleteBubble {
	public static final By FirstBubbleThreeDots =By.xpath("(//rb-dropdown[contains(@class,'roomsListCell')]//div[@role='button'])[1]");
	public static final By DeleteBubbleButton =By.xpath("//div[normalize-space()='Delete bubble']");
	public static final By ConfirmDeleteButton =By.xpath("//span[normalize-space()='Delete']/ancestor::button");
	public static final By CancelDeleteButton =By.xpath("//span[normalize-space()='Cancel']/ancestor::button");
	public static final By AllBubblesLabel =By.id("rooms-title");
	
	
	
}
