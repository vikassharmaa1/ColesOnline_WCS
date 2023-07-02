package com.swiftshop.utilities;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports getInstance(String timeStamp) {
		if (extent == null) {
			createInstance(timeStamp);
		}
		return extent;
	}

	public static ExtentReports createInstance(String timeStamp) {

		File file = new File(System.getProperty("user.dir") + "/ExtentReports/" + timeStamp);
		File file1 = new File(
				System.getProperty("user.dir") + "/ExtentReports/" + timeStamp + "/Failed-TestNGSuite.xml/");
		file.mkdir();
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();
			
			Element root, parameter;
			Attr attr1, attr2, attr3;

				// root element
				root = document.createElement("suite");
				document.appendChild(root);
				attr1 = document.createAttribute("name");
				attr1.setValue("Failed Suite");
				attr2 = document.createAttribute("thread-count");
				attr2.setValue("8");
				attr3 = document.createAttribute("parallel");
				attr3.setValue("tests");
				root.setAttributeNode(attr1);
				root.setAttributeNode(attr2);
				root.setAttributeNode(attr3);
				parameter = document.createElement("parameter");
				root.appendChild(parameter);
				attr1 = document.createAttribute("name");
				attr2 = document.createAttribute("value");
				attr1.setValue("browser");
				attr2.setValue("Firefox");
				parameter.setAttributeNode(attr1);
				parameter.setAttributeNode(attr2);
				
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer;
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(file1);
			transformer.transform(domSource, streamResult);
		} catch (Exception e) {

		}
		String rprtName = System.getProperty("user.dir") + "/ExtentReports/" + timeStamp + "/Test-Report.html";

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(rprtName);

		htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle("COLES Swiftshop Test Automation Project");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("COLES Swiftshop Test Automation Report");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Execution Platform", "Remote Desktop");
		extent.setSystemInfo("Environemnt", "SIT");
		extent.setSystemInfo("User", "Automation Team");
		return extent;

	}
}
