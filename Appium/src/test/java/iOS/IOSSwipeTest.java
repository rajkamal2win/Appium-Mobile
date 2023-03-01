package iOS;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class IOSSwipeTest extends IOSBaseTest {

	@Test
	public void IOSSwipeTestDemo() throws MalformedURLException {
		//Bundle Id - To invoke an already installed app
		Map <String,Object> params = new HashMap<String, Object>();
		params.put("bundleId", "com.apple.mobileslideshow");
		driver.executeScript("mobile:launchApp", params);
		
		driver.findElement(AppiumBy.iOSNsPredicateString("label == 'All Photos'")).click();
		List<WebElement> allPhotos = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeCell"));
		System.out.println(allPhotos.size());
		allPhotos.get(0).click();
		for (int i = 0; i < allPhotos.size(); i++) {
			System.out.println(driver.findElement(By.xpath("//XCUIElementTypeNavigationBar")).getAttribute("name"));
			Map <String,Object> params1 = new HashMap<String, Object>();
			params1.put("direction", "left");
			driver.executeScript("mobile:swipe", params1);
		}
		//to click button in iOS
		driver.navigate().back();
		driver.findElement(AppiumBy.accessibilityId("Albums")).click(); //cleanup
	}
}
