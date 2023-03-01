package org.appium;

import org.appium.TestUtils.AndroidBaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;

public class ecommerce_tc_1 extends AndroidBaseTest {

	@BeforeMethod
	public void preSetup() {
		Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.SplashActivity");
		driver.startActivity(activity);
	}

	@Test
	public void fillForm_ErrorValidation() {
		// driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Messi");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(By.xpath("//*[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		// Toast message will be stored in android.widget.Toast
		// name attribute for the toast messages will have the content
		String toastMessage = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
		System.out.println(toastMessage);
		Assert.assertEquals("Please enter your name", toastMessage);
	}

	@Test
	public void fillForm_PositiveFlow() {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Messi");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(By.xpath("//*[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Assert.assertTrue(driver.findElements(By.xpath("//android.widget.Toast[1]")).size() < 1);
	}
}
