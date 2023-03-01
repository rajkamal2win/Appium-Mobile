package org.appium.utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class IOSActions extends AppiumUtils{
	IOSDriver driver;

	public IOSActions(IOSDriver driver) {
		this.driver = driver;
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
	
	public void slider() {
		
	}
	
	public void swipe() {
		
	}

}
