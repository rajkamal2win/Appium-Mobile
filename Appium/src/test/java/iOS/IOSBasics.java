package iOS;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class IOSBasics extends IOSBaseTest {

	@Test
	public void IOSBasicsTest() throws MalformedURLException {
		//xpath, classname, iosClassChain, iosPredicateString, accessibiltiy id, id
		driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
		
		//Xpath is slow in ios - so two new locators
		//syntax for iosClassChain - **/tagName[`attributeName == 'attributeValue'`]
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == 'Text Entry'`]")).click();
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeCell")).sendKeys("Hello World");
		driver.findElement(AppiumBy.accessibilityId("OK")).click();
		
		//syntax for iosPredicateString attributeName == 'attributeValue'
		driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND value == 'Confirm / Cancel'")).click();
		driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND value BEGINSWITH 'Confirm'")).click();
		//driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Confirm'")).click();
		//driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND value ENDSWITH[c] 'Cancel'")).click();
		driver.findElement(AppiumBy.iOSNsPredicateString("label == 'Confirm'")).click();	
		String text = driver.findElement(AppiumBy.iOSNsPredicateString("name BEGINSWITH[c] == 'A message'")).getText();
		System.out.println(text);
	}

}
