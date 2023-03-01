package org.appium.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumUtils {
	public AppiumDriverLocalService service;
	
	public double getFormattedAmount(String amount) {
		amount = amount.substring(1);
		double price = Double.parseDouble(amount);
		return price;
	}
	
	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"C:\\Users\\Rajkamal\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress(ipAddress).usingPort(port).build();
		service.start();
		
		return service;
	}
	
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath),StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, 
					 new TypeReference<List<HashMap<String, String>>>() {
		});
		return data;
	}

	public void waitforElementToAppear(WebElement ele, AppiumDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeContains(ele, "text", "Cart"));
	}
	
	public String getScreenshot(String testCaseName, AppiumDriver driver) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"//reports/"+testCaseName+".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
		//1.To capture ss and place in folder //2.extent report pick file and attach to report
	}
}
