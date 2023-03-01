package appium;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class AppiumFirstPgm {

@Test
public void AppiumTest () throws MalformedURLException {
	//AndriodDriver, iosDriver
	//Appium Code -> Appium Server -> Mobile
	
	//To Start appium server
	AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Rajkamal\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
																.withIPAddress("127.0.0.1").usingPort(4723).build();
	service.start();
	
	//using google class to invoke emulator
	UiAutomator2Options options = new UiAutomator2Options();
	options.setDeviceName("DEmulator");
	options.setApp("C:\\Users\\Rajkamal\\Downloads\\Automation\\Workplace\\Appium\\src\\test\\java\\resources\\ApiDemos-debug.apk");
	
	AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
	
	//xpath, id, accessibility id, classname, androidUIAutomator
	driver.findElement(AppiumBy.accessibilityId("Preference")).click();
	
	driver.quit();
	service.stop();
}

}
