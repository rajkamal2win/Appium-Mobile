package appium;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class ScrollDemo extends BaseTest {
	@Test
	public void scrollDemoTest() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();

		// Where to scroll prior
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"));

		// no prior idea
		scrollToEndAction();
	}
}
