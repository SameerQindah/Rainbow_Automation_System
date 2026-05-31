package Locaters;

import org.openqa.selenium.By;

public class locatersCreateBubble extends locaersLogin {
	public static final By optionBubble = By.cssSelector("li.nav-item_bubble button.c-button--navigation");
	public static final By CreateBubble = By.id("rooms-header-create-bubble-button");
	public static final By BubbleName =By.xpath("(//input[starts-with(@id,'textField__input--')])[1]");
	public static final By BubbleSubject =By.xpath("(//input[starts-with(@id,'textField__input--')])[2]");
	public static final By ErrorMessageName =By.cssSelector("div.textFieldErrorDetails rb-badge");
	public static final By FirstNextButton = By.id("room-create-default-next-button");
	public static final By CancelButton = By.xpath("//button[.//span[normalize-space()='Cancel']]");
	public static final By SearchForInvite = By.cssSelector("input[placeholder='Invite people by their name']");
	public static final By SearchResultUser =By.xpath("//button[@role='listitem' and contains(@class,'room-add-contact-cell')]");
	public static final By SecondNextButton = By.id("room-create-users-next-button");
	public static final By FirstBackButton = By.id("room-create-default-back-button");
	public static final By SearchImages = By.id("pixabaySearchInput");
	public static final By FirstImage =By.xpath("(//div[@role='listitem' and contains(@class,'imagesGridComponent_listitem')]//img)[1]");
	public static final By SecondBackButton =By.xpath("//button[normalize-space()='Back']");
	public static final By CreateButton =By.xpath("//button[normalize-space()='Create']");
	public static final By AllBubblesLabel =By.id("rooms-title");
	public static final By closeButton =By.xpath("//roomcreate[contains(@class,'c-modal')]//button[@aria-label='Close']");
}
