package com.swiftshop.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.swiftshop.utilities.XLS_Reader;

public class SelLibrary {

	public WebDriver driver;
	public Properties OR_OR;
	public Logger testLog = Logger.getLogger("debugLogger:" + Thread.currentThread().getId());
	public XLS_Reader datatable = null;
	public int status;
	public int random_bag_flag = -1;
	public int currentRow = 0;
	public String sheet_name = "";
	private DriverFactory driverFactory = DriverFactory.getInstance();
	WebDriverWait wait;

	public SelLibrary() {
		this.driver = driverFactory.getDriver();
		this.datatable = Base_Class_Browser.datatable.get();
		this.currentRow = Base_Class_Browser.currentRow.get();
		this.sheet_name = Base_Class_Browser.sheet_name.get();
		this.wait = new WebDriverWait(driver, 30);
		load_Obj_Repository();
	}

	/*
	 * Loading object repository file browser_xpath.properties. OR_OR declared
	 */
	public void load_Obj_Repository() {
		try {
			OR_OR = new Properties();
			FileInputStream fp = new FileInputStream(
					Base_Class_Browser.current_Dir + "/src/main/java/conflib/browser_xpath.properties");
			OR_OR.load(fp);
		} catch (IOException e) {
			testLog.error("Failed to load object repository" + e.toString());
			e.printStackTrace();
		}
	}

	// Functions to validate the assertions
	public void assertCheck(String methodname, String errormsg) {
		Assert.assertEquals(1, 0, "MethodName:" + methodname + ", ErrorMsg:" + errormsg);
	}

	public void assertCheck(String methodname, String errormsg, String ObjName) {
		Assert.assertEquals(1, 0, "MethodName:" + methodname + ", ErrorMsg:" + errormsg + ", Object:" + ObjName);
	}

	public void assertCheck(String methodname, String errormsg, String ObjName, Exception e) {
		Assert.assertEquals(1, 0,
				"MethodName:" + methodname + ", ErrorMsg:" + errormsg + ", Object:" + ObjName + ", Exception:" + e);
	}

	// *****Wait handling methods starts *****
	public void wait(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			assertCheck("wait", "Opps!! Error during waiting", "", e);
		}
	}

	public void checkWebPageIsReady() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Initially below given if condition will check ready state of page.
		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			System.out.println("Page Is loaded.");
			return;
		}
		for (int i = 0; i < 50; i++) {
			wait(1000);
			// To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}

	public void ExplicitWait(String xpath, String ObjName, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			testLog.info("ExplicitWait Element Present: " + ObjName);
		} catch (Exception e) {
			testLog.error("element not Present " + ObjName);
			assertCheck("ExplicitWait", "Element not Present", ObjName, e);
		}
	}

	public void ExplicitWait(String xpath, String ObjName, int time, String text) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		try {
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(xpath), text));
			testLog.info("ExplicitWait Text Present: " + text + " In Element : " + ObjName);
		} catch (Exception e) {
			testLog.error("Text Not Present: " + text + " In Element : " + ObjName);
			assertCheck("ExplicitWait", "Text not Present in Element :", ObjName, e);
		}
	}

	// *****Wait handling methods ends *****

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public void navigateTo(String url) {
		driver.navigate().to(url);
	}

	// Function to click on the xpath button
	public void Click_Button_Xpath(String xpath, String ObjName) {
		try {
			wait(2000);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			driver.findElement(By.xpath(xpath)).click();
			testLog.info("Element is found and clicked: " + ObjName);
		} catch (Exception ex) {
			try {
				wait(2000);
				WebElement element1 = driver.findElement(By.xpath(xpath));
				int x = element1.getLocation().getX();
				int y = element1.getLocation().getY();
				int division = 1;
				wait(2000);
				((JavascriptExecutor) driver)
						.executeScript("window.scrollTo(" + x / division + "," + y / division + ")", "");
				testLog.info("Scrolled to " + ObjName);
				wait(3000);
				WebElement element = driver.findElement(By.xpath(xpath));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);

				testLog.info("Element is found and clicked: " + ObjName);
			} catch (Exception e) {
				testLog.error("Element not found for clicking: " + ObjName);
				assertCheck("Click_Button_Xpath", "Element not found for clicking", ObjName, e);
			}
		}
	}

	// Function to verify the xpath element text is same as the desired text
	public void verify_xpath_text(String ElementName1, String ElementName2) {
		try {
			wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ElementName1)));
			String element1 = driver.findElement(By.xpath(ElementName1)).getText().trim();
			element1 = element1.replaceAll("\\r\\n|\\r|\\n", " ");
			element1 = element1.replaceAll("\\s+", " ");
			String element2 = ElementName2;
			testLog.info("Actual element is: " + element1);
			testLog.info("Expected element is: " + element2);
			String ele1 = element1.replaceAll("\\W", "");
			String ele2 = element2.replaceAll("\\W", "");

			if (ele1.equals(ele2)) {
				testLog.info(element2 + "** is found and same as the desired text: **" + element1);
			} else {
				testLog.error("Element found but the desired text is not matching: " + ElementName2);
				assertCheck("verify_xpath_text", "Element found but the desired text is not matching",
						"Actual text is:**" + element1 + ", Expected Text is**:" + ElementName2);
			}
		} catch (Exception e) {
			testLog.error("Element not found: " + ElementName1);
			assertCheck("verify_xpath_text", "Element not found!!", ElementName1, e);
		}
	}

	public void verify_xpath_text_longwait(String ElementName1, String ElementName2) {
		try {
			for (int i = 1; i <= 10; i++) {
				if (false == isElementPresent(ElementName1, ElementName1)) {
					wait(2000);
				} else {
					break;
				}
			}
			String element1 = driver.findElement(By.xpath(ElementName1)).getText();
			element1 = element1.replaceAll("\\r\\n|\\r|\\n", " ");
			element1 = element1.replaceAll("\\s+", " ");
			String element2 = ElementName2;
			testLog.info("Actual element is: " + element1);
			testLog.info("Expected element is: " + element2);
			String ele1 = element1.replaceAll("\\W", "");
			String ele2 = element2.replaceAll("\\W", "");

			if (ele1.equals(ele2)) {
				testLog.info("Element is found and same as the desired text: " + element1);
			} else {
				testLog.error("Element not found or the desired text is not matching: " + ElementName2);
				assertCheck("verify_xpath_text", "Element found but the desired text is not matching",
						"Actual text is:**" + element1 + ", Expected Text is**:" + ElementName2);
			}
		} catch (Exception e) {
			testLog.error("Element not present: " + ElementName1);
			assertCheck("verify_xpath_text", "In catch block!! Please check exception", ElementName1, e);
		}
	}

	// Function to verify that the xpath element text contains the desired text
	public void verify_xpath_contains_text(String ElementName1, String ElementName2) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ElementName1)));
			String element1 = driver.findElement(By.xpath(ElementName1)).getText().trim();
			element1 = element1.replaceAll("\\r\\n|\\r|\\n", " ");
			element1 = element1.replaceAll("\\s+", " ");
			String element2 = ElementName2;
			testLog.info("Actual element: ***" + element1);
			testLog.info("Expected element that CONTAINS in Actual element is ***: " + element2);
			if (element1.toLowerCase().contains(element2.toLowerCase())) {
				testLog.info(
						"Element **" + element1 + "** found and CONTAINS the desired text/value: **" + element2 + "**");
			} else {
				testLog.error("Opps!! Element **" + element1 + "** DO NOT CONTAINS the desired text: **" + ElementName2
						+ "**");
				assertCheck("verify_xpath_contains_text", "Opps!! Element **" + element1
						+ "** DO NOT CONTAINS the desired text: **" + ElementName2 + "**", ElementName2);
			}
		} catch (Exception e) {
			testLog.error("Element not present: " + ElementName2);
			assertCheck("verify_xpath_contains_text", "Element not present", ElementName2, e);
		}
	}

	public void screenshot() {
		try {
			DateFormat df = new SimpleDateFormat("dd_MM_yy_HH_mm_ss");
			Date dateobj = new Date();
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot,
					new File(System.getProperty("au.com.coles.rs.test.home") + "/logs/Screenshots_"
							+ System.getProperty("au.com.coles.rs.test.device") + "/screenshot" + df.format(dateobj)
							+ ".jpeg"));
		} catch (Exception e) {
			testLog.error("Failed to take screenshot " + e);
		}
	}

	// Function to clear the text in the input field
	public void Clear_Text(String xpath, String ObjName) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			driver.findElement(By.xpath(xpath)).clear();
			testLog.info("Text cleared for " + ObjName);

		} catch (Exception e) {
			testLog.error("Failed to clear text for" + ObjName);
			assertCheck("Clear_Text", "Not able to clear field", ObjName, e);
		}
	}

	// Function to enter the variable string into the xpath input field
	public void Sendkey_xpath(String xpath, String variable, String ObjName) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			driver.findElement(By.xpath(xpath)).clear();
			wait(1000);
			driver.findElement(By.xpath(xpath)).sendKeys(variable);
			testLog.info("Data entered for text box: " + ObjName);
		} catch (Exception e) {
			testLog.error("Text" + variable + "not entered :" + ObjName);
			assertCheck("Sendkey_xpath", "Not able to enter text", ObjName, e);
		}
	}

	public boolean isElementPresent(String xpath, String ObjName) {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 2);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			driver.findElement(By.xpath(xpath));
			testLog.info("Element " + ObjName + " is present.");
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			// Reporting.test.get().log(Status.WARNING, ObjName + " is not
			// present on webpage.");
			testLog.warn("No such element is present" + ObjName);
			return false;
		}
	}

	// Function to validate if the xPath element is present on the screen
	public boolean isElementNotPresent(String xpath, String ObjName) {
		try {
			if (driver.findElements(By.xpath(xpath)).isEmpty()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	// Function to validate element enable or not and return boolean value
	public boolean isElementEnable(String xpath, String ObjName) {
		try {
			if (driver.findElement(By.xpath(xpath)).isEnabled()) {
				return true;
			} else {
				return false;
			}
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// Function to validate element selected or not and return boolean value
	public boolean isElementSelected(String xpath) {
		try {
			if (driver.findElement(By.xpath(xpath)).isSelected()) {
				testLog.info("Element is selected");
				return true;
			} else {
				testLog.warn("Element is not selected");
				return false;
			}
		} catch (Exception e) {
			assertCheck("isElementSelected", "function failure", xpath, e);
			return false;
		}
	}

	// Function to verify if a element is visible on the screen and return the
	// boolean status
	public boolean isElementVisible(String xpath, String Object) {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			wait.until(ExpectedConditions.visibilityOf(element));
			if (element.isDisplayed()) {
				return true;
			} else {
				// driver.manage().timeouts().implicitlyWait(15,
				// TimeUnit.SECONDS);
				return false;
			}
		} catch (Exception e) {
			testLog.warn("No such element is visible :" + Object);
			return false;
		}
	}

	// Function to get the attribute id using the xpath property
	public String get_xpath_attribute_id(String ElementName1, String ObjName) {
		String get_text = "";
		try {
			get_text = driver.findElement(By.xpath(ElementName1)).getAttribute("id");
			testLog.info("Element found: " + ObjName);
		} catch (Exception e) {
			testLog.error("Not able to get Id of element: " + ObjName);
			assertCheck("get_xpath_attribute_id", "Not able to get attribute ID", ObjName, e);
		}
		return get_text;
	}

	public String get_xpath_attribute_class(String ElementName1, String ObjName) {
		String get_class = "";
		try {
			get_class = driver.findElement(By.xpath(ElementName1)).getAttribute("class");
			testLog.info("Element found: " + ObjName);
		} catch (Exception e) {
			testLog.error("unable to find atribute class for element: " + ObjName);
			assertCheck("get_xpath_attribute_class", "unable to get attribute class", ObjName, e);
		}
		return get_class;
	}

	// Function to click on the ID button
	public void click_Element_By_ID(String ElementID, String ObjName) {
		try {
			wait(2000);
			driver.findElement(By.id(ElementID)).click();
			testLog.info("Element is found and clicked: " + ObjName);
		} catch (Exception e) {
			testLog.error("Not able to click element: " + ObjName);
			assertCheck("click_Element_By_ID", "Element not clicked", ObjName, e);
		}
	}

	// Function to get the text present for an element using the xpath property
	public String get_xpath_text(String ElementName, String ObjName) {
		String get_text = "";
		try {
			get_text = driver.findElement(By.xpath(ElementName)).getText();
			get_text = get_text.replaceAll("\\r\\n|\\r|\\n", " ");
			get_text = get_text.replaceAll("\\s+", " ");
			testLog.info("Element found: " + ObjName);
		} catch (Exception e) {
			testLog.warn("Exception in object:" + ObjName);
			testLog.info("Exception is:" + e.toString());
			testLog.warn("Returning null");
		}
		return get_text;
	}

	// Function to enter the variable string into the id input field
	public void Sendkey_id(String id, String variable, String ObjName) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
			driver.findElement(By.id(id)).sendKeys(variable);
			testLog.info("Data entered for text box: " + ObjName);
		} catch (Exception e) {
			testLog.error("Element not found :" + ObjName);
			assertCheck("Sendkey_id", "Element not found", ObjName, e);
		}
	}

	// Function to get the tab counter and return the integer value
	public int get_Tab_Counter(String xpath, String ObjName) {
		int tabCount = 0;
		try {
			wait(3000);
			String tab_Counter;
			tab_Counter = driver.findElement(By.xpath(xpath)).getText();
			tab_Counter = tab_Counter.replaceAll("\\s+", "");
			tab_Counter = tab_Counter.replace(",", "");
			tabCount = Integer.parseInt(tab_Counter);
			if (tabCount > 0) {
				testLog.info("The tab count is: " + tabCount);
			} else if (tabCount == 0) {
				testLog.info("The tab count is: " + tabCount);
			}
		} catch (Exception e) {
			testLog.error("Element not found: " + ObjName);
			assertCheck("get_Tab_Counter", "Element not found", ObjName, e);
		}
		return tabCount;
	}

	// Function to get the count of matching xpath value
	public int getXpathCount(String ElementCount, String ObjName) {
		int elementCount = 0;
		try {
			wait(2000);
			By getChanges = By.xpath(ElementCount);
			java.util.List<WebElement> located_elements = driver.findElements(getChanges);
			elementCount = located_elements.size();
			testLog.info("count of objects for " + ObjName + " is :" + elementCount);
		} catch (Exception e) {
			testLog.error("Element not found: " + ObjName);
			assertCheck("get_Tab_Counter", "Element not found", ObjName, e);
		}
		return elementCount;
	}

	// Function to switch on frame passing xPath
	public void switch_Frame(String frame_xpath) {
		wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(OR_OR.getProperty(frame_xpath))));
			// driver.switchTo().frame(driver.findElement(By.xpath(OR_OR.getProperty(frame_xpath))));
			System.out.println("Navigated to frame:" + frame_xpath);
		} catch (NoSuchFrameException e) {
			testLog.warn("Unable to locate frame:" + frame_xpath + ". Exception :" + e.getMessage().toString());

		} catch (Exception e) {
			testLog.warn("Unable to navigate to frame:" + frame_xpath + ". Exception :" + e.getMessage().toString());
		}
	}

	// Function to switch back to default frame
	public void switch_To_DefaultFrame() {
		driver.switchTo().defaultContent();
	}

	// This method will find elements by xpath, cssselctor,id
	public WebElement findElement(String by, String Obj) {
		WebElement el = null;
		try {
			String selector = by.toLowerCase();
			switch (selector) {
			case "xpath":
				el = driver.findElement(By.xpath(OR_OR.getProperty(Obj)));
				break;
			case "cssselector":
				el = driver.findElement(By.cssSelector(OR_OR.getProperty(Obj)));
				break;
			case "id":
				el = driver.findElement(By.id(OR_OR.getProperty(Obj)));
				break;
			}
		} catch (NoSuchElementException e) {
			el = null;
		}
		return el;
	}

	// This method implemented to select the visible text from drop-down
	public void selectValueFromDropDown(String xPath, String visibleText) {
//		wait(30000);
//		switch_Frame("ACS_Emulator_IFrame");
		try {
			WebElement element = driver.findElement(By.xpath(xPath));
			// Waiting for element to be visible
			wait.until(ExpectedConditions.visibilityOf(element));
			// Creating Select class object
			Select selectText = new Select(element);
			// Selecting text from drop-down
			selectText.selectByVisibleText(visibleText);
			testLog.info("Select value from emulator dropdown is :" + visibleText);
		} catch (Exception e) {
			testLog.warn("Opps!! Text is not visibale in dropdown or not able to select :" + visibleText);
		}
	}
	
	public WebElement getWebElement(String xpath) {
		WebElement element = null;
		element = driver.findElement(By.xpath(OR_OR.getProperty(xpath)));
		return element;
	}
	
	public Actions performActions() {
		Actions actions = new Actions(driver);
		return actions;
	}
}
