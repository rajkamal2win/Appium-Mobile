package appium;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class MiscellaneousAppiumActions extends BaseTest {

	@Test
	public void MiscellaneousAppiumActions() {		
		//App Package and App Activity
		//adb shell dympsys window | grep -E 'mCurrentFocus'  - MAC
		//adb shell dumpsys window | find "mCurrentFocus" - Windows
		Activity activity = new Activity("io.appium.android.apis", "io.appium.android.apis.preference.PreferenceDependencies");
		driver.startActivity(activity);
		driver.findElement(By.id("android:id/checkbox")).click();
		
		//To rotate landscape
		DeviceRotation landscape = new DeviceRotation(0,0,90);
		driver.rotate(landscape);
		
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		String alertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertEquals(alertTitle, "WiFi settings");
		
		//copy paste from clipboard
		driver.setClipboardText("DeaDshot");
		driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
		
		//Pressing Key
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
	}

}
