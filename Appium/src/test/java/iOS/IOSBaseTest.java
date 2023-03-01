package iOS;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class IOSBaseTest {
	public IOSDriver driver;
	public AppiumDriverLocalService service;

	@BeforeClass
	public void configureAppium() throws MalformedURLException {
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"C:\\Users\\Rajkamal\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();

		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName("iphone 13 pro");		
		options.setApp(
				"C:\\Users\\Rajkamal\\Downloads\\Automation\\Workplace\\Appium\\src\\test\\java\\resources\\UIKitCatalog.app");
		options.setPlatformVersion("15.5");
		//Appium - Webdriver Agent -> IOS Apps
		options.setWdaLaunchTimeout(Duration.ofSeconds(20));
		
		driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public void touchAndHold(WebElement ele) {
		Map <String,Object> params = new HashMap<String, Object>();
		params.put("element", ((RemoteWebElement)ele).getId());
		params.put("duration", 5);
		driver.executeScript("mobile:touchAndHold", params);
	}
	
	public void scroll(WebElement ele) {
		Map <String,Object> params = new HashMap<String, Object>();
		params.put("element", ((RemoteWebElement)ele).getId());
		params.put("direction", "down");
		driver.executeScript("mobile:scroll", params);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
