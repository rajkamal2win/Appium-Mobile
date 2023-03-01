package org.appium.pageObjects.ios;

import org.appium.utils.IOSActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AlertViews extends IOSActions {
	IOSDriver driver;

	//GrandParent(AppiumUtils) -> IOSActions -> AlertViews
	public AlertViews(IOSDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == 'Text Entry'`]")
	private WebElement textEntryMenu;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell")
	private WebElement textBox;

	@iOSXCUITFindBy(accessibility = "OK")
	private WebElement acceptPopup;

	@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Confirm'")
	private WebElement confirmMenuItem;

	@iOSXCUITFindBy(iOSNsPredicate = "name BEGINSWITH[c] == 'A message")
	private WebElement confirmMessage;

	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Confirm")
	private WebElement submit;

	public void fillTextLabel(String name) {
		textEntryMenu.click();
		textBox.sendKeys(name);
		acceptPopup.click();
	}

	public String getConfirmMessage() {
		confirmMenuItem.click();
		return confirmMessage.getText();
	}

	public void submitMsg() {
		submit.click();

	}
}
