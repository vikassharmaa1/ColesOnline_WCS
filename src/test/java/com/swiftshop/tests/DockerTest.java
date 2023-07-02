package com.swiftshop.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.swiftshop.main.Base_Class_Browser;

public class DockerTest extends Base_Class_Browser{

	@Test
	public void HRMLogin() {
		try {
			String pageSource = driver.getPageSource();
			testLog.info("--------------PAGE SOURCE START--------------");
			testLog.info(pageSource);
			testLog.info("--------------PAGE SOURCE END--------------");

			testLog.info("-----Getting Page Title.....");
			testLog.info("Page Title is:" + driver.getTitle());

			 testLog.info("Entering Username.....");
			 driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
			 Thread.sleep(2000);
			 testLog.info("Entering Password.....");
			 driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
			 Thread.sleep(2000);

			 testLog.info("Clicking on login button.....");
			 driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
			 testLog.info("Getting Current URL.....");
			 testLog.info("current url is:"+driver.getCurrentUrl());
			
		} catch (Exception e) {
			testLog.info("Error:" + e.toString());
		}
	}
}
