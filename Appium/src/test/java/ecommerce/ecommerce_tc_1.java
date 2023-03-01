package ecommerce;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import appium.BaseTest;
import io.appium.java_client.AppiumBy;

public class ecommerce_tc_1 extends BaseTest {
	@Test
	public void fillForm() {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Messi");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(By.xpath("//*[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		//Toast message will be stored in android.widget.Toast
		// name attribute for the toast messages will have the content
		String toastMessage = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
		System.out.println(toastMessage);
		Assert.assertEquals("Please enter your name", toastMessage);

	}
}
