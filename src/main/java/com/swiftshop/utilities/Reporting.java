package com.swiftshop.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.swiftshop.main.Base_Class_Browser;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Reporting implements ITestListener {

	static String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	private static ExtentReports extent = ExtentManager.createInstance(timeStamp);
	private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	ThreadLocal<String> testName = new ThreadLocal<String>();
	String failedTests = System.getProperty("user.dir") + "\\ExtentReports\\" + timeStamp + "\\Failed-TestNGSuite.xml";

	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	private String getTestMethodName(ITestResult result) {
		return result.getName();
	}

	@Override
	public synchronized void onFinish(ITestContext result) {
		extent.flush();
	}

	@Override
	public synchronized void onStart(ITestContext result) {
		testName.set(result.getName());
		ExtentTest parent = extent.createTest(testName.get());
		parentTest.set(parent);
	}

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void createxml(String methodName) {
		try {
			
		boolean testExist = false;
		Element root, test, classes, Class, methods, include, parameter;
		Attr attr1, attr2;
		File file = new File(failedTests);
				
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

		Document document = documentBuilder.parse(file);

//		if (!(document.getElementsByTagName("suite").getLength() > 0)) {
//			// root element
//			root = document.createElement("suite");
//			document.appendChild(root);
//			attr1 = document.createAttribute("name");
//			attr1.setValue("Failed Suite");
//			attr2 = document.createAttribute("thread-count");
//			attr2.setValue("8");
//			attr3 = document.createAttribute("parallel");
//			attr3.setValue("tests");
//			root.setAttributeNode(attr1);
//			root.setAttributeNode(attr2);
//			root.setAttributeNode(attr3);
//		} else {
			root = (org.w3c.dom.Element) document.getElementsByTagName("suite").item(0);
//		}

		NodeList nl = document.getElementsByTagName("test");
		if (nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); ++i) {

				Node currentItem = nl.item(i);
				String key = currentItem.getAttributes().getNamedItem("name").getNodeValue();
				if (key.equals(testName.get())) {
					test = (org.w3c.dom.Element) currentItem;
					
					NodeList subnode =  currentItem.getChildNodes();
					 Node classes1= subnode.item(3);
					 subnode =  classes1.getChildNodes();
					 Node class1= subnode.item(1);
					 subnode =  class1.getChildNodes();
					 Node methods1= subnode.item(1);
//					 subnode =  methods1.getChildNodes();
//					 Node include1= subnode.item(1);
					 
//					NodeList childnodes = test.getChildNodes();
//
//					for (int j = 0; j < childnodes.getLength(); ++j) {
//
//						if (childnodes.item(j).getLocalName().equals("methods")) {
//							methods = (org.w3c.dom.Element) childnodes.item(j);
							include = document.createElement("include");
							methods1.appendChild(include);
							attr1 = document.createAttribute("name");
							attr1.setValue(methodName);
							include.setAttributeNode(attr1);
//						}
//					}

					testExist = true;

				}
			}
		}

		// test element
		if (testExist == false) {
			test = document.createElement("test");
			root.appendChild(test);
			// set an attribute to staff element
			Attr attr = document.createAttribute("name");
			attr.setValue(testName.get());
			test.setAttributeNode(attr);

			parameter = document.createElement("parameter");
			test.appendChild(parameter);
			attr1 = document.createAttribute("name");
			attr2 = document.createAttribute("value");
			attr1.setValue("sheetname");
			attr2.setValue(testName.get().replaceAll("[^a-zA-Z0-9]", " "));
			parameter.setAttributeNode(attr1);
			parameter.setAttributeNode(attr2);

			classes = document.createElement("classes");
			test.appendChild(classes);

			Class = document.createElement("class");
			classes.appendChild(Class);
			attr1 = document.createAttribute("name");
			attr1.setValue("com.swiftshop.tests." + testName.get().replaceAll("[^a-zA-Z0-9]", " "));
			Class.setAttributeNode(attr1);

			methods = document.createElement("methods");
			Class.appendChild(methods);

			include = document.createElement("include");
			methods.appendChild(include);
			// set an attribute to staff element
			attr1 = document.createAttribute("name");
			attr1.setValue(methodName);
			include.setAttributeNode(attr1);
		}
		
		// create the xml file
		// transform the DOM Object to an XML File
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		DOMSource domSource = new DOMSource(document);
		StreamResult streamResult = new StreamResult(new File(failedTests));

		// If you use
		// StreamResult result = new StreamResult(System.out);
		// the output will be pushed to the standard output ...
		// You can use that for debugging

		transformer.transform(domSource, streamResult);

		System.out.println("Done creating XML File");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		try {
			createxml(result.getName());
			// send the passed information to the report with GREEN color highlighted
			// We are skipping the screenshot if driver is null in Ebay scripts
			String time_stamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			String screenshotPath = "";
			Object testClass = result.getInstance();
			WebDriver driver = null;
			try {
				driver = ((Base_Class_Browser) testClass).driver;
			} catch (Exception e) {
				System.out.println(e.toString());
			}

			if (driver != null) {
				TakesScreenshot newScreen = (TakesScreenshot) driver;
				File scnShot = newScreen.getScreenshotAs(OutputType.FILE);

				screenshotPath = System.getProperty("user.dir") + "\\ExtentReports\\" + timeStamp + "\\Screenshots\\"
						+ result.getName() + "-" + time_stamp + ".png";

				File DestFile = new File(screenshotPath);
				FileUtils.copyFile(scnShot, DestFile);
			}
			test.get().fail(MarkupHelper.createLabel(getTestMethodName(result) + " FAILED due to below issues:",
					ExtentColor.RED));
			test.get().fail(result.getThrowable());
			test.get().fail("Snapshot below: ").addScreenCaptureFromPath(screenshotPath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		try {
			String time_stamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			Object testClass = result.getInstance();
			String screenshotPath = "";
			WebDriver driver = null;
			try {
				driver = ((Base_Class_Browser) testClass).driver;
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			// We are skipping the screenshot if driver is null in Ebay scripts
			if (driver != null) {
				TakesScreenshot newScreen = (TakesScreenshot) driver;
				File scnShot = newScreen.getScreenshotAs(OutputType.FILE);
				screenshotPath = System.getProperty("user.dir") + "\\ExtentReports\\" + timeStamp
						+ "\\SkipScreenshots\\" + result.getName() + "-" + time_stamp + ".png";
				File DestFile = new File(screenshotPath);
				FileUtils.copyFile(scnShot, DestFile);
			}

			test.get().skip(MarkupHelper.createLabel(getTestMethodName(result) + " Test Skipped", ExtentColor.ORANGE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		ExtentTest child = parentTest.get().createNode(result.getName());
		test.set(child);
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		// test.get().pass("Test passed");
		test.get().pass(MarkupHelper.createLabel(getTestMethodName(result) + " Test Passed", ExtentColor.GREEN));
	}
}
