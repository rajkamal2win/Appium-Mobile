package org.appium.TestUtils;

import java.io.IOException;

import org.appium.utils.AppiumUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;

public class Listeners extends AppiumUtils implements ITestListener {
	ExtentReports extent = ExterntReporterNG.getReporterObject();
	ExtentTest test;
	AppiumDriver driver;

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//screenshot code
		try {
			driver = (AppiumDriver) result
					.getTestClass() //Get Testname in testng.xml
					.getRealClass() //Get classname for the above tests in testng.xml
					.getField("driver") //get driver for above classname
					.get(result.getInstance()); //get instance for that driver
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		test.fail(result.getThrowable());
		try {
			test.addScreenCaptureFromPath(getScreenshot(result.getMethod().getMethodName(),driver), result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
