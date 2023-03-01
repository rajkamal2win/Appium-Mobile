package appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	public AndroidDriver driver;
	public AppiumDriverLocalService service;

	@BeforeClass
	public void configureAppium() throws MalformedURLException {
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"C:\\Users\\Rajkamal\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("DEmulator"); //emulator
		//options.setDeviceName("Android Device"); // real device

		
		  options.setApp(
		  "C:\\Users\\Rajkamal\\Downloads\\Automation\\Workplace\\Appium\\src\\test\\java\\resources\\ApiDemos-debug.apk"
		  );
		 
		/*
		 * options.setApp(
		 * "C:\\Users\\Rajkamal\\Downloads\\Automation\\Workplace\\Appium\\src\\test\\java\\resources\\General-Store.apk"
		 * );
		 */
		options.setChromedriverExecutable(
				"C:\\Users\\Rajkamal\\Downloads\\Automation\\Workplace\\Appium\\src\\test\\java\\resources\\chromedriver.exe");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public void longPressAction(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 2000));
	}

	public void scrollToEndAction() {
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 3.0));
		} while (canScrollMore);
	}

	public void swipeAction(WebElement ele, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) ele).getId(), "direction", direction, "percent", 0.75));
	}

	public void dragAndDropAction(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "endX", 649, "endY", 681));
	}

	public double getFormattedAmount(String amount) {
		amount = amount.substring(1);
		double price = Double.parseDouble(amount);
		return price;
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
