package com.swiftshop.main;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

	private static String current_Dir = System.getProperty("user.dir");

	private static DriverFactory instance = null;
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private static String firefox_exe;
	private static String chrome_exe;

	private DriverFactory() {
		// Do not allow to initialize this class from outside
	}

	public static DriverFactory getInstance() {
		if (instance == null) {
			instance = new DriverFactory();
		}
		return instance;
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public final void setDriver(String borwser) {
		try {
			firefox_exe = System.getProperty("FIREFOX_EXE");
			chrome_exe = System.getProperty("CHROME_EXE");

			switch (borwser) {
			case "Firefox":
				System.getProperty(current_Dir + "/src/main/java/conflib/log4j_Firefox.properties");
				Properties logProperties = new Properties();
				logProperties
						.load(new FileInputStream(current_Dir + "/src/main/java/conflib/log4j_Firefox.properties"));
				PropertyConfigurator.configure(logProperties);
				System.setProperty("webdriver.gecko.driver", firefox_exe);
				final FirefoxProfile firefoxProfile = new FirefoxProfile();
				firefoxProfile.setPreference("xpinstall.signatures.required", false);
				FirefoxOptions foptions = new FirefoxOptions();
				foptions.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
				driver.set(new FirefoxDriver(foptions));
				getDriver().manage().window().maximize();
				break;
			case "HeadlessFirefox":
				FirefoxBinary firefoxBinary = new FirefoxBinary();
				firefoxBinary.addCommandLineOptions("--headless");
				System.getProperty(current_Dir + "/src/main/java/conflib/log4j_Firefox.properties");
				Properties headlesslogProperties = new Properties();
				headlesslogProperties
						.load(new FileInputStream(current_Dir + "/src/main/java/conflib/log4j_Firefox.properties"));
				PropertyConfigurator.configure(headlesslogProperties);
				System.setProperty("webdriver.gecko.driver", firefox_exe);
				final FirefoxProfile headlessFirefoxProfile = new FirefoxProfile();
				headlessFirefoxProfile.setPreference("xpinstall.signatures.required", false);
				FirefoxOptions foptionsHeadless = new FirefoxOptions();
				foptionsHeadless.setCapability(FirefoxDriver.PROFILE, headlessFirefoxProfile);
				foptionsHeadless.setBinary(firefoxBinary);
				driver.set(new FirefoxDriver(foptionsHeadless));
				getDriver().manage().window().maximize();
				break;
			case "Chrome":
				System.getProperty(current_Dir + "/src/main/java/conflib/log4j_Chrome.properties");
				Properties chromelogProperties = new Properties();
				chromelogProperties
						.load(new FileInputStream(current_Dir + "/src/main/java/conflib/log4j_Chrome.properties"));
				PropertyConfigurator.configure(chromelogProperties);
				System.setProperty("webdriver.chrome.driver", chrome_exe);
				driver.set(new ChromeDriver());
				getDriver().manage().window().maximize();
				break;
			case "HeadlessChrome":

				ChromeOptions options = new ChromeOptions().addArguments(
						"--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
						.setHeadless(true).setAcceptInsecureCerts(true)
						.addArguments("--disable-web-security").addArguments("--ignore-certificate-errors")
						.addArguments("--allow-running-insecure-content").addArguments("--allow-insecure-localhost")
						.addArguments("--disable-gpu").addArguments("--window-size=1920,1080");

				System.setProperty("webdriver.chrome.verboseLogging", "true");
				System.setProperty("webdriver.chrome.logfile", "D:\\chromedriver.log");
				
				System.setProperty("webdriver.chrome.whitelistedIps", "");
				System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
				
				System.getProperty("/testautomation/ColesOnline_WCS/src/main/java/conflib/log4j_Chrome.properties");
				Properties chromeHeadlesslogProperties = new Properties();
				chromeHeadlesslogProperties.load(new FileInputStream(
						"/testautomation/ColesOnline_WCS/src/main/java/conflib/log4j_Chrome.properties"));
				PropertyConfigurator.configure(chromeHeadlesslogProperties);
				driver.set(new ChromeDriver(options));
				break;
			case "Node1":
				String nodeURL = "http://172.17.139.51:4444/wd/hub";
				// final FirefoxProfile firefoxProfile1 = new FirefoxProfile();
				// firefoxProfile.setPreference("xpinstall.signatures.required", false);
				FirefoxOptions foptions1 = new FirefoxOptions();
				// driver = new RemoteWebDriver(new URL(nodeURL), foptions1);
				driver.set(new RemoteWebDriver(new URL(nodeURL), foptions1));
				break;
			case "Node2":
				String nodeURL1 = "http://http://172.26.144.52:5567/wd/hub";
				FirefoxOptions foptions2 = new FirefoxOptions();
				driver.set(new RemoteWebDriver(new URL(nodeURL1), foptions2));
				break;
			default:
				// testLog.error("Incorrect device selected!!");
				break;
			}
		} catch (Exception e) {

		}
	}
}
