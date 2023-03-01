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

public class IOSSlider extends IOSBaseTest {

	@Test
	public void IOSSliderTest() throws MalformedURLException {
		//use Testapp 3.app app for this test case
		WebElement slider = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeSlider[`label == 'AppElem'`]"));
		slider.sendKeys("0%");
		Assert.assertEquals("0", slider.getAttribute("value")); //0%
		slider.sendKeys("1%");
		Assert.assertEquals("100%",slider.getAttribute("value")); //100%
	}
}
