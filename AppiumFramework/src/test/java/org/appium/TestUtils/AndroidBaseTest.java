package org.appium.TestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.appium.pageObjects.android.FormPage;
import org.appium.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AndroidBaseTest extends AppiumUtils {
	public AndroidDriver driver;
	public FormPage formPage;

	@BeforeClass(alwaysRun = true)
	public void configureAppium() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\org\\appium\\resources\\data.properties");
		prop.load(fis);
		//To run in maven else from property file
		String ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		//String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		service = startAppiumServer(ipAddress, Integer.parseInt(port));

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("DEmulator")); // emulator
		// options.setDeviceName("Android Device"); // real device

		/*
		 * options.setApp( System.getProperty("user.dir")+
		 * "\\src\\test\\java\org\\appium\\resources\\ApiDemos-debug.apk" );
		 */

		options.setApp(System.getProperty("user.dir") + "\\src\\test\\java\\org\\appium\\resources\\General-Store.apk");
		options.setChromedriverExecutable(
				"C:\\Users\\Rajkamal\\Downloads\\Automation\\Workplace\\Appium\\src\\test\\java\\resources\\chromedriver.exe");

		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
