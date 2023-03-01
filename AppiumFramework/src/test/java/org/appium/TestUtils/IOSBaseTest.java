package org.appium.TestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.appium.pageObjects.ios.HomePage;
import org.appium.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

public class IOSBaseTest extends AppiumUtils {
	public IOSDriver driver;
	public HomePage homePage;

	@BeforeClass
	public void configureAppium() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\org\\appium\\resources\\data.properties");
		prop.load(fis);
		String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		service = startAppiumServer(ipAddress, Integer.parseInt(port));

		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName("iphone 13 pro");
		options.setApp(System.getProperty("user.dir") + "\\src\\test\\java\\resources\\UIKitCatalog.app");
		options.setPlatformVersion("15.5");
		// Appium - Webdriver Agent -> IOS Apps
		options.setWdaLaunchTimeout(Duration.ofSeconds(20));

		driver = new IOSDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		homePage = new HomePage(driver);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
