package com.swiftshop.utilities;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.swiftshop.main.Base_Class_Browser;

public class Retry extends Base_Class_Browser implements IRetryAnalyzer {

	private int count = 0;
	private static int maxTry = 2; // Run the failed test 2 times

	@Override
	public boolean retry(ITestResult iTestResult) {
		if (!iTestResult.isSuccess()) { // Check if test not succeed
			if (count < maxTry) { // Check if maxTry count is reached
				count++; // Increase the maxTry count by 1
				iTestResult.setStatus(ITestResult.FAILURE); // Mark test as failed and take base64Screenshot
//                try {
//					extendReportsFailOperations(iTestResult);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}    //ExtentReports fail operations
				return true; // Tells TestNG to re-run the test
			}
		} else {
			iTestResult.setStatus(ITestResult.SUCCESS); // If test passes, TestNG marks it as passed
		}
		return false;
	}

	public void extendReportsFailOperations(ITestResult result) throws Exception {
		ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
		// Object testClass = result.getInstance();
		TakesScreenshot newScreen = (TakesScreenshot) driver;
		File scnShot = newScreen.getScreenshotAs(OutputType.FILE);

		String screenshotPath = System.getProperty("user.dir") + "\\ExtentReports\\Screenshots\\" + result.getName()
				+ ".png";
		File DestFile = new File(screenshotPath);
		FileUtils.copyFile(scnShot, DestFile);
		test.get().fail(
				MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));
		test.get().fail(result.getThrowable());
		test.get().fail("Snapshot below: ").addScreenCaptureFromPath(screenshotPath);
	}
}