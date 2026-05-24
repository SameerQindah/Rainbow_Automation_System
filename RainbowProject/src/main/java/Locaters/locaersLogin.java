package Locaters;

import org.openqa.selenium.By;

public class locaersLogin {
	public static final By Username = By.id("username");
	public static final By ModifyEmailButton = By.cssSelector("div[role='button'][aria-label='Modify the email address']");	
	
	public static final By Password = By.id("authPwd");
	
	public static final By DontHaveAnAccountlink = By.id("notAlreadyAnAccount_buttonId");
	public static final By CreateAccountTitle =By.cssSelector("h2.authWindowContent__information");
	
	public static final By ContinueButton = By.xpath("//span[normalize-space()='Continue']/ancestor::button");	
	public static final By ConnectButton =By.xpath("//span[normalize-space()='Connect']/ancestor::button");
	public static final By LearnMoreLink =By.cssSelector("a.login__visit");
	
	public static final By ForgotPassword =By.cssSelector("button.authentication__forgot-password");
	public static final By ResetPasswordTitle =By.xpath("//h2[normalize-space()='Reset your password']");
	
	public static final By KeepSessionAliveCheckbox =By.cssSelector("rb-checkbox[name='rememberMe']");
	
	public static final By TermsOfServiceLink =By.cssSelector("a[href*='terms-of-service']");
	public static final By PrivacyPolicyLink = By.cssSelector("a[href*='data-privacy']");
	
	public static final By EnvironmentDropdown = By.id("authentication__select");
	public static final By SreEnvironmentOption =By.xpath("//option[normalize-space()='sre-edge-lts-sbg-dev-1.openrainbow.org']");
	public static final By WelcomeArabicText = By.xpath("//p[@aria-live='polite' and contains(text(),'مرحبا')]");
}
