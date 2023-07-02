package com.swiftshop.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.swiftshop.pages.GetPages;
import com.swiftshop.utilities.XLS_Reader;

public class Base_Class_Browser {

	private DriverFactory driverFactory = DriverFactory.getInstance();
	public GetPages getPages;
	public FunLibrary funLibrary;
	public SelLibrary selLibrary;

	public static String current_Dir = System.getProperty("user.dir");
	public static ThreadLocal<String> method_name = new ThreadLocal<String>();
	public static ThreadLocal<String> sheet_name = new ThreadLocal<String>();

	public Logger testLog = Logger.getLogger("debugLogger:" + Thread.currentThread().getId());
	public WebDriver driver;

	public static ThreadLocal<Integer> currentRow = new ThreadLocal<Integer>();

	public static ThreadLocal<XLS_Reader> datatable = new ThreadLocal<XLS_Reader>();

	private static String data_file = "";
	private static String url = "";
	private String currentWindow = "";

	@BeforeSuite
	public void killProcesses(){
//		try {
//			Runtime.getRuntime().exec("Taskkill /T /F /IM geckodriver.exe /IM chromedriver.exe");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	@BeforeClass(alwaysRun = true)
	@Parameters({ "browser", "sheetname" })
	synchronized public void beforeClass(String browser, String sheetname) {
		getProperties();
		driverFactory.setDriver(browser);
		datatable.set(new XLS_Reader(current_Dir + "/src/test/resources/Test Data/" + data_file));
		sheet_name.set(sheetname);
		driver = driverFactory.getDriver();
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method) throws Exception {
		launchApplicationURL();
		String m = method.getName();
		method_name.set(m);
		if (isTCIDFound()) {
			testLog.info("TEST STARTED:" + method_name.get());
			getPages = new GetPages();
			funLibrary = new FunLibrary();
			selLibrary = new SelLibrary();
		} else {
			throw new SkipException("Test case - " + method_name.get() + " is not present in the sheet");
		}
		currentWindow = driver.getWindowHandle();
	}
	@AfterMethod
	public void afterMethod() {			
		driver.switchTo().window(currentWindow);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {
		driver.close();
	}

	public boolean isTCIDFound() {
		String sheetname = sheet_name.get();
		for (int i = 2; i <= datatable.get().getRowCount(sheetname); i++) {
			if (datatable.get().getCellData(sheetname, "TestMethodName", i).equals(method_name.get())) {
				testLog.info("TCID match found");
				// testLog.info("STARTING TESTCASE:" + method_name);
				currentRow.set(i);
				return true;
			}
		}
		return false;
	}

	public void setDatatable() {
		datatable.set(new XLS_Reader(current_Dir + "\\src\\test\\resources\\Test Data\\" + data_file));
	}

	/* Set url, datafile, driver path etc from baseConfig file */
	private void getProperties() {
		Properties logProperties = new Properties();
		try {
			logProperties.load(new FileInputStream(current_Dir + "/src/main/baseConfig.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		PropertyConfigurator.configure(logProperties);
		for (String name : logProperties.stringPropertyNames()) {
			String value = logProperties.getProperty(name);
			System.setProperty(name, value);
		}

		url = System.getProperty("ENV");
		data_file = System.getProperty("DataFile");

	}

	/* url is managed in baseConfig.properties. */
	public void launchApplicationURL() throws Exception {
		try {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Thread.sleep(3000);
		}catch(Exception e) {
			testLog.error(e.toString());
		}
	}
}
