package iOS;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class IOSScrollTest extends IOSBaseTest {

	@Test
	public void IOSScrollTestDemo() throws MalformedURLException {
		WebElement ele = driver.findElement(AppiumBy.accessibilityId("Web View"));
		Map <String,Object> params = new HashMap<String, Object>();
		params.put("element", ((RemoteWebElement)ele).getId());
		params.put("direction", "down");
		driver.executeScript("mobile:scroll", params);
		//scroll(ele);
		ele.click();
		
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name='UKitCatalog']")).click();
		driver.findElement(AppiumBy.accessibilityId("Picker View")).click();
		
		driver.findElement(AppiumBy.accessibilityId("Red color component view")).sendKeys("80");
		driver.findElement(AppiumBy.accessibilityId("Green color component view")).sendKeys("220");
		driver.findElement(AppiumBy.accessibilityId("Blue color component view")).sendKeys("105"); 
		
		String number = driver.findElement(AppiumBy.accessibilityId("Blue color component view")).getText();
		Assert.assertEquals(number, "105");
	}
}
