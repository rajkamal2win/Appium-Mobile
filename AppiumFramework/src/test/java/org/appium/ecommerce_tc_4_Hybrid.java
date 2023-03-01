package org.appium;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.appium.TestUtils.AndroidBaseTest;
import org.appium.pageObjects.android.CartPage;
import org.appium.pageObjects.android.ProductCatalogue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.Activity;

public class ecommerce_tc_4_Hybrid extends AndroidBaseTest {	
	//TestData
	//Parse Json file -> Json String (Commons-io)
	//Parse Json String -> HashMap (Jackson)
	//Parse HasMap -> DataProvider
	
	@BeforeMethod(alwaysRun = true)
	public void preSetup() {
		//Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
		Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.SplashActivity");
		driver.startActivity(activity);
	}
	
	@Test(dataProvider = "getData", groups ={"Smoke"})
	public void fillForm(HashMap<String,String> input) throws InterruptedException {
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));
		ProductCatalogue productCatalogue = formPage.submitForm();

		productCatalogue.addItemtoCartByIndex(0);
		productCatalogue.addItemtoCartByIndex(0);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Thread.sleep(2000);
		WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
		cartPage.waitforElementToAppear(ele, driver);

		double totalSum = cartPage.getProductSum();
		double displayFormattedSum = cartPage.getTotalAmountDisplayed();
		Assert.assertEquals(totalSum, displayFormattedSum);
		cartPage.acceptTermsConditions();
		cartPage.submitOrder();
		Thread.sleep(3000);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		//return new Object[][] { {"Messi","male","Argentina"} };
		List<HashMap<String,String>> data = getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\org\\appium\\testData\\ecommerce.json");
		return new Object[][] { {data.get(0)}, {data.get(1)} };
	}
}
