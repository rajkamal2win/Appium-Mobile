package org.appium;
import java.net.MalformedURLException;

import org.appium.TestUtils.IOSBaseTest;
import org.appium.pageObjects.ios.AlertViews;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IOSBasics extends IOSBaseTest {

	@Test
	public void IOSBasicsTest() throws MalformedURLException {
		//xpath, classname, iosClassChain, iosPredicateString, accessibiltiy id, id
		AlertViews alertViews = homePage.selectAlertViews();
		alertViews.fillTextLabel("Hello World");
		String actualMessage = alertViews.getConfirmMessage();
		Assert.assertEquals(actualMessage, "A message should be a short, complete sentence.");
		alertViews.submitMsg();
	}

}
